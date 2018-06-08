package android.sgz.com.adapter;

import android.content.Context;
import android.os.Build;
import android.sgz.com.R;
import android.sgz.com.bean.ProfessionBean;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/5/9.
 */

public class ChooseProfessionAdapter extends BaseAdapter{
    public int UPDATE_TEXT_COLOR = -1;
    private Context mContext;
    private List<ProfessionBean.DataBean> mList ;
    ViewHolder holder = null;
    public ChooseProfessionAdapter(Context context, List<ProfessionBean.DataBean> list) {
        mContext = context;
        mList = list;
    }

    public void setData(List<ProfessionBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_choose_profession_level, null);
            convertView.setTag(holder);
            holder.tvName = convertView.findViewById(R.id.tv_name);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mList.get(position).getProfession());
        setTextStyle(position);
        return convertView;
    }
    /***
     * 设置选中后的字体颜色和背景色
     * @param position
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setTextStyle(int position) {
        if (UPDATE_TEXT_COLOR == position) {
            holder.tvName.setTextColor(mContext.getResources().getColor(R.color.color_62d));
            holder.tvName.setBackground(mContext.getResources().getDrawable(R.drawable.popu_tv_select_style));
        } else {
            holder.tvName.setTextColor(mContext.getResources().getColor(R.color.text_color_3));
            holder.tvName.setBackground(mContext.getResources().getDrawable(R.drawable.popu_tv_style));
        }
    }
    public void updateTextColor(int position) {
        UPDATE_TEXT_COLOR = position;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView tvName;
    }
}
