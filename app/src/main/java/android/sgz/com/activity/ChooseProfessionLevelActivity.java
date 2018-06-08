package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ChooseProfessionLevelAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ProfessionLevelBean;
import android.sgz.com.utils.ConfigUtil;
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
 * Created by 92457 on 2018/6/7.
 * 获取职称
 */

public class ChooseProfessionLevelActivity extends BaseActivity implements View.OnClickListener {

    private ChooseProfessionLevelAdapter adapter;
    private List<ProfessionLevelBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private ListView listView;
    private int professionlevelId = 0; //保存职称的id

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
        setSettingBtn("保存");
        queryAllProfessionLevelData();
        listView = findViewById(R.id.listView);
        adapter = new ChooseProfessionLevelAdapter(mContext,mList);
        listView.setAdapter(adapter);

        setListener();
    }

    /***
     * 设置监听器
     */
    private void setListener() {
        tvSet.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ProfessionLevelBean.DataBean bean = (ProfessionLevelBean.DataBean) adapterView.getAdapter().getItem(position);
                if (bean != null) {
                    professionlevelId = bean.getId();
                }
                adapter.updateTextColor(position);
            }
        });
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
        switch (action) {
            case ConfigUtil.SAVE_PROFESSION_LEVEL_URL_ACTION:
                handleUserProfessionLevelInfo(json);
                break;
            case ConfigUtil.QUERY_ALL_PROFESSION_LEVEL_URL_ACTION:
                handlerQueryAllProfessionLevel(json);
                break;
        }
    }

    /***
     * 处理保存用户职称
     * @param json
     */
    private void handleUserProfessionLevelInfo(String json) {
        Log.d("Dong", "保存职称--》" +json);
        int requestCode = getRequestCode(json);
        if (requestCode == 1) {
            toastMessage("保存成功");
            finish();
        }
    }

    /***
     * 查询职称列表
     * @param json
     */
    private void handlerQueryAllProfessionLevel(String json) {
        ProfessionLevelBean levelBean = JSON.parseObject(json, ProfessionLevelBean.class);
        if (levelBean != null) {
            mList = levelBean.getData();
            if (mList != null) {
                adapter.setData(mList);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                //保存用户选择的职称信息
                saveUserProfessionLevelInfo();
                break;
        }
    }

    /****
     * 保存用户职业
     */
    private void saveUserProfessionLevelInfo() {
        if (professionlevelId == 0) {
            toastMessage("请选择职称");
            return;
        }
        startIOSDialogLoading(mContext, "保存中..");
        Map<String, String> params = new HashMap<>();
        params.put("professionlevelid", String.valueOf(professionlevelId));
        httpPostRequest(ConfigUtil.SAVE_PROFESSION_LEVEL_URL, params, ConfigUtil.SAVE_PROFESSION_LEVEL_URL_ACTION);
    }
}
