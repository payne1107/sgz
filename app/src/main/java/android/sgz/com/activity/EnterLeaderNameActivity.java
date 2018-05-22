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
 * Created by 92457 on 2018/5/22.
 * 负责人
 */

public class EnterLeaderNameActivity extends BaseActivity {

    private AutoLinearLayout layoutConfim;
    private EditText etLeader;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_enter_leader_name);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("负责人", true, true);
        layoutConfim = (AutoLinearLayout) findViewById(R.id.layout_confirm);
        etLeader = (EditText) findViewById(R.id.et_leader);

        layoutConfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workOrderName = etLeader.getText().toString().trim();
                if (!StringUtils.isEmpty(workOrderName)) {
                    Intent intent = new Intent();
                    intent.putExtra(ReleaseOrderActivity.REQUEST_LEADER_NAME_KEY, workOrderName);
                    setResult(RESULT_OK,intent);
                    finish();
                } else {
                    toastMessage("工单名称不能为空");
                }
            }
        });
    }
}
