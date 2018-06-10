package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ProfessionBean;
import android.sgz.com.bean.VipMemberListBean;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * Created by WD on 2018/6/10.
 */

public class VipMemberListAdapter extends RecyclerView.Adapter<VipMemberListAdapter.ViewHolder> {
    private Context mContext;
    private List<VipMemberListBean.DataBean> mList;

    public VipMemberListAdapter(Context context, List<VipMemberListBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }


    public void setData(List<VipMemberListBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vip_member_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvVipType.setText("计工资" + mList.get(position).getMonth() +"个月会员");
        holder.tvVipPrice.setText("￥" + mList.get(position).getMoney());
        View itemView =((AutoLinearLayout) holder.itemView).getChildAt(0);
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVipType,tvVipPrice,tvOpenupVip;
        public ViewHolder(View itemView) {
            super(itemView);
            tvVipPrice = itemView.findViewById(R.id.tv_vip_price);
            tvVipType = itemView.findViewById(R.id.tv_vip_type);
            tvOpenupVip = itemView.findViewById(R.id.tv_openup_vip);
        }
    }
    private IRecycleViewOnItemClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IRecycleViewOnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
