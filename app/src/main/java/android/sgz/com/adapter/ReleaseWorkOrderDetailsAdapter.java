package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.MineWorkOrderFragmentBean;
import android.sgz.com.bean.WorkOrderDetailsBean;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
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

public class ReleaseWorkOrderDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private List<WorkOrderDetailsBean.DataBean> mList;
    public ReleaseWorkOrderDetailsAdapter(Context context, List<WorkOrderDetailsBean.DataBean> list) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<WorkOrderDetailsBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_release_order_detials, null);
            holder.tvUserName =convertView.findViewById(R.id.tv_username);
            holder.tvSalary =convertView.findViewById(R.id.tv_salary);
            holder.tvAddWorkSalary= convertView.findViewById(R.id.tv_add_work_salary);
            holder.tvAllowance=convertView.findViewById(R.id.tv_allowance);
            holder.tvEdit=convertView.findViewById(R.id.tv_edit);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        WorkOrderDetailsBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String allowance=bean.getAllowance();
            String salary =bean.getSalary();
            String overWorkSalalry = bean.getOverworksalary();
            holder.tvAddWorkSalary.setText(StringUtils.isEmpty(overWorkSalalry) ? "0" : overWorkSalalry);
            holder.tvAllowance.setText(StringUtils.isEmpty(allowance) ? "0" : allowance);
            holder.tvSalary.setText(StringUtils.isEmpty(salary) ? "0" : salary);
            WorkOrderDetailsBean.DataBean.UserBean user = bean.getUser();
            if (user != null) {
                String realName = user.getRealname();
                holder.tvUserName.setText(""+realName);
            }

            holder.tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v,i);
                }
            });
        }
        return convertView;
    }

    class ViewHolder{
        TextView tvUserName,tvSalary,tvAddWorkSalary,tvAllowance, tvEdit;
    }

    private IRecycleViewOnItemClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IRecycleViewOnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
