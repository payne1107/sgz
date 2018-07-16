package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.WXPayBean;
import android.sgz.com.utils.AppManager;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.wxapi.WXPayEntryActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/7/13.
 */

public class ToUpPayActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout layoutUpPay;
    private EditText etRechargePayMoney;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_to_up_pay);
        mContext = ToUpPayActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("充值", true, true);
        layoutUpPay = findViewById(R.id.layout_up_pay);
        etRechargePayMoney = findViewById(R.id.recharge_pay_money);
        layoutUpPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_up_pay:
                postPay();
                break;

        }
    }

    private void postPay() {
        String money = etRechargePayMoney.getText().toString().trim();
        if (StringUtils.isEmpty(money)) {
            toastMessage("金额不能为空");
            return;
        }
        startIOSDialogLoading(mContext, "充值中..");
        Map<String, String> params = new HashMap<>();
        params.put("paytype", "wxpay");
        params.put("money", money);
        httpPostRequest(ConfigUtil.TO_RECHARGE_URL, params, ConfigUtil.TO_RECHARGE_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.TO_RECHARGE_URL_ACTION:
                Log.d("Dong", "充值成了" + json);
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
