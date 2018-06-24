package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MineExpendDetailsAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.MineExpendDetailsBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/6/24.
 * 具体工单的花名册
 */

public class MineExpendDetails extends BaseActivity{

    private TextView tvProjectName;
    private TextView tvWorkAddress;
    private TextView tvStartWorkDate;
    private int projectId;
    private String address;
    private String startTime;
    private PullToRefreshListView listView;
    private List<MineExpendDetailsBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private MineExpendDetailsAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_expend_details);
        mContext = MineExpendDetails.this;
    }

    @Override
    protected void initData() {
        queryProjectWorkSalary();
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工资详情", true, true);
        projectId = getIntent().getIntExtra("projectId", 0);
        address = getIntent().getStringExtra("address");
        startTime = getIntent().getStringExtra("startTime");
        String projectName = getIntent().getStringExtra("projectName");
        tvProjectName = findViewById(R.id.tv_project_name);
        tvWorkAddress = findViewById(R.id.tv_work_address);
        tvStartWorkDate = findViewById(R.id.tv_start_work_date);
        listView = findViewById(R.id.listView);
        tvProjectName.setText("" + projectName);
        tvWorkAddress.setText("" + address);
        tvStartWorkDate.setText("" + startTime);

        adapter = new MineExpendDetailsAdapter(mContext,mList);
        listView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                queryProjectWorkSalary();
            }
        });
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                toastMessage("----发工资了---？" + position);
            }
        });
    }

    /****
     * 查询花名册列表
     */
    private void queryProjectWorkSalary() {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        httpPostRequest(ConfigUtil.QUERY_PROJECT_WORK_SALARY_URL, params, ConfigUtil.QUERY_PROJECT_WORK_SALARY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_PROJECT_WORK_SALARY_URL_ACTION:
                handleQueryProjectWorkSalary(json);
                break;
        }
    }

    /****
     * 处理获取所有花名册逻辑
     * @param json
     */
    private void handleQueryProjectWorkSalary(String json) {
        Log.d("Dong", "花名册---？" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        MineExpendDetailsBean bean = JSON.parseObject(json, MineExpendDetailsBean.class);
        if (bean != null) {
            mList = bean.getData();
            adapter.setData(mList);
        }
    }
}
