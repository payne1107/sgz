package android.sgz.com.activity;

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
 * Created by WD on 2018/6/21.
 * 审批加班详情
 */

public class ApproveExtroWorkDetailsActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvAgree;
    private TextView tvNoAgree;
    private TextView tvProjectName;
    private TextView tvStartDate;
    private TextView tvEndDate;
    private TextView tvName;
    private TextView tvStatus;
    private int approveid;
    private int status = 0;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_approve_extro_work_details);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("审批加班", true, true);
        String workName = getIntent().getStringExtra("workname");
        String startTime =getIntent().getStringExtra("starttime");
        String endTime = getIntent().getStringExtra("endtime");
        String projectName = getIntent().getStringExtra("projectname");
        int status = getIntent().getIntExtra("status", -1);
        approveid = getIntent().getIntExtra("approveid", -1);

        tvAgree = findViewById(R.id.tv_agree);
        tvNoAgree = findViewById(R.id.tv_no_agree);
        tvProjectName = findViewById(R.id.tv_project_name);
        tvStartDate =findViewById(R.id.tv_start_date);
        tvEndDate =findViewById(R.id.tv_end_date);
        tvName = findViewById(R.id.tv_name);
        tvStatus = findViewById(R.id.tv_status);

        tvProjectName.setText("" + projectName);
        tvStartDate.setText("" + startTime);
        tvEndDate.setText("" + endTime);
        tvName.setText("" + workName + "的加班");
        if (status == 0) {
            tvStatus.setText("不通过");
            tvNoAgree.setText("已拒绝");
            tvNoAgree.setEnabled(false);
            tvNoAgree.setBackgroundColor(getResources().getColor(R.color.mask_color));
            tvAgree.setEnabled(false);
            tvAgree.setBackgroundColor(getResources().getColor(R.color.mask_color));
        } else if (status == 1) {
            tvStatus.setText("已通过");
            tvAgree.setText("已审批");
            tvAgree.setEnabled(false);
            tvAgree.setBackgroundColor(getResources().getColor(R.color.mask_color));
            tvNoAgree.setEnabled(false);
            tvNoAgree.setBackgroundColor(getResources().getColor(R.color.mask_color));
        } else {
            tvStatus.setText("审核中");
        }
        setListener();
    }

    private void setListener() {
        tvAgree.setOnClickListener(this);
        tvNoAgree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_agree:
                status = 1;
                approveExtraWork(status);
                break;
            case R.id.tv_no_agree:
                status = 0;
                approveExtraWork(status);
                break;
        }
    }

    /***
     * 审批加班
     */
    private void approveExtraWork(int status) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(approveid));
        params.put("status", String.valueOf(status));
        httpPostRequest(ConfigUtil.APPROVE_EXTRA_WORK_URL, params, ConfigUtil.APPROVE_EXTRA_WORK_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.APPROVE_EXTRA_WORK_URL_ACTION:
                handleApproveExtraWork(json);
                break;
        }
    }

    /****
     * 处理审批加班
     * @param json
     */
    private void handleApproveExtraWork(String json) {
        Log.d("Dong", "审批加班---->" + json);
        if (getRequestCode(json) == 1) {
            toastMessage("审核成功");
            finish();
        }
    }
}
