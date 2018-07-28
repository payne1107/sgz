package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.MineExpendDetailsBean;
import android.sgz.com.bean.WorkerUserListBean;
import android.sgz.com.utils.DateUtils;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.sgz.com.widget.camera.IRecycleViewOnRadioButtonListener;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by WD on 2018/7/20.
 */

public class ClearSalaryContactsAdapter extends BaseAdapter{
    private Context mContext;
    private List<WorkerUserListBean.DataBean> mList;
    private static HashMap<Integer,Boolean> mapSelect;
    //存储用户id
    private static List<Integer> mlistUseId ;
    public static List<Integer> getMlistUseId() {
        return mlistUseId;
    }

    public ClearSalaryContactsAdapter(Context context, List<WorkerUserListBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
        mapSelect = new HashMap<>();
        mlistUseId=new ArrayList<>();

    }

    private void initData() {
        for (int i = 0; i < mList.size(); i++) {
            mapSelect.put(i, false);
        }
    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<WorkerUserListBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
        initData();
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_work_user, null);
            holder.tvDate =convertView.findViewById(R.id.tv_date);
            holder.tvUsername = convertView.findViewById(R.id.tv_username);
            holder.radioButton1 =convertView.findViewById(R.id.radiobutton1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvUsername.setText(mList.get(position).getRealname());
        String dateStr = DateUtils.getYear()+"-" + DateUtils.getMonth() +"-"+ DateUtils.getCurrentDayOfMonth();
        holder.tvDate.setText(dateStr);
        int status =mList.get(position).getStatus();
        if (status > 0) {
            //大于0已经清除工资
            holder.radioButton1.setVisibility(View.GONE);
        } else {
            holder.radioButton1.setVisibility(View.VISIBLE);
        }
        holder.radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapSelect.get(position)) {
                    mapSelect.put(position, false);
                    if (mlistUseId != null) {
                        Log.d("Dong", "position ======" + position);
                        for (int i = 0; i < mlistUseId.size(); i++) {
                            if (mList.get(position).getUserid() == mlistUseId.get(i)) {
                                mlistUseId.remove(i);
                            }
                        }
                    }
                } else {
                    mapSelect.put(position, true);
                    //添加用户选择的userid
                    mlistUseId.add(mList.get(position).getUserid());
                    Log.d("Dong", "mliszie ---------." + mlistUseId.size());
                }
            }
        });
        holder.radioButton1.setChecked(mapSelect.get(position));
        return convertView;
    }

    class ViewHolder {
         TextView tvDate,tvUsername;
         CheckBox radioButton1;
    }
}
