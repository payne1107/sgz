package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/6/7.
 * 设置工资
 */

public class SetUserSalaryActivity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutConfirm;
    private EditText etDaySalary;
    private EditText etMonthSalary;
    private EditText etOvertimeSalary;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_set_user_salary);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工资单价", true, true);
        etDaySalary = findViewById(R.id.et_day_salary);
        etMonthSalary = findViewById(R.id.et_month_salary);
        etOvertimeSalary = findViewById(R.id.et_overtime);
        layoutConfirm = findViewById(R.id.layout_confirm);

        setListener();
    }

    private void setListener() {
        layoutConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_confirm:
                saveSalaryPost();
                break;
        }
    }

    /****
     * 保存工资
     */
    private void saveSalaryPost() {
        String daySalary = etDaySalary.getText().toString().trim();
        String monthSalary = etMonthSalary.getText().toString().trim();
        String overtimeSalary = etOvertimeSalary.getText().toString().trim();
        if (StringUtils.isEmpty(daySalary)) {
            toastMessage("日工资不能为空");
            return;
        }
        if (StringUtils.isEmpty(monthSalary)) {
            toastMessage("月工资不能为空");
            return;
        }
        if (StringUtils.isEmpty(overtimeSalary)) {
            toastMessage("加班工资不能为空");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("dsalary", daySalary);
        params.put("msalary", monthSalary);
        params.put("addsalary", overtimeSalary);
        httpPostRequest(ConfigUtil.SAVE_SALARY_URL, params, ConfigUtil.SAVE_SALARY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.SAVE_SALARY_URL_ACTION:
                handleSaveSalary(json);
                break;
        }
    }

    /***
     * 保存工资
     * @param json
     */
    private void handleSaveSalary(String json) {
        int requestCode = getRequestCode(json);
        if (requestCode == 1) {
            toastMessage("保存成功");
            finish();
        }
    }
}
