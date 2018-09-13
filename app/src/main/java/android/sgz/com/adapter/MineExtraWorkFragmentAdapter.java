package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ContactsBean;
import android.sgz.com.bean.MineExtraWorkBean;
import android.sgz.com.utils.StringUtils;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/6/19.
 */

public class MineExtraWorkFragmentAdapter extends BaseAdapter{
    private Context mContext;
    private List<MineExtraWorkBean.DataBean.ListBean> mList;
    public MineExtraWorkFragmentAdapter(Context context, List<MineExtraWorkBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<MineExtraWorkBean.DataBean.ListBean> data) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_extra_work, null);
            view.setTag(holder);
            holder.tvName = view.findViewById(R.id.tv_approve_person);
            holder.tvStatus= view.findViewById(R.id.tv_status);
            holder.tvStartDate =view.findViewById(R.id.tv_start_date);
            holder.tvEndDate = view.findViewById(R.id.tv_end_date);
            holder.tvProjectName =view.findViewById(R.id.tv_project_name);
            holder.tvRemark =view.findViewById(R.id.tv_remark);

            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MineExtraWorkBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            String starTime = bean.getStarttime();
            String endTime = bean.getEndtime();
            String approveName = bean.getProjectleadname();
            int status = bean.getStatus();
            String projectName = bean.getProjectname();
            String remark = bean.getRemark();
            holder.tvRemark.setText(StringUtils.isEmpty(remark) ? "" : remark);
            holder.tvStartDate.setText(starTime);
            holder.tvEndDate.setText(endTime);
            holder.tvName.setText("审批人："+approveName);
            holder.tvProjectName.setText(StringUtils.isEmpty(projectName) ? "" : projectName);
            if (status == 0) {
                holder.tvStatus.setText("审核不通过");
            } else if (status == 1) {
                holder.tvStatus.setText("审核通过");
            } else {
                holder.tvStatus.setText("审核中");
            }
        }
        return view;
    }

    class ViewHolder{
        TextView tvName;//审批人
        TextView tvStatus;
        TextView tvStartDate;
        TextView tvEndDate;
        TextView tvProjectName;
        public TextView tvRemark;
    }
}
