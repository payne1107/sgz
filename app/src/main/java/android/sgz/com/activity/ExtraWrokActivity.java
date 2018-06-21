package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.fragment.ApproveExtroWorkFragment;
import android.sgz.com.fragment.MineExtraWorkFragment;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

/**
 * Created by WD on 2018/6/19.
 * 加班管理
 */

public class ExtraWrokActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private int mIndex = 0;
    private Fragment[] mFragments;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_extra_wrok);
        mContext = ExtraWrokActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("加班管理", true, true);
        setSettingBtn("申请加班");
        RadioGroup rbExtraWorkType = findViewById(R.id.rg_extra_work_type);
        rbExtraWorkType.setOnCheckedChangeListener(this);

        tvSet.setOnClickListener(this);
        initFragment();
    }

    private void initFragment() {
        MineExtraWorkFragment fragment1 = new MineExtraWorkFragment();
        ApproveExtroWorkFragment fragment2 = new ApproveExtroWorkFragment();

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                startActivity(new Intent(mContext, ApplyExtraWorkActivity.class));
                break;
        }
    }
}
