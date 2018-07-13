package android.sgz.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.activity.ApproveExtroWorkDetailsActivity;
import android.sgz.com.adapter.ApproveExtroWorkFragmentAdapter;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.ApproveExtroWorkBean;
import android.sgz.com.utils.ConfigUtil;
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
 * Created by WD on 2018/6/19.
 * 审批加班
 */

public class ApproveExtroWorkFragment extends BaseFragment {
    private PullToRefreshListView listView;
    private ApproveExtroWorkFragmentAdapter adapter;
    private int pageNo =1;
    private List<ApproveExtroWorkBean.DataBean.ListBean> mList = new ArrayList<>();
    private int pageSize;
    private boolean swipeLoadMore = false;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_approve_extra_work, null);
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
        listView = mRootView.findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new ApproveExtroWorkFragmentAdapter(getActivity(),mList);
        listView.setAdapter(adapter);
        setListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        queryApproveExtroWork(pageNo);
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryApproveExtroWork(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryApproveExtroWork(pageNo);
                } else {
                    delayedToast();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ApproveExtroWorkBean.DataBean.ListBean bean = (ApproveExtroWorkBean.DataBean.ListBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    int approveId = bean.getId();
                    String workName = bean.getWorkname();
                    int status = bean.getStatus();
                    if (status != 1 && status != 0) {
                        String startTime = bean.getStarttime();
                        String endTime = bean.getEndtime();
                        String projectName = bean.getProjectname();
                        Intent intent = new Intent(getActivity(),ApproveExtroWorkDetailsActivity.class);
                        intent.putExtra("workname", workName);
                        intent.putExtra("status", status);
                        intent.putExtra("starttime", startTime);
                        intent.putExtra("endtime", endTime);
                        intent.putExtra("projectname", projectName);
                        intent.putExtra("approveid", approveId);
                        startActivity(intent);
                    }
                }
            }
        });
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
     * 获取需要我审批的加班申请
     */
    private void queryApproveExtroWork(int pageNo) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        httpPostRequest(ConfigUtil.QUERY_TO_APPROVE_EXTRAWORK_LIST_URL,params,ConfigUtil.QUERY_TO_APPROVE_EXTRAWORK_LIST_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_TO_APPROVE_EXTRAWORK_LIST_URL_ACTION:
                handleQueryApproveExtroWork(json);
                break;
        }
    }

    /****
     * 处理审核加班
     * @param json
     */
    private void handleQueryApproveExtroWork(String json) {
        Log.d("Dong", "审批加班----》" +json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        ApproveExtroWorkBean bean = JSON.parseObject(json, ApproveExtroWorkBean.class);
        if (bean != null) {
            ApproveExtroWorkBean.DataBean data = bean.getData();
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
