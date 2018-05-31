package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by WD on 2018/5/7.
 * 我的支出
 */

public class MineExpendActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvExpendDetails;
    private TextView tvRechargeDetails;
    private TextView tvRecharge;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_expend);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的支出", true, true);
        tvRecharge = findViewById(R.id.tv_recharge);
        tvRechargeDetails = findViewById(R.id.tv_recharge_details);
        tvExpendDetails = findViewById(R.id.tv_expend_details);

        setListener();
    }

    private void setListener() {
        tvRecharge.setOnClickListener(this);
        tvRechargeDetails.setOnClickListener(this);
        tvExpendDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_recharge:
                toastMessage("充值");
                break;
            case R.id.tv_recharge_details:
                toastMessage("充值明细");
                break;
            case R.id.tv_expend_details:
                toastMessage("支出明细");
                break;
        }
    }
}
