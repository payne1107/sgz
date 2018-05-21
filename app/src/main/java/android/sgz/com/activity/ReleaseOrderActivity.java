package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 92457 on 2018/5/19.
 */

public class ReleaseOrderActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvAddPerson;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_release_order);
        mContext = ReleaseOrderActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("发布工单", true, true);
        tvAddPerson = (TextView) findViewById(R.id.tv_add_person);

        setListener();
    }


    private void setListener() {
        tvAddPerson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_person:
                startActivity(new Intent(mContext, ContactsActivity.class));
                break;
        }
    }
}
