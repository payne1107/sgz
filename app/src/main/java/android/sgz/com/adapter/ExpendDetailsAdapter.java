package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ToRechargeDeailsBean;
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

public class ExpendDetailsAdapter extends BaseAdapter{
    private Context mContext;
    private List<ToRechargeDeailsBean.DataBean.ListBean> mList;
    public ExpendDetailsAdapter(Context cotext, List<ToRechargeDeailsBean.DataBean.ListBean> list) {
        this.mContext = cotext;
        this.mList = list;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<ToRechargeDeailsBean.DataBean.ListBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_to_recharge_details, null);
            view.setTag(holder);
            holder.tvMoney = view.findViewById(R.id.tv_money);
            holder.tvNumber = view.findViewById(R.id.tv_number);
            holder.tvTime = view.findViewById(R.id.tv_time);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ToRechargeDeailsBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            String time = bean.getCreatetime();
            String number =bean.getUserflowno();
            String money =bean.getTjmoney();
            holder.tvMoney.setText("-" + money + "元");
            holder.tvTime.setText("" + time);
            holder.tvNumber.setText("" + number);
        }
        return view;
    }

    class ViewHolder{
        TextView tvNumber,tvTime,tvMoney;
    }
}
