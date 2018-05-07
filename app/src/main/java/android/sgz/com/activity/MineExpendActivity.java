package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WD on 2018/5/7.
 * 我的支出
 */

public class MineExpendActivity extends BaseActivity{

    private PullToRefreshListView listView;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_expend);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的支出", true, true);

    }
}
