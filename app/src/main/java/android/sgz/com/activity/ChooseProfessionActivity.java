package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ChooseProfessionAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.widget.SpacesItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WD on 2018/5/9.
 * 选择职业
 */

public class ChooseProfessionActivity extends BaseActivity{

    private RecyclerView recyclerView;
    private List<String> mList = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_profession);
        mContext = ChooseProfessionActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("职业", true, true);
        mList.add("工程师");
        mList.add("水电工");
        mList.add("机械工");
        mList.add("瓦工");
        mList.add("油漆工");
        mList.add("木工");
        mList.add("架手工");
        mList.add("塔吊工");
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        ChooseProfessionAdapter adapter = new ChooseProfessionAdapter(mContext,mList);
        recyclerView.setAdapter(adapter);
    }
}
