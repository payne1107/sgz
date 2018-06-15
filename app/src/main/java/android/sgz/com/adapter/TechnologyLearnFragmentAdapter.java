package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.bean.Fragment2Bean;
import android.sgz.com.bean.ProfessionLevelBean;
import android.sgz.com.utils.StringUtils;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/5/3.
 */

public class TechnologyLearnFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<Fragment2Bean.DataBean.ListBean> mList;

    public TechnologyLearnFragmentAdapter(Context context, List<Fragment2Bean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setData(List<Fragment2Bean.DataBean.ListBean> data) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_technology_learn_fragment, null);
            convertView.setTag(holder);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.ivCover = convertView.findViewById(R.id.iv_cover);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Fragment2Bean.DataBean.ListBean bean = mList.get(position);
        if (bean != null) {
            String cover = bean.getCover();
            int isFree = bean.getIsfree();//是否收费
            String price = bean.getPrice();
            String title = bean.getTitle();
            if (!StringUtils.isEmpty(cover)) {
                MyApplication.imageLoader.displayImage(cover,holder.ivCover);
            }
            if (isFree == 0) {
                holder.tvPrice.setText("免费");
            } else {
                if (StringUtils.isEmpty(price)) {
                    price = "0.0";
                }
                holder.tvPrice.setText("" + price);
            }
            holder.tvTitle.setText("" + title);
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvTitle;
        ImageView ivCover;
        TextView tvPrice;

    }
}
