package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.ContactsBean;
import android.sgz.com.bean.PaymentByProjectBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/6/24.
 */

public class MineExpendAdapter extends BaseAdapter {
    private Context mContext;
    private List<PaymentByProjectBean.DataBean.ListBean> mList;

    public MineExpendAdapter(Context context, List<PaymentByProjectBean.DataBean.ListBean> list) {
        this.mList = list;
        this.mContext = context;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<PaymentByProjectBean.DataBean.ListBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_expend, null);
            view.setTag(holder);
            holder.tvProjectTitle =view.findViewById(R.id.tv_project_title);
            holder.tvWorkPersonNum =view.findViewById(R.id.tv_work_person_num);
            holder.tvWorkDays = view.findViewById(R.id.tv_work_days);
            holder.tvAvgSalary = view.findViewById(R.id.tv_avgsalary);
            holder.tvSalaryTotal=view.findViewById(R.id.tv_salart_total);
            holder.tvAllPaySalary =view.findViewById(R.id.tv_all_pay_salary);
            holder.tvAddWorkDays =view.findViewById(R.id.tv_add_work_days);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        PaymentByProjectBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            String projectName = bean.getProjectname();
            int workPersonCount =bean.getWorkercount();///工人数
            int workdays =bean.getAlladdworkdays() ;//工作天数
            int addWorkDays =bean.getAlladdworkdays();//加班数
            String avgSalary =bean.getAvgsalay();//平均工资
            String allSalary =bean.getAllsalay();//总工资
            String allPaySalary = bean.getAllpaysalary();//已发工资

            holder.tvProjectTitle.setText("" + projectName);
            holder.tvAllPaySalary.setText("" + allPaySalary);
            holder.tvAvgSalary.setText(""+avgSalary+"/人");
            holder.tvSalaryTotal.setText("" + allSalary);
            holder.tvWorkDays.setText("" + workdays);
            holder.tvWorkPersonNum.setText("" + workPersonCount);
            holder.tvAddWorkDays.setText("" + addWorkDays);
        }
        return view;
    }

    class ViewHolder{

        public TextView tvProjectTitle,tvAddWorkDays;
        public TextView tvWorkPersonNum,tvWorkDays,tvAvgSalary,tvSalaryTotal,tvAllPaySalary;
    }
}
