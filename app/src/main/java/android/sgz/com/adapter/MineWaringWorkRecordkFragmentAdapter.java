package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.WaringApplyListBean;
import android.sgz.com.utils.StringUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/6/27.
 */

public class MineWaringWorkRecordkFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<WaringApplyListBean.DataBean.ListBean> mList;

    public MineWaringWorkRecordkFragmentAdapter(Context context, List<WaringApplyListBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<WaringApplyListBean.DataBean.ListBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
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
        return mList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_waring_work_record, null);
            view.setTag(holder);
            holder.tvStartTime = view.findViewById(R.id.tv_start_work_time);
            holder.tvEndTime = view.findViewById(R.id.tv_end_work_time);
            holder.tvProjectName = view.findViewById(R.id.tv_project_name);
            holder.tvAddress = view.findViewById(R.id.tv_address);
            holder.tvStatus = view.findViewById(R.id.tv_status);
            holder.tvRemark = view.findViewById(R.id.tv_remark);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        WaringApplyListBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            WaringApplyListBean.DataBean.ListBean.ProjectBean projectBean = bean.getProject();
            String remark = bean.getRemark();
            holder.tvRemark.setText(StringUtils.isEmpty(remark) ? "" : remark);
            if (projectBean != null) {
                String projectName = projectBean.getName();
                String address = projectBean.getAddress();
                String startWorkTime = projectBean.getStartworktime();
                String endWorkTime = projectBean.getEndworktime();
                holder.tvProjectName.setText(StringUtils.isEmpty(projectName) ? "" : projectName);
                holder.tvAddress.setText(StringUtils.isEmpty(address) ? "" : address);
                holder.tvStartTime.setText(StringUtils.isEmpty(startWorkTime) ? "" : "起始时间："+startWorkTime);
                holder.tvEndTime.setText(StringUtils.isEmpty(endWorkTime) ? "" : "结束时间："+ endWorkTime);
            }
            int status = bean.getStatus();
            if (status == 1) {
                holder.tvStatus.setText("审核通过");
            } else if (status == 2) {
                holder.tvStatus.setText("审核中");
            } else {
                holder.tvStatus.setText("不通过");
            }
        }
        return view;
    }

    class ViewHolder{
        TextView tvStartTime;
        TextView tvEndTime;
        TextView tvProjectName;
        TextView tvStatus;
        TextView tvAddress;
        TextView tvRemark;
    }
}
