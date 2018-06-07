package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ChooseProfessionLevelAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ProfessionLevelBean;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 92457 on 2018/6/7.
 * 获取职称
 */

public class ChooseProfessionLevelActivity extends BaseActivity {

    private ChooseProfessionLevelAdapter adapter;
    private List<ProfessionLevelBean.DataBean> mList = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_profession_level);
        mContext = ChooseProfessionLevelActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("职称", true, true);
        queryAllProfessionLevelData();
        ListView listView = findViewById(R.id.listView);
        adapter = new ChooseProfessionLevelAdapter(mContext,mList);
        listView.setAdapter(adapter);
    }

    /****
     * 获取职称信息
     */
    private void queryAllProfessionLevelData() {
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_ALL_PROFESSION_LEVEL_URL, params, ConfigUtil.QUERY_ALL_PROFESSION_LEVEL_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        handlerQueryAllProfessionLevel(json);
    }

    private void handlerQueryAllProfessionLevel(String json) {
        Log.d("Dong", "json --->" +json);
        ProfessionLevelBean levelBean = JSON.parseObject(json, ProfessionLevelBean.class);
        if (levelBean != null) {
            mList = levelBean.getData();
            if (mList != null) {
                adapter.setData(mList);
            }
        }
    }
}
