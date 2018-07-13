package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.CardCountingAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.RecordWorkBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.DateUtils;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/5/10.
 * 打卡统计
 */

public class CardCountingActivity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutChooseDay;
    private PopupWindow popuRightWindow;
    private List<String> mList = new ArrayList<>();
    private Context mContext;
    private TextView tvChooseDay;
    private AutoLinearLayout layoutTurnForWorkDays;
    private AutoLinearLayout layoutTurnForWork;
    private AutoLinearLayout layoutLate;
    private AutoLinearLayout layoutLeaveEarly;
    private AutoLinearLayout layoutLackCard;
    private AutoLinearLayout layoutAbsenteeism;
    private AutoLinearLayout layoutOverTime;
    private String currentYearMoth;
    private TextView tvWorkDays;
    private TextView tvLateCount;
    private TextView tvLeaveCount;
    private TextView tvNoRecordCount;
    private TextView tvAddWorkCount;
    private int projectId;
    private String projectName;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_card_counting);
        mContext = CardCountingActivity.this;
        mList.add("2018-06");
        mList.add("2018-07");
        mList.add("2018-08");
        mList.add("2018-09");
        mList.add("2018-10");
        mList.add("2018-11");
        mList.add("2018-12");
        initRightPopuWindow(R.layout.item_card_counting_title);
    }

    @Override
    protected void initData() {
        queryWorkRecord();
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("打卡统计", true, true);

        layoutChooseDay = (AutoLinearLayout) findViewById(R.id.layout_choose_day);
        tvChooseDay = (TextView) findViewById(R.id.tv_choose_day);
        layoutTurnForWorkDays = (AutoLinearLayout) findViewById(R.id.layout_turn_for_work_days);
        layoutTurnForWork = (AutoLinearLayout) findViewById(R.id.layout_turn_for_work);
        layoutLate = (AutoLinearLayout) findViewById(R.id.layout_late);
        layoutLeaveEarly = (AutoLinearLayout) findViewById(R.id.layout_leave_early);
        layoutLackCard = (AutoLinearLayout) findViewById(R.id.layout_lack_card);
        layoutAbsenteeism = (AutoLinearLayout) findViewById(R.id.layout_absenteeism);
        layoutOverTime = (AutoLinearLayout) findViewById(R.id.layout_overtime);
        tvWorkDays = findViewById(R.id.tv_work_days);
        tvLateCount = findViewById(R.id.tv_late_count);
        tvLeaveCount = findViewById(R.id.tv_leave_count);
        tvNoRecordCount = findViewById(R.id.tv_no_record_count);
        tvAddWorkCount = findViewById(R.id.tv_add_work_count);

        currentYearMoth = DateUtils.getYear() + "-" + (DateUtils.getMonth() < 10 ? "0" + DateUtils.getMonth() : DateUtils.getMonth());
        tvChooseDay.setText(currentYearMoth);
        setListener();
    }

    private void setListener() {
        layoutChooseDay.setOnClickListener(this);
        layoutTurnForWorkDays.setOnClickListener(this);
        layoutTurnForWork.setOnClickListener(this);
        layoutLate.setOnClickListener(this);
        layoutLeaveEarly.setOnClickListener(this);
        layoutLackCard.setOnClickListener(this);
        layoutAbsenteeism.setOnClickListener(this);
        layoutOverTime.setOnClickListener(this);
    }


    /****
     * 展示对话框
     */
    private void showPopuWindow(PopupWindow popupWindow) {
        //适配到7.0
        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.showAsDropDown(layoutChooseDay);
        } else {
            // 适配 android 7.0
            int[] location = new int[2];
            //获取控件在window里的位置,
            // 0位表示x轴方向的坐标,说白点就是view距左边的距离
            // 1位表示y轴的方向,是view距离顶部(包括状态栏)的位置
            layoutChooseDay.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            //表示显示在view的下方)
            popupWindow.showAtLocation(layoutChooseDay, Gravity.NO_GRAVITY, 0, y + layoutChooseDay.getHeight());
        }
    }


    /****
     * 初始化Right的view
     * @param popuWindowLayout
     */
    private void initRightPopuWindow(final int popuWindowLayout) {
        View timeView = LayoutInflater.from(this).inflate(popuWindowLayout, null);
        popuRightWindow = new PopupWindow(timeView);
        popuRightWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popuRightWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popuRightWindow.setContentView(timeView);
        popuRightWindow.setOutsideTouchable(true);
        popuRightWindow.setFocusable(true);
        popuRightWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

        AutoLinearLayout layoutContainer = (AutoLinearLayout) timeView.findViewById(R.id.layout_center);
        RecyclerView recyclerView = (RecyclerView) timeView.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        CardCountingAdapter adapter = new CardCountingAdapter(mContext, mList);
        recyclerView.setAdapter(adapter);
        layoutContainer.setOnClickListener(this);
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                currentYearMoth = mList.get(position);
                tvChooseDay.setText(currentYearMoth);
                dimissPopuWindow();
                queryWorkRecord();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_choose_day:
                showPopuWindow(popuRightWindow);
                break;
            case R.id.layout_center:
                dimissPopuWindow();
                break;
            case R.id.layout_turn_for_work_days:
            case R.id.layout_turn_for_work:
            case R.id.layout_late:
            case R.id.layout_leave_early:
            case R.id.layout_lack_card:
            case R.id.layout_absenteeism:
            case R.id.layout_overtime:
                //打卡详情
                if (projectId > 0) {
                    startActivity(new Intent(mContext, CardCountingDetailsActivity.class).putExtra("current_month", currentYearMoth).putExtra("projectId", projectId).putExtra("projectName", projectName));
                } else {
                    toastMessage("没有工单统计数据");
                }
                break;
        }
    }


    /****
     * 销毁对话框
     */
    private void dimissPopuWindow() {
        if (popuRightWindow != null) {
            popuRightWindow.dismiss();
        }
    }

    /****
     * 根据年月查询打卡统计数据
     */
    private void queryWorkRecord() {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("month", currentYearMoth);
        httpPostRequest(ConfigUtil.QUERY_RECORD_BY_MONTH_URL, params, ConfigUtil.QUERY_RECORD_BY_MONTH_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_RECORD_BY_MONTH_URL_ACTION:
                handleQueryRecordByMoth(json);
                break;
        }
    }

    /*****
     * 处理查询打卡统计数据
     * @param json
     */
    private void handleQueryRecordByMoth(String json) {
        Log.d("Dong", "处理查询打卡----》" + json);
        RecordWorkBean bean = JSON.parseObject(json, RecordWorkBean.class);
        if (bean != null) {
            RecordWorkBean.DataBean data = bean.getData();
            if (data != null) {
                int leak = data.getLeak();//漏打卡
                int attendance = data.getAttendance();//出勤天数
                int late = data.getLate();//迟到
                int extraworkTime = data.getExtraworktime();//加班时长
                int leave = data.getLeave();//早退
                projectName = data.getProjectname();
                projectId = data.getProjectid();
                tvWorkDays.setText(attendance+"天");
                tvLateCount.setText(late + "次");
                tvLeaveCount.setText(leave + "次");
                tvAddWorkCount.setText(extraworkTime + "小时");
                tvNoRecordCount.setText(leak+"次");
            }
        }
    }
}
