package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/***
 * 修改工单
 */
public class MotifyOrderNameActivity extends BaseActivity implements View.OnClickListener {

    private EditText etName;
    private TextView tvSave;
    private int projectId;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_motify_order_name);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("修改工单名称", true, true);
        projectId = getIntent().getIntExtra("projectId", 0);
        etName = findViewById(R.id.et_name);
        tvSave = findViewById(R.id.tv_save);
        tvSave.setOnClickListener(this);
    }

    /***
     * 修改工单名称
     */
    private void motifyOrderName() {
        String name = etName.getText().toString().trim();
        if (StringUtils.isEmpty(name)) {
            toastMessage("请输入工单名称");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        params.put("name", name);
        httpPostRequest(ConfigUtil.MOTIFY_PROJECT_NAME_URL, params, ConfigUtil.MOTIFY_PROJECT_NAME_URL_ACTION);
    }


    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.MOTIFY_PROJECT_NAME_URL_ACTION:
                if (getRequestCode(json) == 1) {
                    toastMessage("修改成功");
                    finish();
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                motifyOrderName();
                break;
        }
    }
}
