package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.bean.ContactsBean;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.CircleImageView;
import android.sgz.com.widget.LoadingDailog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by 92457 on 2018/5/25.
 */

public class ContactsAdapter extends BaseAdapter{
    private Context mContext;
    private List<ContactsBean.DataBean.ListBean> mList;
    private LayoutInflater inflater;

    public ContactsAdapter(Context context, List<ContactsBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
        this.inflater = LayoutInflater.from(mContext);
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<ContactsBean.DataBean.ListBean> data) {
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
            convertView = inflater.inflate(R.layout.item_select_contacts, null);
            convertView.setTag(holder);
            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.ivAvatar = convertView.findViewById(R.id.iv_avatar);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ContactsBean.DataBean.ListBean bean = mList.get(position);
        if (bean != null) {
            String realName=bean.getRealname();
            String profession = bean.getProfession();
            holder.tvName.setText(profession + " - " + realName);
            String photoUrl = bean.getPhoto();
            if (!StringUtils.isEmpty(photoUrl)) {
                MyApplication.imageLoader.displayImage(photoUrl, holder.ivAvatar);
            } else {
                holder.ivAvatar.setImageResource(R.mipmap.default_avatar);
            }
        }
        return convertView;
    }

    class ViewHolder{
        TextView tvName;
        CircleImageView ivAvatar;
    }
}
