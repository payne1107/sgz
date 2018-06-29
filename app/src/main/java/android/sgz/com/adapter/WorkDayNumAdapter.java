package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ProjectSalaryListBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/5/7.
 */

public class WorkDayNumAdapter extends BaseAdapter{
    private Context mContext;
    private List<ProjectSalaryListBean.DataBean.ListBean> mList;

    public WorkDayNumAdapter(Context context, List<ProjectSalaryListBean.DataBean.ListBean> list) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mine_salary, null);
            convertView.setTag(holder);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.tvTitle.setText(mList.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView tvTitle;
    }
}
