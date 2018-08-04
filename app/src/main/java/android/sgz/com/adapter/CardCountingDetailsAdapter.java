package android.sgz.com.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.sgz.com.R;
import android.sgz.com.bean.MineExtraWorkBean;
import android.sgz.com.bean.MothWorkerStatusBean;
import android.sgz.com.utils.DateUtils;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/7/23.
 */

public class CardCountingDetailsAdapter extends BaseAdapter {
    private List<MothWorkerStatusBean.DataBean> mList;
    private Context mContext;
    private int UPDATE_TEXT_COLOR = -1;
    ViewHolder viewHolder;
    public CardCountingDetailsAdapter(Context context, List<MothWorkerStatusBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
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

    public void updateTextColor(int position) {
        UPDATE_TEXT_COLOR = position;
        notifyDataSetChanged();
    }

    /***
     * 设置选中后的字体颜色和背景色
     * @param position
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setTextStyle(int position) {
        if (UPDATE_TEXT_COLOR == position) {
            viewHolder.date_item.setTextColor(mContext.getResources().getColor(R.color.color_62d));
            viewHolder.date_item.setBackground(mContext.getResources().getDrawable(R.drawable.background_click_item));
        } /*else {
            viewHolder.date_item.setTextColor(mContext.getResources().getColor(R.color.cp_colorAccent));
            viewHolder.date_item.setBackground(mContext.getResources().getDrawable(R.drawable.background_item2));
        }*/
    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<MothWorkerStatusBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, null);
            viewHolder.date_item = (TextView) view.findViewById(R.id.date_item);
            view.setTag(viewHolder);
            AutoUtils.autoSize(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        MothWorkerStatusBean.DataBean bean = mList.get(position);
        viewHolder.date_item.setText(bean.getDate());
        Log.d("Dong", "日期-----》" + bean.getDate() + bean.getStartstatus());
        if (bean.getStartstatus() == 1 && bean.getEndstatus() == 1) {
            //上班正常
            viewHolder.date_item.setTextColor(mContext.getResources().getColor(R.color.white));
            viewHolder.date_item.setBackground(mContext.getResources().getDrawable(R.drawable.background_item2));
        } else if (bean.getStartstatus() == 4 || bean.getEndstatus() == 4) {
            //无考勤打卡记录
            viewHolder.date_item.setTextColor(mContext.getResources().getColor(R.color.google_red));
            viewHolder.date_item.setBackground(mContext.getResources().getDrawable(R.drawable.background_item2));
        } else if (bean.getStartstatus() == 2) {
            //上班迟到
            viewHolder.date_item.setTextColor(mContext.getResources().getColor(R.color.edcd71));
            viewHolder.date_item.setBackground(mContext.getResources().getDrawable(R.drawable.background_item2));
        } else if (bean.getEndstatus() == 3) {
            //早退
            viewHolder.date_item.setTextColor(mContext.getResources().getColor(R.color.google_blue));
            viewHolder.date_item.setBackground(mContext.getResources().getDrawable(R.drawable.background_item2));
        }  else {
            //未知异常
            Log.d("Dong", "else-------------->" + bean.getDate());
            viewHolder.date_item.setTextColor(mContext.getResources().getColor(R.color.ff6000));
            viewHolder.date_item.setBackground(mContext.getResources().getDrawable(R.drawable.background_item2));
        }
        setTextStyle(position);
        if (bean.getIfextrawork() == 1) {
            //已加班
            viewHolder.date_item.setTextColor(mContext.getResources().getColor(R.color.white));
            viewHolder.date_item.setBackground(mContext.getResources().getDrawable(R.drawable.background_item3));
        }
        return view;
    }

    /**
     * 优化Adapter
     */
    class ViewHolder {
        TextView date_item;
    }
}
