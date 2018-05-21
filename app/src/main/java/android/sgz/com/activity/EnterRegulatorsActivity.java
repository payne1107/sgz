package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;

/**
 * Created by WD on 2018/5/21.
 * 输入监管单位
 */

public class EnterRegulatorsActivity extends BaseActivity{
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_regulators);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("监管单位", true, true);
    }
}
