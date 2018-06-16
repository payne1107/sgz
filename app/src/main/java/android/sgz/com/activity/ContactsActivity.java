package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ContactsAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ContactsBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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
 * Created by 92457 on 2018/5/19.
 * 联系人
 */

public class ContactsActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private List<ContactsBean.DataBean.ListBean> mList = new ArrayList<>();
    private ContactsAdapter adapter;
    private int pageNo = 1;
    private int pageSize;
    private PullToRefreshListView listView;
    private boolean swipeLoadMore = false;
    private TextView tvSearchContacts;
    private EditText etSearch;
    private String searchName;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contacts);
        mContext = ContactsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工友信息", true, true);
        listView = findViewById(R.id.recycler_view);
        tvSearchContacts = findViewById(R.id.tv_search_contact);
        etSearch = findViewById(R.id.et_search);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new ContactsAdapter(mContext, mList);
        listView.setAdapter(adapter);
        setListener();
        queryContactsList(pageNo, searchName);
    }

    private void setListener() {
//        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(mContext, ReleaseOrderActivity.class);
//                intent.putExtra(ReleaseOrderActivity.REQUEST_CHOOSE_CONTACTS_KEY, mList.get(position));
//                setResult(RESULT_OK,intent);
//                finish();
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactsBean.DataBean.ListBean bean = (ContactsBean.DataBean.ListBean) parent.getAdapter().getItem(position);
                if (bean != null) {
                    int friendId = bean.getId();
                    startActivity(new Intent(mContext, ContactsDetailsActivity.class).putExtra("friendId", friendId));
                }
            }
        });

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryContactsList(pageNo,searchName);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryContactsList(pageNo, searchName);
                } else {
                    delayedToast();
                }
            }
        });

        tvSearchContacts.setOnClickListener(this);
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
    /****
     * 获取好友列表
     */
    private void queryContactsList(int pageNo,String name) {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        if (!StringUtils.isEmpty(name)) {
            params.put("name", name);
        }
        httpPostRequest(ConfigUtil.QUERY_WORK_FRIEDNS_LIST_URL, params, ConfigUtil.QUERY_WORK_FRIEDNS_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_WORK_FRIEDNS_LIST_URL_ACTION:
                handlerQueryContactsList(json);
                break;
        }
    }

    private void handlerQueryContactsList(String json) {
        Log.d("Dong", "json ----------->" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        ContactsBean contactsBean = JSON.parseObject(json, ContactsBean.class);
        if (contactsBean != null) {
            ContactsBean.DataBean data = contactsBean.getData();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search_contact:
                pageNo = 1;
                searchName = etSearch.getText().toString().trim();
                if (StringUtils.isEmpty(searchName)) {
                    toastMessage("请输入联系人姓名");
                    return;
                }
                queryContactsList(pageNo, searchName);
                break;
        }
    }
}
