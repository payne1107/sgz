package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ReleaseOrderAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.SpacesItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 92457 on 2018/5/19.
 * 发布工单
 */

public class ReleaseOrderActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {

    //选择地理位置信息
    private static final int REQUEST_CHOOSE_LOCATION_CODE = 10001;
    protected static final String REQUEST_CHOOSE_LOCATION_ADDRESS_KEY = "request_choose_location_address_key";
    protected static final String REQUEST_CHOOSE_LOCATION_LON_KEY = "request_choose_location_lon_key";
    protected static final String REQUEST_CHOOSE_LOCATION_LAT_KEY = "request_choose_location_lat_key";
    //工单名称
    private static final int REQUEST_WORK_ORDER_NAME_CODE = 10002;
    protected static final String REQUEST_WORK_ORDER_NAME_KEY = "request_work_order_name_key";
    //监管单位
    private static final int REQUEST_REGULATORS_NAME_CODE = 10003;
    protected static final String REQUEST_REGULATORS_NAME_KEY = "request_regulators_name_key";
    //负责人
    private static final int REQUEST_LEADER_NAME_CODE = 10004;
    protected static final String REQUEST_LEADER_NAME_KEY = "request_leader_name_key";
    //工友信息
    private static final int REQUEST_CHOOSE_CONTACTS_CODE = 10005;
    protected static final String REQUEST_CHOOSE_CONTACTS_KEY = "request_choose_contacts_key";

    private TextView tvAddPerson;
    private Context mContext;
    private TextView tvWorkOrderName;
    private TextView tvRegulators;
    private TextView tvLeader;
    private TextView tvStartDate;
    private TextView tvChooseLocation;

    //存储联系人集合
    private List<String> listContacts = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReleaseOrderAdapter adapter;

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
        setSettingBtn("保存");
        tvAddPerson = (TextView) findViewById(R.id.tv_add_person);
        tvWorkOrderName = (TextView) findViewById(R.id.tv_work_order_name);
        tvRegulators = (TextView) findViewById(R.id.tv_regulators);
        tvLeader = (TextView) findViewById(R.id.tv_leader);
        tvStartDate = (TextView) findViewById(R.id.tv_start_date);
        tvChooseLocation = (TextView) findViewById(R.id.tv_choose_location);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 6);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(3));
        adapter = new ReleaseOrderAdapter(mContext, listContacts);
        recyclerView.setAdapter(adapter);
        setListener();
        initViewDateDialog(this,System.currentTimeMillis());
    }

    private void setListener() {
        tvAddPerson.setOnClickListener(this);
        tvWorkOrderName.setOnClickListener(this);
        tvRegulators.setOnClickListener(this);
        tvLeader.setOnClickListener(this);
        tvStartDate.setOnClickListener(this);
        tvChooseLocation.setOnClickListener(this);
        tvSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_person:
                startActivityForResult(new Intent(mContext, ContactsActivity.class),REQUEST_CHOOSE_CONTACTS_CODE);
                break;
            case R.id.tv_work_order_name:
                startActivityForResult(new Intent(mContext, EnterWorkOrderNameActivity.class),REQUEST_WORK_ORDER_NAME_CODE);
                break;
            case R.id.tv_regulators:
                startActivityForResult(new Intent(mContext, EnterRegulatorsActivity.class),REQUEST_REGULATORS_NAME_CODE);
                break;
            case R.id.tv_leader:
                startActivityForResult(new Intent(mContext, EnterLeaderNameActivity.class),REQUEST_LEADER_NAME_CODE);
                break;
            case R.id.tv_start_date:
                //项目开始日期
                dialogDay.show(getSupportFragmentManager(), "all");
                break;
            case R.id.tv_choose_location:
                //选择位置信息
                Intent intent = new Intent(mContext, ChooseLocationActivity.class);
                startActivityForResult(intent,REQUEST_CHOOSE_LOCATION_CODE);
                break;
            case R.id.activity_set:
                //保存工单信息
                toastMessage("save order details");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CHOOSE_LOCATION_CODE:
                    //选择地理位置
                    String addressName = data.getStringExtra(REQUEST_CHOOSE_LOCATION_ADDRESS_KEY);
                    tvChooseLocation.setText(addressName);
                    break;
                case REQUEST_LEADER_NAME_CODE:
                    String leaderName = data.getStringExtra(REQUEST_LEADER_NAME_KEY);
                    tvLeader.setText(leaderName);
                    break;
                case REQUEST_REGULATORS_NAME_CODE:
                    String regulatorsName = data.getStringExtra(REQUEST_REGULATORS_NAME_KEY);
                    tvRegulators.setText(regulatorsName);
                    break;
                case REQUEST_WORK_ORDER_NAME_CODE:
                    String workOrderName = data.getStringExtra(REQUEST_WORK_ORDER_NAME_KEY);
                    tvWorkOrderName.setText(workOrderName);
                    break;
                case REQUEST_CHOOSE_CONTACTS_CODE:
                    String contactsName = data.getStringExtra(REQUEST_CHOOSE_CONTACTS_KEY);
                    if (!StringUtils.isEmpty(contactsName)) {
                        listContacts.add(contactsName);
                        adapter.notifyDataSetChanged();
                    }
                    toastMessage("数组大小----------> " + listContacts.size());
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date d = new Date(millseconds);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        tvStartDate.setText(sf.format(d));
    }
}
