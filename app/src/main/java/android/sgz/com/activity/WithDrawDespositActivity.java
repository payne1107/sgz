package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by 92457 on 2018/5/19.
 * 提现
 */

public class WithDrawDespositActivity extends BaseActivity implements View.OnClickListener {

    private EditText etMonkey;
    private AutoLinearLayout layoutConfim;
    private TextView tvBindCard;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_withdraw_desposit);
        mContext = WithDrawDespositActivity.this;
    }

    @Override
    protected void initData() {

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
                startActivity(new Intent(mContext, BindBankCardActivity.class));
                break;
        }
    }
}
