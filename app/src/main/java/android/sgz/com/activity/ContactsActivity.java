package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ContactsAdapter;
import android.sgz.com.adapter.ReleaseOrderAdapter;
import android.sgz.com.application.MyApplication;
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
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

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
    private String searchName ="";
    private int queryContactId; //等于1 就是发布工单进入好友信息 ，点击好友信息就关闭当前页面
    private AutoLinearLayout layoutShareSMS;
    private AutoLinearLayout layoutShareWxFriend;
    private AutoLinearLayout layoutShareWXMoments;
    private AutoLinearLayout layoutShareQQ;
    private String inviteUrl = "http://www.52sgz.com/share/login.html?referee=" ;
    private int projectId;

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
        queryContactId = getIntent().getIntExtra("query_contacts_info", -1);//入口从哪来
        projectId = getIntent().getIntExtra("projectId", 0);
        listView = findViewById(R.id.recycler_view);
        tvSearchContacts = findViewById(R.id.tv_search_contact);
        etSearch = findViewById(R.id.et_search_contact);
        layoutShareSMS = findViewById(R.id.layout_share_sms);
        layoutShareWxFriend = findViewById(R.id.layout_share_wx_friend);
        layoutShareWXMoments = findViewById(R.id.layout_share_wx_moments);
        layoutShareQQ = findViewById(R.id.layout_share_qq);

        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new ContactsAdapter(mContext, mList);
        listView.setAdapter(adapter);
        setListener();
        queryContactsList(pageNo, searchName);
    }

    private void setListener() {
        layoutShareSMS.setOnClickListener(this);
        layoutShareWxFriend.setOnClickListener(this);
        layoutShareWXMoments.setOnClickListener(this);
        layoutShareQQ.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactsBean.DataBean.ListBean bean = (ContactsBean.DataBean.ListBean) parent.getAdapter().getItem(position);
                if (queryContactId == 1) {
                    if (bean != null) {
                        int userid =bean.getId();
                        if (ReleaseOrderActivity.listCon != null && ReleaseOrderActivity.listCon.size() > 0) {
                            for (int i = 0; i < ReleaseOrderActivity.listCon.size(); i++) {
                                if (ReleaseOrderActivity.listCon.get(i) == userid) {
                                    toastMessage("已经添加过此工人");
                                    return;
                                }
                            }
                        }
                        ReleaseOrderActivity.listCon.add(userid);
                        String allowance = bean.getAllowance();
                        String salary =bean.getAllsalary();
                        String overWorkSalary = bean.getAddsalary();
                        String realName = bean.getRealname();
                        String profession = bean.getProfession();

                        //跳转到发布工单
                        Intent intent = new Intent(mContext, ReleaseOrderActivity.class);
                        intent.putExtra("userid", userid);
                        intent.putExtra("allowance", allowance);
                        intent.putExtra("salary", salary);
                        intent.putExtra("overWorkSalary", overWorkSalary);
                        intent.putExtra("realName", realName);
                        intent.putExtra("profession", profession);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                } else if (queryContactId == 2) {
                    //跳转设置工资页面
                    int userid = bean.getId();
                    startActivity(new Intent(mContext, SetWorkPresonSalaryActivity.class).putExtra("projectId", projectId).putExtra("userId", userid).putExtra("update_person_salary", 1));
                    finish();
                } else {
                    //跳转到工友详情页面
                    if (bean != null) {
                        int friendId = bean.getId();
                        String photoUrl = bean.getPhoto();
                        String realName = bean.getRealname();
                        startActivity(new Intent(mContext, ContactsDetailsActivity.class).putExtra("friendId", friendId));

                    }
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
        Log.d("Dong", "获取好友列表 ----------->" + json);
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
            } else {
                mList.clear();
                adapter.setData(mList);
                setEmptyView(listView);
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
            case R.id.layout_share_sms:
                sendSms(mContext,inviteUrl + MyApplication.userPhone);
                break;
            case R.id.layout_share_qq:
                shareAction(SHARE_MEDIA.QQ,mContext,inviteUrl,MyApplication.userPhone);
                break;
            case R.id.layout_share_wx_friend:
                shareAction(SHARE_MEDIA.WEIXIN,mContext,inviteUrl,MyApplication.userPhone);
                break;
            case R.id.layout_share_wx_moments:
                shareAction(SHARE_MEDIA.WEIXIN_CIRCLE,mContext,inviteUrl,MyApplication.userPhone);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 短信分享
     * @param mContext
     * @param smstext 短信分享内容
     * @return
     */
    public static Boolean sendSms(Context mContext, String smstext) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        mIntent.putExtra("sms_body", smstext);
        mContext.startActivity(mIntent);
        return null;
    }
}
