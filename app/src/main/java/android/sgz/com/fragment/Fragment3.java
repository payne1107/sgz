package android.sgz.com.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MessageAdapter;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.MessageBean;
import android.sgz.com.utils.ConfigUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by 92457 on 2018/4/16.
 */

public class Fragment3 extends BaseFragment {

    private PullToRefreshListView listView;
    private List<MessageBean.DataBean.ListBean> mList = new ArrayList<>();
    private MessageAdapter adapter;
    private int pageSize;
    private boolean swipeLoadMore = false;
    private int pageNo = 1;


    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment3, null);
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
        setInVisibleTitleIcon("消息", true, false);
        queryPushMessage(pageNo);
        listView = mRootView.findViewById(R.id.listview);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MessageAdapter(getActivity(),mList);
        listView.setAdapter(adapter);
        setListener();

       // initConversionList();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryPushMessage(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryPushMessage(pageNo);
                } else {
                    delayedToast();
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
     * 查询消息推送
     */
    private void queryPushMessage(int pageNo) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        httpPostRequest(ConfigUtil.QUERY_MY_PUSH_MESSAGE_ULR, params, ConfigUtil.QUERY_MY_PUSH_MESSAGE_ULR_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MY_PUSH_MESSAGE_ULR_ACTION:
                hanldeQueryMyPushMessage(json);
                break;
        }
    }

    /***
     * 处理消息推送
     * @param json
     */
    private void hanldeQueryMyPushMessage(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        MessageBean bean =  JSON.parseObject(json, MessageBean.class);
        if (bean != null) {
            MessageBean.DataBean data = bean.getData();
            if (data != null) {
                pageSize = data.getCoutpage();
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    mList = data.getList();
                    if (mList != null && mList.size() > 0) {
                        adapter.setData(mList);
                        listView.setVisibility(View.VISIBLE);
                    } else {
                        setEmptyView(listView);
                    }
                }
            }
        }
    }

    private void initConversionList() {
        //会话列表
        ConversationListFragment conversationListFragment = new ConversationListFragment();
        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist").appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话，该会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//设置系统会话，该会话非聚合显示
                .build();
        conversationListFragment.setUri(uri);

        FragmentManager fragmentManager = getChildFragmentManager();
      //  FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.add(R.id.rong_container,conversationListFragment);
        //transaction.commit();
    }
}
