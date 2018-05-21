package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;

/**
 * Created by 92457 on 2018/5/19.
 * 联系人
 */

public class ContactsActivity extends BaseActivity {

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contacts);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工友信息", true, true);
    }
}
