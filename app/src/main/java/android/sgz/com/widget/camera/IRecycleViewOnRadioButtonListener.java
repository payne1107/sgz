package android.sgz.com.widget.camera;

import android.view.View;
import android.widget.CompoundButton;

/**
 * Created by 92457 on 2018/2/9.
 * RecycleView监听 自定义监听
 */

public interface IRecycleViewOnRadioButtonListener {
    /****
     * 设置item点击回掉事件
     * @param buttonView
     * @param position
     * @param  isCheck
     */
    void onRadioButtonClick(CompoundButton buttonView, boolean isCheck,int position);


}
