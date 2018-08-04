package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ApproveExtroWorkBean;
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
 * Created by WD on 2018/6/21.
 */

public class ApproveExtroWorkFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<ApproveExtroWorkBean.DataBean.ListBean> mList;

    public ApproveExtroWorkFragmentAdapter(Context context, List<ApproveExtroWorkBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setData(List<ApproveExtroWorkBean.DataBean.ListBean> data) {
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_approve_extro_work, null);
            holder.tvStartDate =view.findViewById(R.id.tv_start_date);
            holder.tvEndDate =view.findViewById(R.id.tv_end_date);
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.tvStatus = view.findViewById(R.id.tv_status);
            holder.tvApplyTime = view.findViewById(R.id.tv_apply_time);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ApproveExtroWorkBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            String startTime = bean.getStarttime();
            String endTime =bean.getEndtime();
            int status =bean.getStatus();
            String workName = bean.getWorkname();
            String approveTime = bean.getApprovetime();
            holder.tvStartDate.setText("" + startTime);
            holder.tvEndDate.setText("" + endTime);
            holder.tvName.setText("" + workName +"的加班");
            if (status == 0) {
                holder.tvStatus.setText("不通过");
            } else if (status == 1) {
                holder.tvStatus.setText("审核通过");
            } else {
                holder.tvStatus.setText("审核中");
            }
            holder.tvApplyTime.setText(StringUtils.isEmpty(approveTime) ? "" : approveTime);
        }
        return view;
    }

    class ViewHolder{
        TextView tvName,tvStatus,tvStartDate,tvEndDate,tvApplyTime;
    }
}
