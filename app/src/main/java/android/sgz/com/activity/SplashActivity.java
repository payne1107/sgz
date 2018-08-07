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
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.LoginSucessBean;
import android.sgz.com.bean.RefreshTokenBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.NetWorkUtils;
import android.sgz.com.utils.SPUtil;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.MyDialog;
import android.util.Log;
import android.view.View;

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
        //每次进入app进行赋值
        MyApplication.isLogin = SPUtil.getString(mContext, "token");
        MyApplication.refreshToken = SPUtil.getString(mContext, "refresh_token");
        MyApplication.userId = SPUtil.getString(mContext, "userId");
        MyApplication.userPhone = SPUtil.getString(mContext, "userPhone");
    }

    @Override
    protected void initData() {
        PermissionGen.with(SplashActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething(){
        //延时跳转到主页面，splash用来做引导页
        if (StringUtils.isEmpty(MyApplication.refreshToken)) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //判断是否当前有可用网络
                    if (!NetWorkUtils.isNetworkConnected(mContext)) {
                        showSetNetworkDialog();
                        return;
                    }
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                }
            },3000);
        } else {
            refreshToken();
        }
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething(){
        final MyDialog dialog = new MyDialog(this);
        dialog.setMessage("定位权限，存储权限是必选项，全部开通才可以正常使用");
        dialog.setCancelable(false);
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                dialog.dismiss();
            }
        });

        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionGen.with(SplashActivity.this)
                        .addRequestCode(100)
                        .permissions(
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                        .request();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    /***
     * 跳转到主页面
     */
    private void toIntent() {
        if (StringUtils.isEmpty(MyApplication.isLogin)) {
            //说明没有登录过
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
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

    /****
     * 刷新token
     */
    private void refreshToken() {
        Map<String, String> params = new HashMap<>();
        params.put("refreshcode", MyApplication.refreshToken);
        httpPostRequest(ConfigUtil.REFRESH_TOKEN_URL, params, ConfigUtil.REFRESH_TOKEN_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.REFRESH_TOKEN_URL_ACTION:
                Log.d("Dong", "刷新token === " +json);
                handleFreshToken(json);
                break;


        }
    }


    /****
     * 刷新token处理
     * @param json
     */
    private void handleFreshToken(String json) {
        if (getRequestCode(json) == 1) {
            //刷新成功
            RefreshTokenBean tokenBean = JSON.parseObject(json, RefreshTokenBean.class);
            if (tokenBean != null) {
                String refreshToken = tokenBean.getRefreshMsg();
                String token = tokenBean.getResultMsg();
                SPUtil.putString(mContext, "token", token);
                SPUtil.putString(mContext, "refresh_token", refreshToken);
                MyApplication.isLogin = token;
                MyApplication.refreshToken = refreshToken;
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }
        } else if (getRequestCode(json) == 18011) {
            startActivity(new Intent(mContext, LoginActivity.class));
            finish();
        }
    }
}
