package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ChoosePorjectCategoryAdapter;
import android.sgz.com.adapter.ChooseProfessionLevelAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ProjectCategoryBean;
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
 * Created by WD on 2018/7/4.
 * 获取工程类别
 */

public class SetProjectCategoryActivity extends BaseActivity {

    private ListView listView;
    private Context mContext;
    private List<ProjectCategoryBean.DataBean> mList = new ArrayList<>();
    private ChoosePorjectCategoryAdapter adapter;


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_project_category);
        mContext = SetProjectCategoryActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("选择工程类别", true, true);
        listView = findViewById(R.id.listView);
        adapter = new ChoosePorjectCategoryAdapter(mContext,mList);
        listView.setAdapter(adapter);
        queryProjectCategory();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProjectCategoryBean.DataBean bean = (ProjectCategoryBean.DataBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    int categoryid = bean.getId();
                    String categoryName = bean.getName();
                    Intent intent = new Intent();
                    intent.putExtra("categoryid", categoryid);
                    intent.putExtra("categoryName", categoryName);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    /****
     * 获取工程类别
     */
    private void queryProjectCategory() {
        Map<String, String> params = new HashMap<>();
        params.put("random", "12");
        httpPostRequest(ConfigUtil.QUERY_PROJECT_CATEGORY_URL, params, ConfigUtil.QUERY_PROJECT_CATEGORY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_PROJECT_CATEGORY_URL_ACTION:
                Log.d("Dong", "json ---_>" + json);
                handleQueryProjectCategory(json);
                break;
        }
    }

    private void handleQueryProjectCategory(String json) {
        ProjectCategoryBean bean = JSON.parseObject(json, ProjectCategoryBean.class);
        if (bean != null) {
            mList = bean.getData();
            if (mList != null) {
                adapter.setData(mList);
            }
        }
    }
}
