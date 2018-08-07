package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MineSalaryAdapter;
import android.sgz.com.adapter.WorkDayNumAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ProjectIncomeBean;
import android.sgz.com.bean.ProjectSalaryListBean;
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
 * Created by WD on 2018/5/7.
 * 工作天数
 */

public class WorkDayNumActivity extends BaseActivity{

    private PullToRefreshListView listView;
   private List<ProjectSalaryListBean.DataBean.ListBean> mList = new ArrayList<>();
    private TextView tvWorkDays;
    private TextView tvAddWorkTime;
     private int countPage;
     private boolean swipeLoadMore =false;
     private MineSalaryAdapter adapter;
    private int pageNo =1;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_work_day_num);
        mContext = WorkDayNumActivity.this;
    }

    @Override
    protected void initData() {
        queryProjectIncome();
        queryProjectSalaryList(pageNo);
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工作天数", true, true);

        tvWorkDays = findViewById(R.id.tv_work_days);
        tvAddWorkTime = findViewById(R.id.tv_add_work_time);
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
         adapter = new MineSalaryAdapter(this,mList);
        listView.setAdapter(adapter);

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

    /****
     * 获取总共的工资，津贴，加班费
     */
    private void queryProjectIncome() {
        Map<String, String> params = new HashMap<>();
        params.put("random", "132");
        httpPostRequest(ConfigUtil.QUERY_PROJECT_INCOM_URL, params, ConfigUtil.QUERY_PROJECT_INCOME_URL_ACTION);
    }

    /****
     * 获取我的工资 项目列表 包工头和普通员工页面不一样
     */
    private void queryProjectSalaryList(int page) {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        httpPostRequest(ConfigUtil.QUERY_PROJECT_SALARY_LIST_URL, params, ConfigUtil.QUERY_PROJECT_SALARY_LIST_URL_ACTION);
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
     * 处理查询我的工资津贴加班费
     * @param json
     */
    private void handlerQueryProjectIncome(String json) {
        Log.d("Dong", "加班费---》" + json);
        ProjectIncomeBean incomeBean = JSON.parseObject(json, ProjectIncomeBean.class);
        if (incomeBean != null) {
            ProjectIncomeBean.DataBean data = incomeBean.getData();
            if (data != null) {
                String addWorkTime = data.getAddtime();
                int workDays = data.getWorkdays();
                tvAddWorkTime.setText(StringUtils.isEmpty(addWorkTime) ? "0小时" : addWorkTime+"小时");
                tvWorkDays.setText("" + workDays);
            }
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
                      setEmptyView(listView);
                    }
                }
            }
        }
    }
}
