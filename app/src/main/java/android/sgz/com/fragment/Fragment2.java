package android.sgz.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.activity.Fragment2DetailsActivity;
import android.sgz.com.adapter.AllProjectOrderAdapter;
import android.sgz.com.adapter.TechnologyLearnFragmentAdapter;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.AllPorjectOrderBean;
import android.sgz.com.bean.Fragment2Bean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
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
 * Created by 92457 on 2018/4/16.
 * 所有工單列表
 */

public class Fragment2 extends BaseFragment implements View.OnClickListener {
    private List<AllPorjectOrderBean.DataBean.ListBean> mList = new ArrayList<>();
    private PullToRefreshListView listView;
    private int pageNo = 1;
    private AllProjectOrderAdapter adapter;
    private int pageSize;
    private boolean swipeLoadMore = false;
    private EditText etSearch;
    private TextView tvSearch;
    private String orderName = "";//搜索工單名稱

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment2, null);
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
        setInVisibleTitleIcon("", true, false);
        etSearch = (EditText) mRootView.findViewById(R.id.et_search);
        etSearch.setHint("搜索工单..");
        etSearch.setVisibility(View.VISIBLE);
        tvSearch = (TextView) mRootView.findViewById(R.id.activity_set);
        tvSearch.setText("搜索");
        tvSearch.setVisibility(View.VISIBLE);

        listView = (PullToRefreshListView) mRootView.findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new AllProjectOrderAdapter(getActivity(),mList);
        listView.setAdapter(adapter);
        queryAllProjectOrder(pageNo, MyApplication.currentCity, orderName);

        setListener();
    }

    private void setListener() {
        tvSearch.setOnClickListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryAllProjectOrder(pageNo, MyApplication.currentCity, orderName);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                swipeLoadMore = true;
                if (pageNo <= pageSize) {
                    queryAllProjectOrder(pageNo, MyApplication.currentCity, orderName);
                } else {
                    delayedToast();
                }
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("Dong", "12131231" +editable.toString());
                orderName = "";
            }
        });
        ////暂时不需要此功能
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                AllPorjectOrderBean.DataBean.ListBean bean = (AllPorjectOrderBean.DataBean.ListBean) adapterView.getAdapter().getItem(i);
//                if (bean != null) {
//                    String name = bean.getName();
//                    String headMan =bean.getHeadman();//负责人
//                    String address =bean.getAddress();
//                    String categoryname=bean.getCategoryname();
//                    String startTime =bean.getStarttime();
//                    int projectId = bean.getId();
//                    Intent intent = new Intent(getActivity(), Fragment2DetailsActivity.class);
//                    intent.putExtra("name", name);
//                    intent.putExtra("headMan", headMan);
//                    intent.putExtra("address", address);
//                    intent.putExtra("categoryname", categoryname);
//                    intent.putExtra("startTime", startTime);
//                    intent.putExtra("projectId", projectId);
//                    startActivity(intent);
//                }
//            }
//        });
    }

    private void delayedToast() {
        Toast.makeText(getActivity(),"没有更多数据啦",Toast.LENGTH_SHORT).show();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
                swipeLoadMore = false;
            }
        },1000);
    }

    /****
     * 查询所有工单
     * @param pageNo
     * @param address 地址
     */
    private void queryAllProjectOrder(int pageNo, String address,String orderName) {
        startIOSDialogLoading(getActivity(), "加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        params.put("address", address);
        if (!StringUtils.isEmpty(orderName)) {
            params.put("name", orderName);
        }
        httpPostRequest(ConfigUtil.QUERY_PROJECT_LISTS_URL, params, ConfigUtil.QUERY_PROJECT_LISTS_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_PROJECT_LISTS_URL_ACTION:
                handlerQueryAllProjectOrder(json);
                break;
        }
    }

    /****
     * 查询所有视频
     * @param json
     */
    private void handlerQueryAllProjectOrder(String json) {
        Log.d("Dong", "获取所有工单---》" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        AllPorjectOrderBean bean = JSON.parseObject(json, AllPorjectOrderBean.class);
        if (bean != null) {
            AllPorjectOrderBean.DataBean data = bean.getData();
            if (data != null) {
                pageSize = data.getCoutpage();
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    mList = data.getList();
                    if (mList != null && mList.size() <= 0) {
                        //没有数据
                        adapter.setData(mList);
                        setEmptyView(listView);
                    } else {
                        adapter.setData(mList);
                    }
                }
            } else {
                //没有数据
                adapter.setData(mList);
                setEmptyView(listView);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                //搜索
                pageNo = 1;
                orderName = etSearch.getText().toString().trim();
                if (StringUtils.isEmpty(orderName)) {
                    Toast.makeText(getActivity(), "请输入工单名称", Toast.LENGTH_LONG).show();
                    return;
                }
                queryAllProjectOrder(pageNo, MyApplication.currentCity, orderName);
                break;
        }
    }
}
