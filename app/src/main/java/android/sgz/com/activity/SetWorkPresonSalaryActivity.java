package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.AddOrderContactsBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/7/1.
 * 设置工人工资
 */

public class SetWorkPresonSalaryActivity extends BaseActivity implements View.OnClickListener {

    private int userId;
    private EditText etSalary;
    private EditText etAddWorkSalary;
    private EditText etAllonce;
    private AutoLinearLayout layoutConfirm;
    private List<AddOrderContactsBean> mList = new ArrayList<>();
    private int updatePersonSalry;
    private int projectId;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_set_workperrson_salary);
        mContext = SetWorkPresonSalaryActivity.this;
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("设置工人工资", true, true);
        userId = getIntent().getIntExtra("userId", 0);
        projectId = getIntent().getIntExtra("projectId", 0);
        ///一共有3处进入这个activity 发布工单 修改工单 修改工单直接在这设置工资 1=修改工单添加人添加工资 2=修改工资 3 审核新加入工单员工设置工资
        updatePersonSalry = getIntent().getIntExtra("update_person_salary",0);
        etSalary = findViewById(R.id.et_salary);
        etAddWorkSalary = findViewById(R.id.et_add_work_salary);
        etAllonce = findViewById(R.id.et_allowce);
        layoutConfirm = findViewById(R.id.layout_confirm);

        layoutConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_confirm:
                String salary = etSalary.getText().toString().trim();
                String addWorkSalary = etAddWorkSalary.getText().toString().trim();
                String allownce = etAllonce.getText().toString().trim();
                if (StringUtils.isEmpty(salary)) {
                    toastMessage("工人工资必须设置");
                    return;
                }
                if (updatePersonSalry == 1) {
                    //修改工单的时候设置工资
                    addPersonSalary(projectId, userId, salary, allownce, addWorkSalary);
                } else if (updatePersonSalry == 2) {
                    //修改工资
                    editWorkPersonSalary(userId, salary, allownce, addWorkSalary);
                } else if (updatePersonSalry == 3) {
                   //审核新加入工单员工设置工资
                    applyInOrder(projectId, 1, salary, allownce, addWorkSalary);
                } else {
                    //不等于说明是发布工单的时候设置工资
                    Intent intent = new Intent();
                    intent.putExtra("userId", userId);
                    intent.putExtra("salary", salary);
                    intent.putExtra("extraworksalary", addWorkSalary);
                    intent.putExtra("allownce", allownce);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }

    /****
     * 添加工人工资
     * @param projectId  项目id
     * @param userid  用户id
     * @param salary 工资
     * @param allowance 津贴
     * @param overworksalay  加班工资
     */
    private void addPersonSalary(int projectId,int userid,String salary,String allowance,String overworksalay) {
        startIOSDialogLoading(mContext,"添加中..");
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        params.put("userid", String.valueOf(userid));
        params.put("salary", salary);
        params.put("allowance", allowance);
        params.put("overworksalary", overworksalay);
        httpPostRequest(ConfigUtil.ADD_PROJECT_ORDER_WORK_URL, params, ConfigUtil.ADD_PROJECT_ORDER_WORK_URL_ACTION);
    }

    /*****
     * 修改工资
     * @param id
     * @param salary
     * @param allowance
     * @param overworksalay
     */
    private void editWorkPersonSalary(int id ,String salary,String allowance,String overworksalay) {
        startIOSDialogLoading(mContext,"修改中..");
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        params.put("salary", salary);
        params.put("allowance", allowance);
        params.put("overworksalary", overworksalay);
        httpPostRequest(ConfigUtil.EDIT_PROJECT_WORK_SALARY_URL, params, ConfigUtil.EDIT_PROJECT_WORK_SALARY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.ADD_PROJECT_ORDER_WORK_URL_ACTION:
                Log.d("Dong", "json ------ " + json);
                if (getRequestCode(json) == 1) {
                    toastMessage("添加成功");
                    finish();
                }
                break;
            case ConfigUtil.EDIT_PROJECT_WORK_SALARY_URL_ACTION:
                //修改工人工资
                Log.d("Dong", "修改工人工资" +json);
                if (getRequestCode(json) == 1) {
                    toastMessage("修改成功");
                    finish();
                }
                break;
            case ConfigUtil.CHECK_IN_PROJECT_ORDER_APPLY_URL_ACTION:
                if (getRequestCode(json) == 1) {
                    toastMessage("审核成功");
                    finish();
                }
                break;
        }
    }

    /****
     *  审核加入工单
     */
    private void applyInOrder(int projectid,int status,String salary,String allowance,String overworksalay) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(projectid));
        params.put("status", String.valueOf(status));
        params.put("salary", salary);
        params.put("allowance", allowance);
        params.put("overworksalary", overworksalay);
        httpPostRequest(ConfigUtil.CHECK_IN_PROJECT_ORDER_APPLY_URL, params, ConfigUtil.CHECK_IN_PROJECT_ORDER_APPLY_URL_ACTION);
    }
}

