package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.BankCradlistInfoAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.BindBankCardInfoBean;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by WD on 2018/6/14.
 */

public class BankCardListInfoActivity extends BaseActivity {
    private Context mContext;
    private List<BindBankCardInfoBean.DataBean> mList;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_bank_card_list_info);
        mContext = BankCardListInfoActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的银行卡", true, true);
        setSettingBtn("添加银行");
        String str = getIntent().getStringExtra("strBankListInfo");
        ListView listView = findViewById(R.id.listView);
        BindBankCardInfoBean bean = JSON.parseObject(str, BindBankCardInfoBean.class);
        if (bean != null) {
            mList = bean.getData();
            BankCradlistInfoAdapter adapter = new BankCradlistInfoAdapter(mContext, mList);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BindBankCardInfoBean.DataBean dataBean = (BindBankCardInfoBean.DataBean) adapterView.getAdapter().getItem(i);
                if (dataBean != null) {
                    String banCard = dataBean.getBankcard();
                    int bankId = dataBean.getId();
                    String subBankName = dataBean.getSubbankname();
                    Intent intent = new Intent();
                    intent.putExtra("bankcard_number", banCard);
                    intent.putExtra("bankId", bankId);
                    intent.putExtra("subBankName", subBankName);
                    setResult(WithDrawDespositActivity.REQUEST_BANK_CARD_INFO_CODE, intent);
                    finish();
                }
            }
        });
    }
}
