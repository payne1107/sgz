package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/6/27.
 * 审核异常列表
 */

public class CheckWaringWorkRecordActivity extends BaseActivity implements View.OnClickListener {

    private int id;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_check_waring_work_record);
        mContext = CheckWaringWorkRecordActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("补卡审核", true, true);

        String projectName =getIntent().getStringExtra("projectName");
        String address =getIntent().getStringExtra("address");
        String startWorkTime =getIntent().getStringExtra("startWorkTime");
        String endWorkTime =getIntent().getStringExtra("endWorkTime");
        String remark = getIntent().getStringExtra("remark");
        String userName = getIntent().getStringExtra("userName");
        int status =getIntent().getIntExtra("status", 0);
        id = getIntent().getIntExtra("id", 0);

        TextView tvProjectName =findViewById(R.id.tv_project_name);
        TextView tvUserName =findViewById(R.id.tv_username);
        TextView tvStartWorkNaem =findViewById(R.id.tv_start_work_time);
        TextView tvEndWorkTime =findViewById(R.id.tv_end_work_time);
        TextView tvRemark = findViewById(R.id.tv_remark);
        TextView tvAgress =findViewById(R.id.tv_agree);
        TextView tvNoAgree = findViewById(R.id.tv_no_agree);
        TextView tvAddress =findViewById(R.id.tv_address);

        tvProjectName.setText("" + projectName);
        tvUserName.setText("" + userName);
        tvAddress.setText("" + address);
        tvStartWorkNaem.setText("" + startWorkTime);
        tvEndWorkTime.setText("" + endWorkTime);
        tvRemark.setText("" + remark);

        tvNoAgree.setOnClickListener(this);
        tvAgress.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_agree:
                checkWaringWorkRecord(id, 1);
                break;
            case R.id.tv_no_agree:
                checkWaringWorkRecord(id,0);
                break;
        }
    }


    /***
     * 审核补卡信息
     */
    private void checkWaringWorkRecord(int id,int status) {
        startIOSDialogLoading(mContext, "加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        params.put("status", String.valueOf(status));
        httpPostRequest(ConfigUtil.CHECK_MAKE_CARD_APPLY_URL, params, ConfigUtil.CHECK_MAKE_CARD_APPLY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.CHECK_MAKE_CARD_APPLY_URL_ACTION:
                Log.d("Dong", "---->" + json);
                if (getRequestCode(json) == 1) {
                    toastMessage("审核成功");
                    finish();
                }
                break;
        }
    }
}
