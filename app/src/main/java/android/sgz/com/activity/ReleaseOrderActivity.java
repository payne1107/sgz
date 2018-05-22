package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.view.View;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 92457 on 2018/5/19.
 */

public class ReleaseOrderActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {

    private TextView tvAddPerson;
    private Context mContext;
    private TextView tvWorkOrderName;
    private TextView tvRegulators;
    private TextView tvLeader;
    private TextView tvStartDate;
    private TextView tvChooseLocation;

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
        tvWorkOrderName = (TextView) findViewById(R.id.tv_work_order_name);
        tvRegulators = (TextView) findViewById(R.id.tv_regulators);
        tvLeader = (TextView) findViewById(R.id.tv_leader);
        tvStartDate = (TextView) findViewById(R.id.tv_start_date);
        tvChooseLocation = (TextView) findViewById(R.id.tv_choose_location);
        setListener();
        initViewDateDialog(this);
    }

    private void setListener() {
        tvAddPerson.setOnClickListener(this);
        tvWorkOrderName.setOnClickListener(this);
        tvRegulators.setOnClickListener(this);
        tvLeader.setOnClickListener(this);
        tvStartDate.setOnClickListener(this);
        tvChooseLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_person:
                startActivity(new Intent(mContext, ContactsActivity.class));
                break;
            case R.id.tv_work_order_name:
                startActivity(new Intent(mContext, EnterWorkOrderNameActivity.class));
                break;
            case R.id.tv_regulators:
                startActivity(new Intent(mContext, EnterRegulatorsActivity.class));
                break;
            case R.id.tv_leader:
                startActivity(new Intent(mContext, EnterLeaderNameActivity.class));
                break;
            case R.id.tv_start_date:
                //项目开始日期
                dialogDay.show(getSupportFragmentManager(), "all");
                break;
            case R.id.tv_choose_location:
                //选择位置信息
                startActivity(new Intent(mContext, ChooseLocationActivity.class));
                break;
        }
    }


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date d = new Date(millseconds);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        tvStartDate.setText(sf.format(d));
    }
}
