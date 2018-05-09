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
 * Created by WD on 2018/5/9.
 */

public class ChooseProfessionAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public ChooseProfessionAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_choose_profession, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //绑定数据
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvProfessionName.setText(mList.get(position));
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
}
