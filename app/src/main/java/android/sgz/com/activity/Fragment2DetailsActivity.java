package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/7/3.
 * 工单详情---》申请加入工单
 */

public class Fragment2DetailsActivity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutApply;
    private TextView tvTitle;
    private TextView tvHeadMan;
    private TextView tvAddress;
    private TextView tvCategory;
    private TextView tvStartTime;
    private int projectId;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fragment2_details);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工单详情", true, true);
        String name =getIntent().getStringExtra("name");
        String headMan = getIntent().getStringExtra("headMan");
        String address = getIntent().getStringExtra("address");
        String categoryname = getIntent().getStringExtra("categoryname");
        String startTime = getIntent().getStringExtra("startTime");
        projectId = getIntent().getIntExtra("projectId", 0);
        layoutApply = findViewById(R.id.layout_apply);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvHeadMan = (TextView)findViewById(R.id.tv_headman);
        tvAddress = (TextView)findViewById(R.id.tv_address);
        tvCategory = (TextView)findViewById(R.id.tv_category);
        tvStartTime = (TextView)findViewById(R.id.tv_start_date);
        tvTitle.setText("" + name);
        tvHeadMan.setText("" + headMan);
        tvAddress.setText("" + address);
        tvCategory.setText("" + categoryname);
        tvStartTime.setText("" + startTime);
        setListener();
    }

    private void setListener() {
        layoutApply.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_apply:
                //申请加入工单
                applyInOrder(projectId);
                break;
        }
    }

    /****
     * 申请加入工单
     * @param projectId
     */
    private void applyInOrder(int projectId) {
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        httpPostRequest(ConfigUtil.APPLY_IN_PROJECT_ORDER_URL, params, ConfigUtil.APPLY_IN_PROJECT_ORDER_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.APPLY_IN_PROJECT_ORDER_URL_ACTION:
                Log.d("Dong", "申请加入工单");
                if (getRequestCode(json) == 1) {
                    toastMessage("申请成功");
                    finish();
                }
                break;
        }
    }
}
