package android.sgz.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.activity.ClearSalaryActivity;
import android.sgz.com.activity.MotifyOrderNameActivity;
import android.sgz.com.activity.ReleaseWorkOrderDetailsActivity;
import android.sgz.com.adapter.MineReleaseOrderFragmentAdapter;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.MineWorkOrderFragmentBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by WD on 2018/6/25.
 */

public class MineReleaseOrderFragment extends BaseFragment{
    private PullToRefreshListView listView;
    private int pageSize;
    private List<MineWorkOrderFragmentBean.DataBean.ListBean> mList = new ArrayList<>();
    private int pageNo = 1;
    private boolean swipeLoadMore =false;
    private MineReleaseOrderFragmentAdapter adapter;
    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_mine_realse_order, null);
        }
        //缓存的rootView需要判断是否已经被加过parent,如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (PullToRefreshListView) mRootView.findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MineReleaseOrderFragmentAdapter(getActivity(),mList);
        listView.setAdapter(adapter);

        setListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        queryAllProjectOrder(pageNo);
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MineWorkOrderFragmentBean.DataBean.ListBean bean = (MineWorkOrderFragmentBean.DataBean.ListBean) parent.getAdapter().getItem(position);
                if (bean != null) {
                    int projectId = bean.getId();
                    String projectName = bean.getName();
                    int ifent = bean.getIfend();
                   // if (ifent == 0) {
                    startActivity(new Intent(getActivity(), ReleaseWorkOrderDetailsActivity.class).putExtra("projectId", projectId).putExtra("projectName", projectName).putExtra("ifend", ifent));
                   // }
                }
            }
        });

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryAllProjectOrder(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryAllProjectOrder(pageNo);
                } else {
                    delayedToast();
                }
            }
        });
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("Dong", "清除工资");
                MineWorkOrderFragmentBean.DataBean.ListBean bean = mList.get(position);
                if (bean != null) {
                    int projctId = bean.getId();
                    int ifend = bean.getIfend();
                    if (ifend == 0) {
                        startActivity(new Intent(getActivity(),ClearSalaryActivity.class).putExtra("projectId",projctId));
                    }
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
                MineWorkOrderFragmentBean.DataBean.ListBean bean = mList.get(position);
                if (bean != null) {
                    int projectId = bean.getId();
                    //修改工单名称
                    motifyOrderNmae(projectId);
                }
            }
        });
    }

    /****
     * 修改工单名称
     * @param projectId
     */
    private void motifyOrderNmae(int projectId) {
        Intent intent = new Intent(getActivity(), MotifyOrderNameActivity.class);
        intent.putExtra("projectId", projectId);
        startActivity(intent);
    }

    private void delayedToast() {
        Toast.makeText(getActivity(),"没有更多数据啦",Toast.LENGTH_LONG).show();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
                swipeLoadMore = false;
            }
        },1000);
    }
    /****
     * 获取我发布的工单
     */
    private void queryAllProjectOrder(int pageNo) {
        startIOSDialogLoading(getActivity(),"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        httpPostRequest(ConfigUtil.QUERY_MY_PROJECT_ORDER_URL, params, ConfigUtil.QUERY_MY_PROJECT_ORDER_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MY_PROJECT_ORDER_URL_ACTION:
                handlerQueryAllProjectOrder(json);
                break;
        }
    }


    private void handlerQueryAllProjectOrder(String json) {
        Log.d("Dong", "获取我发布的工单---》" +json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        MineWorkOrderFragmentBean bean = JSON.parseObject(json, MineWorkOrderFragmentBean.class);
        if (bean != null) {
            MineWorkOrderFragmentBean.DataBean data = bean.getData();
            if (data != null) {
                pageSize = data.getCoutpage();
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    mList = data.getList();
                    if (mList != null && mList.size() > 0) {
                        adapter.setData(mList);
                        listView.setVisibility(View.VISIBLE);
                    } else {
                        setEmptyView(listView);
                    }
                }
            }
        }
    }
}
