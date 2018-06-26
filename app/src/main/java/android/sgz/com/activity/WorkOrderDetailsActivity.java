package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.DateAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.DateUtils;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.MyGridView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/5/17.
 * 工单详细
 */

public class WorkOrderDetailsActivity extends BaseActivity implements View.OnClickListener {
    private int[][] days;
    private DateAdapter dateAdapter;
    private int[] dayList =new int[42];
    private AutoLinearLayout layoutDefaultWorkerOrder;
    private MyGridView gridView;
    private TextView tvProjectName;
    private TextView tvStartWorkTime;
    private TextView tvEndWorkTime;
    private TextView tvWorkDays;
    private TextView tvAddWorkTime;
    private int projectId;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_workorder_details);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        int year = DateUtils.getYear();
        final int month = DateUtils.getMonth();
        final int day = DateUtils.getCurrentDayOfMonth();
        setInVisibleTitleIcon(year + "年" + month +"月", true, true);
        projectId = getIntent().getIntExtra("projectId", 0);
        String projectName= getIntent().getStringExtra("projectName");
        String startWorkTime=  getIntent().getStringExtra("startWorkTime");
        String endWorkTime=   getIntent().getStringExtra("endWorkTime");
        String workDays=  getIntent().getStringExtra("workDays");
        String addTime=  getIntent().getStringExtra("addTime");

        gridView = (MyGridView) findViewById(R.id.calendar_gridView);
        layoutDefaultWorkerOrder = findViewById(R.id.layout_default_worker_order);
        tvProjectName = findViewById(R.id.tv_project_name);
        tvStartWorkTime = findViewById(R.id.tv_start_work_time);
        tvEndWorkTime = findViewById(R.id.tv_end_work_time);
        tvWorkDays = findViewById(R.id.tv_work_days);
        tvAddWorkTime = findViewById(R.id.tv_add_work_time);

        days = DateUtils.getDayOfMonthFormat(year, month);
        convertArray();
        dateAdapter = new DateAdapter(this, dayList, year, month,day);//传入当前月的年
        gridView.setAdapter(dateAdapter);
        gridView.setVerticalSpacing(60);
        gridView.setEnabled(true);


        tvProjectName.setText("" + projectName);
        tvAddWorkTime.setText(StringUtils.isEmpty(addTime) ? "0" : addTime);
        tvEndWorkTime.setText(StringUtils.isEmpty(endWorkTime) ? "未结束" : endWorkTime);
        tvStartWorkTime.setText(StringUtils.isEmpty(startWorkTime) ? "未开始" : startWorkTime);
        tvWorkDays.setText(StringUtils.isEmpty(workDays) ? "0" : workDays);
        if (projectId <= 0) {
            layoutDefaultWorkerOrder.setVisibility(View.GONE);
        }
        setListener();
    }

    private void setListener() {
        layoutDefaultWorkerOrder.setOnClickListener(this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int array = (int) parent.getAdapter().getItem(position);
                if (position < 7 && array > 20) {

                } else if (position > 20 && array < 15) {

                } else {
                    int clickDay = (int) parent.getAdapter().getItem(position);
                    dateAdapter.updateTextColor(position);
                    dateAdapter.notifyDataSetChanged();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_default_worker_order:
                //设置默认工单
                setDefaultProject();
                break;
        }
    }

    /****
     * 设置默认工单
     */
    private void setDefaultProject() {
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        httpPostRequest(ConfigUtil.SET_DEFAUTL_PROJECT_ORDER_URL, params, ConfigUtil.SET_DEFAUTL_PROJECT_ORDER_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.SET_DEFAUTL_PROJECT_ORDER_URL_ACTION:
                Log.d("Dong", "设置默认工单——》" + json);
                if (getRequestCode(json) == 1) {
                    toastMessage("设置默认打卡工单成功");
                    finish();
                }
                break;
        }
    }
}
