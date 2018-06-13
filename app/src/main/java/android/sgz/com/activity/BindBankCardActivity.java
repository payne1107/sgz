package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.services.route.RidePath;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/6/12.
 * 绑定银行卡
 */

public class BindBankCardActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvSave;
    private TextView tvCancel;
    private TextView tvChooseBankInfo;
    private Context mContext;
    protected static final int REQUEST_BANK_INFO_CODE = 10001;
    private String bankCode;
    private EditText etBankCard;
    private EditText etSubBankName;
    private EditText etIdCard;
    private EditText etName;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_bind_bank_card);
        mContext = BindBankCardActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("添加银行卡", true, true);
        tvSave = findViewById(R.id.tv_save);
        tvCancel = findViewById(R.id.tv_cancel);
        etName = findViewById(R.id.et_name);
        tvChooseBankInfo = findViewById(R.id.tv_choose_bank_info);
        etBankCard = findViewById(R.id.et_bank_card);
        etSubBankName = findViewById(R.id.et_sub_band_name);
        etIdCard = findViewById(R.id.et_id_card);
        setListener();
    }

    private void setListener() {
        tvSave.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvChooseBankInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                saveBankCardInfo();
                break;
            case R.id.tv_cancel:

                break;
            case R.id.tv_choose_bank_info:
                startActivityForResult(new Intent(mContext, BankInfoActivity.class),REQUEST_BANK_INFO_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_BANK_INFO_CODE:
                if (data != null) {
                    String bankName = data.getStringExtra("bankname");
                    bankCode = data.getStringExtra("bankcode");
                    if (!StringUtils.isEmpty(bankName)) {
                        tvChooseBankInfo.setText(bankName);
                    }
                }
                break;
        }
    }

    /****
     * 保存银行卡信息
     */
    private void saveBankCardInfo() {
        String name = etName.getText().toString().trim();
        String bankcard = etBankCard.getText().toString().trim();
        String idCard = etIdCard.getText().toString().trim();
        String bankInfo = tvChooseBankInfo.getText().toString().trim();
        String subBankName = etSubBankName.getText().toString().trim();
        if (StringUtils.isEmpty(name)) {
            toastMessage("姓名不能为空");
            return;
        }
        if (StringUtils.isEmpty(bankcard)) {
            toastMessage("银行卡号不能为空");
            return;
        }
        if (StringUtils.isEmpty(idCard)) {
            toastMessage("身份证信息不能为空");
            return;
        }
        if (StringUtils.isEmpty(bankInfo) || bankInfo.equals("请选择")) {
            toastMessage("请选择银行名称");
            return;
        }
        if (StringUtils.isEmpty(subBankName)) {
            toastMessage("请输入支行名称");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("bankcode", bankCode);
        params.put("bankcard", bankcard);
        params.put("idcard", idCard);
        params.put("realname", name);
        params.put("subbankname", subBankName);
        httpPostRequest(ConfigUtil.SAVE_BANK_INFO_URL, params, ConfigUtil.SAVE_BANK_INFO_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.SAVE_BANK_INFO_URL_ACTION:
                Log.d("Dong", "json----->" + json);
                if (getRequestCode(json) == 1) {
                    toastMessage("添加成功");
                    finish();
                }
                break;
        }
    }
}
