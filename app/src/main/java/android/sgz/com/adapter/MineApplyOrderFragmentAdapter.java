package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.MineApplyOrderListBean;
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

public class MineApplyOrderFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<MineApplyOrderListBean.DataBean.ListBean> mList;

    public MineApplyOrderFragmentAdapter(Context context, List<MineApplyOrderListBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<MineApplyOrderListBean.DataBean.ListBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_apply_order, null);
            view.setTag(holder);
            holder.tvStartTime = view.findViewById(R.id.tv_start_work_time);
            holder.tvEndTime = view.findViewById(R.id.tv_end_work_time);
            holder.tvProjectName = view.findViewById(R.id.tv_project_name);
            holder.tvAddress = view.findViewById(R.id.tv_address);
            holder.tvStatus = view.findViewById(R.id.tv_status);
            holder.tvApprovePerson = view.findViewById(R.id.tv_approve_person);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MineApplyOrderListBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            MineApplyOrderListBean.DataBean.ListBean.ProjectBean projectBean = bean.getProject();
            if (projectBean != null) {
                String projectName = projectBean.getName();
                String address = projectBean.getAddress();
                String startWorkTime = projectBean.getStartworktime();
                String endWorkTime = projectBean.getEndworktime();
                String headMan = projectBean.getHeadman();
                holder.tvProjectName.setText(StringUtils.isEmpty(projectName) ? "" : projectName);
                holder.tvAddress.setText(StringUtils.isEmpty(address) ? "" : address);
                holder.tvStartTime.setText(StringUtils.isEmpty(startWorkTime) ? "" : "起始时间："+startWorkTime);
                holder.tvEndTime.setText(StringUtils.isEmpty(endWorkTime) ? "" : "结束时间："+ endWorkTime);
                holder.tvApprovePerson.setText(StringUtils.isEmpty(headMan) ? "" : "审批人：" + headMan);
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
        TextView tvApprovePerson;
    }
}
