package android.sgz.com.activity;

import android.app.Dialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.autolayout.AutoLinearLayout;

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
    private TextView tvAddPerson;
    private String projectName;
    private int ifend;
    private Dialog dialog;
    private int choosePostion;


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
        projectName = getIntent().getStringExtra("projectName");
        ifend = getIntent().getIntExtra("ifend", -1);

        setSettingBtn("结束工单");
        listView = findViewById(R.id.listView);
        tvAddPerson = findViewById(R.id.tv_add_person);
        adapter = new ReleaseWorkOrderDetailsAdapter(mContext, mList);
        listView.setAdapter(adapter);

        if (ifend == 1) {
            //工单结束隐藏这些按钮
            tvSet.setVisibility(View.GONE);
            tvAddPerson.setVisibility(View.GONE);
        }
        setListener();

    }

    private void setListener() {
        tvSet.setOnClickListener(this);
        tvAddPerson.setOnClickListener(this);
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                toastMessage("position" +mList.get(position).getUserid());
                if (mList != null) {
                    int userId = mList.get(position).getId();
                    if (ifend == 0) {
                        //编辑工资
                        startActivity(new Intent(mContext, SetWorkPresonSalaryActivity.class).putExtra("projectId", projectId).putExtra("update_person_salary", 2).putExtra("userId", userId));
                    } else {
                        toastMessage("工单已经结束");
                    }
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                deleteContacts(i);
                showMyDialog(i);
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (mList != null) {
                    int userId = mList.get(position).getUserid();
                    Intent intent = new Intent(mContext, PersonOrderSalaryActivity.class);
                    intent.putExtra("projectId", projectId);
                    intent.putExtra("userId", userId);
                    intent.putExtra("projectName", projectName);
                    startActivity(intent);
                }
            }
        });
    }

    /***
     * 弹出是否删除工人和是否禁用工人信息
     * @param i 当前选择的用户position
     */
    private void showMyDialog(int i) {
        choosePostion = i;
        showSeclectPhotoDialog();
    }

    /**
     * 选着照片和拍照的Dialog
     * @author Dong
     ***/
    private void showSeclectPhotoDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_delete_user, null);
        dialog = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = this.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        TextView btnCancel = (TextView) view.findViewById(R.id.tv_cancel_photo);
        TextView tvDeleteUser = (TextView) view.findViewById(R.id.tv_delete_user);
        TextView tvDisableUser = (TextView) view.findViewById(R.id.tv_disable_user);
        btnCancel.setOnClickListener(this);
        tvDeleteUser.setOnClickListener(this);
        tvDisableUser.setOnClickListener(this);
    }



    @Override
    protected void onResume() {
        super.onResume();
        queryProjectOrderUsers(projectId);
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
            case R.id.tv_add_person:
                //添加好友
                startActivity(new Intent(mContext, ContactsActivity.class).putExtra("query_contacts_info", 2).putExtra("projectId", projectId));
                break;
            case R.id.tv_delete_user:
                deleteContacts(choosePostion);
                dialog.dismiss();
                break;
            case R.id.tv_disable_user:
                //禁用用户
                disableUser(choosePostion, 0);
                dialog.dismiss();
                break;
            case R.id.tv_cancel_photo:
                dialog.dismiss();
                break;
        }
    }

    /***
     * 禁用用户打卡
     * @param choosePostion
     */
    private void disableUser(int choosePostion, final int status) {
        WorkOrderDetailsBean.DataBean bean = mList.get(choosePostion);
        if (bean != null) {
            userid =bean.getUserid();
        }

        final MyDialog dialog = new MyDialog(mContext);
        dialog.setMessage("是否禁用该用户？禁用之后该用户不可打卡，谨慎操作！！");
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
                Map<String, String> params = new HashMap<>();
                params.put("projectid", String.valueOf(projectId));
                params.put("userid", String.valueOf(userid));
                params.put("status", String.valueOf(status));
                httpPostRequest(ConfigUtil.EDIT_PROJECT_WORK_STATUS_URL, params, ConfigUtil.EDIT_PROJECT_WORK_STATUS_URL_ACTION);
                dialog.dismiss();
            }
        });
        dialog.show();
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
            case ConfigUtil.EDIT_PROJECT_WORK_STATUS_URL_ACTION:
                toastMessage("禁用成功");
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
