package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ToRechargeDeailsBean;
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
 * Created by WD on 2018/6/25.
 * 充值明细
 */

public class ToRechargeDetailsActivity extends BaseActivity {

    private PullToRefreshListView listView;
    private List<ToRechargeDeailsBean.DataBean.ListBean> mList = new ArrayList<>();
    private Context mCotext;
    private int type = 3;
    private int pageNo = 1;
    private int pageSize;
    private boolean swipeLoadMore = false;
    private ToRechargeDetailsAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_to_recharge_details);
        mCotext = ToRechargeDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("充值明细", true, true);
        queryRechargeDeails(pageNo,type);
        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new ToRechargeDetailsAdapter(mCotext, mList);
        listView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryRechargeDeails(pageNo, type);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryRechargeDeails(pageNo, type);
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
     * 查询充值明细
     * @param pageNo
     * @param type ==3 充值 == 1 支出
     */
    private void queryRechargeDeails(int pageNo,int type) {
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
                Log.d("Dong", "充值明细---》" + json);
                handleQueryUserFlowList(json);
                break;
        }
    }

    private void handleQueryUserFlowList(String json) {
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
                    adapter.setData(mList);
                    listView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
