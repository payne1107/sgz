package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ContactsBean;
import android.sgz.com.bean.PersonWorkRecordBean;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.CircleImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/7/5.
 */

public class PersonWorkRecrdAdapter extends BaseAdapter{
    private Context mContext;
    private List<PersonWorkRecordBean.DataBean.ListBean> mList;

    public PersonWorkRecrdAdapter(Context context, List<PersonWorkRecordBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<PersonWorkRecordBean.DataBean.ListBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_person_work_record, null);
            holder.tvDate =view.findViewById(R.id.tv_date);
            holder.tvStartWorkRecord=view.findViewById(R.id.tv_start_work_record);
            holder.tvEndWorkRecord=view.findViewById(R.id.tv_end_work_record);
            holder.tvWaringStartWorkRecord = view.findViewById(R.id.tv_waring_start_work_record);
            holder.tvWaringEndWorkRecord = view.findViewById(R.id.tv_waring_end_work_record);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        PersonWorkRecordBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            String createTime =bean.getCreatetime();
            String startRecordTime =bean.getStartrecordtime();
            String endRecordTime=bean.getEndrecordtime();
            int startSatus=bean.getStartstatus();
            int endStatus = bean.getEndstatus();

            holder.tvDate.setText("" + createTime);
            holder.tvStartWorkRecord.setText(StringUtils.isEmpty(startRecordTime) ?"未打卡": startRecordTime.substring(startRecordTime.indexOf(" "),startRecordTime.length()));
            holder.tvEndWorkRecord.setText(StringUtils.isEmpty(endRecordTime) ? "未打卡" : endRecordTime.substring(endRecordTime.indexOf(" "), endRecordTime.length()));
            if (startSatus == 1) {
                holder.tvWaringStartWorkRecord.setText("正常");
                holder.tvWaringStartWorkRecord.setTextColor(mContext.getResources().getColor(R.color.color_62d));
            } else if (startSatus == 2) {
                holder.tvWaringStartWorkRecord.setText("上班迟到");
                holder.tvWaringStartWorkRecord.setTextColor(mContext.getResources().getColor(R.color.google_red));
            } else if (startSatus == 3) {
                holder.tvWaringStartWorkRecord.setText("早退");
                holder.tvWaringStartWorkRecord.setTextColor(mContext.getResources().getColor(R.color.google_red));
            } else {
                holder.tvWaringStartWorkRecord.setText("未打卡");
                holder.tvWaringStartWorkRecord.setTextColor(mContext.getResources().getColor(R.color.google_red));
            }
            if (endStatus == 1) {
                holder.tvWaringEndWorkRecord.setText("正常");
                holder.tvWaringEndWorkRecord.setTextColor(mContext.getResources().getColor(R.color.color_62d));
            } else if (endStatus == 2) {
                holder.tvWaringEndWorkRecord.setText("上班迟到");
                holder.tvWaringEndWorkRecord.setTextColor(mContext.getResources().getColor(R.color.google_red));
            } else if (endStatus == 3) {
                holder.tvWaringEndWorkRecord.setText("早退");
                holder.tvWaringEndWorkRecord.setTextColor(mContext.getResources().getColor(R.color.google_red));
            } else {
                holder.tvWaringEndWorkRecord.setText("未打卡");
                holder.tvWaringEndWorkRecord.setTextColor(mContext.getResources().getColor(R.color.google_red));
            }
        }
        return view;
    }

    class ViewHolder{
        TextView tvDate,tvStartWorkRecord,tvEndWorkRecord,tvWaringStartWorkRecord,tvWaringEndWorkRecord;

    }
}
