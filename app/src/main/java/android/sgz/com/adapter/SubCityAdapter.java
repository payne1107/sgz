package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ChooseAllCityBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by 92457 on 2018/6/16.
 */

public class SubCityAdapter extends BaseAdapter {
    private Context mContext;
    private List<ChooseAllCityBean.DataBean.CityListBean> mList;
    public SubCityAdapter(Context context, List<ChooseAllCityBean.DataBean.CityListBean> list) {
        this.mContext = context;
        this.mList = list;
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sub_city, null);
            convertView.setTag(holder);
            holder.tvCityName = convertView.findViewById(R.id.tv_city_name);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCityName.setText(mList.get(position).getName());
        return convertView;
    }

    class ViewHolder{
        TextView tvCityName;
    }
}
