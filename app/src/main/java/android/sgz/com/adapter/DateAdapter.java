package android.sgz.com.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.sgz.com.R;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by WD on 2018/5/17.
 */

public class DateAdapter extends BaseAdapter {
    private int[] days = new int[42];
    private Context context;
    private int year;
    private int month;
    private int day;
    private int UPDATE_TEXT_COLOR = -1;
    private ViewHolder viewHolder;

    public DateAdapter(Context context, int[] days, int year, int month,int day) {
        this.context = context;
        this.year = year;
        this.month = month;
        this.day = day;
        this.days = days;
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
            viewHolder.date_item.setTextColor(context.getResources().getColor(R.color.color_62d));
            viewHolder.date_item.setBackground(context.getResources().getDrawable(R.drawable.background_click_item));
        } else {
            viewHolder.date_item.setTextColor(context.getResources().getColor(R.color.cp_colorAccent));
            viewHolder.date_item.setBackground(null);
        }
    }
    @Override
    public int getCount() {
        return days.length;
    }

    @Override
    public Object getItem(int i) {
        return days[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_calendar, null);
            viewHolder = new ViewHolder();
            viewHolder.date_item = (TextView) view.findViewById(R.id.date_item);
            view.setTag(viewHolder);
            AutoUtils.autoSize(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        setTextStyle(i);

        if (days[i] == day) {
            //等于当前日期设置不同的颜色和背景
            viewHolder.date_item.setTextColor(context.getResources().getColor(R.color.color_62d));
            viewHolder.date_item.setBackground(context.getResources().getDrawable(R.drawable.background_item));
        }

        viewHolder.date_item.setText(days[i] + "");
        if (i < 7 && days[i] > 20) {
            //判断第一排日期是否大于20 如果大于说明是上个月的日期
            viewHolder.date_item.setTextColor(Color.rgb(204, 204, 204));//将上个月的和下个月的设置为灰色
        } else if (i > 20 && days[i] < 15) {
            viewHolder.date_item.setTextColor(Color.rgb(204, 204, 204));
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
