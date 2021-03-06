package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.view.View;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by 92457 on 2018/6/21.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutLogout;
    private Context mContext;
    private TextView tvVeresionCode;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        mContext = SettingActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("设置", true, true);
        layoutLogout = findViewById(R.id.layout_logout);
        tvVeresionCode = findViewById(R.id.tv_version_code);

        tvVeresionCode.setText("v"+getAppVersionName());
        layoutLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_logout:
                clearAppData();
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
                break;
        }
    }
}
