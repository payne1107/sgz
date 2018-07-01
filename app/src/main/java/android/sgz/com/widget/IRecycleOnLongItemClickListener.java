package android.sgz.com.widget;

import android.view.View;

/**
 * Created by WD on 2018/7/1.
 * recycleview长按事件
 */

public interface IRecycleOnLongItemClickListener {
    /****
     * 设置item点击回掉事件
     * @param view
     * @param position
     */
    void onLongItemClick(View view, int position);
}
