package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.PoiAddressBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

/**
 * Created by 92457 on 2018/5/22.
 */

public class PoiKeywordSearchAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<PoiAddressBean> mList;

    public PoiKeywordSearchAdapter(Context context, ArrayList<PoiAddressBean> list) {
        mContext = context;
        mList = list;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_poi_keyword_search, null);
            holder.tvAddressName = (TextView) convertView.findViewById(R.id.tv_detailAddress);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvAddressName.setText(mList.get(position).getDetailAddress());
        return convertView;
    }

    class ViewHolder{
        TextView tvAddressName;
    }
}
