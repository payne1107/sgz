package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;

/**
 * Created by WD on 2018/6/7.
 * 设置工资
 */

public class SetUserSalaryActivity extends BaseActivity {
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
    }
}
