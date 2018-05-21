package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;

/**
 * Created by 92457 on 2018/5/19.
 */

public class ReleasePicActivity extends BaseActivity {

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_release_pic);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("发表图片", true, true);
    }
}
