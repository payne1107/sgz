package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.MineWorkOrderFragmentBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/6/25.
 */

public class MineReleaseOrderFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<MineWorkOrderFragmentBean.DataBean.ListBean> mList;

    public MineReleaseOrderFragmentAdapter(Context context, List<MineWorkOrderFragmentBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<MineWorkOrderFragmentBean.DataBean.ListBean> data) {
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mine_work_order, null);
            convertView.setTag(holder);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvHeadMan = (TextView) convertView.findViewById(R.id.tv_headman);
            holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);
            holder.tvCategory = (TextView) convertView.findViewById(R.id.tv_category);
            holder.tvStartTime = (TextView) convertView.findViewById(R.id.tv_start_date);
            holder.tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MineWorkOrderFragmentBean.DataBean.ListBean bean = mList.get(position);
        if (bean != null) {
            String name =bean.getName();
            String headMan =bean.getHeadman();//负责人
            String address =bean.getAddress();
            String categoryname=bean.getCategoryname();
            String startTime =bean.getStarttime();
            int status = bean.getStatus();
            holder.tvTitle.setText("" + name);
            holder.tvHeadMan.setText("" + headMan);
            holder.tvAddress.setText("" + address);
            holder.tvCategory.setText("" + categoryname);
            holder.tvStartTime.setText("" + startTime);
        }
        return convertView;
    }

    class ViewHolder{
        TextView tvTitle,tvHeadMan,tvAddress,tvCategory,tvStartTime,tvStatus;
    }
}
