package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ProfessionBean;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * Created by WD on 2018/5/9.
 */

public class ChooseProfessionAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<ProfessionBean.DataBean> mList;
    private LayoutInflater inflater;

    public ChooseProfessionAdapter(Context context, List<ProfessionBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<ProfessionBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_choose_profession, parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        //绑定数据
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvProfessionName.setText(mList.get(position).getProfession());

        View itemView =((AutoLinearLayout) holder.itemView).getChildAt(0);
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvProfessionName;
        public ViewHolder(View itemView) {
            super(itemView);
            tvProfessionName = (TextView) itemView.findViewById(R.id.tv_profession_name);
        }
    }

    private IRecycleViewOnItemClickListener mOnItemClickListener;//聲明接口
    public void setOnItemClickListener(IRecycleViewOnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
