package android.sgz.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.BindBankCardInfoBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/6/14.
 */

public class BankCradlistInfoAdapter extends BaseAdapter {
    private Context mContext;
    private List<BindBankCardInfoBean.DataBean> mList;
    public BankCradlistInfoAdapter(Context context, List<BindBankCardInfoBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_bank_card_list_info, null);
            view.setTag(holder);
            holder.tvName = view.findViewById(R.id.tv_name);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(mList.get(i).getBankcard());
        return view;
    }

    class ViewHolder{
        TextView tvName;
    }
}
