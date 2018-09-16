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

    protected static final int REQUEST_BANK_CARD_INFO_CODE = 10002;
    private EditText etMonkey;
    private AutoLinearLayout layoutConfim;
    private TextView tvBindCard;
    private Context mContext;
    private List<BindBankCardInfoBean.DataBean> data;
    private String strBankListInfo;
    private int bankId;
    private double withdrawalBalance;
    private int projectId;

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
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("提现", true, true);
        withdrawalBalance = getIntent().getDoubleExtra("withdrawalBalance", 0.0);
        projectId = getIntent().getIntExtra("projectId", 0);
        etMonkey = (EditText) findViewById(R.id.et_money);
        layoutConfim = (AutoLinearLayout) findViewById(R.id.layout_confirm);
        tvBindCard = findViewById(R.id.tv_bind_card);
        TextView tvWithDrawalBlance = findViewById(R.id.withdrawal_balance);
        tvWithDrawalBlance.setText("" + withdrawalBalance);
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
                applyCash();
                break;
            case R.id.tv_bind_card:
                if (data != null && data.size() > 0) {
                    //跳转到现有的银行卡信息页面 否则跳转到绑定银行卡信息页面
                    startActivityForResult(new Intent(mContext, BankCardListInfoActivity.class).putExtra("strBankListInfo", strBankListInfo),REQUEST_BANK_CARD_INFO_CODE);
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
                BindBankCardInfoBean bean = JSON.parseObject(json, BindBankCardInfoBean.class);
                if (bean != null) {
                    data = bean.getData();
                    if (data != null && data.size() > 0) {
                        strBankListInfo = json;
                        BindBankCardInfoBean.DataBean bankBean = data.get(0);
                        String realName = bankBean.getSubbankname();
                        String bankCard = bankBean.getBankcard();
                        bankId = bankBean.getId();
                        tvBindCard.setText("" + realName + bankCard.substring(bankCard.length() - 4, bankCard.length()));
                    }
                }
                break;
            case ConfigUtil.APPLY_CASH_URL_ACTION:
                if (getRequestCode(json) == 1) {
                    toastMessage("提现成功");
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_BANK_CARD_INFO_CODE:
                if (data != null) {
                    String bankCardNo = data.getStringExtra("bankcard_number");
                    String subBankName = data.getStringExtra("subBankName");
                    bankId = data.getIntExtra("bankId",0);
                    tvBindCard.setText("" + subBankName + (bankCardNo.substring(bankCardNo.length() - 4, bankCardNo.length())));

                    Log.d("Dong", "-----------------------------------------------" + bankCardNo);
                }
                break;
        }
    }

    /***
     * 申请提现
     */
    private void applyCash() {
        String money = etMonkey.getText().toString().trim();
        if (StringUtils.isEmpty(money)) {
            toastMessage("提现金额不能为空");
            return;
        }
        if (Double.valueOf(money) > withdrawalBalance) {
            toastMessage("提现金额不能大于可提现余额");
            return;
        }
        if (bankId <= 0) {
            toastMessage("请绑定银行卡");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("tjmoney", money);
        params.put("userbankid", String.valueOf(bankId));
        if (projectId > 0) {
            params.put("projectid", String.valueOf(projectId));
        }
        httpPostRequest(ConfigUtil.APPLY_CASH_URL, params, ConfigUtil.APPLY_CASH_URL_ACTION);
    }
}
