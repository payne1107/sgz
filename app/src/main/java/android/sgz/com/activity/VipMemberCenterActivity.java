package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.VipMemberListAdapter;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.VIPMemberCenterBasicInfoBean;
import android.sgz.com.bean.VipMemberListBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.CircleImageView;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.sgz.com.widget.SpacesItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/5/6.
 */

public class VipMemberCenterActivity extends BaseActivity {
    private Context mContext;
    private TextView tvUserName;
    private TextView tvProfession;
    private TextView tvIsVip;
    private CircleImageView circleImageView;
    private RecyclerView recyclerView;
    private List<VipMemberListBean.DataBean> mList = new ArrayList();
    private VipMemberListAdapter adapter;


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vip_member_center);
        mContext = VipMemberCenterActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("VIP会员", true, true);
        setSettingBtn("vip排名提升");
        tvUserName = findViewById(R.id.tv_username);
        tvProfession = findViewById(R.id.tv_profession);
        tvIsVip = findViewById(R.id.tv_is_vip);
        circleImageView = findViewById(R.id.circleImageView);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        recyclerView.setLayoutManager(layoutManager);
        adapter = new VipMemberListAdapter(mContext, mList);
        recyclerView.setAdapter(adapter);

        queryVipBasicInfo();
        queryVipListInfo();

        setListener();
    }

    private void setListener() {
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                toastMessage("开通----》" + mList.get(position).getMonth());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }

    /***
     * 查询vip会员基本信息
     */
    private void queryVipBasicInfo() {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_VIP_BASIC_INFO_URL, params, ConfigUtil.QUERY_VIP_BASIC_INFO_URL_ACTION);
    }

    /****
     * 查询vip套餐列表
     */
    private void queryVipListInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_VIP_LIST_URL, params, ConfigUtil.QUERY_VIP_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_VIP_BASIC_INFO_URL_ACTION:
                handlerQueryVipBasicInfo(json);
                break;
            case ConfigUtil.QUERY_VIP_LIST_URL_ACTION:
                handlerQueryVipList(json);
                break;
        }
    }

    /****
     * 处理vip套餐列表
     * @param json
     */
    private void handlerQueryVipList(String json) {
        Log.d("Dong", "套餐列表===" + json);
        VipMemberListBean bean = JSON.parseObject(json, VipMemberListBean.class);
        if (bean != null) {
            mList = bean.getData();
            adapter.setData(mList);
        }
    }

    /***
     * 基本信息获取处理
     * @param json
     */
    private void handlerQueryVipBasicInfo(String json) {
        Log.d("Dong", "vip基本信息---->" + json);
        VIPMemberCenterBasicInfoBean basicInfoBean = JSON.parseObject(json, VIPMemberCenterBasicInfoBean.class);
        if (basicInfoBean != null) {
            VIPMemberCenterBasicInfoBean.DataBean data = basicInfoBean.getData();
            if (data != null) {
                String realName = data.getRealname();//姓名
                String photoUrl = data.getPhoto(); //头像
                String profession = data.getProfession(); //职业
                String isVip = data.getVipuserid();//是否是vip 不为空就是vip

                if (!StringUtils.isEmpty(realName)) {
                    tvUserName.setText(realName);
                }

                if (!StringUtils.isEmpty(photoUrl)) {
                    MyApplication.imageLoader.displayImage(photoUrl, circleImageView);
                }
                if (!StringUtils.isEmpty(profession)) {
                    tvProfession.setText(profession);
                }

                if (!StringUtils.isEmpty(isVip)) {
                    tvIsVip.setText("VIP会员");
                } else {
                    tvIsVip.setText("开通VIP");
                }
            }
        }
    }
}
