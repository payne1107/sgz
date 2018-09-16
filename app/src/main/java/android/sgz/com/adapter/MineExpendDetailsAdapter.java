package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.MineExpendDetailsBean;
import android.sgz.com.widget.IRecycleViewOnItemClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.route.RidePath;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/6/24.
 */

public class MineExpendDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private List<MineExpendDetailsBean.DataBean> mList;

    public MineExpendDetailsAdapter(Context context, List<MineExpendDetailsBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<MineExpendDetailsBean.DataBean> data) {
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_expend_details, null);
            view.setTag(holder);
            holder.tvUserName = view.findViewById(R.id.tv_username);
            holder.tvPhone =view.findViewById(R.id.tv_phone);
            holder.tvAllSalary =view.findViewById(R.id.tv_all_salary);
            holder.tvAddSalary =view.findViewById(R.id.tv_add_salary);
            holder.allPaySalary =view.findViewById(R.id.tv_all_pay_salary);
            holder.layoutPaySalary =view.findViewById(R.id.layout_pay_salary);
            holder.tvToBePay =view.findViewById(R.id.tv_to_be_pay);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MineExpendDetailsBean.DataBean bean = mList.get(position);
        if (bean != null) {
            String name =bean.getRealname();
            String mobile =bean.getMobile();
            String paymentSalary =bean.getPaymentsalary();
            String allSalary =bean.getAllsalary();
            String addSalary = bean.getAlladdsalary();
            holder.tvUserName.setText("" + name);
            holder.tvPhone.setText("" + mobile);
            //总工资 = 总工资+ 加班费
            Double allSa = Double.parseDouble(allSalary) + Double.parseDouble(addSalary);
            //剩余需要支付工资 总工资-已支付工资
            Double toBePay = allSa - Double.parseDouble(paymentSalary);

            holder.tvAllSalary.setText("" + allSa);
            holder.tvAddSalary.setText("加班工资：" + addSalary);
            holder.allPaySalary.setText("已支付:" + paymentSalary);

            holder.tvToBePay.setText("剩余支付工资：" + toBePay);
        }
        holder.layoutPaySalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v,position);
            }
        });

        return view;
    }

    class ViewHolder {
        TextView tvUserName,tvPhone,tvAllSalary,tvAddSalary,allPaySalary;
        AutoLinearLayout layoutPaySalary;
        public TextView tvToBePay;
    }

    private IRecycleViewOnItemClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IRecycleViewOnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
