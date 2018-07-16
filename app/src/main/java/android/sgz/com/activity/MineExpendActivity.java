package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MineExpendAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.MineBalaneBean;
import android.sgz.com.bean.PaymentByProjectBean;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/5/7.
 * 我的财务
 */

public class MineExpendActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvExpendDetails;
    private TextView tvRechargeDetails;
    private TextView tvRecharge;
    private Context mContext;
    private TextView tvBalance;
    private PullToRefreshListView listView;
    private List<PaymentByProjectBean.DataBean.ListBean> mList = new ArrayList();
    private int pageNo =1;
    private int pageSize;
    private boolean swipeLoadMore = false;
    private MineExpendAdapter adapter;


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_expend);
        mContext = MineExpendActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        queryMyBalance();
        queryMinePaymentByProject(pageNo);
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的财务", true, true);
        tvRecharge = findViewById(R.id.tv_recharge);
        tvRechargeDetails = findViewById(R.id.tv_recharge_details);
        tvExpendDetails = findViewById(R.id.tv_expend_details);
        tvBalance = findViewById(R.id.tv_balance);
        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MineExpendAdapter(mContext,mList);
        listView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        tvRecharge.setOnClickListener(this);
        tvRechargeDetails.setOnClickListener(this);
        tvExpendDetails.setOnClickListener(this);

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryMinePaymentByProject(pageNo);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryMinePaymentByProject(pageNo);
                } else {
                    delayedToast();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PaymentByProjectBean.DataBean.ListBean bean = (PaymentByProjectBean.DataBean.ListBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    int prjectId = bean.getProjectid();
                    String address = bean.getAddress();
                    String startTime = bean.getStarttime();
                    String projectName = bean.getProjectname();
                    Intent intent = new Intent(mContext, MineExpendDetails.class);
                    intent.putExtra("projectId", prjectId);
                    intent.putExtra("address", address);
                    intent.putExtra("startTime", startTime);
                    intent.putExtra("projectName", projectName);
                    startActivity(intent);
                }
            }
        });
    }

    private void delayedToast() {
        toastMessage("没有更多数据啦");
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
                swipeLoadMore = false;
            }
        },1000);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_recharge:
                startActivity(new Intent(mContext, ToUpPayActivity.class));
                break;
            case R.id.tv_recharge_details:
                startActivity(new Intent(mContext, ToRechargeDetailsActivity.class));
                break;
            case R.id.tv_expend_details:
                startActivity(new Intent(mContext, ExpendDetailsActivity.class));
                break;
        }
    }

    /***
     * 查询我的账户余额
     */
    private void queryMyBalance() {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("randow", "123");
        httpPostRequest(ConfigUtil.QUERY_MY_BALANCE_URL, params, ConfigUtil.QUERY_MY_BALANCE_URL_ACTION);
    }

    /***
     * 获取我需要支付的和已经支付过的工资列表
     */
    private void queryMinePaymentByProject(int pageNo) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        httpPostRequest(ConfigUtil.QUERY_PAYMENT_BY_PROJECT_ORDER_URL, params, ConfigUtil.QUERY_PAYMENT_BY_PROJECT_ORDER_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MY_BALANCE_URL_ACTION:
                handleQueryMyBalance(json);
                break;
            case ConfigUtil.QUERY_PAYMENT_BY_PROJECT_ORDER_URL_ACTION:
                handleQueryPaymentByProject(json);
                break;
        }
    }

    /****
     * 处理需要我支出的列表
     * @param json
     */
    private void handleQueryPaymentByProject(String json) {
        Log.d("Dong", "需要我支出的——》" +json);
        PaymentByProjectBean bean = JSON.parseObject(json, PaymentByProjectBean.class);
        if (bean != null) {
            PaymentByProjectBean.DataBean data = bean.getData();
            if (data != null) {
                pageSize = data.getCoutpage();
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    mList = data.getList();
                    adapter.setData(mList);
                    listView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /***
     * 处理查询我的账户余额
     * @param json
     */
    private void handleQueryMyBalance(String json) {
        MineBalaneBean bean = JSON.parseObject(json, MineBalaneBean.class);
        if (bean != null) {
            MineBalaneBean.DataBean data = bean.getData();
            if (data != null) {
                double balance = data.getBalance();
                tvBalance.setText("" + balance);
            }
        }
    }
}
