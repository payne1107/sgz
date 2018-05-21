package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;

/**
 * Created by WD on 2018/5/21.
 * 输入工单名称
 */

public class EnterWorkOrderNameActivity extends BaseActivity {
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_enter_work_order_name);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工单名称", true, true);
    }
}
