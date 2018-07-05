package android.sgz.com.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ContactDynamicAdapter;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ContactDynamicBean;
import android.sgz.com.bean.ContactsDetailsBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.widget.CircleImageView;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 92457 on 2018/6/16.
 * 工友详情信息
 */

public class ContactsDetailsActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private int friendId;
    private TextView tvName;
    private TextView tvProfession;
    private TextView tvPhone;
    private AutoLinearLayout layoutPersonInfo;
    private AutoLinearLayout layoutFriendState;
    private List<ContactDynamicBean.DataBean.ListBean> mList = new ArrayList<>();
    private PullToRefreshListView listView;
    private Context mContext;
    private int pageSize;
    private boolean swipeLoadMore = false;
    private ContactDynamicAdapter adapter;
    private RadioGroup rgType;
    private int pageNo = 1;
    private CircleImageView circleImageView;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contact_details);
        mContext = ContactsDetailsActivity.this;
    }

    @Override
    protected void initData() {
        queryContactsDetails();
        queryContactState(pageNo);
    }

    @Override
    protected void initView() {
        super.initView();
        friendId = getIntent().getIntExtra("friendId", 0);
        //设置沉浸式状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        rgType = findViewById(R.id.rg_type);
        tvName = findViewById(R.id.tv_name);
        tvProfession = findViewById(R.id.tv_profession);
        tvPhone = findViewById(R.id.tv_phone);
        layoutPersonInfo = findViewById(R.id.layout_person_info);
        layoutFriendState =findViewById(R.id.layout_friend_state);
        circleImageView = findViewById(R.id.iv_avatar);
        listView = findViewById(R.id.gridView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new ContactDynamicAdapter(mContext, mList);
        listView.setAdapter(adapter);

        setListener();

    }

    private void setListener() {
        rgType.setOnCheckedChangeListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryContactState(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryContactState(pageNo);
                } else {
                    delayedToast();
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
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_friend_info:
                //资料
                layoutFriendState.setVisibility(View.GONE);
                layoutPersonInfo.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_firend_state:
                //动态
                layoutFriendState.setVisibility(View.VISIBLE);
                layoutPersonInfo.setVisibility(View.GONE);
                break;
        }
    }

    /****
     * 获取好友动态
     */
    private void queryContactState(int pageNo) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", String.valueOf(friendId));
        params.put("page", String.valueOf(pageNo));
        httpPostRequest(ConfigUtil.QUERY_FRIEND_DYNAMIC_URL, params, ConfigUtil.QUERY_FRIEND_DYNAMIC_URL_ACTION);
    }

    /****
     * 查询好友资料
     */
    private void queryContactsDetails() {
        Map<String, String> params = new HashMap<>();
        params.put("friendsid", String.valueOf(friendId));
        params.put("page", "1");
        httpPostRequest(ConfigUtil.QUERY_WORK_FRIEDNS_LIST_URL, params, ConfigUtil.QUERY_WORK_FRIEDNS_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_WORK_FRIEDNS_LIST_URL_ACTION:
                Log.d("Dong", "好友详情 --->" + json);
                handlerQueryContactsDetails(json);
                break;
            case ConfigUtil.QUERY_FRIEND_DYNAMIC_URL_ACTION:
                Log.d("Dong", "获取好友动态---》" +json);
                handleQueryFriendDynamic(json);
                break;
        }
    }

    /***
     * 好友动态
     * @param json
     */
    private void handleQueryFriendDynamic(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        ContactDynamicBean dynamicBean = JSON.parseObject(json, ContactDynamicBean.class);
        if (dynamicBean != null) {
            ContactDynamicBean.DataBean data = dynamicBean.getData();
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

    private void handlerQueryContactsDetails(String json) {
        ContactsDetailsBean bean = JSON.parseObject(json, ContactsDetailsBean.class);
        if (bean != null) {
            ContactsDetailsBean.DataBean data = bean.getData();
            if (data != null) {
                String mobile = data.getMobile();
                String profession =data.getProfession();
                String realName = data.getRealname();
                String photoUrl = data.getPhoto();
                tvName.setText("" + realName);
                tvPhone.setText("" + mobile);
                tvProfession.setText("" + profession);
                MyApplication.imageLoader.displayImage(photoUrl, circleImageView);
            }
        }
    }
}
