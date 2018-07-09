package android.sgz.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.activity.ContactsActivity;
import android.sgz.com.activity.MineSalaryActivity;
import android.sgz.com.activity.SearchActivity;
import android.sgz.com.activity.WorkDayNumActivity;
import android.sgz.com.activity.WorkOrderActivity;
import android.sgz.com.adapter.FirstFragmentAdapter;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.TopInfoBean;
import android.sgz.com.bean.WorkStatusBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.DateUtils;
import android.sgz.com.utils.SPUtil;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zaaach.citypicker.CityPickerActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 92457 on 2018/4/16.
 */

public class Fragment1 extends BaseFragment implements View.OnClickListener {

    private static final int REQUEST_CODE_PICK_CITY = 1;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private EditText etSearch;
    private AutoRelativeLayout tvTitle;
    private AutoLinearLayout layoutWorkDay;
    private AutoLinearLayout layoutFriends;
    private AutoLinearLayout layoutworkOrder;
    private AutoLinearLayout layoutSalary;
    private String city;
    private TextView tvCity;
    private TextView tvWorkDays;
    private TextView tvWorkFriends;
    private TextView tvWorkOrder;
    private TextView tvSalary;
    private AutoLinearLayout layoutWorkRecord;
    private TextView tvWorkStatus;
    private TextView tvDate;


    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment1, null);
        }
        //缓存的rootView需要判断是否已经被加过parent,如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvTitle = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_title);
        etSearch = (EditText) mRootView.findViewById(R.id.et_search);
        tvCity = (TextView) mRootView.findViewById(R.id.activity_city);
        layoutWorkDay = (AutoLinearLayout) mRootView.findViewById(R.id.layout_work_day);
        layoutFriends = (AutoLinearLayout) mRootView.findViewById(R.id.layout_friends);
        layoutworkOrder = (AutoLinearLayout) mRootView.findViewById(R.id.layout_word_order);
        layoutSalary = (AutoLinearLayout) mRootView.findViewById(R.id.layout_salary);
        tvWorkDays = mRootView.findViewById(R.id.tv_work_days);
        tvWorkFriends = mRootView.findViewById(R.id.tv_work_friends);
        tvWorkOrder = mRootView.findViewById(R.id.tv_work_order);
        tvSalary = mRootView.findViewById(R.id.tv_salary);
        layoutWorkRecord = mRootView.findViewById(R.id.layout_work_record);
        tvWorkStatus =mRootView.findViewById(R.id.tv_work_status);
        tvDate = mRootView.findViewById(R.id.tv_date);

        viewPager = (ViewPager) mRootView.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) mRootView.findViewById(R.id.tabLayout);
        FirstFragmentAdapter adapter = new FirstFragmentAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setListener();
        initData();
    }

    /***
     * 初始化数据
     */
    private void initData() {
        initLocation();
        queryWorkStatus();
        queryTopInfo();
    }

    /****
     * 设置监听器
     */
    private void setListener() {
        tvTitle.setOnClickListener(this);
        layoutWorkDay.setOnClickListener(this);
        layoutFriends.setOnClickListener(this);
        layoutworkOrder.setOnClickListener(this);
        layoutSalary.setOnClickListener(this);
        layoutWorkRecord.setOnClickListener(this);
        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    startActivity(new Intent(getActivity(), SearchActivity.class));
                    etSearch.clearFocus();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_work_day:
                startActivity(new Intent(getActivity(), WorkDayNumActivity.class));
                break;
            case R.id.layout_friends:
                startActivity(new Intent(getActivity(), ContactsActivity.class));
                break;
            case R.id.layout_word_order:
                //工单
                startActivity(new Intent(getActivity(), WorkOrderActivity.class));
                break;
            case R.id.layout_salary:
                startActivity(new Intent(getActivity(), MineSalaryActivity.class));
                break;
            case R.id.rl_title:
                startLocationActivity();
                break;
            case R.id.layout_work_record:
                //打卡按钮
                addWorkRecord();
                break;
        }
    }

    /****
     * 跳转到选择城市页面
     */
    private void startLocationActivity() {
        startActivityForResult(new Intent(getActivity(),CityPickerActivity.class),REQUEST_CODE_PICK_CITY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode ==  RESULT_OK) {
            if (data != null) {
                city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                SPUtil.putString(getActivity(), "current_city", city);
                tvCity.setText(city);
                //选择城市后将当前选择城市赋值
                MyApplication.currentCity = city;
            }
        }
    }

    private void initLocation() {
        AMapLocationClient mLocationClient = new AMapLocationClient(getActivity());
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //登陆之后记录经纬度
                        MyApplication.currentLat = aMapLocation.getLatitude();
                        MyApplication.currentLon = aMapLocation.getLongitude();
                        MyApplication.currentArea = aMapLocation.getCity() + aMapLocation.getPoiName();
                        MyApplication.currentCity = aMapLocation.getCity();
                        city = aMapLocation.getCity();
                        tvCity.setText(city);
                        Log.d("Dong", "定位成功-----》" + MyApplication.currentArea + "," + MyApplication.currentLat +","  + MyApplication.currentLon);
                    } else {
                        //定位失败
                        city = "合肥市";
                        tvCity.setText(city);
                        MyApplication.currentCity = city;
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }

    /****
     * 获取工友 工资等信息
     */
    private void queryTopInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_INDEX_DATA_URL, params, ConfigUtil.QUERY_INDEX_DATA_URL_ACTION);
    }

    /****
     * 获取打卡按钮状态
     */
    private void queryWorkStatus() {
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_WORK_STATUS_URL, params, ConfigUtil.QUERY_WORK_STATUS_URL_ACTION);
    }

    /***
     * 打卡请求
     */
    private void addWorkRecord() {
        startIOSDialogLoading(getActivity(),"打卡中..");
        Map<String, String> params = new HashMap<>();
        params.put("address", MyApplication.currentArea);
        params.put("lng", String.valueOf(MyApplication.currentLon));
        params.put("lat", String.valueOf(MyApplication.currentLat));
        httpPostRequest(ConfigUtil.ADD_WORK_RECORD_URL, params, ConfigUtil.ADD_WORK_RECORD_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_INDEX_DATA_URL_ACTION:
                handlerQueryTopInfo(json);
            break;
            case ConfigUtil.QUERY_WORK_STATUS_URL_ACTION:
                handleQueryWorkStatus(json);
                break;
            case ConfigUtil.ADD_WORK_RECORD_URL_ACTION:
                handleAddWorkRecord(json);
                break;
        }
    }

    /****
     * 处理打卡反馈
     * @param json
     */
    private void handleAddWorkRecord(String json) {
        Log.d("Dong", "处理打卡反馈结果---》" + json);
        if (getRequestCode(json) == 1) {
            Toast.makeText(getActivity(), "打卡成功", Toast.LENGTH_LONG).show();
            queryWorkStatus();
            //打卡成功刷新下面的数据
            EventBus.getDefault().post(ConfigUtil.EVENT_TYPE_CODE_ONE);
        }
    }

    /***
     * 处理打卡状态状态
     * @param json
     */
    private void handleQueryWorkStatus(String json) {
        Log.d("Dong", "打卡状态---》" + json);
        WorkStatusBean bean = JSON.parseObject(json, WorkStatusBean.class);
        if (bean != null) {
            String result = bean.getResultMsg();
            tvWorkStatus.setText("start".equals(result) ? "上班打卡" : "下班打卡");
            tvDate.setText("" + DateUtils.getMonth()+"月" + DateUtils.getCurrentDayOfMonth()+"号");
        }
    }

    /****
     * 顶部数据处理
     * @param json
     */
    private void handlerQueryTopInfo(String json) {
        Log.d("Dong", "顶部数据处理----->" + json);
        TopInfoBean topInfoBean = JSON.parseObject(json, TopInfoBean.class);
        if (topInfoBean != null) {
            TopInfoBean.DataBean data = topInfoBean.getData();
            if (data != null) {
                int workDays = data.getWorkdays();
                int workFriends = data.getWorkfriends();
                int projectCount = data.getProjectcount();
                String salaryTotal = data.getIncome();//工资
                tvSalary.setText(""+salaryTotal+"元");
                tvWorkDays.setText(""+workDays+"天");
                tvWorkFriends.setText(""+workFriends + "人");
                tvWorkOrder.setText(""+projectCount+"个");
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

