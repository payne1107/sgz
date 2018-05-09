package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.view.View;

import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by WD on 2018/5/9.
 * 修改昵称
 */

public class UpdateNickNameActvity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutConfirm;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_update_nick_name);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("姓名", true, true);
        layoutConfirm = (AutoLinearLayout) findViewById(R.id.layout_confirm);
        layoutConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_confirm:
                //保存用户名
                break;
        }
    }
}
