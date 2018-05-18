package android.sgz.com.widget;

import android.app.Dialog;
import android.content.Context;
import android.sgz.com.R;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class MyDialog extends Dialog {

    private TextView titleTxv;        //标题
    private TextView msgTxv;        //提示内容文字
    private TextView positiveTxv;        //确定按钮
    private TextView negativeTxv;        //取消按钮
    private Context mContext;
    /**
     * @param context
     */
    public MyDialog(Context context) {
        super(context, R.style.CustomDialog);        //自定义style主要去掉标题，标题将在setCustomView中自定义设置
        setCustomView();
        mContext = context;
    }

    public MyDialog(Context context, boolean cancelable,
                    OnCancelListener cancelListener) {
        super(context, R.style.CustomDialog);
        this.setCancelable(cancelable);
        this.setOnCancelListener(cancelListener);
        setCustomView();
        mContext = context;
    }

    public MyDialog(Context context, int theme) {
        super(context, R.style.CustomDialog);
        setCustomView();
        mContext = context;
    }

    /**
     * 设置整个弹出框的视图
     */
    private void setCustomView(){
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, null);
        titleTxv = (TextView) mView.findViewById(R.id.title);
        msgTxv = (TextView) mView.findViewById(R.id.message);
        positiveTxv = (TextView) mView.findViewById(R.id.positiveButton);
        negativeTxv = (TextView) mView.findViewById(R.id.negativeButton);
        super.setContentView(mView);
    }

    @Override
    public void setContentView(View view){
        //重写本方法，使外部调用时不可破坏控件的视图。
        //也可以使用本方法改变CustomDialog的内容部分视图，比如让用户把内容视图变成复选框列表，图片等。这需要获取mView视图里的其它控件
    }

    /**
     * 设置提示内容文字
     * @param msg
     */
    public void setMessage(CharSequence msg){
        msgTxv.setText(msg);
    }

    /**
     * 确定键监听器
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        positiveTxv.setOnClickListener(listener);
    }

    /***
     * 确定监听器
     * @param listener
     * @param str 设置按钮文字
     */
    public void setOnPositiveListener(View.OnClickListener listener,String str){
        positiveTxv.setText(str);
        positiveTxv.setOnClickListener(listener);
    }

    /**
     * 取消键监听器
     * @param listener
     */
    public void setOnNegativeListener(View.OnClickListener listener){
        negativeTxv.setOnClickListener(listener);
    }

    /****
     * 取消键监听器
     * @param listener
     * @param isInvisible 是否隐藏取消按钮
     */
    public void setOnNegativeListener(View.OnClickListener listener,boolean isInvisible){
        negativeTxv.setOnClickListener(listener);
        if (isInvisible) {
            negativeTxv.setVisibility(View.GONE);
        }
    }
}
