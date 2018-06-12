package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.view.View;
import android.widget.TextView;

import com.amap.api.services.route.RidePath;

/**
 * Created by WD on 2018/6/12.
 * 绑定银行卡
 */

public class BindBankCardActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvSave;
    private TextView tvCancel;
    private TextView tvChooseBankInfo;
    private Context mContext;

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
        tvChooseBankInfo = findViewById(R.id.tv_choose_bank_info);

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

                break;
            case R.id.tv_cancel:

                break;
            case R.id.tv_choose_bank_info:
                startActivity(new Intent(mContext, BankInfoActivity.class));
                break;
        }
    }
}
