package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/5/25.
 */

public class SalaryDetailsAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public SalaryDetailsAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
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
            convertView = inflater.inflate(R.layout.item_salary_details, null);
            convertView.setTag(holder);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mList.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView tvName;
    }
}
