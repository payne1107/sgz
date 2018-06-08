package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;

/**
 * Created by WD on 2018/6/8.
 */

public class ChooseCityActivity extends BaseActivity {
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_city);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("常驻城市", true, true);
    }
}
