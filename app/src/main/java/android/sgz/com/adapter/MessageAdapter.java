package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.MessageBean;
import android.sgz.com.bean.MineHomePageBean;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/6/24.
 */

public class MessageAdapter extends BaseAdapter {
    private Context mContext;
    private List<MessageBean.DataBean.ListBean> mList;
    public MessageAdapter(Context context, List<MessageBean.DataBean.ListBean> list) {
        this.mList = list;
        this.mContext = context;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<MessageBean.DataBean.ListBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_message, null);
            view.setTag(holder);
            holder.tvDate = view.findViewById(R.id.tv_date_label);
            holder.tvContent =view.findViewById(R.id.tv_content);
            holder.tvTitle =view.findViewById(R.id.tv_title);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        MessageBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            String createTime =bean.getCreatetime();
            String content= bean.getContent();
            String title = bean.getTitle();
            holder.tvTitle.setText("" + title);
            holder.tvContent.setText("" + content);
            holder.tvDate.setText("" + createTime);
        }
        return view;
    }

    class ViewHolder{
        TextView tvTitle,tvDate,tvContent;

    }
}
