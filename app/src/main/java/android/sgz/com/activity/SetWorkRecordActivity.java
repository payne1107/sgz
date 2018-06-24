package android.sgz.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.view.View;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WD on 2018/6/24.
 * 打卡时间设置
 */

public class SetWorkRecordActivity extends BaseActivity implements View.OnClickListener,OnDateSetListener {

    private TextView tvStartDate;
    private TextView tvEndDate;
    private TimePickerDialog dialogDay2;
    private int setTime = 1; //1开始时间 2 下班时间
    private AutoLinearLayout layoutCommit;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_set_work_record);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("打卡时间设置",true,true);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvEndDate = findViewById(R.id.tv_end_date);
        layoutCommit = findViewById(R.id.layout_commit);

        tvEndDate.setOnClickListener(this);
        tvStartDate.setOnClickListener(this);
        layoutCommit.setOnClickListener(this);
        initViewDateDialog(this,System.currentTimeMillis());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start_date:
                dialogDay2.show(getSupportFragmentManager(), "all");
                setTime = 1;
                break;
            case R.id.tv_end_date:
                dialogDay2.show(getSupportFragmentManager(), "all");
                setTime = 2;
                break;
            case R.id.layout_commit:
                String startDate =tvStartDate.getText().toString().trim();
                String endDate =tvEndDate.getText().toString().trim();
                if (startDate.equals("请选择")) {
                    toastMessage("请选择上班时间");
                    return;
                }
                if (endDate.equals("请选择")) {
                    toastMessage("请选择下班时间");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("startworktime", startDate);
                intent.putExtra("endworktime", endDate);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }


    /**
     * 初始化时间选择器
     */
    protected void initViewDateDialog(OnDateSetListener listener, long currentMillSeconds) {
        dialogDay2 = new TimePickerDialog.Builder()
                .setCallBack(listener)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - ConfigUtil.TenYears8)
                .setMaxMillseconds(System.currentTimeMillis() +   ConfigUtil.TenYears)
                .setCurrentMillseconds(currentMillSeconds /*System.currentTimeMillis() - ConfigUtil.TenYears*/)//设置当前日期
                .setThemeColor(getResources().getColor(R.color.cccccc))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSize(16)
                .build();
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date d = new Date(millseconds);
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        if (setTime == 1) {
            tvStartDate.setText(sf.format(d));
        } else {
            tvEndDate.setText(sf.format(d));
        }
    }
}
