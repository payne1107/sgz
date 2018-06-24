package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ChooseCompanyBean;
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

public class ChooseCompanyAdapter extends BaseAdapter {
    private Context mContext;
    private List<ChooseCompanyBean.DataBean> mList;
    public ChooseCompanyAdapter(Context context, List<ChooseCompanyBean.DataBean> list) {
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
        ViewHolder holder ;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_company, null);
            view.setTag(holder);
            holder.tvCompanyName = view.findViewById(R.id.tv_company_name);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvCompanyName.setText(mList.get(i).getMerchantname());
        return view;
    }

    class ViewHolder{
        public TextView tvCompanyName;
    }
}
