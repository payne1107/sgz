package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ChooseProfessionAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ProfessionBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/5/9.
 * 选择职业
 */

public class ChooseProfessionActivity extends BaseActivity implements View.OnClickListener {

    private List<ProfessionBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private ChooseProfessionAdapter adapter;
    private ListView listView;
    private int professionId = 0;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_profession);
        mContext = ChooseProfessionActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("职业", true, true);
        setSettingBtn("保存");
        getProfessionList();
        listView = findViewById(R.id.listView);
        adapter = new ChooseProfessionAdapter(mContext,mList);
        listView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        tvSet.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProfessionBean.DataBean bean = (ProfessionBean.DataBean) parent.getAdapter().getItem(position);
                if (bean != null) {
                    professionId = bean.getId();
                }
                adapter.updateTextColor(position);
            }
        });
    }

    /****
     * 获取所有职业列表
     */
    private void getProfessionList() {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("random", "12");
        httpPostRequest(ConfigUtil.QUERY_ALL_PROFESSION_URL, params, ConfigUtil.QUERY_ALL_PROFESSION_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        handleQueryAllProfession(json);

        switch (action) {
            case ConfigUtil.SAVE_USER_PROFESSION_URL_ACTION:
                handleSaveUserProfessionInfo(json);
                break;
        }
    }

    /****
     * 获取所有职业
     * @param json
     */
    private void handleQueryAllProfession(String json) {
        if (!StringUtils.isEmpty(json)) {
            ProfessionBean professionBean = JSON.parseObject(json, ProfessionBean.class);
            if (professionBean != null) {
                mList = professionBean.getData();
                if (mList != null) {
                    adapter.setData(mList);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_set:
                saveUserPorfessionInfo();
                break;
        }
    }

    /****
     * 保存用户职业
     */
    private void saveUserPorfessionInfo() {
        if (professionId == 0) {
            toastMessage("请选择职称");
            return;
        }
        startIOSDialogLoading(mContext, "保存中..");
        Map<String, String> params = new HashMap<>();
        params.put("professionid", String.valueOf(professionId));
        httpPostRequest(ConfigUtil.SAVE_USER_PROFESSION_URL, params, ConfigUtil.SAVE_USER_PROFESSION_URL_ACTION);
    }

    /***
     * 保存用户职业
     * @param json
     */
    private void handleSaveUserProfessionInfo(String json) {
        Log.d("Dong", "json ====>  " + json);
        int resultCode = getRequestCode(json);
        if (resultCode == 1) {
            toastMessage("保存成功");
            finish();
        }
    }
}
