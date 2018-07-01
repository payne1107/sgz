package android.sgz.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.AddOrderContactsBean;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_set_workperrson_salary);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("设置工人工资", true, true);
        userId = getIntent().getIntExtra("userId", 0);
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
                Intent intent = new Intent();
                intent.putExtra("userId", userId);
                intent.putExtra("salary", salary);
                intent.putExtra("extraworksalary", addWorkSalary);
                intent.putExtra("allownce", allownce);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}

