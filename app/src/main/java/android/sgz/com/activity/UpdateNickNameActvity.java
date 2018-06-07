package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.UpdateNickNameBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/5/9.
 * 修改昵称
 */

public class UpdateNickNameActvity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutConfirm;
    private EditText etNickName;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_update_nick_name);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("姓名", true, true);
        layoutConfirm = (AutoLinearLayout) findViewById(R.id.layout_confirm);
        etNickName = findViewById(R.id.et_nick_name);
        layoutConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_confirm:
                //保存用户名
                String nickName = etNickName.getText().toString().trim();
                updateNickNamePost(nickName);
                break;
        }
    }

    /****
     * 上传用户名
     * @param nickName
     */
    private void updateNickNamePost(String nickName) {
        if (StringUtils.isEmpty(nickName)) {
            toastMessage("昵称不能为空");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("realname", nickName);
        httpPostRequest(ConfigUtil.UPDATE_NICK_NAME_URL, params, ConfigUtil.UPDATE_NICK_NAME_URL_ACTION);
    }


    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.UPDATE_NICK_NAME_URL_ACTION:
                handleUpdateNickName(json);
                break;
        }
    }

    /****
     * 修改昵称
     * @param json
     */
    private void handleUpdateNickName(String json) {
        if (!StringUtils.isEmpty(json)) {
            UpdateNickNameBean nickNameBean = JSON.parseObject(json, UpdateNickNameBean.class);
            if (nickNameBean != null) {
                if (nickNameBean.isSuccess()) {
                    //修改昵称成功
                    toastMessage("修改成功");
                    finish();
                }
            }
        }
    }
}
