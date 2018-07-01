package android.sgz.com.widget;

import android.view.View;

/**
 * Created by 92457 on 2018/2/9.
 * RecycleView监听 自定义监听
 */

public interface IRecycleViewOnItemClickListener {
    /****
     * 设置item点击回掉事件
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);


    /*****
     * 设置item长按点击事件
     */
    void onLongItemClick(View view, int position);
}
