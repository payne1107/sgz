package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MessageAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.MessageBean;
import android.sgz.com.utils.ConfigUtil;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/8/16.
 */

public class NoticeActivity extends BaseActivity {
    private PullToRefreshListView listView;
    private List<MessageBean.DataBean.ListBean> mList = new ArrayList<>();
    private MessageAdapter adapter;
    private int pageSize;
    private boolean swipeLoadMore = false;
    private int pageNo = 1;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notices);
        mContext = NoticeActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("消息通知", true, true);
        queryPushMessage(pageNo);
        listView = findViewById(R.id.listview);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MessageAdapter(mContext, mList);
        listView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryPushMessage(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryPushMessage(pageNo);
                } else {
                    delayedToast();
                }
            }
        });
    }

    private void delayedToast() {
        Toast.makeText(mContext, "没有更多数据啦", Toast.LENGTH_LONG).show();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
                swipeLoadMore = false;
            }
        }, 1000);
    }

    /****
     * 查询消息推送
     */
    private void queryPushMessage(int pageNo) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        httpPostRequest(ConfigUtil.QUERY_MY_PUSH_MESSAGE_ULR, params, ConfigUtil.QUERY_MY_PUSH_MESSAGE_ULR_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MY_PUSH_MESSAGE_ULR_ACTION:
                hanldeQueryMyPushMessage(json);
                break;
        }
    }

    /***
     * 处理消息推送
     * @param json
     */
    private void hanldeQueryMyPushMessage(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        MessageBean bean = JSON.parseObject(json, MessageBean.class);
        if (bean != null) {
            MessageBean.DataBean data = bean.getData();
            if (data != null) {
                pageSize = data.getCoutpage();
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    mList = data.getList();
                    if (mList != null && mList.size() > 0) {
                        adapter.setData(mList);
                        listView.setVisibility(View.VISIBLE);
                    } else {
                        setEmptyView(listView);
                    }
                }
            }
        }
    }
}
