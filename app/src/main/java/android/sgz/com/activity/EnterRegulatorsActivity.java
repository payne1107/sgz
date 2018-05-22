package android.sgz.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;

import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by WD on 2018/5/21.
 * 输入监管单位
 */

public class EnterRegulatorsActivity extends BaseActivity{
    private AutoLinearLayout layoutConfim;
    private EditText etRegulatorsName;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_regulators);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("监管单位", true, true);
        layoutConfim = (AutoLinearLayout) findViewById(R.id.layout_confirm);
        etRegulatorsName = (EditText) findViewById(R.id.et_regulators);

        layoutConfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regulatorsName = etRegulatorsName.getText().toString().trim();
                if (!StringUtils.isEmpty(regulatorsName)) {
                    Intent intent = new Intent();
                    intent.putExtra(ReleaseOrderActivity.REQUEST_REGULATORS_NAME_KEY, regulatorsName);
                    setResult(RESULT_OK,intent);
                    finish();
                } else {
                    toastMessage("工单名称不能为空");
                }
            }
        });
    }
}
