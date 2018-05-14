package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by WD on 2018/5/14.
 * 登录
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivPhoneClear;
    private EditText etPhone;
    private EditText etPassword;
    private ImageView ivPwdVisible;
    private TextView tvRegister;
    private TextView tvLogin;
    private TextView tvRemeberPwd;
    private Context mContext;
    private boolean isVisble = false;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("登录", true, true);
        ivPhoneClear = (ImageView) findViewById(R.id.iv_phone_clear);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPassword = (EditText) findViewById(R.id.et_password);
        ivPwdVisible = (ImageView) findViewById(R.id.iv_pwd_visible);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvRemeberPwd = (TextView) findViewById(R.id.tv_remember_pwd);
        setListener();
    }

    private void setListener() {
        ivPhoneClear.setOnClickListener(this);
        ivPwdVisible.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvRemeberPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_phone_clear:
                etPhone.getText().clear();
                break;
            case R.id.tv_register:
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
            case R.id.tv_login:
                finish();
                break;
            case R.id.tv_remember_pwd:
                //忘记密码
                startActivity(new Intent(mContext, RemberPwdActivity.class));
                break;
            case R.id.iv_pwd_visible:
                //隐藏和显示密码
                setVisiblePwd();
                break;
        }
    }

    /***
     * 设置密码是否显示
     */
    private void setVisiblePwd() {
        isVisble = !isVisble;
        if (isVisble) {
            ivPwdVisible.setImageResource(R.mipmap.icon_visible);
            etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            ivPwdVisible.setImageResource(R.mipmap.icon_unvisible);
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        etPassword.setSelection(etPassword.getText().length());
    }
}
