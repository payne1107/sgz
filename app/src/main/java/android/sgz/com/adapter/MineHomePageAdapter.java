package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.bean.MineHomePageBean;
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
 * Created by WD on 2018/6/24.
 */

public class MineHomePageAdapter extends BaseAdapter {
    private Context mContext;
    private List<MineHomePageBean.DataBean.ListBean> mList;
    public MineHomePageAdapter(Context context, List<MineHomePageBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<MineHomePageBean.DataBean.ListBean> data) {
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mine_homepage_dynamic, null);
            convertView.setTag(holder);
            holder.tvContext = convertView.findViewById(R.id.tv_content);
            holder.tvDate = convertView.findViewById(R.id.tv_date);
            holder.ivPic = convertView.findViewById(R.id.iv_pic);
            holder.tvUserName = convertView.findViewById(R.id.tv_username);
            holder.ivAvatar = convertView.findViewById(R.id.iv_avatar);

            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MineHomePageBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            String content = bean.getContent();
            String createTime = bean.getCreatetime();
            holder.tvContext.setText(StringUtils.isEmpty(content) ? "" : content);
            holder.tvDate.setText(StringUtils.isEmpty(createTime) ? "" : createTime);
            List<String> photolist = bean.getPhotolist();
            if (photolist != null && photolist.size() > 0) {
                String contentImg = photolist.get(0);
                MyApplication.imageLoader.displayImage(contentImg, holder.ivPic);
            } else {
                holder.ivPic.setVisibility(View.GONE);
            }
            MineHomePageBean.DataBean.ListBean.UserBean userBean = bean.getUser();
            if (userBean != null) {
                String username =userBean.getRealname();
                String avatraUrl = userBean.getPhoto();
                holder.tvUserName.setText(StringUtils.isEmpty(username) ? "" : username);
                if (!StringUtils.isEmpty(avatraUrl)) {
                    MyApplication.imageLoader.displayImage(avatraUrl, holder.ivAvatar);
                }
            }
        }
        return convertView;
    }


    class ViewHolder{
        TextView tvContext;
        TextView tvDate;
        ImageView ivPic;
        ImageView ivAvatar;
        TextView tvUserName;
    }
}
