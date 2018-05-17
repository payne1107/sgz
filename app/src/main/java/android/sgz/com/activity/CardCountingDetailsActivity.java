package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.widget.GridView;

/**
 * Created by 92457 on 2018/5/17.
 * 打卡详情
 */

public class CardCountingDetailsActivity extends BaseActivity {
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
        setInVisibleTitleIcon("......", true, true);
        GridView gridView = (GridView) findViewById(R.id.calendar_gridView);


    }
}
