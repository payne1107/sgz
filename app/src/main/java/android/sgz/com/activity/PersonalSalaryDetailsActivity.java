package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.DateAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ProjectIncomeDetailBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.DateUtils;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 92457 on 2018/5/26.
 * 个人工资详情页面
 */

public class PersonalSalaryDetailsActivity extends BaseActivity implements View.OnClickListener {
    private int[][] days;
    private DateAdapter dateAdapter;
    private int[] dayList =new int[42];
    private TextView tvWithdraw;
    private Context mContext;
    private int orderId;
    private TextView tvAllowance;
    private TextView tvAddTime;
    private TextView tvWorkDays;
    private TextView tvAddSalary;
    private TextView tvSalary;
    private TextView tvTitle;
    private TextView tvIsHaveSalary;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal_salary_details);
        mContext = PersonalSalaryDetailsActivity.this;
    }

    @Override
    protected void initData() {
        queryProjectIncomeById(orderId);
    }

    @Override
    protected void initView() {
        super.initView();
        orderId = getIntent().getIntExtra("id", 0);
        tvAddSalary = findViewById(R.id.tv_add_salary);
        tvWorkDays = findViewById(R.id.tv_work_days);
        tvAddTime = findViewById(R.id.tv_addtime);
        tvSalary = findViewById(R.id.tv_salary);
        tvAllowance = findViewById(R.id.tv_allowance);
        tvTitle = findViewById(R.id.tv_title);
        tvIsHaveSalary = findViewById(R.id.tv_salary_is_have);

        tvWithdraw = findViewById(R.id.tv_withdraw);
        int year = DateUtils.getYear();
        final int month = DateUtils.getMonth();
        final int day = DateUtils.getCurrentDayOfMonth();
        setInVisibleTitleIcon(year + "年" + month +"月", true, true);
        GridView gridView = findViewById(R.id.calendar_gridView);
        days = DateUtils.getDayOfMonthFormat(year, month);
        convertArray();
        dateAdapter = new DateAdapter(this, dayList, year, month,day);//传入当前月的年
        gridView.setAdapter(dateAdapter);
        gridView.setVerticalSpacing(60);
        gridView.setEnabled(true);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int array = (int) parent.getAdapter().getItem(position);
                if (position < 7 && array > 20) {

                } else if (position > 20 && array < 15) {

                } else {
                    int clickDay = (int) parent.getAdapter().getItem(position);
                    Toast.makeText(PersonalSalaryDetailsActivity.this,"您点击的是---》" + clickDay  +"当前日期---》"  + day +"moth--->" + month,Toast.LENGTH_SHORT).show();
                    dateAdapter.updateTextColor(position);
                    dateAdapter.notifyDataSetChanged();
                }
            }
        });

        tvWithdraw.setOnClickListener(this);
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
            case R.id.tv_withdraw:
                startActivity(new Intent(mContext, WithDrawDespositActivity.class));
                break;
        }
    }

    /***
     * 获取工单的具体详情数据
     */
    private void queryProjectIncomeById(int id) {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        httpPostRequest(ConfigUtil.QUERY_PROJECT_INCOME_BY_PROJECT_ID_URL, params, ConfigUtil.QUERY_PROJECT_INCOME_BY_PROJECT_ID_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        handlerQueryProjectIncomeById(json);
    }

    /****
     * 处理工单详情数据
     * @param json
     */
    private void handlerQueryProjectIncomeById(String json) {
        Log.d("Dong", "工单详情数据---->" + json);
        ProjectIncomeDetailBean bean = JSON.parseObject(json, ProjectIncomeDetailBean.class);
        if (bean != null) {
            ProjectIncomeDetailBean.DataBean data = bean.getData();
            if (data != null) {
                String name = data.getName();
                double salary = data.getSalary();
                String addSalary = data.getAddsalary();
                double allowance = data.getAllowance();
                int workDays = data.getWorkdays();
                String addTime = data.getAddtime();

                if (StringUtils.isEmpty(addSalary)) {
                    addSalary = "0";
                }
                if (StringUtils.isEmpty(addTime)) {
                    addTime = "0";
                }
                tvSalary.setText("" + salary);
                tvWorkDays.setText("" +workDays+"天");
                tvAddSalary.setText("" + addSalary);
                tvAllowance.setText(allowance + "");
                tvAddTime.setText("" + addTime + "小时");
                tvTitle.setText("" + name);

                tvIsHaveSalary.setText("工资有没有发");
            }
        }
    }
}
