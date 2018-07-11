package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.MyDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/7/11.
 * 清除工资
 */

public class ClearSalaryActivity extends BaseActivity implements View.OnClickListener {

    private int projectId;
    private EditText etDesc;
    private TextView tvClearDayy;
    private TextView tvClearHalf;
    private android.content.Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_clear_salary);
        mContext = ClearSalaryActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("清除工资", true, true);
        projectId = getIntent().getIntExtra("projectId", 0);
        etDesc = findViewById(R.id.et_desc);
        tvClearDayy = findViewById(R.id.tv_clear_day); //整天
        tvClearHalf = findViewById(R.id.tv_clear_half);

        setListener();
    }

    private void setListener() {
        tvClearDayy.setOnClickListener(this);
        tvClearHalf.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_clear_day:
                clearSalaryDialog("all");
                break;
            case R.id.tv_clear_half:
                clearSalaryDialog("half");
                break;
        }
    }

    /***
     * 清除
     * @param type
     */
    private void clearSalaryDialog(final String type) {
        final MyDialog dialog = new MyDialog(mContext);
        dialog.setMessage("是否清除工资?");
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearSalary(projectId,type);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void clearSalary(int projectId, String type) {
        String remark =etDesc.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        if (!StringUtils.isEmpty(remark)) {
            params.put("operaremark", remark);
        }
        params.put("type", type);
        httpPostRequest(ConfigUtil.CLEAR_SALARY_URL,params,ConfigUtil.CLEAR_SALARY_URL_ACTION);

    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.CLEAR_SALARY_URL_ACTION:
                Log.d("Dong", "清除工资"+json);
                if (getRequestCode(json) == 1) {
                    toastMessage("清除成功");
                }
                break;
        }
    }
}
