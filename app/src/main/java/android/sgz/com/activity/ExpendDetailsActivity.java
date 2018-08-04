package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ExpendDetailsAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ToRechargeDeailsBean;
import android.sgz.com.utils.ConfigUtil;
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
 * Created by WD on 2018/6/25.
 * 支出明细
 */

public class ExpendDetailsActivity extends BaseActivity{
    private Context mCotext;
    private int type = 1;
    private int pageNo = 1;
    private int pageSize;
    private PullToRefreshListView listView;
    private boolean swipeLoadMore = false;
    private List<ToRechargeDeailsBean.DataBean.ListBean> mList = new ArrayList<>();
    private ExpendDetailsAdapter adapter;


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_expend_details);
        mCotext = ExpendDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("支出明细", true, true);
        queryExpendDeails(pageNo,type);
        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new ExpendDetailsAdapter(mCotext, mList);
        listView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryExpendDeails(pageNo, type);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryExpendDeails(pageNo, type);
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
     * 查询支出明细
     * @param pageNo
     * @param type
     */
    private void queryExpendDeails(int pageNo, int type) {
        startIOSDialogLoading(mCotext, "加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        params.put("type", String.valueOf(type));
        httpPostRequest(ConfigUtil.QUERY_USER_FLOW_LIST_URL, params, ConfigUtil.QUERY_USER_FLOW_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_USER_FLOW_LIST_URL_ACTION:
                handleQueryExpendList(json);
                break;
        }
    }

    private void handleQueryExpendList(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        ToRechargeDeailsBean bean = JSON.parseObject(json, ToRechargeDeailsBean.class);
        if (bean != null) {
            ToRechargeDeailsBean.DataBean data = bean.getData();
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
                        adapter.setData(mList);
                        setEmptyView(listView);
                    }
                }
            }
        }
    }
}
