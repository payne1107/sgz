package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.BankInfoAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.BankListInfoBean;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/6/12.
 * 银行列表选择
 */

public class BankInfoActivity extends BaseActivity {

    private ListView listView;
    private Context mContext;
    private List<BankListInfoBean.DataBean>  mList = new ArrayList<>();
    private BankInfoAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_bank_info);
        mContext = BankInfoActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("银行信息", true, true);
        listView = findViewById(R.id.listView);
        queryBankInfo();
        adapter = new BankInfoAdapter(mContext, mList);
        listView.setAdapter(adapter);
    }

    /***
     * 查询银行信息
     */
    private void queryBankInfo() {
        startIOSDialogLoading(mContext, "查询中..");
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_BANK_INFO_LIST_URL, params, ConfigUtil.QUERY_BANK_INFO_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        Log.d("Dong", "银行信息----》" +json);
        BankListInfoBean bean = JSON.parseObject(json, BankListInfoBean.class);
        if (bean != null) {
            mList = bean.getData();
            adapter.setData(mList);
        }
    }
}
