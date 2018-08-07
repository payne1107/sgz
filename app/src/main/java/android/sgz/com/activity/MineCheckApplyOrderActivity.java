package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/8/5.
 * 审核加入工单
 */

public class MineCheckApplyOrderActivity extends BaseActivity implements View.OnClickListener {

    private int projectId;
    private int status;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_check_apply_ordere);
        mContext = MineCheckApplyOrderActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("审核工单", true, true);
        projectId = getIntent().getIntExtra("projectId", 0);
        String projectName = getIntent().getStringExtra("projectName");
        String address = getIntent().getStringExtra("address");
        String startWorkTime = getIntent().getStringExtra("startWorkTime");
        String endWorkTime = getIntent().getStringExtra("endWorkTime");
        String realName = getIntent().getStringExtra("realName");

        TextView tvProjectName =findViewById(R.id.tv_project_name);
        TextView tvUserName =findViewById(R.id.tv_username);
        TextView tvStartWorkNaem =findViewById(R.id.tv_start_work_time);
        TextView tvEndWorkTime =findViewById(R.id.tv_end_work_time);
        TextView tvAgress =findViewById(R.id.tv_agree);
        TextView tvNoAgree = findViewById(R.id.tv_no_agree);
        TextView tvAddress =findViewById(R.id.tv_address);

        tvProjectName.setText(StringUtils.isEmpty(projectName) ? "" : projectName);
        tvUserName.setText(StringUtils.isEmpty(realName) ? "" : realName);
        tvAddress.setText(StringUtils.isEmpty(address) ? "" : address);
        tvStartWorkNaem.setText(StringUtils.isEmpty(startWorkTime) ? "" : startWorkTime);
        tvEndWorkTime.setText(StringUtils.isEmpty(endWorkTime) ? "" : endWorkTime);

        tvNoAgree.setOnClickListener(this);
        tvAgress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_no_agree:
                status = 0;
                applyInOrder(projectId,status);
                break;
            case R.id.tv_agree:
                status = 1;
               // applyInOrder(projectId, status);
                startActivity(new Intent(mContext, SetWorkPresonSalaryActivity.class).putExtra("update_person_salary", 3).putExtra("projectId", projectId));
                finish();
                break;
        }
    }

    /****
     *  审核加入工单
     */
    private void applyInOrder(int projectid,int status) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(projectid));
        params.put("status", String.valueOf(status));
        params.put("replay", "");
        httpPostRequest(ConfigUtil.CHECK_IN_PROJECT_ORDER_APPLY_URL, params, ConfigUtil.CHECK_IN_PROJECT_ORDER_APPLY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.CHECK_IN_PROJECT_ORDER_APPLY_URL_ACTION:
                if (getRequestCode(json) == 1) {
                    toastMessage("审核成功");
                    finish();
                }
                break;
        }
    }
}
