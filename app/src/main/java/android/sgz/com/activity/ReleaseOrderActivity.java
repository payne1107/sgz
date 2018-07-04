package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ReleaseOrderAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.AddOrderContactsBean;
import android.sgz.com.bean.SetContactsSalaryBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.sgz.com.widget.MyDialog;
import android.sgz.com.widget.SpacesItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //选择城市信息
    private static final int REQUEST_CHOOSE_COMPANY_CODE = 10006;
    protected static final String REQUEST_CHOOSE_COMPANY_KEY = "request_choose_company_key";
    //打卡时间设置
    private static final int REQUEST_SET_WORK_RECORD_CODE =  10007;
    //设置工资工资信息
    private static final int REQUEST_SET_CONTACTS_SALARY_CODE = 10008;


    private TextView tvAddPerson;
    private Context mContext;
    private TextView tvWorkOrderName;
    private TextView tvRegulators;
    private TextView tvLeader;
    private TextView tvStartDate;
    private TextView tvChooseLocation;

    //存储联系人集合
    private List<AddOrderContactsBean> listContacts = new ArrayList<>();
    //设置工人工资临时存储的集合
    private List<SetContactsSalaryBean> listSalary = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReleaseOrderAdapter adapter;
    private TextView tvChooseCompany;

    private AutoLinearLayout layoutSetWorkRecord;
    private String startWorkTime;
    private String endWorkTime;
    private int merchantId;
    private String lonLocation;
    private String latLocation;
    private TextView tvSetWorkRecord;

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
        tvChooseCompany = findViewById(R.id.tv_choose_company);
        layoutSetWorkRecord = findViewById(R.id.layout_set_work_record);
        tvSetWorkRecord = findViewById(R.id.tv_set_work_record);
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
        tvChooseCompany.setOnClickListener(this);
        layoutSetWorkRecord.setOnClickListener(this);
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("Dong", "------->" + listContacts.get(position).getRealName());
                AddOrderContactsBean contactsBean = listContacts.get(position);
                if (contactsBean != null) {
                    int userId = contactsBean.getUserId();
                    Intent intent = new Intent(mContext, SetWorkPresonSalaryActivity.class);
                    intent.putExtra("userId", userId);
                    startActivityForResult(intent,REQUEST_SET_CONTACTS_SALARY_CODE);
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
                deleteContacts(position);
            }
        });
    }

    /****
     * 删除联系人对话框
     * @param position
     */
    private void deleteContacts(final int position) {
        AddOrderContactsBean bean = listContacts.get(position);
        final int userId =bean.getUserId();
        final MyDialog dialog = new MyDialog(mContext);
        dialog.setMessage("是否删除此工人信息？");
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取消
                dialog.dismiss();
            }
        });
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listContacts.remove(position);
                adapter.notifyDataSetChanged();
                //确认
                for (int i = 0; i < listSalary.size(); i++) {
                    if (listSalary.get(i).getUserid() == userId) {
                        listSalary.remove(i);
                    }
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_person:
                Intent intentContact = new Intent(mContext, ContactsActivity.class);
                intentContact.putExtra("query_contacts_info", 1);
                startActivityForResult(intentContact,REQUEST_CHOOSE_CONTACTS_CODE);
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
//                Intent intent = new Intent(mContext, ChooseLocationActivity.class);
                Intent intent = new Intent(mContext, ChooseLocation3Activity.class);
                startActivityForResult(intent,REQUEST_CHOOSE_LOCATION_CODE);
                break;
            case R.id.activity_set:
                //保存工单信息
                releaseOrder();
                break;
            case R.id.tv_choose_company:
                startActivityForResult(new Intent(mContext, ChooseCompanyActivity.class),REQUEST_CHOOSE_COMPANY_CODE);
                break;
            case R.id.layout_set_work_record:
                startActivityForResult(new Intent(mContext, SetWorkRecordActivity.class),REQUEST_SET_WORK_RECORD_CODE); //设置打卡时间
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
                    lonLocation = data.getStringExtra(REQUEST_CHOOSE_LOCATION_LON_KEY);
                    latLocation = data.getStringExtra(REQUEST_CHOOSE_LOCATION_LAT_KEY);
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
                    //此处除了userid和name有用其他无意义
                    int userid = data.getIntExtra("userid", -1);
                    String allowance = data.getStringExtra("allowance");
                    String salary = data.getStringExtra("salary");
                    String overWorkSalary = data.getStringExtra("overWorkSalary");
                    String realName = data.getStringExtra("realName");
                    String profession = data.getStringExtra("profession");
                    AddOrderContactsBean contactsName = new AddOrderContactsBean();
                    contactsName.setUserId(userid);
                    contactsName.setSalary(StringUtils.isEmpty(salary) ? "" : salary);
                    contactsName.setAllowance(StringUtils.isEmpty(allowance) ? "" : allowance);
                    contactsName.setOverWorkSalary(StringUtils.isEmpty(overWorkSalary) ? "" : overWorkSalary);
                    contactsName.setProfession(StringUtils.isEmpty(profession)? "" : profession);
                    contactsName.setRealName(StringUtils.isEmpty(realName) ? "" : realName);
                    listContacts.add(contactsName);
                    adapter.notifyDataSetChanged();
                    break;
                case REQUEST_CHOOSE_COMPANY_CODE:
                    merchantId = data.getIntExtra(REQUEST_CHOOSE_COMPANY_KEY, -1);
                    String merchantName = data.getStringExtra("merchantname");
                    tvChooseCompany.setText(merchantName);
                    break;
                case REQUEST_SET_WORK_RECORD_CODE:
                    startWorkTime = data.getStringExtra("startworktime");
                    endWorkTime = data.getStringExtra("endworktime");
                    tvSetWorkRecord.setText("" + startWorkTime + "~" + endWorkTime);
                    break;
                case REQUEST_SET_CONTACTS_SALARY_CODE:
                    Log.d("Dong", "设置工人工资---->" );
                    if (data != null) {
                        int userId = data.getIntExtra("userId", -1);
                        String allowanceSalary = data.getStringExtra("allownce");
                        String contactSalary = data.getStringExtra("salary");
                        String addWorkSalary = data.getStringExtra("extraworksalary");
                        SetContactsSalaryBean bean = new SetContactsSalaryBean();

                        bean.setUserid(userId);
                        bean.setOverworksalary(addWorkSalary);
                        bean.setAllowance(allowanceSalary);
                        bean.setSalary(contactSalary);
                        listSalary.add(bean);
                    }
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

    /****
     * 发布工单
     */
    private void releaseOrder() {
        String workOrderName = tvWorkOrderName.getText().toString().trim();
        String company = tvChooseCompany.getText().toString().trim();
        String leader =tvLeader.getText().toString().trim();
        String address = tvChooseLocation.getText().toString().trim();
        String projectStartDate =tvStartDate.getText().toString().trim();
        if (("请输入").equals(workOrderName)) {
            toastMessage("请输入工单名称");
            return;
        }
//        if ("请选择".equals(company)) {
//            toastMessage("请选择所属公司");
//            return;
//        }
        if ("自己".equals(leader)) {
            toastMessage("请选择负责人");
            return;
        }
        if ("选择地点".equals(address)) {
            toastMessage("请选择工地的地理位置");
            return;
        }
        if ("请选择".equals(projectStartDate)) {
            toastMessage("请选择开工日期");
            return;
        }
        if (StringUtils.isEmpty(startWorkTime) || StringUtils.isEmpty(endWorkTime)) {
            toastMessage("请设置上班和下班打卡时间");
            return;
        }
        //工单员工信息
        String strJson = JSON.toJSONString(listSalary);
        if (StringUtils.isEmpty(strJson)) {
            toastMessage("请添加员工");
            return;
        }
        startIOSDialogLoading(mContext,"发布中..");
        Map<String, String> params = new HashMap<>();
        params.put("name", workOrderName);
        params.put("headman", leader);
        params.put("address", address);
        params.put("lng", lonLocation);
        params.put("lat", latLocation);
        params.put("starttime", projectStartDate);
        params.put("startworktime", startWorkTime);
        params.put("endworktime", endWorkTime);
        if (merchantId > 0) {
            params.put("merchantid", String.valueOf(merchantId));
        }
        params.put("roster", strJson);
        Log.d("Dong", "roster ---->" + strJson);
        httpPostRequest(ConfigUtil.ADD_PROJECT_ORDER_URL, params, ConfigUtil.ADD_PROJECT_ORDER_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.ADD_PROJECT_ORDER_URL_ACTION:
                Log.d("Dong", "发布工单 ：" + json);
                if (getRequestCode(json) ==1) {
                    toastMessage("工单发布成功");
                    finish();
                }
                break;
        }
    }
}
