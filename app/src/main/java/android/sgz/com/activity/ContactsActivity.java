package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ContactsAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.sgz.com.widget.SpacesItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 92457 on 2018/5/19.
 * 联系人
 */

public class ContactsActivity extends BaseActivity {

    private Context mContext;
    private List<String> mList = new ArrayList<>();
    private ContactsAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contacts);
        mContext = ContactsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        mList.add("孙东升");
        mList.add("魏东");
        mList.add("孟强");
        mList.add("王灵嗯");
        mList.add("权传奇");
        mList.add("豆豆");
        mList.add("李阿飞");
        mList.add("嗷嗷");

        setInVisibleTitleIcon("工友信息", true, true);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ContactsAdapter(mContext, mList);
        recyclerView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, ReleaseOrderActivity.class);
                intent.putExtra(ReleaseOrderActivity.REQUEST_CHOOSE_CONTACTS_KEY, mList.get(position));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
