package android.sgz.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MineSalaryAdapter;
import android.sgz.com.adapter.MineWorkOrderAdapter;
import android.sgz.com.base.BaseActivity;
import android.view.View;
import android.widget.AdapterView;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WD on 2018/5/16.
 * 工单
 */

public class WorkOrderActivity extends BaseActivity {

    private PullToRefreshListView listView;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_work_order);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我发布的工单", true, true);
        mList.add("花园别墅1#项目建设");
        mList.add("花园别墅2#项目建设");
        mList.add("花园别墅3#项目建设");
        mList.add("花园别墅4#项目建设");
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        MineWorkOrderAdapter adapter = new MineWorkOrderAdapter(this,mList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(WorkOrderActivity.this, WorkOrderDetailsActivity.class));
            }
        });
    }
}
