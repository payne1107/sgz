package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/6/21.
 */

public class ApplyExtraWorkActivity extends BaseActivity implements View.OnClickListener,OnDateSetListener {

    private AutoLinearLayout layoutCommit;
    private TextView tvEndDate;
    private TextView tvStartDate;
    private TimePickerDialog dialogDay1;
    private int setTime = 1;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_apply_extra_work);
        mContext = ApplyExtraWorkActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("申请加班", true, true);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvEndDate = findViewById(R.id.tv_end_date);
        layoutCommit = findViewById(R.id.layout_commit);
        initViewDateDialog(this,System.currentTimeMillis());
        setListener();
    }

    private void setListener() {
        tvStartDate.setOnClickListener(this);
        tvEndDate.setOnClickListener(this);
        layoutCommit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_commit:
                commitExtraWorkApply();
                break;
            case R.id.tv_start_date:
                setTime = 1;
                dialogDay1.show(getSupportFragmentManager(), "all");
                break;
            case R.id.tv_end_date:
                setTime = 2;
                dialogDay1.show(getSupportFragmentManager(), "all");
                break;
        }
    }

    /****
     * 提交加班申请
     */
    private void commitExtraWorkApply() {
        String startDate =tvStartDate.getText().toString().trim();
        String endDate =tvEndDate.getText().toString().trim();
        if (startDate.equals("请选择")) {
            toastMessage("请选择起始日期");
            return;
        }
        if (endDate.equals("请选择")) {
            toastMessage("请选择结束日期");
            return;
        }
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("starttime", startDate);
        params.put("endtime", endDate);
        httpPostRequest(ConfigUtil.POST_APPLY_EXTRAWORK_RECORD_URL, params, ConfigUtil.POST_APPLY_EXTRAWORK_RECORD_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.POST_APPLY_EXTRAWORK_RECORD_URL_ACTION:
                handlePostApplyExtraWork(json);
                break;
        }
    }

    /***
     * 提交加班申请处理
     * @param json
     */
    private void handlePostApplyExtraWork(String json) {
        Log.d("Dong", "加班申请----》" + json);
        if (getRequestCode(json) == 1) {
            toastMessage("申请成功");
            finish();
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date d = new Date(millseconds);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        if (setTime == 1) {
            tvStartDate.setText(sf.format(d));
        } else {
            tvEndDate.setText(sf.format(d));
        }
    }

    /**
     * 初始化时间选择器
     */
    protected void initViewDateDialog(OnDateSetListener listener,long currentMillSeconds) {
        dialogDay1 = new TimePickerDialog.Builder()
                .setCallBack(listener)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - ConfigUtil.TenYears8)
                .setMaxMillseconds(System.currentTimeMillis() +   ConfigUtil.TenYears)
                .setCurrentMillseconds(currentMillSeconds /*System.currentTimeMillis() - ConfigUtil.TenYears*/)//设置当前日期
                .setThemeColor(getResources().getColor(R.color.cccccc))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSize(16)
                .build();
    }
}
