package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ClearSalaryContactsAdapter;
import android.sgz.com.adapter.VipMemberListAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.WorkerUserListBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.sgz.com.widget.MyDialog;
import android.sgz.com.widget.SpacesItemDecoration;
import android.sgz.com.widget.camera.IRecycleViewOnRadioButtonListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/7/11.
 * 清除工资
 */

public class ClearSalaryActivity extends BaseActivity implements View.OnClickListener {

    private int projectId;
    private EditText etDesc;
    private TextView tvClearDayy;
    private TextView tvClearHalf;
    private android.content.Context mContext;
    private ListView listView;
    private List<WorkerUserListBean.DataBean> mList = new ArrayList<>();
    private ClearSalaryContactsAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_clear_salary);
        mContext = ClearSalaryActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("清除工资", true, true);
        projectId = getIntent().getIntExtra("projectId", 0);
        etDesc = findViewById(R.id.et_desc);
        tvClearDayy = findViewById(R.id.tv_clear_day); //整天
        tvClearHalf = findViewById(R.id.tv_clear_half);
        listView = findViewById(R.id.listView);
        adapter = new ClearSalaryContactsAdapter(mContext, mList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListener();
    }

    private void setListener() {
        tvClearDayy.setOnClickListener(this);
        tvClearHalf.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_clear_day:
                clearSalaryDialog("all");
                break;
            case R.id.tv_clear_half:
                clearSalaryDialog("half");
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryContactList();
    }

    /****
     * 查询工单人员
     */
    private void queryContactList() {
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        httpPostRequest(ConfigUtil.QUERY_WORKER_USER_URL, params, ConfigUtil.QUERY_WORKER_USER_URL_ACTION);
    }

    /***
     * 清除
     * @param type
     */
    private void clearSalaryDialog(final String type) {
        final MyDialog dialog = new MyDialog(mContext);
        dialog.setMessage("是否清除工资?");
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearSalary(projectId,type);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void clearSalary(int projectId, String type) {
        List<Integer> list = adapter.getMlistUseId();
        String strUserId = "";
        if (list != null && list.size() > 0) {
            strUserId = StringUtils.listToString(list, ',');
        } else {
            toastMessage("请选择要清除的工人");
            return;
        }

        String remark =etDesc.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        if (!StringUtils.isEmpty(remark)) {
            params.put("operaremark", remark);
        }
        params.put("type", type);
        params.put("userids", strUserId);
        httpPostRequest(ConfigUtil.CLEAR_SALARY_URL,params,ConfigUtil.CLEAR_SALARY_URL_ACTION);

    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.CLEAR_SALARY_URL_ACTION:
                Log.d("Dong", "清除工资"+json);
                if (getRequestCode(json) == 1) {
                    toastMessage("清除成功");
                    finish();
                }
                break;
            case ConfigUtil.QUERY_WORKER_USER_URL_ACTION:
                haneleQueryWorkerUserList(json);
                break;

        }
    }

    /****
     * 获取工单人员列表
     * @param json
     */
    private void haneleQueryWorkerUserList(String json) {
        Log.d("Dong", "湖区工单人员" + json);
        WorkerUserListBean bean = JSON.parseObject(json, WorkerUserListBean.class);
        if (bean != null) {
            mList = bean.getData();
            adapter.setData(mList);
        }
    }
}
