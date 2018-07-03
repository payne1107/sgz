package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/5/14.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private static final int REUQEST_PROGFESSION_CODE = 10001;//选择职业
    private TextView tvGetCode;
    private EditText etPhone;
    boolean isRun = true;//是否在获取验证码中
    private int count;
    private TextView tvRegister;
    private EditText etNickName;
    private EditText etPwd;
    private EditText etVerifyCode;
    private Context mContext;
    private TextView tvChooseProfession;
    private int professionId;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        mContext = RegisterActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("注册", true, true);
        tvGetCode = (TextView) findViewById(R.id.tv_verify_code);
        etPhone = (EditText) findViewById(R.id.et_phone);
        tvRegister = findViewById(R.id.tv_register);
        etNickName = findViewById(R.id.et_nick_name);
        etVerifyCode = findViewById(R.id.et_verify_code);
        etPwd = findViewById(R.id.et_pwd);
        tvChooseProfession = findViewById(R.id.tv_choose_profession);

        setListener();
    }

    private void setListener() {
        tvGetCode.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvChooseProfession.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_verify_code:
                getCheckCode();
                break;
            case R.id.tv_register:
                register();
                break;
            case R.id.tv_choose_profession:
                //未完成
                startActivityForResult(new Intent(mContext, ChooseProfessionActivity.class).putExtra(ChooseProfessionActivity.EXTRA_TYPE_KEY, 1), REUQEST_PROGFESSION_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REUQEST_PROGFESSION_CODE:
                if (data != null) {
                    professionId = data.getIntExtra("professionId", -1);
                    String professionName = data.getStringExtra("professionName");
                    tvChooseProfession.setText("" + professionName);
                    Log.d("Dong", "--->" + professionId);
                }
                break;
        }
    }

    /****
     * 注册
     */
    private void register() {
        String phone = etPhone.getText().toString().trim();
        String nickName = etNickName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();
        String professionName = tvChooseProfession.getText().toString().trim();
        if (StringUtils.isEmpty(phone) || phone.length() != 11) {
            toastMessage("手机号格式不正确");
            return;
        }
        if (StringUtils.isEmpty(verifyCode)) {
            toastMessage("验证码不能为空");
            return;
        }
        if (StringUtils.isEmpty(nickName)) {
            toastMessage("昵称不能为空");
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            toastMessage("密码不能为空");
            return;
        }
        if ("请选择".equals(professionName)) {
            toastMessage("职业不能为空");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("mobile", phone);
        params.put("code", verifyCode);
        params.put("password", pwd);
        params.put("realname", nickName);
        params.put("professionid", String.valueOf(professionId));
        httpPostRequest(ConfigUtil.REGISTER_URL, params, ConfigUtil.REGISTER_URL_ACTION);
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
        httpPostRequest(ConfigUtil.QUERY_REGISTER_CODE_URL, params, ConfigUtil.QUERY_REGISTER_CODE_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_REGISTER_CODE_URL_ACTION:
                Log.d("Dong", "短信---》" + json);
                break;
            case ConfigUtil.REGISTER_URL_ACTION:
                Log.d("Dong", "注册成功====》" + json);
                handlerRegister(json);
                break;
        }
    }

    /****
     * 处理注册后结果
     * @param json
     */
    private void handlerRegister(String json) {
        if (getRequestCode(json) == 1) {
            toastMessage("注册成功");
            finish();
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
