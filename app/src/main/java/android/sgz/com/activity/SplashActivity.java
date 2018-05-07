package android.sgz.com.activity;

import android.Manifest;
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
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

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
        PermissionGen.with(SplashActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething(){
        Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();
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

    @PermissionFail(requestCode = 100)
    public void doFailSomething(){
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
        PermissionGen.with(SplashActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request();
    }

    @Override
    protected void onResume() {
        super.onResume();

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
