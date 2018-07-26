package android.sgz.com.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


/**
 * Created by Android Studio
 * User: weidong
 * Date: 2016-04-27
 * Time: 9:57
 * Email: 929728742@qq.com
 * Description: 会话页面 聊天页面
 */
public class ConversationActivity extends BaseActivity {

    private static final String TAG = ConversationActivity.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_conversation);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        String sTargetId = getIntent().getData().getQueryParameter("targetId");
        String sTitle = getIntent().getData().getQueryParameter("title");
        setInVisibleTitleIcon(sTitle, true, false);
    }
}
