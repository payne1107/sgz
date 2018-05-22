package android.sgz.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;

import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by WD on 2018/5/21.
 * 输入工单名称
 */

public class EnterWorkOrderNameActivity extends BaseActivity {

    private AutoLinearLayout layoutConfim;
    private EditText etWorkOrderName;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_enter_work_order_name);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工单名称", true, true);
        layoutConfim = (AutoLinearLayout) findViewById(R.id.layout_confirm);
        etWorkOrderName = (EditText) findViewById(R.id.et_work_order_name);

        layoutConfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workOrderName = etWorkOrderName.getText().toString().trim();
                if (!StringUtils.isEmpty(workOrderName)) {
                    Intent intent = new Intent();
                    intent.putExtra(ReleaseOrderActivity.REQUEST_WORK_ORDER_NAME_KEY, workOrderName);
                    setResult(RESULT_OK,intent);
                    finish();
                } else {
                    toastMessage("工单名称不能为空");
                }
            }
        });
    }
}
