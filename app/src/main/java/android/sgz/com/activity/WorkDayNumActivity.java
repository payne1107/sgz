package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MineSalaryAdapter;
import android.sgz.com.adapter.WorkDayNumAdapter;
import android.sgz.com.base.BaseActivity;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WD on 2018/5/7.
 * 工作天数
 */

public class WorkDayNumActivity extends BaseActivity{

    private PullToRefreshListView listView;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_work_day_num);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工作天数", true, true);
        mList.add("花园别墅3#项目建设");
        mList.add("花园别墅1#项目建设");
        mList.add("花园别墅2#项目建设");
        mList.add("花园别墅5#项目建设");
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        WorkDayNumAdapter adapter = new WorkDayNumAdapter(this,mList);
        listView.setAdapter(adapter);
    }
}
