package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.TenderListBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/5/3.
 */

public class TenderInfomationFragmentAdapter  extends BaseAdapter{
    private Context mContext;
    private List<TenderListBean.DataBean.ListBean> mList;
    public TenderInfomationFragmentAdapter(Context context, List<TenderListBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<TenderListBean.DataBean.ListBean> data) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tender_infomation_fragment, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.tvLinkPerson = (TextView) convertView.findViewById(R.id.tv_link_person);
            holder.tvNumber = convertView.findViewById(R.id.tv_number);
            holder.tvOwner = convertView.findViewById(R.id.tv_owner);
            holder.tvPhone = convertView.findViewById(R.id.tv_phone);
            holder.tvCompany = convertView.findViewById(R.id.tv_company);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TenderListBean.DataBean.ListBean data = mList.get(position);
        if (data != null) {
            holder.tvLinkPerson.setText(""+data.getLinkname());
            holder.tvNumber.setText(""+data.getTenderno());
            holder.tvOwner.setText(""+data.getProcurement());//采购业主
            holder.tvCompany.setText(""+data.getTendercompany());
            holder.tvPhone.setText(""+data.getMobile());
        }
        return convertView;
    }

    class ViewHolder{
        TextView tvLinkPerson,tvNumber,tvOwner,tvCompany,tvPhone;
    }
}
