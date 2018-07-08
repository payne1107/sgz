package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MineSalaryAdapter;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ProjectIncomeBean;
import android.sgz.com.bean.ProjectSalaryListBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
 * Created by WD on 2018/5/7.
 * 我的工资
 */

public class MineSalaryActivity extends BaseActivity implements View.OnClickListener {

    private PullToRefreshListView listView;
    private List<ProjectSalaryListBean.DataBean.ListBean> mList = new ArrayList<>();
    private TextView tvWithDraw;
    private Context mContext;
    private TextView tvAllSalary;
    private TextView tvSalary;
    private TextView tvAllowance;
    private TextView tvAddSalary;
    private int pageNo = 1;
    private int countPage;
    private boolean swipeLoadMore = false;
    private MineSalaryAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_salary);
        mContext = MineSalaryActivity.this;
    }

    @Override
    protected void initData() {
        queryProjectIncome();
        queryProjectSalaryList(pageNo);
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的工资", true, true);
        tvWithDraw =  findViewById(R.id.activity_set);
        tvAllSalary = findViewById(R.id.tv_all_salary);
        tvSalary = findViewById(R.id.tv_salary);
        tvAllowance = findViewById(R.id.tv_allowance);
        tvAddSalary = findViewById(R.id.tv_add_salary);

        listView =  findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MineSalaryAdapter(this,mList);
        listView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        tvWithDraw.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProjectSalaryListBean.DataBean.ListBean bean = (ProjectSalaryListBean.DataBean.ListBean) parent.getAdapter().getItem(position);
//                if (false) {
//                    //包工头进入的页面
//                    startActivity(new Intent(mContext, SalaryDetailsActivity.class));
//                } else {
                    //个人工资详情页面
//                    startActivity(new Intent(mContext, PersonalSalaryDetailsActivity.class).putExtra("id", mList.get(position).getId()));
                if (bean != null) {
                    //跳转到个人考勤页面
                    int projectId = bean.getId();
                    String projectName = bean.getName();
                    startActivity(new Intent(mContext, PersonOrderSalaryActivity.class).putExtra("projectId", projectId).putExtra("projectName", projectName).putExtra("userId", Integer.valueOf(MyApplication.userId)).putExtra(ConfigUtil.EXTRA_SET_DEFAULT_ORDER_KEY, 3));
                }
            }
        });
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryProjectSalaryList(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= countPage) {
                    swipeLoadMore = true;
                    queryProjectSalaryList(pageNo);
                } else {
                    delayedToast();
                }
            }
        });
    }

    /***
     * 没有更多数据加载
     */
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_set:
                startActivity(new Intent(mContext, WithDrawDespositActivity.class));
                break;
        }
    }

    /****
     * 获取我的工资 项目列表 包工头和普通员工页面不一样
     */
    private void queryProjectSalaryList(int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        httpPostRequest(ConfigUtil.QUERY_PROJECT_SALARY_LIST_URL, params, ConfigUtil.QUERY_PROJECT_SALARY_LIST_URL_ACTION);
    }

    /****
     * 获取总共的工资，津贴，加班费
     */
    private void queryProjectIncome() {
        Map<String, String> params = new HashMap<>();
        params.put("random", "132");
        httpPostRequest(ConfigUtil.QUERY_PROJECT_INCOM_URL, params, ConfigUtil.QUERY_PROJECT_INCOME_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_PROJECT_INCOME_URL_ACTION:
                handlerQueryProjectIncome(json);
                break;
            case ConfigUtil.QUERY_PROJECT_SALARY_LIST_URL_ACTION:
                handlerQueryProjectSalaryList(json);
                break;
        }
    }


    /***
     * 处理我的工资项目列表
     * @param json
     */
    private void handlerQueryProjectSalaryList(String json) {
        Log.d("Dong", "工资项目列表---->" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        ProjectSalaryListBean bean = JSON.parseObject(json, ProjectSalaryListBean.class);
        if (bean != null) {
            ProjectSalaryListBean.DataBean data = bean.getData();
            if (data != null) {
                countPage = data.getCoutpage();//总页数
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    //下拉刷新
                    mList = data.getList();
                    if (mList != null && mList.size() > 0) {
                        adapter.setData(mList);
                        listView.setVisibility(View.VISIBLE);
                    } else {
                        adapter.setData(mList);
                        //可以加个设置空页面
                    }
                }
            }
        }
    }

    /***
     * 处理查询我的工资津贴加班费
     * @param json
     */
    private void handlerQueryProjectIncome(String json) {
        Log.d("Dong", "加班费---》" + json);
        ProjectIncomeBean incomeBean = JSON.parseObject(json, ProjectIncomeBean.class);
        if (incomeBean != null) {
            ProjectIncomeBean.DataBean data = incomeBean.getData();
            if (data != null) {
                String salary = data.getSalary();//总工资
                String addSalary = data.getAddsalary(); //加班费
                String allowance = data.getAllowance(); //总津贴
                double allSalary = Double.valueOf((StringUtils.isEmpty(salary)?"0":salary)) +Double.valueOf((StringUtils.isEmpty(addSalary)?"0":addSalary)) +Double.valueOf((StringUtils.isEmpty(allowance)?"0":allowance));//总工资
                if (StringUtils.isEmpty(salary)) {
                    tvAllSalary.setText("0");
                    tvSalary.setText("0");
                } else {
                    tvAllSalary.setText(allSalary + "");
                    tvSalary.setText(salary + "");
                }
                if (StringUtils.isEmpty(addSalary)) {
                    tvAddSalary.setText("0");
                } else {
                    tvAddSalary.setText(addSalary + "");
                }
                if (StringUtils.isEmpty(allowance)) {
                    tvAllowance.setText("0");
                } else {
                    tvAllowance.setText(allowance + "");
                }
            }
        }
    }
}
