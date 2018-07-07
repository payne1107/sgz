package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.TenderInfomationFragmentAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.TenderListBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.DpAndPxUtils;
import android.sgz.com.utils.PhoneSystem;
import android.sgz.com.utils.StringUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/5/6.
 * 搜索
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private EditText etSearch;
    private TextView tvSearch;
    private TagFlowLayout tagFlowLayout;
    private AutoLinearLayout layoutSearch;
    private List<String> tagFlowLayoutList = new ArrayList<>();
    private String searchStr;
    private int pageNo = 1;
    private PullToRefreshListView listView;
    private int pageSize;
    private boolean swipeLoadMore = false;
    private List<TenderListBean.DataBean.ListBean> mList = new ArrayList<>();
    private TenderInfomationFragmentAdapter adapter;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activivty_search);
        mContext = SearchActivity.this;
    }

    @Override
    protected void initData() {
        tagFlowLayoutList.add("日结");
        tagFlowLayoutList.add("家教");
        tagFlowLayoutList.add("服务员");
        setFlowLayout(tagFlowLayout,tagFlowLayoutList);
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("", true, true);
        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.setVisibility(View.VISIBLE);
        etSearch.setHint("搜索标书：标题");
        tvSearch = (TextView) findViewById(R.id.activity_set);
        tvSearch.setText("搜索");
        tvSearch.setVisibility(View.VISIBLE);

        tagFlowLayout = (TagFlowLayout) findViewById(R.id.TagFlowLayout);
        layoutSearch = (AutoLinearLayout) findViewById(R.id.layout_search);
        adapter = new TenderInfomationFragmentAdapter(mContext, mList);
        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setAdapter(adapter);
//        initListView();
        setListenter();
    }

    private void setListenter() {
        tvSearch.setOnClickListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                searchBiaoShu(pageNo,searchStr);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    searchBiaoShu(pageNo,searchStr);
                } else {
                    delayedToast();
                }
            }
        });

        //浮动标签点击事件
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                searchStr = tagFlowLayoutList.get(position);
                return false;
            }
        });
    }

    private void delayedToast() {
        Toast.makeText(mContext,"没有更多数据啦",Toast.LENGTH_SHORT).show();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
                swipeLoadMore = false;
            }
        },1000);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_set:
                searchStr = etSearch.getText().toString().trim();
                if (StringUtils.isEmpty(searchStr)) {
                    toastMessage("请输入标书标题,编号，手机号等进行搜索");
                    return;
                }
                searchBiaoShu(pageNo, searchStr);
                break;
        }
    }

    /*****
     * 标书搜索
     */
    private void searchBiaoShu(int page,String searchStr) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("searchStr", searchStr);
        httpPostRequest(ConfigUtil.QUERY_TENDER_LIST_URL, params, ConfigUtil.QUERY_TENDER_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_TENDER_LIST_URL_ACTION:
                handlerQueryTenderList(json);
                break;
        }
    }

    private void handlerQueryTenderList(String json) {
        Log.d("Dong", "获取标书列表---》" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        TenderListBean tenderListBean = JSON.parseObject(json, TenderListBean.class);
        if (tenderListBean != null) {
            TenderListBean.DataBean data = tenderListBean.getData();
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
     * 设置浮动标签
     */
    private void setFlowLayout(TagFlowLayout flowLayout,List<String> mValues) {
        final List<TextView> tvs = new ArrayList<>();
        final LayoutInflater mInflater = LayoutInflater.from(this);
        final TagFlowLayout finalFlowLayout = flowLayout;
        TagAdapter adapter = new TagAdapter<String>(mValues) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.flag_text_context, finalFlowLayout, false);
                tv.setText(s);
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (!StringUtils.isEmpty(PhoneSystem.getSystem()) && PhoneSystem.getSystem().equals(PhoneSystem.SYS_EMUI)) {
                    lp.setMargins(0, DpAndPxUtils.dip2px(SearchActivity.this,6),
                            DpAndPxUtils.dip2px(SearchActivity.this,4), 0);
                } else {
                    lp.setMargins(0, DpAndPxUtils.dip2px(SearchActivity.this,12), DpAndPxUtils.dip2px(SearchActivity.this,8), 0);
                }
                tv.setLayoutParams(lp);
                tvs.add(tv);
                return tv;
            }
        };
        flowLayout.setAdapter(adapter);
    }
}
