package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.DateAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.WorkRecordByTimeBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.DateUtils;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 92457 on 2018/5/17.
 * 打卡详情
 */

public class CardCountingDetailsActivity extends BaseActivity implements View.OnClickListener {
    private int[][] days;
    private DateAdapter dateAdapter;
    private int[] dayList =new int[42];
    private String curentYearMonth;
    private int projectId;
    private Context mContext;
    private TextView tvStartRecordTime;
    private TextView tvStartStatus;
    private TextView tvStartAddress;
    private TextView tvEndRecordTime;
    private TextView tvEndRecordAddress;
    private TextView tvEndStatus;
    private GridView gridView;
    private int clickDay;
    private TextView tvProjectName;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_card_counting_details);
        mContext = CardCountingDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        int year = DateUtils.getYear();
        final int month = DateUtils.getMonth();
        int day = DateUtils.getCurrentDayOfMonth();

        projectId = getIntent().getIntExtra("projectId", 0);
        curentYearMonth = getIntent().getStringExtra("current_month");
        String projectName =getIntent().getStringExtra("projectName");
        clickDay = DateUtils.getCurrentDayOfMonth();
        //第一次进入用这个查询
        String firstYearMonth = curentYearMonth + "-" + (day < 10 ? "0" + day : day);
        setInVisibleTitleIcon(curentYearMonth, true, true);
        gridView = (GridView) findViewById(R.id.calendar_gridView);
        tvStartRecordTime = findViewById(R.id.tv_start_record_time);
        tvStartStatus = findViewById(R.id.tv_start_status);
        tvStartAddress = findViewById(R.id.tv_start_address);
        tvEndRecordTime = findViewById(R.id.tv_end_record_time);
        tvEndStatus = findViewById(R.id.tv_end_status);
        tvEndRecordAddress = findViewById(R.id.tv_end_record_address);
        tvProjectName = findViewById(R.id.tv_project_name);
        tvProjectName.setText("项目名称：" + projectName);

        days = DateUtils.getDayOfMonthFormat(year, month);
        convertArray();
        dateAdapter = new DateAdapter(this, dayList, year, month,day);//传入当前月的年
        gridView.setAdapter(dateAdapter);
        gridView.setVerticalSpacing(60);
        gridView.setEnabled(true);

        queryWorkRecordByTime(projectId,firstYearMonth);

        setListener();
    }

    private void setListener() {
        tvSet.setOnClickListener(this);
        tvStartStatus.setOnClickListener(this);
        tvEndStatus.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int array = (int) parent.getAdapter().getItem(position);
                if (position < 7 && array > 20) {
                    View view1 =parent.getChildAt(position);
                    view1.setEnabled(false);
                } else if (position > 20 && array < 15) {
                    View view1 =parent.getChildAt(position);
                    view1.setEnabled(false);
                } else {
                    clickDay = (int) parent.getAdapter().getItem(position);
                    dateAdapter.updateTextColor(position);
                    dateAdapter.notifyDataSetChanged();
                    String month = curentYearMonth + "-" + (clickDay < 10 ? "0" + clickDay : clickDay);
                    queryWorkRecordByTime(projectId, month);
                }
            }
        });
    }

    /****
     * 二维数据转成一维数组
     */
    private void convertArray() {
        int dayNum = 0;
        //将二维数组转化为一维数组，方便使用
        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < days[i].length; j++) {
                this.dayList[dayNum] = days[i][j];
                dayNum++;
            }
        }
    }

    /****
     * 根据年月日查询打卡记录
     */
    private void queryWorkRecordByTime(int projectid,String date) {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectid));
        params.put("date", date);
        httpPostRequest(ConfigUtil.QUERY_WORK_RECORD_BY_TIME_URL, params, ConfigUtil.QUERY_WORK_RECORD_BY_TIME_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_WORK_RECORD_BY_TIME_URL_ACTION:
                handleQueryWorkRecordByTime(json);
                break;
        }
    }

    /****
     * 处理打卡记录
     * @param json
     */
    private void handleQueryWorkRecordByTime(String json) {
        Log.d("Dong", "json -----》" +json);
        WorkRecordByTimeBean bean = JSON.parseObject(json, WorkRecordByTimeBean.class);
        if (bean != null) {
            WorkRecordByTimeBean.DataBean data = bean.getData();
            if (data != null) {
                String startRecordTime =data.getStartrecordtime();
                String endRecordTime =data.getEndrecordtime();
                String startRecordAddress=data.getStartrecordaddress();
                String endRecordAddress =data.getEndrecordaddress();
                int startStatus =data.getStartstatus();
                int endStatus =data.getEndstatus();
                tvStartRecordTime.setText(StringUtils.isEmpty(startRecordTime) ? "打卡时间:" : "打卡时间:" + startRecordTime);
                tvStartAddress.setText(StringUtils.isEmpty(startRecordAddress) ? "未打卡" : startRecordAddress);
                if (startStatus == 1) {
                    tvStartStatus.setText("正常");
                } else if(startStatus == 2){
                    tvStartStatus.setText("迟到");
                } else if (startStatus == 3) {
                    tvStartStatus.setText("早退");
                } else {
                    tvStartStatus.setText("未打卡");
                }
                tvEndRecordAddress.setText(StringUtils.isEmpty(endRecordAddress) ? "未打卡" : endRecordAddress);
                tvEndRecordTime.setText(StringUtils.isEmpty(endRecordTime) ? "打卡时间:" : "打卡时间:" + endRecordTime);
                if (endStatus == 1) {
                    tvEndStatus.setText("正常");
                } else if(endStatus == 2){
                    tvEndStatus.setText("迟到");
                } else if (endStatus == 3) {
                    tvEndStatus.setText("早退");
                } else {
                    tvEndStatus.setText("未打卡");
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, ApplyMakeRecordActivity.class);
        switch (view.getId()) {
            case R.id.activity_set:

                break;
            case R.id.tv_start_status:
                //申请补上班卡
                String startStatus = tvStartStatus.getText().toString().trim();
                if (startStatus.equals("未打卡")) {
                    intent.putExtra("projectId", projectId);
                    intent.putExtra("applyTime", curentYearMonth + "-" + clickDay);
                    intent.putExtra("type", 1); //上班卡
                    startActivity(intent);
                }
                break;
            case R.id.tv_end_status:
                //申请补下班卡
                String endStatus = tvEndStatus.getText().toString().trim();
                if (endStatus.equals("未打卡")) {
                    intent.putExtra("projectId", projectId);
                    intent.putExtra("applyTime", curentYearMonth + "-" + clickDay);
                    intent.putExtra("type", 2);
                    startActivity(intent);
                }
                break;
        }
    }
}

