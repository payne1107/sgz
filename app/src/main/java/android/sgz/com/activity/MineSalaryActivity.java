package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MineSalaryAdapter;
import android.sgz.com.base.BaseActivity;
import android.view.View;
import android.widget.TextView;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WD on 2018/5/7.
 * 我的工资
 */

public class MineSalaryActivity extends BaseActivity implements View.OnClickListener {

    private PullToRefreshListView listView;
    private List<String> mList = new ArrayList<>();
    private TextView tvWithDraw;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_salary);
        mContext = MineSalaryActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的工资", true, true);
        tvWithDraw = (TextView) findViewById(R.id.activity_set);
        tvWithDraw.setVisibility(View.VISIBLE);
        tvWithDraw.setText("提现");

        mList.add("花园别墅1#项目建设");
        mList.add("花园别墅2#项目建设");
        mList.add("花园别墅3#项目建设");
        mList.add("花园别墅4#项目建设");
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        MineSalaryAdapter adapter = new MineSalaryAdapter(this,mList);
        listView.setAdapter(adapter);


        setListener();
    }

    private void setListener() {
        tvWithDraw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_set:
                startActivity(new Intent(mContext, WithDrawDespositActivity.class));
                break;
        }
    }
}
