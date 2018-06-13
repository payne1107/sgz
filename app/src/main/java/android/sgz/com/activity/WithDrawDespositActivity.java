package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.BindBankCardInfoBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 92457 on 2018/5/19.
 * 提现
 */

public class WithDrawDespositActivity extends BaseActivity implements View.OnClickListener {

    private EditText etMonkey;
    private AutoLinearLayout layoutConfim;
    private TextView tvBindCard;
    private Context mContext;
    private List<BindBankCardInfoBean.DataBean> data;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_withdraw_desposit);
        mContext = WithDrawDespositActivity.this;
    }

    @Override
    protected void initData() {
        queryBankCardInfo();
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("提现", true, true);
        etMonkey = (EditText) findViewById(R.id.et_money);
        layoutConfim = (AutoLinearLayout) findViewById(R.id.layout_confirm);
        tvBindCard = findViewById(R.id.tv_bind_card);

        setListener();
    }

    private void setListener() {
        tvBindCard.setOnClickListener(this);
        layoutConfim.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_confirm:
                String money = etMonkey.getText().toString().trim();
                if (StringUtils.isEmpty(money)) {
                    toastMessage("提现金额不能为空");
                }
                break;
            case R.id.tv_bind_card:
                if (data != null && data.size() > 0) {
                    //跳转到现有的银行卡信息页面 否则跳转到绑定银行卡信息页面
                } else {
                    startActivity(new Intent(mContext, BindBankCardActivity.class));
                }
                break;
        }
    }

    /****
     * 获取已经绑定的银行卡信息
     */
    private void queryBankCardInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_BIND_BANK_CARD_INFO_URL, params, ConfigUtil.QUERY_BIND_BANK_CARD_INFO_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_BIND_BANK_CARD_INFO_URL_ACTION:
                Log.d("Dong", "json ---- >" + json);
                BindBankCardInfoBean bean = JSON.parseObject(json, BindBankCardInfoBean.class);
                if (bean != null) {
                    data = bean.getData();
                    if (data != null && data.size() > 0) {
                        BindBankCardInfoBean.DataBean bankBean = data.get(0);
                        String realName = bankBean.getSubbankname();
                        String bankCard = bankBean.getBankcard();
                        tvBindCard.setText("" + realName + bankCard.substring(bankCard.length() - 4, bankCard.length()));
                    }
                }
                break;
        }
    }
}
