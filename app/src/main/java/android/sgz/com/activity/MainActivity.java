package android.sgz.com.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.fragment.Fragment1;
import android.sgz.com.fragment.Fragment2;
import android.sgz.com.fragment.Fragment3;
import android.sgz.com.fragment.Fragment4;
import android.sgz.com.utils.PopupMenuUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imageView;
    private Context mContext;
    private RelativeLayout rlBtnFrist,rlBtnSecond,rlBtnThird,rlBtnFourth;
    private FrameLayout frameLayout;
    private Fragment[] mFragments;
    private int mIndex = 0;
    private TextView tvBtnFirst;
    private TextView tvBtnSecond;
    private TextView tvBtnThird;
    private TextView tvBtnFourth;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        imageView = (ImageView) findViewById(R.id.iv_img);
        rlBtnFrist = (RelativeLayout) findViewById(R.id.rl_btn_first);
        rlBtnSecond = (RelativeLayout) findViewById(R.id.rl_btn_second);
        rlBtnThird = (RelativeLayout) findViewById(R.id.rl_btn_third);
        rlBtnFourth = (RelativeLayout) findViewById(R.id.rl_btn_fourth);
        tvBtnFirst = (TextView) findViewById(R.id.tv_btn_first);
        tvBtnSecond = (TextView) findViewById(R.id.tv_btn_second);
        tvBtnThird = (TextView) findViewById(R.id.tv_btn_third);
        tvBtnFourth = (TextView) findViewById(R.id.tv_btn_fourth);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);

        imageView.setOnClickListener(this);
        rlBtnFrist.setOnClickListener(this);
        rlBtnSecond.setOnClickListener(this);
        rlBtnThird.setOnClickListener(this);
        rlBtnFourth.setOnClickListener(this);
        initFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() != R.id.iv_img) {
            resetButton();
        }
        switch (v.getId()) {
            case R.id.iv_img:
                PopupMenuUtil.getInstance()._show(mContext, imageView);
                break;
            case R.id.rl_btn_first:
                Drawable drawable = getResources().getDrawable(R.drawable.dibu_icon_sy1);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvBtnFirst.setCompoundDrawables(null,drawable ,null,null);
                tvBtnFirst.setTextColor(getResources().getColor(R.color.google_red));
                setIndexSelected(0);
                break;
            case R.id.rl_btn_second:
                Drawable drawable1 = getResources().getDrawable(R.drawable.dibu_icon_tg1);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                tvBtnSecond.setCompoundDrawables(null,drawable1 ,null,null);
                tvBtnSecond.setTextColor(getResources().getColor(R.color.google_red));
                setIndexSelected(1);
                break;
            case R.id.rl_btn_third:
                Drawable drawable2 = getResources().getDrawable(R.drawable.dibu_icon_xx1);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                tvBtnThird.setCompoundDrawables(null,drawable2 ,null,null);
                tvBtnThird.setTextColor(getResources().getColor(R.color.google_red));
                setIndexSelected(2);
                break;
            case R.id.rl_btn_fourth:
                Drawable drawable3 = getResources().getDrawable(R.drawable.dibu_icon_wd1);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                tvBtnFourth.setCompoundDrawables(null,drawable3 ,null,null);
                tvBtnFourth.setTextColor(getResources().getColor(R.color.google_red));
                setIndexSelected(3);
                break;
            default:
                break;
        }
    }

    /***
     * 初始化Fragment
     */
    private void initFragment() {
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        //添加到数组
        mFragments = new Fragment[]{fragment1, fragment2, fragment3, fragment4};
        //开始事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.framelayout, fragment1).commit();
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
            ft.add(R.id.framelayout, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;
    }

    /****
     * 点击按钮之前重置样式
     */
    private void resetButton() {
        Drawable drawable1 = getResources().getDrawable(R.drawable.dibu_icon_sy);
        Drawable drawable2 = getResources().getDrawable(R.drawable.dibu_icon_tg);
        Drawable drawable3 = getResources().getDrawable(R.drawable.dibu_icon_xx);
        Drawable drawable4 = getResources().getDrawable(R.drawable.dibu_icon_wd);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());

        tvBtnFirst.setTextColor(getResources().getColor(R.color.text_color_3));
        tvBtnSecond.setTextColor(getResources().getColor(R.color.text_color_3));
        tvBtnThird.setTextColor(getResources().getColor(R.color.text_color_3));
        tvBtnFourth.setTextColor(getResources().getColor(R.color.text_color_3));

        tvBtnFirst.setCompoundDrawables(null, drawable1, null, null);
        tvBtnSecond.setCompoundDrawables(null, drawable2, null, null);
        tvBtnThird.setCompoundDrawables(null, drawable3, null, null);
        tvBtnFourth.setCompoundDrawables(null, drawable4, null, null);
    }
}
