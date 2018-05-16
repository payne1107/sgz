package android.sgz.com.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by WD on 2018/5/14.
 */

public class RemberPwdActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvGetCode;
    private EditText etPhone;
    boolean isRun = true;//是否在获取验证码中
    private int count;


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rember_pwd);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("找回密码", true, true);
        etPhone = (EditText) findViewById(R.id.et_phone);
        tvGetCode = (TextView) findViewById(R.id.tv_verify_code);

        setListener();
    }

    private void setListener() {
        tvGetCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_verify_code:
                getCheckCode();
                break;
        }
    }

    /***
     * 获取验证码
     */
    private void getCheckCode() {
        String userPhone = etPhone.getText().toString().trim();
        if (StringUtils.isEmpty(userPhone) || userPhone.length() != 11) {
            toastMessage("请输入正确的手机号");
            return;
        }
        codeUnClick();
//        Map<String, String> params = new HashMap<>();
//        params.put("loginName", userPhone);
//        httpPostRequest(ConfigUtil.GET_PHONE_REGISTER_VERIFY_CODE, params, ConfigUtil.GET_PHONE_REGISTER_VERIFY_CODE_ACTON);
    }

    /**
     * 获取验证码不能点击
     */
    private void codeUnClick() {
        tvGetCode.setTextColor(getResources().getColor(R.color.text_color_9));
        tvGetCode.setClickable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun) {
                    count++;
                    Message msg = Message.obtain();
                    msg.arg1 = count;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count >= 59) {
                        isRun = false;
                    }
                }
                Message msg = Message.obtain();
                msg.arg1 = -1;
                handler.sendMessage(msg);
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 != -1) {
                int i = 60 - msg.arg1;
                tvGetCode.setText(i + "s后重发");
            } else {
                tvGetCode.setText("获取验证码");
                tvGetCode.setTextColor(getResources().getColor(R.color.color_62d));
                tvGetCode.setClickable(true);
                count = 0;
                isRun = true;
            }
        }
    };
}
