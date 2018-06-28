package android.sgz.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.activity.PlayVideoActivity;
import android.sgz.com.adapter.TechnologyLearnFragmentAdapter;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.Fragment2Bean;
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
 * Created by 92457 on 2018/4/16.
 */

public class Fragment2 extends BaseFragment{
    private List<Fragment2Bean.DataBean.ListBean> mList = new ArrayList<>();
    private PullToRefreshListView listView;
    private int pageNo = 1;
    private TechnologyLearnFragmentAdapter adapter;
    private int pageSize;
    private boolean swipeLoadMore = false;


    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment2, null);
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
        setInVisibleTitleIcon("学习", true, false);

        listView = (PullToRefreshListView) mRootView.findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new TechnologyLearnFragmentAdapter(getActivity(),mList);
        listView.setAdapter(adapter);
        queryAllVideo(pageNo);

        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryAllVideo(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                swipeLoadMore = true;
                if (pageNo <= pageSize) {
                    queryAllVideo(pageNo);
                } else {
                    delayedToast();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment2Bean.DataBean.ListBean bean = (Fragment2Bean.DataBean.ListBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    String videoUrl = bean.getVideo();
                    startActivity(new Intent(getActivity(), PlayVideoActivity.class).putExtra("videoUrl", videoUrl));
                }
            }
        });
    }

    private void delayedToast() {
        Toast.makeText(getActivity(),"没有更多数据啦",Toast.LENGTH_SHORT).show();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
                swipeLoadMore = false;
            }
        },1000);
    }

    /****
     * 查询所有能观看的视频
     */
    private void queryAllVideo(int pageNo) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        httpPostRequest(ConfigUtil.QUERY_ALL_VIDEO_URL, params, ConfigUtil.QUERY_ALL_VIDEO_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_ALL_VIDEO_URL_ACTION:
                handlerQueryAllVideo(json);
                break;
        }
    }

    /****
     * 查询所有视频
     * @param json
     */
    private void handlerQueryAllVideo(String json) {
        Log.d("Dong", "所有视频---》" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        Fragment2Bean bean = JSON.parseObject(json, Fragment2Bean.class);
        if (bean != null) {
            Fragment2Bean.DataBean data = bean.getData();
            if (data != null) {
                pageSize = data.getCoutpage();
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    mList = data.getList();
                    adapter.setData(mList);
                }
            } else {
                //没有数据
                adapter.setData(mList);
                setEmptyView(listView);
            }
        }
    }
}
