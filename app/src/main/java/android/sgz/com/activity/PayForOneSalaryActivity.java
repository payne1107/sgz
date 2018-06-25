package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/6/25.
 * 给个人发工资
 */

public class PayForOneSalaryActivity extends BaseActivity{

    private int projectId;
    private int userId;
    private EditText etSalary;
    private AutoLinearLayout layoutPaySalary;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay_for_one_salary);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("发放工资", true, true);
        projectId = getIntent().getIntExtra("projectId", -1);
        userId = getIntent().getIntExtra("userId", -1);
        etSalary = findViewById(R.id.et_salary);
        layoutPaySalary = findViewById(R.id.layout_pay_salary);
        layoutPaySalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发工资
                payForOneSalary();
            }
        });
    }

    /****
     * 发放工资
     */
    private void payForOneSalary() {
        String salary = etSalary.getText().toString().trim();
        if (StringUtils.isEmpty(salary)) {
            toastMessage("请输入发放金额");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        params.put("userid", String.valueOf(userId));
        params.put("topay", salary);
        httpPostRequest(ConfigUtil.TO_PAY_FOR_ONE_SALARY_URL, params, ConfigUtil.TO_PAY_FOR_ONE_SALARY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.TO_PAY_FOR_ONE_SALARY_URL_ACTION:
                handleToPayOneSalary(json);
                break;
        }
    }

    private void handleToPayOneSalary(String json) {
        Log.d("Dong", "单独发工资---" + json);
        if (getRequestCode(json) == 1) {
            toastMessage("发放工资成功");
            finish();
        }
    }
}
