package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.fragment.MineCheckWaringWorkRecordFragment;
import android.sgz.com.fragment.MineReleaseOrderFragment;
import android.sgz.com.fragment.MineWaringWorkRecordkFragment;
import android.sgz.com.fragment.MineWorkOrderkFragment;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

/**
 * Created by WD on 2018/6/27.
 * 异常打卡信息管理
 */

public class MineWaringManagerActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private MineWaringManagerActivity mContext;
    private Fragment[] mFragments;
    private int mIndex;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_waring_manager);
        mContext = MineWaringManagerActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("异常打卡", true, true);
        RadioGroup rbExtraWorkType = findViewById(R.id.rg_waring_manger_type);
        rbExtraWorkType.setOnCheckedChangeListener(this);
        initFragment();
    }

    private void initFragment() {
        MineWaringWorkRecordkFragment fragment1 = new MineWaringWorkRecordkFragment();
        MineCheckWaringWorkRecordFragment fragment2 = new MineCheckWaringWorkRecordFragment();

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
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rb_mine_waring_work_record:
                setIndexSelected(0);
                break;
            case R.id.rb_approve_waring_work_record:
                setIndexSelected(1);
                break;
        }
    }
}
