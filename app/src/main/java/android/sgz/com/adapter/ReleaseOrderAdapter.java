package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 92457 on 2018/5/25.
 */

public class ReleaseOrderAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public ReleaseOrderAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_release_order, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvName.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
