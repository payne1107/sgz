package android.sgz.com.fragment;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.TechnologyLearnFragmentAdapter;
import android.sgz.com.base.BaseFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 92457 on 2018/4/16.
 */

public class Fragment2 extends BaseFragment{
    private List<String> mList = new ArrayList<>();
    private PullToRefreshListView listView;

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
        setInVisibleTitleIcon("学习", true, false);
        mList.add("免费");
        mList.add("￥30");
        mList.add("￥31");
        mList.add("￥32");

        listView = (PullToRefreshListView) mRootView.findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        TechnologyLearnFragmentAdapter adapter = new TechnologyLearnFragmentAdapter(getActivity(),mList);
        listView.setAdapter(adapter);
    }
}
