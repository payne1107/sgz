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
            holder.tvWaringWorkRecord = view.findViewById(R.id.tv_waring_work_record);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        PersonWorkRecordBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            String createTime =bean.getCreatetime();
            String startRecordTime =bean.getStartrecordtime();
            String endRecordTime=bean.getEndrecordtime();
            int startStatus=bean.getStartstatus();
            int endStatus = bean.getEndstatus();

            holder.tvDate.setText("" + createTime);
            holder.tvStartWorkRecord.setText("" + StringUtils.stringSpacingToString(startRecordTime));
            holder.tvEndWorkRecord.setText("" + StringUtils.stringSpacingToString(endRecordTime));
//            if (startSatus == 1) {
//                tvStartWorkStatus.setText("正常");
//                tvStartWorkStatus.setTextColor(mContext.getResources().getColor(R.color.color_62d));
//            } else if(startSatus ==2) {
//                tvStartWorkStatus.setText("迟到");
//                tvStartWorkStatus.setTextColor(mContext.getResources().getColor(R.color.google_red));
//            } else if (startSatus == 3) {
//                tvStartWorkStatus.setText("早退");
//                tvStartWorkStatus.setTextColor(mContext.getResources().getColor(R.color.google_red));
//            } else {
//                tvStartWorkStatus.setText("未打卡");
//                tvStartWorkStatus.setTextColor(mContext.getResources().getColor(R.color.google_red));
//            }
        }
        return view;
    }

    class ViewHolder{
        TextView tvDate,tvStartWorkRecord,tvEndWorkRecord,tvWaringWorkRecord;

    }
}
