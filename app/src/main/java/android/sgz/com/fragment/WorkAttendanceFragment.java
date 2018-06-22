package android.sgz.com.fragment;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.DefaultProjectOrderBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/4/29.
 */

public class WorkAttendanceFragment extends BaseFragment {
    private View mRootView;
    private TextView tvProjectName;
    private TextView tvDate;
    private TextView tvStartWorkTime;
    private TextView tvEndWorkTime;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_work_attendance, null);
        }
        //缓存的rootView需要判断是否已经被加过parent,如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        queryDefaultOrder();
    }

    private void initView() {
        tvProjectName = mRootView.findViewById(R.id.tv_project_name);
        tvDate = mRootView.findViewById(R.id.tv_date);
        tvStartWorkTime = mRootView.findViewById(R.id.tv_start_work_time);
        tvEndWorkTime = mRootView.findViewById(R.id.tv_end_work_time);

    }

    /***
     * 获取默认工单
     */
    public void queryDefaultOrder() {
        startIOSDialogLoading(getActivity(),"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("randow", "123");
        httpPostRequest(ConfigUtil.QUERY_DEFAULT_PROJECT_URL, params, ConfigUtil.QUERY_DEFAULT_PROJECT_URL_ACTION);
    }

    /***
     * 获取当天打卡记录
     */
    private void queryWorkRecord() {
        Map<String, String> parms = new HashMap<>();
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        handleQueryDefaultOrder(json);
    }

    /****
     * 获取默认工单
     * @param json
     */
    private void handleQueryDefaultOrder(String json) {
        Log.d("Dong", "默认工单---->" + json);
        DefaultProjectOrderBean bean = JSON.parseObject(json, DefaultProjectOrderBean.class);
        if (bean != null) {
            DefaultProjectOrderBean.DataBean data = bean.getData();
            if (data != null) {
                String projectName = data.getName();
                String startTime = data.getStarttime();
                String startWorkTime = data.getStartworktime();
                String endWorkTime = data.getEndworktime();
                tvProjectName.setText("" + projectName);
                tvDate.setText("" + startTime);
                tvStartWorkTime.setText("上班时间 " + startWorkTime);
                tvEndWorkTime.setText("下班时间" + endWorkTime);
            }
        }
    }
}
