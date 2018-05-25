package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.SalaryDetailsAdapter;
import android.sgz.com.base.BaseActivity;
import android.view.View;
import android.widget.AdapterView;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WD on 2018/5/25.
 * 工资详情
 */

public class SalaryDetailsActivity extends BaseActivity {

    private PullToRefreshListView listView;
    private List<String> mList = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_salary_details);
        mContext = SalaryDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工资详情", true, true);
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        mList.add("张三");
        mList.add("李四");
        mList.add("王五");
        mList.add("嘻嘻");
        mList.add("哈哈哈");
        mList.add("嘻嘻");
        mList.add("哈哈哈");

        SalaryDetailsAdapter adapter = new SalaryDetailsAdapter(mContext,mList);
        listView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(mContext, PersonalSalaryActivity.class));
            }
        });
    }
}
