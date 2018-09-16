package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.PayAllSalaryDetailsBean;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/6/25.
 * 一键发放工资
 */

public class PayForAllSalaryActivity extends BaseActivity implements View.OnClickListener {
    private int projectId;
    private AutoLinearLayout layoutPayAllSalary;
    private int page = 1;
    private TextView tvProjectName;
    private TextView tvWorkAddress;
    private TextView tvStartWorkDate;
    private TextView tvWorkPersonNum;
    private TextView tvWorkDays;
    private TextView tvAvgSalary;
    private TextView tvSalaryTotal;
    private TextView tvAllPaySalary;
    private TextView tvAddWorkDays;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay_for_all_salary);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        projectId = getIntent().getIntExtra("projectId", 0);
        setInVisibleTitleIcon("统发工资", true, true);
        layoutPayAllSalary = findViewById(R.id.layout_pay_all_salary);
        tvProjectName = findViewById(R.id.tv_project_name);
        tvWorkAddress = findViewById(R.id.tv_work_address);
        tvStartWorkDate = findViewById(R.id.tv_start_work_date);
        tvWorkPersonNum = findViewById(R.id.tv_work_person_num);
        tvAddWorkDays = findViewById(R.id.tv_add_work_days);
        tvWorkDays = findViewById(R.id.tv_work_days);
        tvAvgSalary = findViewById(R.id.tv_avgsalary);
        tvSalaryTotal = findViewById(R.id.tv_salart_total);
        tvAllPaySalary = findViewById(R.id.tv_all_pay_salary);

        layoutPayAllSalary.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_pay_all_salary:
                //一键发放工资
                payForAllSalary();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryMinePaymentByProject(page,projectId);
    }

    /****
     * 一键发放所有工资
     */
    private void payForAllSalary() {
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        httpPostRequest(ConfigUtil.TO_PAY_ALL_SALARY_URL, params, ConfigUtil.TO_PAY_ALL_SALARY_URL_ACTION);
    }

    /***
     * 根据projectid 查询项目信息
     */
    private void queryMinePaymentByProject(int pageNo,int projectId) {
        startIOSDialogLoading(PayForAllSalaryActivity.this, "加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        params.put("projectid", String.valueOf(projectId));
        httpPostRequest(ConfigUtil.QUERY_PAYMENT_BY_PROJECT_ORDER_URL, params, ConfigUtil.QUERY_PAYMENT_BY_PROJECT_ORDER_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.TO_PAY_ALL_SALARY_URL_ACTION:
                hanldeToPayAllSalary(json);
                break;
            case ConfigUtil.QUERY_PAYMENT_BY_PROJECT_ORDER_URL_ACTION:
                handleQueryPaymentByProject(json);
                break;
        }
    }

    private void handleQueryPaymentByProject(String json) {
        PayAllSalaryDetailsBean bean = JSON.parseObject(json, PayAllSalaryDetailsBean.class);
        if (bean != null) {
            PayAllSalaryDetailsBean.DataBean data = bean.getData();
            if (data != null) {
                String projectName = data.getProjectname();
                int workPersonCount =data.getWorkercount();///工人数
                int workdays =data.getAlladdworkdays() ;//工作天数
                int addWorkDays =data.getAlladdworkdays();//加班数
                double avgSalary =data.getAvgsalay();//平均工资
                double allSalary =data.getAllsalay();//总工资
                double allPaySalary = data.getAllpaysalary();//已发工资

                tvProjectName.setText("" + projectName);
                tvAllPaySalary.setText("" + allPaySalary);
                tvAvgSalary.setText(""+avgSalary+"/人");
                tvSalaryTotal.setText("" + allSalary);
                tvWorkDays.setText("" + workdays);
                tvWorkPersonNum.setText("" + workPersonCount);
                tvAddWorkDays.setText("" + addWorkDays);
            }
        }
    }

    /***
     * 处理一键发放工资
     * @param json
     */
    private void hanldeToPayAllSalary(String json) {
        Log.d("Dong", "发送工资---" + json);
        if (getRequestCode(json) == 1) {
            toastMessage("发放工资成功");
            finish();
        }
    }
}
