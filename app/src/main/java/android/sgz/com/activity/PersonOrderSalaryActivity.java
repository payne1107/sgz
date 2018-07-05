package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ContactsAdapter;
import android.sgz.com.adapter.PersonWorkRecrdAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.MineExpendDetailsBean;
import android.sgz.com.bean.PersonOrderSalaryBean;
import android.sgz.com.bean.PersonWorkRecordBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
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
 * Created by WD on 2018/7/5.
 * 个人考勤于工资表
 */

public class PersonOrderSalaryActivity extends BaseActivity {

    private int projectId;
    private int userId;
    private Context mContext;
    private String projectName;
    private TextView tvProjectName;
    private TextView tvWorkDays;
    private TextView tvUserName;
    private TextView tvAddWorkTime;
    private TextView tvAllSalary;
    private TextView tvAddSalary;
    private TextView tvAllowance;
    private int pageNo = 1;
    private PullToRefreshListView listView;
    private List<PersonWorkRecordBean.DataBean.ListBean> mList = new ArrayList();
    private PersonWorkRecrdAdapter adapter;
    private int pageSize;
    private boolean swipeLoadMore =false;


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_person_order_salary);
        mContext = PersonOrderSalaryActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("个人考勤与工资表", true, true);
        projectId = getIntent().getIntExtra("projectId", 0);
        userId = getIntent().getIntExtra("userId", 0);
        projectName = getIntent().getStringExtra("projectName");
        tvProjectName = findViewById(R.id.tv_project_name);
        tvWorkDays = findViewById(R.id.tv_work_days);
        tvUserName = findViewById(R.id.tv_username);
        tvAddWorkTime = findViewById(R.id.tv_add_work_time);
        tvAllSalary = findViewById(R.id.tv_all_salary);
        tvAddSalary = findViewById(R.id.tv_add_salary);
        tvAllowance = findViewById(R.id.tv_allowance);
        listView = findViewById(R.id.listview);
        adapter = new PersonWorkRecrdAdapter(mContext, mList);
        listView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryWorkRecordInfo(pageNo,projectId,userId);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryWorkRecordInfo(pageNo, projectId,userId);
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
    @Override
    protected void onResume() {
        super.onResume();
        queryProjectWorkSalary(projectId,userId);
        queryWorkRecordInfo(pageNo,projectId,userId);
    }

    /****
     * 获取员工个人工资信息
     */
    private void queryProjectWorkSalary(int projectId,int userId) {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        params.put("id", String.valueOf(userId));
        httpPostRequest(ConfigUtil.QUERY_PROJECT_WORK_SALARY_URL, params, ConfigUtil.QUERY_PROJECT_WORK_SALARY_URL_ACTION);
    }

    /****
     * 查询工人打卡记录
     * @param pageNo
     * @param projectId
     * @param userId
     */
    private void queryWorkRecordInfo(int pageNo,int projectId,int userId) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        params.put("projectid", String.valueOf(projectId));
        params.put("userid", String.valueOf(userId));
        httpPostRequest(ConfigUtil.QUERY_WORK_RECORD_URL,params,ConfigUtil.QUERY_WORK_RECORD_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_PROJECT_WORK_SALARY_URL_ACTION:
                handleQueryProjectWorkSalary(json);
                break;
            case ConfigUtil.QUERY_WORK_RECORD_URL_ACTION:
                hanldeQueryWorkRecord(json);
                break;
        }
    }

    /****
     * 获取查询工作打卡记录
     * @param json
     */
    private void hanldeQueryWorkRecord(String json) {
        Log.d("Dong", "打卡记录----》" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        PersonWorkRecordBean bean = JSON.parseObject(json, PersonWorkRecordBean.class);
        if (bean != null) {
            PersonWorkRecordBean.DataBean data = bean.getData();
            if (data != null) {
                pageSize=data.getCoutpage();
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    mList = data.getList();
                    adapter.setData(mList);
                    listView.setVisibility(View.VISIBLE);
                }
            } else {
                mList.clear();
                adapter.setData(mList);
                setEmptyView(listView);
            }
        }
    }

    /****
     * 处理获取所有花名册逻辑
     * @param json
     */
    private void handleQueryProjectWorkSalary(String json) {
        Log.d("Dong", "花名册---？" + json);
        PersonOrderSalaryBean bean = JSON.parseObject(json, PersonOrderSalaryBean.class);
        if (bean != null) {

            PersonOrderSalaryBean.DataBean data = bean.getData();
            if (data != null) {
                String realName = data.getRealname();
                int workdays = data.getWorkdays();
                double allSalary = data.getAllsalary();
                String addSalary = data.getAddsalary();
                int allowance =data.getTotalallowance();
                int allAddTime =data.getAlladdtime();
                tvProjectName.setText(projectName);
                tvUserName.setText("" + realName);
                tvWorkDays.setText("" + workdays);
                tvAllSalary.setText("" + allSalary);
                tvAddSalary.setText(StringUtils.isEmpty(addSalary) ? "加班费:0" : "加班费：" + addSalary);
                tvAllowance.setText("津贴：" + allowance);
                tvAddWorkTime.setText("" + allAddTime + "小时");
            }
        }
    }
}
