package android.sgz.com.activity;

import android.os.Bundle;

import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.WXPayBean;
import android.sgz.com.utils.AppManager;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.wxapi.WXPayEntryActivity;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by WD on 2018/7/29.
 * 充值vip页面
 */

public class RechargeVIPActivity extends BaseActivity implements View.OnClickListener {

    private int payType = 1; //1.余额支付2.wx支付

    private int vipId;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recharge_vip);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("选择支付方式", true, true);
        vipId = getIntent().getIntExtra("vipId", 0);


        RadioGroup radioGroup = findViewById(R.id.radioGroup1);
        AutoLinearLayout layoutRecharge = findViewById(R.id.layout_recharge);
        layoutRecharge.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radiobutton1:
                        payType = 1;
                        break;
                    case R.id.radiobutton2:
                        payType = 2;
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_recharge:
                //充值vip
                if (payType == 1) {
                    openVip(vipId);
                } else {
                    wxToPayVip(vipId);
                }
                break;

        }
    }


    /****
     * 余额充值vip
     * @param vipId
     */
    private void openVip(int vipId) {
        Map<String, String> params = new HashMap<>();
        params.put("vipid", String.valueOf(vipId));
        httpPostRequest(ConfigUtil.OPEN_VIP_URL, params, ConfigUtil.OPEN_VIP_URL_ACTION);
    }

    /****
     * 微信充值vip
     * @param vipId
     */
    private void wxToPayVip(int vipId) {
        Map<String, String> params = new HashMap<>();
        params.put("vipid", String.valueOf(vipId));
        params.put("paytype", "wxpay");
        httpPostRequest(ConfigUtil.TO_PAY_VIP_WX_URL, params, ConfigUtil.TO_PAY_VIP_WX_URL_ACTIN);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.OPEN_VIP_URL_ACTION:
                if (getRequestCode(json) == 1) {
                    toastMessage("开通VIP成功");
                    finish();
                }
                break;
            case ConfigUtil.TO_PAY_VIP_WX_URL_ACTIN:
                Log.d("Dong", "冲vip" + json);
                if (getRequestCode(json) == 1) {
                    JSONObject jsonObject = JSONObject.parseObject(json);
                    String data = jsonObject.getString("data");
                    wxPay(data);
                }
                break;
        }
    }

    /***
     * 微信充值
     * @param json
     */
    private void wxPay(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        WXPayBean.prepayid = jsonObject.getString("prepay_id"); //预支付交易会话ID
        WXPayBean.noncestr = jsonObject.getString("nonceStr"); //随机字符串
        WXPayBean.timestamp = jsonObject.getString("timeStamp");//时间戳
        WXPayBean.sign = jsonObject.getString("paySign"); //签名

        PayReq req = new PayReq();
        req.appId = MyApplication.wxAppID; //应用ID
        req.partnerId = MyApplication.MCH_ID; //商户号
        req.prepayId = WXPayBean.prepayid;//预支付交易会话ID
        req.packageValue = "Sign=WXPay"; //扩展字段
        req.nonceStr = WXPayBean.noncestr;
        req.timeStamp = WXPayBean.timestamp;
        req.sign = WXPayBean.sign;
        MyApplication.iwxapi.sendReq(req);
        Log.d("Dong", "支付----" + req.toString());
        AppManager.getInstance().PushActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (WXPayEntryActivity.IS_WX_PAY_SUCESS) {
            finish();
        }
    }
}
