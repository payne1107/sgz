package android.sgz.com.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MineExtraWorkFragmentAdapter;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.MineExtraWorkBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.widget.MyDialog;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/6/19.
 */

public class MineExtraWorkFragment extends BaseFragment implements View.OnClickListener {

    private PullToRefreshListView listView;
    private List<MineExtraWorkBean.DataBean.ListBean> mList = new ArrayList<>();
    private MineExtraWorkFragmentAdapter adapter;
    private int pageNo = 1;
    private int pageSize;
    private boolean swipeLoadMore = false;
    private Dialog dialog;
    private int extraWorkId = 0;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_mine_extra_work, null);
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
        listView = mRootView.findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MineExtraWorkFragmentAdapter(getActivity(),mList);
        listView.setAdapter(adapter);
        queryExtraWorkList(pageNo);

        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryExtraWorkList(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryExtraWorkList(pageNo);
                } else {
                    delayedToast();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MineExtraWorkBean.DataBean.ListBean bean = (MineExtraWorkBean.DataBean.ListBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    int status = bean.getStatus();
                    extraWorkId = bean.getId();
                    if (status == 2) {
                        showPopuWindow();
                    }
                }
            }
        });
    }

    private void delayedToast() {
        Toast.makeText(getActivity(),"没有更多数据啦",Toast.LENGTH_LONG).show();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
                swipeLoadMore = false;
            }
        },1000);
    }
    /****
     * 获取加班列表
     */
    private void queryExtraWorkList(int pageNo) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        httpPostRequest(ConfigUtil.QUERY_EXTRA_WORK_LIST_URL, params, ConfigUtil.QUERY_EXTRA_WORK_LIST_URL_ACTION);
    }

    /***
     * 删除加班申请
     */
    private void deleteExtraWork() {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(extraWorkId));
        httpPostRequest(ConfigUtil.DELETE_EXTRA_WORK_RECORD_URL, params, ConfigUtil.DELETE_EXTRA_WORK_RECORD_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_EXTRA_WORK_LIST_URL_ACTION:
                handleQueryExtraWorkList(json);
                break;
            case ConfigUtil.DELETE_EXTRA_WORK_RECORD_URL_ACTION:
                handleDeleteExtraWork(json);
                break;
        }
    }

    /***
     * 删除加班申请处理
     * @param json
     */
    private void handleDeleteExtraWork(String json) {
        Log.d("Dong", "删除加班申请---->" + json);
        if (getRequestCode(json) == 1) {
            pageNo = 1;
            //删除成功后刷新列表
            queryExtraWorkList(pageNo);
            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
        }
    }

    /****
     * 处理我的加班列表
     * @param json
     */
    private void handleQueryExtraWorkList(String json) {
        Log.d("Dong", "加班列表--->" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        MineExtraWorkBean bean = JSON.parseObject(json, MineExtraWorkBean.class);
        if (bean != null) {
            MineExtraWorkBean.DataBean data = bean.getData();
            if (data != null) {
                pageSize = data.getCoutpage();
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    mList = data.getList();
                    adapter.setData(mList);
                    listView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /****
     * 展示对话框
     */
    private void showPopuWindow() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popu_delete_approve_extra_work, null);
        dialog = new Dialog(getActivity(), R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        TextView tvSavePhoto = (TextView) dialog.findViewById(R.id.tv_delete_extra_work);
        tvSavePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_delete_extra_work:
                deleteExtraWork();
                dialog.dismiss();
                break;
            case R.id.tv_cancel:
                dialog.dismiss();
                break;
        }
    }


}
