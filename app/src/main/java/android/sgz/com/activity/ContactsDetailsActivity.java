package android.sgz.com.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ContactsDetailsBean;
import android.sgz.com.utils.ConfigUtil;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 92457 on 2018/6/16.
 * 工友详情信息
 */

public class ContactsDetailsActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private int friendId;
    private TextView tvName;
    private TextView tvProfession;
    private TextView tvPhone;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contact_details);
    }

    @Override
    protected void initData() {
        queryContactsDetails();
    }

    @Override
    protected void initView() {
        super.initView();
        friendId = getIntent().getIntExtra("friendId", 0);
        //设置沉浸式状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        RadioGroup rgType = findViewById(R.id.rg_type);
        tvName = findViewById(R.id.tv_name);
        tvProfession = findViewById(R.id.tv_profession);
        tvPhone = findViewById(R.id.tv_phone);

        rgType.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_friend_info:
                //资料

                break;
            case R.id.rb_firend_state:
                //动态
                break;
        }
    }

    /****
     * 查询好友资料
     */
    private void queryContactsDetails() {
        Map<String, String> params = new HashMap<>();
        params.put("friendsid", String.valueOf(friendId));
        params.put("page", "1");
        httpPostRequest(ConfigUtil.QUERY_WORK_FRIEDNS_LIST_URL, params, ConfigUtil.QUERY_WORK_FRIEDNS_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_WORK_FRIEDNS_LIST_URL_ACTION:
                Log.d("Dong", "好友详情 --->" + json);
                handlerQueryContactsDetails(json);
                break;
        }
    }

    private void handlerQueryContactsDetails(String json) {
        ContactsDetailsBean bean = JSON.parseObject(json, ContactsDetailsBean.class);
        if (bean != null) {
            ContactsDetailsBean.DataBean data = bean.getData();
            if (data != null) {
                String mobile = data.getMobile();
                String profession =data.getProfession();
                String realName = data.getRealname();
                tvName.setText("" + realName);
                tvPhone.setText("" + mobile);
                tvProfession.setText("" + profession);
            }
        }
    }
}
