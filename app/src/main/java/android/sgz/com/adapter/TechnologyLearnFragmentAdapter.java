package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/5/3.
 */

public class TechnologyLearnFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public TechnologyLearnFragmentAdapter(Context context, List<String> list) {
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_technology_learn_fragment, null);
            convertView.setTag(holder);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvPrice.setText(mList.get(position));
        return convertView;
    }

    class ViewHolder {

        TextView tvPrice;

    }
}
