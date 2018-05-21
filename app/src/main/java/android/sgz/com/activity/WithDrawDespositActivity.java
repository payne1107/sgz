package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;

import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by 92457 on 2018/5/19.
 * 提现
 */

public class WithDrawDespositActivity extends BaseActivity implements View.OnClickListener {

    private EditText etMonkey;
    private AutoLinearLayout layoutConfim;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_withdraw_desposit);
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
        }
    }
}
