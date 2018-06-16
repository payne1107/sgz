package android.sgz.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.WorkOrderListBean;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/5/16.
 * 工单
 */

public class WorkOrderActivity extends BaseActivity {

    private PullToRefreshListView listView;
    private List<WorkOrderListBean.DataBean.ListBean> mList = new ArrayList<>();
    private int pageNo = 1;
    private int pageSize;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_work_order);
    }

    @Override
    protected void initData() {
        queryAllProjectOrder(pageNo);
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的工单", true, true);

        listView = (PullToRefreshListView) findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
//        MineWorkOrderAdapter adapter = new MineWorkOrderAdapter(this,mList);
//        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(WorkOrderActivity.this, WorkOrderDetailsActivity.class));
            }
        });

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    /****
     * 获取所有的工单
     */
    private void queryAllProjectOrder(int pageNo) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        httpPostRequest(ConfigUtil.QUERY_ALL_PROJECTS_ORDER_URL, params, ConfigUtil.QUERY_ALL_PROJECTS_ORDER_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_ALL_PROJECTS_ORDER_URL_ACTION:
                handlerQueryAllProjectOrder(json);
                break;
        }
    }

    private void handlerQueryAllProjectOrder(String json) {
        Log.d("Dong", "获取所有order---》" +json);
        WorkOrderListBean bean = JSON.parseObject(json, WorkOrderListBean.class);
        if (bean != null) {
            WorkOrderListBean.DataBean data = bean.getData();
            if (data != null) {
                pageSize = data.getCoutpage();
                mList = data.getList();
            }
        }
    }
}
