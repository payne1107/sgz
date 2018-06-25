package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.WorkOrderListBean;
import android.sgz.com.fragment.ApproveExtroWorkFragment;
import android.sgz.com.fragment.MineExtraWorkFragment;
import android.sgz.com.fragment.MineReleaseOrderFragment;
import android.sgz.com.fragment.MineWorkOrderkFragment;
import android.sgz.com.utils.ConfigUtil;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/5/16.
 * 工单
 */

public class WorkOrderActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private Context mContext;
    private Fragment[] mFragments;
    private int mIndex;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_work_order);
        mContext = WorkOrderActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的工单", true, true);
        RadioGroup rbExtraWorkType = findViewById(R.id.rg_extra_work_type);
        rbExtraWorkType.setOnCheckedChangeListener(this);
        initFragment();
    }

    private void initFragment() {
        MineWorkOrderkFragment fragment1 = new MineWorkOrderkFragment();
        MineReleaseOrderFragment fragment2 = new MineReleaseOrderFragment();

        mFragments = new Fragment[]{fragment1, fragment2};
        //开始事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.layout_container, fragment1).commit();
        //默认设置为第0个
        setIndexSelected(0);
    }

    private void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.layout_container, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkId) {
        switch (checkId) {
            case R.id.rb_mine_extra_work:
                setIndexSelected(0);
                break;
            case R.id.rb_approve_exra_work:
                setIndexSelected(1);
                break;
        }
    }
}
