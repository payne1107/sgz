package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.AllPorjectOrderBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/6/25.
 */

public class AllProjectOrderAdapter extends BaseAdapter {
    private Context mContext;
    private List<AllPorjectOrderBean.DataBean.ListBean> mList;

    public AllProjectOrderAdapter(Context context, List<AllPorjectOrderBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<AllPorjectOrderBean.DataBean.ListBean> data) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_work_order, null);
            convertView.setTag(holder);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvHeadMan = (TextView) convertView.findViewById(R.id.tv_headman);
            holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);
            holder.tvCategory = (TextView) convertView.findViewById(R.id.tv_category);
            holder.tvStartTime = (TextView) convertView.findViewById(R.id.tv_start_date);
            holder.tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
            holder.tvPhone = convertView.findViewById(R.id.tv_phone);
            holder.tvStartWorkTime =convertView.findViewById(R.id.tv_start_work_time);
            holder.tvEndWorkTime =convertView.findViewById(R.id.tv_end_work_time);
            holder.layoutShadow =convertView.findViewById(R.id.layout_shadow);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AllPorjectOrderBean.DataBean.ListBean bean = mList.get(position);
        if (bean != null) {
            String name = bean.getName();
            String headMan =bean.getHeadman();//负责人
            String address =bean.getAddress();
            String categoryname=bean.getCategoryname();
            String startTime =bean.getStarttime();
            String mobile = bean.getMobile();
            String startWorkTime=bean.getStartworktime();
            String endWorkTime =bean.getEndworktime();
            int ifend = bean.getIfend(); //工单是否结束 0未结束   1 已结束
            holder.tvTitle.setText("" + name);
            holder.tvHeadMan.setText("" + headMan);
            holder.tvAddress.setText("" + address);
            holder.tvCategory.setText("" + categoryname);
            holder.tvStartTime.setText("" + startTime);
            holder.tvPhone.setText("" + mobile);
            holder.tvEndWorkTime.setText("" + endWorkTime);
            holder.tvStartWorkTime.setText("" + startWorkTime);
            if (ifend == 0) {
                holder.layoutShadow.setVisibility(View.GONE);
            } else {
                holder.layoutShadow.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }

    class ViewHolder{
        TextView tvTitle,tvHeadMan,tvAddress,tvCategory,tvStartTime,tvStatus,tvPhone,tvEndWorkTime,tvStartWorkTime;
        AutoFrameLayout layoutShadow;
    }
}
