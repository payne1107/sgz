package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/6/28.
 * 申请补卡
 */

public class ApplyMakeRecordActivity extends BaseActivity implements View.OnClickListener {

    private String applyTime;
    private int type;
    private int projectId;
    private TextView tvType;
    private TextView tvApplyTime;
    private AutoLinearLayout layoutApplyRecord;
    private EditText etRemark;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_apply_make_record);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("申请补卡", true, true);
        applyTime = getIntent().getStringExtra("applyTime");
        type = getIntent().getIntExtra("type", 0);
        projectId = getIntent().getIntExtra("projectId", 0);

        tvType = findViewById(R.id.tv_type);
        tvApplyTime = findViewById(R.id.tv_apply_time);
        layoutApplyRecord = findViewById(R.id.tv_apply_record);
        etRemark = findViewById(R.id.et_remark);
        layoutApplyRecord.setOnClickListener(this);
        if (type == 1) {
            tvType.setText("上班卡");
        }
        if (type == 2) {
            tvType.setText("下班卡");
        }
        tvApplyTime.setText("" + applyTime);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_apply_record:
                applyRecord();
                break;
        }
    }

    /***
     * 申请补卡
     * type 1是上班卡 2是下班卡
     */
    private void applyRecord() {
        startIOSDialogLoading(ApplyMakeRecordActivity.this,"加载中..");
        String remark =etRemark.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        params.put("type", String.valueOf(type));
        params.put("applytime", applyTime);
        if (!StringUtils.isEmpty(remark)) {
            params.put("remark", remark);
        }
        httpPostRequest(ConfigUtil.POST_MAKE_CARD_APPLY_URL, params, ConfigUtil.POST_MAKE_CARD_APPLY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.POST_MAKE_CARD_APPLY_URL_ACTION:
                if (getRequestCode(json) == 1) {
                    toastMessage("申请成功");
                    finish();
                }
                break;
        }
    }
}
