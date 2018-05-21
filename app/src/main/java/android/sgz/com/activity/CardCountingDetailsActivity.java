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
 * Created by 92457 on 2018/5/17.
 * 打卡详情
 */

public class CardCountingDetailsActivity extends BaseActivity {
    private int[][] days;
    private DateAdapter dateAdapter;
    private int[] dayList =new int[42];

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_card_counting_details);
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
                    Toast.makeText(CardCountingDetailsActivity.this,"您点击的是---》" + clickDay  +"当前日期---》"  + day +"moth--->" + month,Toast.LENGTH_SHORT).show();
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
}
