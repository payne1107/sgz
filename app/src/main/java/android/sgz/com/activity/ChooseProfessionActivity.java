package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ChooseProfessionAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ProfessionBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.sgz.com.widget.SpacesItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/5/9.
 * 选择职业
 */

public class ChooseProfessionActivity extends BaseActivity{

    private RecyclerView recyclerView;
    private List<ProfessionBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private ChooseProfessionAdapter adapter;

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
        getProfessionList();

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        adapter = new ChooseProfessionAdapter(mContext,mList);
        recyclerView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String professionName = mList.get(position).getProfession();
                Intent data = new Intent(mContext,PersonDetailsActivity.class);
                data.putExtra(PersonDetailsActivity.CHOOSE_PROFESSION_KEY, professionName);
                setResult(RESULT_OK, data);
                finish();
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
    }

    /****
     * 获取所有职业
     * @param json
     */
    private void handleQueryAllProfession(String json) {
        if (!StringUtils.isEmpty(json)) {
            ProfessionBean professionBean = JSON.parseObject(json, ProfessionBean.class);
            if (professionBean != null) {
                List<ProfessionBean.DataBean> data = professionBean.getData();
                if (data != null) {
                    adapter.setData(data);
                }
            }
        }
    }
}
