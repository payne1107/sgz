package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ProfessionLevelBean;
import android.sgz.com.bean.ProjectCategoryBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/7/4.
 */

public class ChoosePorjectCategoryAdapter extends BaseAdapter{
    private Context mContext;
    private List<ProjectCategoryBean.DataBean> mList ;
    public ChoosePorjectCategoryAdapter(Context context, List<ProjectCategoryBean.DataBean> list) {
        mContext = context;
        mList = list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }
    public void setData(List<ProjectCategoryBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_choose_project_category, null);
            convertView.setTag(holder);
            holder.tvName = convertView.findViewById(R.id.tv_name);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mList.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        TextView tvName;
    }
}
