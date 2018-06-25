package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.view.View;

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

    /****
     * 一键发放所有工资
     */
    private void payForAllSalary() {
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        httpPostRequest(ConfigUtil.TO_PAY_ALL_SALARY_URL, params, ConfigUtil.TO_PAY_ALL_SALARY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.TO_PAY_ALL_SALARY_URL_ACTION:
                hanldeToPayAllSalary(json);
                break;
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
