package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 92457 on 2018/5/23.
 * 个性签名
 */

public class PersonalizedSignatureActivity extends BaseActivity implements View.OnClickListener {

    private EditText etPersonalizedSignature;
    private AutoLinearLayout layoutConfirm;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personalized_signature);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("个性签名", true, true);
        etPersonalizedSignature = findViewById(R.id.et_personalized_signature);
        layoutConfirm = findViewById(R.id.layout_confirm);

        setListener();
    }

    private void setListener() {
        layoutConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_confirm:
                savePersonalizedSignature();
                break;
        }
    }

    /****
     * 保存个性签名
     */
    private void savePersonalizedSignature() {
        String signture = etPersonalizedSignature.getText().toString().trim();
        if (StringUtils.isEmpty(signture)) {
            toastMessage("签名不能为空");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("sign", signture);
        httpPostRequest(ConfigUtil.SAVE_PERSONAILZED_SIGNATURE_URL, params, ConfigUtil.SAVE_PERSONAILZED_SIGNATURE_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        if (getRequestCode(json) == 1) {
            toastMessage("签名设置成功");
            finish();
        }
    }
}
