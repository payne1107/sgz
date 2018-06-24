package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MineHomePageAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.MineHomePageBean;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/6/24.
 * 我的主页
 */

public class MineHomePageActivity extends BaseActivity{

    private PullToRefreshListView listView;
    private Context mContext;
    private List<MineHomePageBean.DataBean.ListBean> mList  = new ArrayList<>();
    private int pageNo = 1;
    private int pageSize;
    private boolean swipeLoadMore = false;
    private MineHomePageAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_homepage);
        mContext = MineHomePageActivity.this;
    }

    @Override
    protected void initData() {
        queryDynamicList(pageNo);
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的主页", true, true);
        listView = findViewById(R.id.listview);
        adapter = new MineHomePageAdapter(mContext, mList);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryDynamicList(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryDynamicList(pageNo);
                } else {
                    delayedToast();
                }
            }
        });
    }

    private void delayedToast() {
        toastMessage("没有更多数据啦");
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
                swipeLoadMore = false;
            }
        },1000);
    }
    /****
     * 查询我主页数据
     */
    private void queryDynamicList(int pageNo) {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        httpPostRequest(ConfigUtil.QUERY_DYNMAIC_LIST_URL, params, ConfigUtil.QUERY_DYNMAIC_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_DYNMAIC_LIST_URL_ACTION:
                handleQueryDynamicList(json);
                break;
        }
    }

    private void handleQueryDynamicList(String json) {
        Log.d("Dong", "查询我的动态---》" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        MineHomePageBean bean = JSON.parseObject(json, MineHomePageBean.class);
        if (bean != null) {
            MineHomePageBean.DataBean data = bean.getData();
            if (data != null) {
                pageSize =data.getCoutpage();
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    mList = data.getList();
                    adapter.setData(mList);
                    listView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
