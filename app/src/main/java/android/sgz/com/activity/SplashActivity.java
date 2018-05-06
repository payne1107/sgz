package android.sgz.com.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.NetWorkUtils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 92457 on 2018/2/2.
 */

public class SplashActivity extends BaseActivity {

    private Context mContext;
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        //延时跳转到主页面，splash用来做引导页
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否当前有可用网络
                if (!NetWorkUtils.isNetworkConnected(mContext)) {
                    showSetNetworkDialog();
                    return;
                }
                toMainIntent();
            }
        },2000);
    }

    /***
     * 跳转到主页面
     */
    private void toMainIntent() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /***
     * 提示网络状态不可用，并进行设置
     */
    private void showSetNetworkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.str_set_internet);
        builder.setMessage(R.string.str_internet_error);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.str_set_internet, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_DATA_ROAMING_SETTINGS);
                // 如果在设置完成后需要再次进行操作，可以重写操作代码，在这里不再重写
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        if (!this.isFinishing()) {
            builder.create().show();
        }
    }
}
