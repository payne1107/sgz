package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
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
 * Created by 92457 on 2018/5/25.
 */

public class ContactsAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public ContactsAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_select_contacts,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvName.setText(mList.get(position));

        View itemView =((AutoLinearLayout) holder.itemView).getChildAt(0);
        if (recycleViewOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    recycleViewOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }


    private IRecycleViewOnItemClickListener recycleViewOnItemClickListener;//声明接口
    public void setOnItemClickListener(IRecycleViewOnItemClickListener itemClickListener) {
        recycleViewOnItemClickListener = itemClickListener;
    }
}
