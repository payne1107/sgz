package android.sgz.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.activity.CardCountingActivity;
import android.sgz.com.activity.ExtraWrokActivity;
import android.sgz.com.activity.MineApplyOrderActivity;
import android.sgz.com.activity.MineExpendActivity;
import android.sgz.com.activity.MineHomePageActivity;
import android.sgz.com.activity.MineSalaryActivity;
import android.sgz.com.activity.MineWaringManagerActivity;
import android.sgz.com.activity.PersonDetailsActivity;
import android.sgz.com.activity.SettingActivity;
import android.sgz.com.activity.VipMemberCenterActivity;
import android.sgz.com.activity.WorkOrderActivity;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.VIPMemberCenterBasicInfoBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.CircleImageView;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;

/**
 * Created by 92457 on 2018/4/16.
 */

public class Fragment4 extends BaseFragment implements View.OnClickListener {

    private AutoLinearLayout layoutPersonDetails;
    private AutoLinearLayout layoutVipMember;
    private AutoLinearLayout layoutMineSalary;
    private AutoLinearLayout layoutExpend;
    private AutoLinearLayout layoutCardCounting;
    private AutoLinearLayout layoutWorkOrderManager;
    private AutoLinearLayout layoutExtraWork;
    private AutoLinearLayout layoutSetting;
    private AutoLinearLayout layoutMineHomePage;
    private CircleImageView ivAvatar;
    private String personBasicJson = ""; //个人中心基本资料json
    private AutoLinearLayout layoutWaringManager;
    private TextView tvUserName;
    private TextView tvWorkCategory;
    private AutoLinearLayout layoutApplyOrder;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment4, null);
        }
        //缓存的rootView需要判断是否已经被加过parent,如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setInVisibleTitleIcon("我的", true, false);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getBasicInfo();
    }

    /****
     * 初始化View
     */
    private void initView() {
        layoutPersonDetails = (AutoLinearLayout) mRootView.findViewById(R.id.layout_person_details);
        layoutVipMember = (AutoLinearLayout) mRootView.findViewById(R.id.layout_vip_member);
        layoutMineSalary = (AutoLinearLayout) mRootView.findViewById(R.id.layout_mine_salary);
        layoutExpend = (AutoLinearLayout) mRootView.findViewById(R.id.layout_expend);
        layoutCardCounting = (AutoLinearLayout) mRootView.findViewById(R.id.layout_card_counting);
        layoutWorkOrderManager = (AutoLinearLayout) mRootView.findViewById(R.id.layout_workorder_manager);
        layoutExtraWork = mRootView.findViewById(R.id.layout_extra_work);
        layoutSetting = mRootView.findViewById(R.id.layout_setting);
        layoutMineHomePage = mRootView.findViewById(R.id.layout_mine_homepage);
        ivAvatar = mRootView.findViewById(R.id.circleImageView);
        layoutWaringManager = mRootView.findViewById(R.id.layout_waring_manage);
        tvUserName = mRootView.findViewById(R.id.tv_username);
        tvWorkCategory = mRootView.findViewById(R.id.tv_work_category);//工作类别
        //工单审核
        layoutApplyOrder = mRootView.findViewById(R.id.layout_apply_order);

        layoutApplyOrder.setOnClickListener(this);
        layoutPersonDetails.setOnClickListener(this);
        layoutVipMember.setOnClickListener(this);
        layoutMineSalary.setOnClickListener(this);
        layoutExpend.setOnClickListener(this);
        layoutCardCounting.setOnClickListener(this);
        layoutWorkOrderManager.setOnClickListener(this);
        layoutExtraWork.setOnClickListener(this);
        layoutSetting.setOnClickListener(this);
        layoutMineHomePage.setOnClickListener(this);
        layoutWaringManager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_person_details:
                startActivity(new Intent(getActivity(), PersonDetailsActivity.class).putExtra("person_basic_info",personBasicJson));
                break;
            case R.id.layout_vip_member:
                startActivity(new Intent(getActivity(), VipMemberCenterActivity.class));
                break;
            case R.id.layout_mine_salary:
                startActivity(new Intent(getActivity(), MineSalaryActivity.class));
                break;
            case R.id.layout_expend:
                startActivity(new Intent(getActivity(), MineExpendActivity.class));
                break;
            case R.id.layout_card_counting:
                //打卡统计
                startActivity(new Intent(getActivity(), CardCountingActivity.class));
                break;
            case R.id.layout_workorder_manager:
                //工单管理
                startActivity(new Intent(getActivity(), WorkOrderActivity.class));
                break;
            case R.id.layout_extra_work:
                startActivity(new Intent(getActivity(), ExtraWrokActivity.class));
                break;
            case R.id.layout_setting:
                startActivity(new Intent(getActivity(),SettingActivity.class));
                break;
            case R.id.layout_mine_homepage:
                //我的主页
                startActivity(new Intent(getActivity(), MineHomePageActivity.class));
                break;
            case R.id.layout_waring_manage:
                //异常打卡信息管理
                startActivity(new Intent(getActivity(), MineWaringManagerActivity.class));
                break;
            case R.id.layout_apply_order:
                //加入工单审核
                startActivity(new Intent(getActivity(), MineApplyOrderActivity.class));
                break;
        }
    }

    /****
     * 获取基本信息
     */
    private void getBasicInfo() {
        startIOSDialogLoading(getActivity(),"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_VIP_BASIC_INFO_URL, params, ConfigUtil.QUERY_VIP_BASIC_INFO_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_VIP_BASIC_INFO_URL_ACTION:
                Log.d("Dong", "获取基本信息----》" +json);
                handleQueryBasicInfo(json);
                break;
        }
    }

    /****
     * 处理查询我的基本信息
     * @param json
     */
    private void handleQueryBasicInfo(String json) {
        VIPMemberCenterBasicInfoBean bean = JSON.parseObject(json, VIPMemberCenterBasicInfoBean.class);
        if (bean != null) {
            VIPMemberCenterBasicInfoBean.DataBean data = bean.getData();
            if (data != null) {
                personBasicJson = json;
                String photo =data.getPhoto();
                String realName = data.getRealname();
                String profession = data.getProfession();
                if (!StringUtils.isEmpty(photo)) {
                    MyApplication.imageLoader.displayImage(photo, ivAvatar);
                }
                tvUserName.setText(StringUtils.isEmpty(realName) ? "" : realName);
                tvWorkCategory.setText(StringUtils.isEmpty(profession) ? "" : profession);
            }
        }
    }
}
