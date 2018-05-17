package android.sgz.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.activity.CardCountingActivity;
import android.sgz.com.activity.MineExpendActivity;
import android.sgz.com.activity.MineSalaryActivity;
import android.sgz.com.activity.PersonDetailsActivity;
import android.sgz.com.activity.VipMemberCenterActivity;
import android.sgz.com.activity.WorkOrderActivity;
import android.sgz.com.base.BaseFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.AutoLinearLayout;

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
        layoutPersonDetails.setOnClickListener(this);
        layoutVipMember.setOnClickListener(this);
        layoutMineSalary.setOnClickListener(this);
        layoutExpend.setOnClickListener(this);
        layoutCardCounting.setOnClickListener(this);
        layoutWorkOrderManager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_person_details:
                startActivity(new Intent(getActivity(), PersonDetailsActivity.class));
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
        }
    }
}
