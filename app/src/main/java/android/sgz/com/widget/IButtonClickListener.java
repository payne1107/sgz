package android.sgz.com.widget;

import android.view.View;

/**
 * Created by 92457 on 2018/2/9.
 * RecycleView监听 自定义监听
 */

public interface IButtonClickListener {
    /****
     * 编辑事件
     * @param view
     * @param position
     */
    void onEditClick(View view, int position);


    /*****
     * 删除事件
     */
    void onDeleClick(View view, int position);

    /*****
     * 设为满员事件
     */
    void onSaveClick(View view, int position);
}
