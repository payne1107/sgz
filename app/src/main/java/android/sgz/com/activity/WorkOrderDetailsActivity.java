package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.DateAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by WD on 2018/5/17.
 * 工单详细
 */

public class WorkOrderDetailsActivity extends BaseActivity{
    private int[][] days;
    private DateAdapter dateAdapter;

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
        GridView gridView = (GridView) findViewById(R.id.calendar_gridView);

        days = DateUtils.getDayOfMonthFormat(year, month);
        dateAdapter = new DateAdapter(this, days, year, month,day);//传入当前月的年
        gridView.setAdapter(dateAdapter);
        gridView.setVerticalSpacing(60);
        gridView.setEnabled(true);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int clickDay = (int) parent.getAdapter().getItem(position);
                Toast.makeText(WorkOrderDetailsActivity.this,"您点击的是---》" + clickDay  +"当前日期---》"  + day +"moth--->" + month,Toast.LENGTH_SHORT).show();
                dateAdapter.updateTextColor(position);
                dateAdapter.notifyDataSetChanged();
            }
        });
    }
}
