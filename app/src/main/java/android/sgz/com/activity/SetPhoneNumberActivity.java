package android.sgz.com.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 92457 on 2018/5/23.
 * 修改电话号码
 */

public class SetPhoneNumberActivity extends BaseActivity implements View.OnClickListener {

    private EditText etPhone;
    private TextView tvGetCode;
    private EditText etVerifyCode;
    private AutoLinearLayout layoutConfim;
    boolean isRun = true;//是否在获取验证码中
    private int count;
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_set_phonenumber);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("修改号码", true, true);
        etPhone = findViewById(R.id.et_phone);
        tvGetCode = findViewById(R.id.tv_get_code);
        etVerifyCode = findViewById(R.id.et_verify_code);
        layoutConfim = findViewById(R.id.layout_confirm);


        layoutConfim.setOnClickListener(this);
        tvGetCode.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_confirm:
                savePhone();
                break;
            case R.id.tv_get_code:
                getCheckCode();
                break;
        }
    }

    /***
     * 保存手机号
     */
    private void savePhone() {
        String phone =etPhone.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();
        if (StringUtils.isEmpty(phone) || phone.length() != 11) {
            toastMessage("请输入正确的手机号");
            return;
        }
        if (StringUtils.isEmpty(verifyCode)) {
            toastMessage("请输入验证码");
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("mobile", phone);
        params.put("code", verifyCode);
        httpPostRequest(ConfigUtil.UPDATE_MOBILE_NUMBER_URL, params, ConfigUtil.UPDATE_MOBILE_NUMBER_URL_ACTION);
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
        Map<String, String> params = new HashMap<>();
        params.put("mobile", userPhone);
        httpPostRequest(ConfigUtil.QUERY_OTHER_CODE_URL, params, ConfigUtil.QUERY_OTHER_CODE_URL_ACTION);
    }


    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_OTHER_CODE_URL_ACTION:

                break;
            case ConfigUtil.UPDATE_MOBILE_NUMBER_URL_ACTION:
                if (getRequestCode(json) == 1) {
                    toastMessage("更改手机号成功");
                    finish();
                }
                break;
        }
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
