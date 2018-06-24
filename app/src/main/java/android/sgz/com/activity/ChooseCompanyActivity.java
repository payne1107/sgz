package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ChooseCompanyAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ChooseCompanyBean;
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
 * Created by WD on 2018/6/24.
 * 选择所属公司
 */

public class ChooseCompanyActivity extends BaseActivity {

    private ListView listView;
    private Context mContext;
    private List<ChooseCompanyBean.DataBean> mList = new ArrayList();

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_company);
        mContext = ChooseCompanyActivity.this;
    }

    @Override
    protected void initData() {
        queryChooseCompany();
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("选择公司", true, true);
        listView = findViewById(R.id.listView);

        setListener();
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChooseCompanyBean.DataBean bean = (ChooseCompanyBean.DataBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    int merchantid = bean.getMerchantid();
                    String merchantName = bean.getMerchantname();
                    Intent intent = new Intent();
                    intent.putExtra(ReleaseOrderActivity.REQUEST_CHOOSE_COMPANY_KEY, merchantid);
                    intent.putExtra("merchantname", merchantName);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    /****
     * 查询自己所属公司
     */
    private void queryChooseCompany() {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_MY_COMPANY_URL, params, ConfigUtil.QUERY_MY_COMPANY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MY_COMPANY_URL_ACTION:
                Log.d("Dong", "查询所属公司---->" + json);
                ChooseCompanyBean bean = JSON.parseObject(json, ChooseCompanyBean.class);
                if (bean != null) {
                    mList = bean.getData();
                    ChooseCompanyAdapter adapter = new ChooseCompanyAdapter(mContext,mList);
                    listView.setAdapter(adapter);
                }
                break;
        }
    }
}
