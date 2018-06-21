package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.bean.ContactDynamicBean;
import android.sgz.com.utils.StringUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by 92457 on 2018/6/21.
 */

public class ContactDynamicAdapter extends BaseAdapter {
    private Context mContext;
    private List<ContactDynamicBean.DataBean.ListBean> mList;
    public ContactDynamicAdapter(Context context, List<ContactDynamicBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<ContactDynamicBean.DataBean.ListBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_contact_dynamic, null);
            convertView.setTag(holder);
            holder.tvContext = convertView.findViewById(R.id.tv_content);
            holder.tvDate = convertView.findViewById(R.id.tv_date);
            holder.ivPic = convertView.findViewById(R.id.iv_pic);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ContactDynamicBean.DataBean.ListBean bean = mList.get(position);
        if (bean != null) {
            String content =bean.getContent();
            String createTime =bean.getCreatetime();
            String imgUrl = bean.getImgs();
            if (!StringUtils.isEmpty(content)) {
                holder.tvContext.setText(content);
            }
            if (!StringUtils.isEmpty(createTime)) {
                holder.tvDate.setText(createTime);
            }
            if (!StringUtils.isEmpty(imgUrl)) {
                MyApplication.imageLoader.displayImage(imgUrl,holder.ivPic);
            }
        }
        return convertView;
    }

    class ViewHolder{
        TextView tvContext;
        TextView tvDate;
        ImageView ivPic;
    }
}
