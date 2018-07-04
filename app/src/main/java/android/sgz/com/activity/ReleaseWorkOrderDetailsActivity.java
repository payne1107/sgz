package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ReleaseWorkOrderDetailsAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.AddOrderContactsBean;
import android.sgz.com.bean.WorkOrderDetailsBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.sgz.com.widget.MyDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/7/4.
 * 工单详情
 */

public class ReleaseWorkOrderDetailsActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private List<WorkOrderDetailsBean.DataBean> mList = new ArrayList<>();
    private int projectId;
    private ListView listView;
    private ReleaseWorkOrderDetailsAdapter adapter;


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_release_order_details);
        mContext = ReleaseWorkOrderDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("工单详情", true, true);
        projectId = getIntent().getIntExtra("projectId", 0);
        setSettingBtn("结束工单");
        listView = findViewById(R.id.listView);
        adapter = new ReleaseWorkOrderDetailsAdapter(mContext, mList);
        listView.setAdapter(adapter);

        setListener();
        queryProjectOrderUsers(projectId);
    }

    private void setListener() {
        tvSet.setOnClickListener(this);
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                toastMessage("position" +position);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteContacts(i);
                return true;
            }
        });
    }

    /****
     * 删除联系人对话框
     * @param position
     */
    int userid = 0;
    private void deleteContacts(final int position) {
        WorkOrderDetailsBean.DataBean bean = mList.get(position);
        if (bean != null) {
            userid =bean.getUserid();
        }
        final MyDialog dialog = new MyDialog(mContext);
        dialog.setMessage("是否删除此工人信息？");
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取消
                dialog.dismiss();
            }
        });
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteOrderUser(userid,projectId);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                finishOrder(projectId, 1);
                break;
        }
    }

    /****
     * 获取工单人员
     */
    private void queryProjectOrderUsers(int projectid) {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectid));
        httpPostRequest(ConfigUtil.QUERY_PROJECT_WORK_USER_URL, params, ConfigUtil.QUERY_PROJECT_WORK_USER_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_PROJECT_WORK_USER_URL_ACTION:
                handleQueryProjectOrderUser(json);
                break;
            case ConfigUtil.CHANGE_PROJECT_ORDER_STATUS_URL_ACTION:
                if (getRequestCode(json) == 1) {
                    toastMessage("订单结束成功");
                    finish();
                }
                break;
            case ConfigUtil.DELETE_ORDER_USER_URL_ACTION:
                //删除工单好友哦
                Log.d("Dong", "删除工单好友---." + json);
                if (getRequestCode(json) ==1) {
                    //刷新列表
                    queryProjectOrderUsers(projectId);
                }
                break;
        }
    }

    /***
     * @param json
     */
    private void handleQueryProjectOrderUser(String json) {
        Log.d("Dong", "工资工友详情"+json);
        WorkOrderDetailsBean bean = JSON.parseObject(json, WorkOrderDetailsBean.class);
        if (bean != null) {
            mList = bean.getData();
            adapter.setData(mList);
        }
    }

    /****
     * 结束工单
     * @param id  工单id
     * @param ifend  1 结束 0不结束
     */
    private void finishOrder(int id,int ifend) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        params.put("ifend", String.valueOf(ifend));
        httpPostRequest(ConfigUtil.CHANGE_PROJECT_ORDER_STATUS_URL,params,ConfigUtil.CHANGE_PROJECT_ORDER_STATUS_URL_ACTION);
    }


    /****
     * 删除工单好友
     * @param userid
     * @param projectId
     */
    private void deleteOrderUser(int userid,int projectId) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", String.valueOf(userid));
        params.put("projectid", String.valueOf(projectId));
        httpPostRequest(ConfigUtil.DELETE_ORDER_USER_URL, params, ConfigUtil.DELETE_ORDER_USER_URL_ACTION);
    }
}
