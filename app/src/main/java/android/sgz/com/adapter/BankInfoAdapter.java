package android.sgz.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.BankListInfoBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/6/12.
 */

public class BankInfoAdapter extends BaseAdapter {
    private Context mContext;
    private List<BankListInfoBean.DataBean> mList;
    public BankInfoAdapter(Context context, List<BankListInfoBean.DataBean> list) {
        this.mList = list;
        this.mContext = context;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<BankListInfoBean.DataBean> data) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bank_info, null);
            convertView.setTag(holder);
            holder.tvBankName = convertView.findViewById(R.id.tv_bank_name);
            AutoUtils.autoSize(convertView);
        } else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvBankName.setText(mList.get(i).getName());
        return convertView;
    }

    class ViewHolder {
        TextView tvBankName;
    }
}
