package android.sgz.com.base;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.bean.FieldErrors;
import android.sgz.com.httpstack.OkHttpStack;
import android.sgz.com.utils.CacheImgUtil;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.NetWorkUtils;
import android.sgz.com.utils.OpenApkfile;
import android.sgz.com.utils.OpenLocalMapUtil;
import android.sgz.com.utils.PermissionUtils;
import android.sgz.com.utils.SPUtil;
import android.sgz.com.utils.StatusUtils;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.LoadingDailog;
import android.sgz.com.widget.MyDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 92457 on 2018/1/12.
 */

public abstract class BaseActivity extends FragmentActivity {

    private static final int NOTIFICATION_ID = 111;
    private Activity mContext;
    //加载对话框
    public LoadingDailog loadingDailog;
    //标题栏布局
    private RelativeLayout mRlChild;
    public LinearLayout layoutPersonCenter;
    private TextView tvTitle;
    private ImageView ivBack;
    public TextView tvSet;
    private String locationSrc;
    private NotificationManager manager;
    private NotificationCompat.Builder builder;
    public boolean isHandUpdateApk = false;
    protected TimePickerDialog dialogDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateCustom(savedInstanceState);
        initView();
        initData();
        mContext = BaseActivity.this;
    }

    /***
     * 子类可以实现这个方法填充View
     * @param savedInstanceState
     */
    protected abstract void onCreateCustom(Bundle savedInstanceState);

    /***
     * 子类实现此方法,填充数据
     */
    protected abstract void initData();

    /***
     * 子类可以重写这个方法，初始化view
     */
    protected void initView() {
        mRlChild = (RelativeLayout) findViewById(R.id.rl_child);
        layoutPersonCenter = (LinearLayout) findViewById(R.id.layout_person_center);
        tvTitle = (TextView) findViewById(R.id.activity_title);
        ivBack = (ImageView) findViewById(R.id.activity_back);
        tvSet = (TextView) findViewById(R.id.activity_set);
    }

    /************************************************网络请求框架Start*************************************************/
    /**
     * get请求
     */
    public void httpGetRequest(String url, final int action) {
        Log.e("url", url);
        if (MyApplication.mRequestQueue == null) {
            MyApplication.mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String content) {
                        httpOnResponse(content, action);
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                errorResponseHandler(error.networkResponse == null ? 0 : error.networkResponse.statusCode, error, action);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                if (!StringUtils.isEmpty(ConfigUtil.sessionId))
                    headers.put("Cookie", ConfigUtil.sessionId);
                return headers;
            }

            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                // TODO Auto-generated method stub
                try {

                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    String dataString = new String(response.data, "UTF-8");
                    if (!StringUtils.isEmpty(rawCookies)) {
                        String[] cookies = rawCookies.split(";");
                        if (StringUtils.isEmpty(ConfigUtil.sessionId) && !StringUtils.isEmpty(cookies[0])) {
                            ConfigUtil.sessionId = cookies[0];
                        }
                    }
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        MyApplication.mRequestQueue.add(stringRequest);
    }

    /**
     * post请求
     */
    public void httpPostRequest(String url, final Map<String, String> params, final int action) {
//        params.put("appkey", "00056cb1d74435076a4c15a490798df9");
        Log.d("Dong", params.toString());
        Log.d("Dong", "Myapplication =" + MyApplication.isLogin);
        if (MyApplication.mRequestQueue == null)
            MyApplication.mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    public void onResponse(String content) {
                        stopIOSDialogLoading(mContext);
                        httpOnResponse(content, action);
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                stopIOSDialogLoading(mContext);
                errorResponseHandler(error.networkResponse == null ? 0 : error.networkResponse.statusCode, error, action);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                if (!StringUtils.isEmpty(ConfigUtil.sessionId))
//                    headers.put("Cookie", ConfigUtil.sessionId);
//                Log.d("Dong", "ConfigUtil.sessionId ================== " + ConfigUtil.sessionId);
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                headers.put("Accept", "application/json");
                if (!StringUtils.isEmpty(MyApplication.isLogin)) {
                    headers.put("Authorization", MyApplication.isLogin);
                }
                return headers;
            }

            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                // TODO Auto-generated method stub
                try {
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    String dataString = new String(response.data, "UTF-8");
                    if (!StringUtils.isEmpty(rawCookies)) {
                        String[] cookies = rawCookies.split(";");
                        Log.d("Dong", "cookies ================== " + cookies[0] +" ,,,, cooks ==== " +cookies.toString());
                        if (StringUtils.isEmpty(ConfigUtil.sessionId) && !StringUtils.isEmpty(cookies[0])) {
                            ConfigUtil.sessionId = cookies[0];
                        }
                    }
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        MyApplication.mRequestQueue.add(stringRequest);
    }

    /****
     * 请求异常
     * @param code
     * @param error
     * @param action
     */
    protected void errorResponseHandler(int code, Throwable error, int action) {
        switch (code) {
            case 0:
                Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT).show();
                break;
            case 404:
            case 400:
                Toast.makeText(this, "未找到请求地址", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "code = " + code + " message = " + error.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
        httpError(null, action);
    }


    /***
     * 请求返回数据处理
     * @param json 返回的json
     * @param action  标识
     */
    protected void httpOnResponse(String json, int action) {
        Log.e("httpOnResponsejson", json);
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (jsonObject == null) {
                return;
            }
            int code = (int) jsonObject.get("flag");
            FieldErrors error = null;
           if (code == 0) {
                Object data = jsonObject.get("data");
                if (data instanceof JSONObject) {
                    JSONObject jsonData = (JSONObject) data;
                    String jsonString = jsonData.toJSONString();
                    httpResponse(jsonString, action);
                } else if (data instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) data;
                    String jsonString = jSONArray.toJSONString();
                    httpResponse(jsonString, action);
                } else {
                    httpResponse("" + data, action);
                }
            } else {
                error = JSON.parseObject(json, FieldErrors.class);
                if (error != null) {
                    httpError(error, action);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 返回Error对象
     * @param error
     * @param action
     */
    protected void httpError(FieldErrors error, int action) {
        if (null != error) {
            Toast.makeText(this, error.msg, Toast.LENGTH_SHORT).show();
        }
    }

    /****
     * 返回数据
     * @param jsonString
     * @param action
     */
    protected void httpResponse(String jsonString, int action) {

    }

    /***
     * 上传头像
     * @param userId
     * @param photoPath
     */
    public void uploadImg(String userId,String photoPath) {
        startIOSDialogLoading(mContext,"正在上传中..");
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configResponseTextCharset("UTF-8");
        RequestParams params = new RequestParams();
        params.addBodyParameter("file", new File(photoPath));
        params.addBodyParameter("userId", userId);
        params.addHeader("Accept", "application/json");
        if (!StringUtils.isEmpty(MyApplication.isLogin)) {
            params.addHeader("Authorization", MyApplication.isLogin);
        }
        httpUtils.send(HttpRequest.HttpMethod.POST, "url-------->", params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                stopIOSDialogLoading(mContext);
                String json = (String) responseInfo.result;
                Log.d("Dong", "上传图片== " + json);
//                UploadImgBean uploadImgBean = JSON.parseObject(json, UploadImgBean.class);
//                if (uploadImgBean.getCode() == 0) { //上传成功
//                    getImageUrl(uploadImgBean.getUrl());
//                } else {
//                    Toast.makeText(BaseActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                stopIOSDialogLoading(mContext);
                Toast.makeText(BaseActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();//请求上传图片接口失败
            }
        });
    }

    protected void getImageUrl(String url) {

    }

    /****
     * 上传多张图片和内容
     * @param path 图片路径
     * @param userId 用户Id
     * @param taskId 任务Id
     * @param stepId 步骤
     * @param contents 内容
     * @param  attendId 报名成功之后返回的id
     */
    public void uploadImg(List<String> path, String userId, int taskId, Set<String> stepId, List<String> contents,String attendId) {
        List<KeyValue> listPath = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            listPath.add(new KeyValue("file", new File(path.get(i))));
        }
        org.xutils.http.RequestParams par = new org.xutils.http.RequestParams("url------>");
        MultipartBody body = new MultipartBody(listPath, "utf-8");
        par.setRequestBody(body);
        par.setMultipart(true);
        par.addBodyParameter("taskId", String.valueOf(taskId));
        par.addBodyParameter("userId", userId);
        par.addHeader("Accept", "application/json");
        if (!StringUtils.isEmpty(MyApplication.isLogin)) {
            par.addHeader("Authorization", MyApplication.isLogin);
        }
        //遍历取出内容
        for (int i = 0; i < contents.size(); i++) {
            par.addParameter("contents", contents.get(i));
        }
        //遍历步骤Id
        Iterator<String> it = stepId.iterator();
        while (it.hasNext()) {
            par.addParameter("stepId", it.next());
        }
        par.addBodyParameter("attendId", attendId);

        Log.d("Dong", "入参-----> " + par.toString());
        startIOSDialogLoading(mContext,"正在上传中..");
        x.http().post(par, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                stopIOSDialogLoading(mContext);
                getImageUrl(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                stopIOSDialogLoading(mContext);
                Log.d("Dong", "上传失败----》" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                stopIOSDialogLoading(mContext);
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
    /************************************************网络请求框架End*************************************************/

    /**
     * 获取软件当前版本信息
     *
     * @return
     */
    private int getAppVersionCode() {
        try {
            String pkName = this.getPackageName();
            int versionCode = this.getPackageManager().getPackageInfo(pkName, 0).versionCode;
            return versionCode;
        } catch (Exception e) {

        }
        return 1;
    }

    /************************************************6.0动态权限相关start******************************************/
    /**
     * 是否得到权限
     * @param pmss 需要申请的权限
     * @return
     */
    public boolean isGetPermission(String pmss) {
        if (Build.VERSION.SDK_INT >= 23) {
            //没有申请过权限
            return PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, pmss);
        } else {
            //已经申请过权限
            return false;
        }
    }

    public void showCamera() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_CAMERA, mPermissionGrant);
    }

    public void getAccounts() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_GET_ACCOUNTS, mPermissionGrant);
    }

    public void callPhone() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_CALL_PHONE, mPermissionGrant);
    }

    public void readPhoneState() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_READ_PHONE_STATE, mPermissionGrant);
    }

    public void accessFineLocation() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_ACCESS_FINE_LOCATION, mPermissionGrant);
    }

    /**
     * 定位权限
     */
    public void accessCoarseLocation() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_ACCESS_COARSE_LOCATION, mPermissionGrant);
    }

    public void readExternalStorage() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_READ_EXTERNAL_STORAGE, mPermissionGrant);
    }

    public void writeExternalStorage() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE, mPermissionGrant);
    }

    public void recordAudio() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_RECORD_AUDIO, mPermissionGrant);
    }


    public PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_RECORD_AUDIO:
                    Toast.makeText(getApplicationContext(), "Result Permission Grant CODE_RECORD_AUDIO", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_GET_ACCOUNTS:
                    Toast.makeText(getApplicationContext(), "Result Permission Grant CODE_GET_ACCOUNTS", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_PHONE_STATE:
                    Toast.makeText(getApplicationContext(), "Result Permission Grant CODE_READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CALL_PHONE:
//                    Toast.makeText(getApplicationContext(), "Result Permission Grant CODE_CALL_PHONE", Toast.LENGTH_SHORT).show();
                    handleCallPhone();
                    break;
                case PermissionUtils.CODE_CAMERA:
                    handleShowCamera();
                    break;
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
                    Toast.makeText(getApplicationContext(), "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
                    handleAccessCoarseLocation();
                    break;
                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
                    handleReadExternalStorage();
                    break;
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
                    handleWriteExternalStorage();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 拨打电话权限处理结果
     */
    public void handleCallPhone() {

    }

    /**
     * 处理相机拍照权限返回结果
     */
    public void handleShowCamera() {

    }

    /**
     * 处理读取文件权限返回结果
     * <p>
     * 注意：此处是更新apk返回的权限处理结果
     */
    public void handleReadExternalStorage() {
    }

    /**
     * 定位返回结果
     * <p>
     * 注意：此处是更新apk返回的权限处理结果
     */
    public void handleAccessCoarseLocation() {
    }

    /**
     * 处理写入文件权限返回结果
     */
    public void handleWriteExternalStorage() {

    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }

    /***************************************************** 6.0动态权限相关end *********************************************************/

    /************************************************* 更新apk相关start **********************************************/

    /**
     * 是否更新apk
     */

    public void isUpdateApk() {
        PgyUpdateManager.register(this, new UpdateManagerListener() {
            @Override
            public void onNoUpdateAvailable() {
                if (isHandUpdateApk) {
                    Toast.makeText(BaseActivity.this, R.string.str_currently_for_new_version, Toast.LENGTH_SHORT).show();
                    setUpdateClickable();
                }
            }

            @Override
            public void onUpdateAvailable(String s) {
                AppBean appBean = getAppBeanFromString(s);
                MyApplication.SERVER_VERSION_NAME = appBean.getVersionName();
                if (Integer.parseInt(appBean.getVersionCode()) > getAppVersionCode()) {
                    OpenHintUpdate(appBean.getDownloadURL(), appBean.getReleaseNote(), appBean.getVersionCode());
                } else {
                    if (isHandUpdateApk) {
                        Toast.makeText(BaseActivity.this, R.string.str_currently_for_new_version, Toast.LENGTH_SHORT).show();
                    }
                    setUpdateClickable();
                }
            }
        });
    }

    /***
     * 正在下载中 button不可以再点击
     */
    public void setUpdateClickable() {

    }

    /**
     * 提示用户更新
     */
    private void OpenHintUpdate(final String url, final String log, final String code) {
        final MyDialog updateDialog = new MyDialog(this);
        updateDialog.setMessage(log);
        updateDialog.setCancelable(false);
        updateDialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
                setUpdateClickable();
            }
        },MyApplication.isForUpdate);
        updateDialog.setOnPositiveListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyApplication.isClickUpdateVersionBtn = true;
                if (!TextUtils.isEmpty(url)) {
                    locationSrc = CacheImgUtil.PATH_DATA_CACHE + "/"
                            + "sgz"
                            + code + ".apk";
                    File file = new File(locationSrc);
                    if (file.exists()) {
                        clear();
                        Log.d("Dong", "dialog click -------->>>>" +locationSrc);
                        Intent intent = OpenApkfile.openFile(locationSrc,BaseActivity.this);
                        startActivity(intent);
                        setUpdateClickable();
                    } else {
//                        boolean connect = WifiNetworkUtil.OpenNetworkSetting(BaseActivity.this);
                        boolean connect = NetWorkUtils.isWifiConnected(BaseActivity.this);
                        if (!connect) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(BaseActivity.this);
                            dialog.setMessage("亲，连接的不是无线网，下载将要消耗流量,是否继续下载？");
                            dialog.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (isGetPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                        writeExternalStorage();
                                    } else {
                                        download(url);
                                    }
                                    dialog.dismiss();
                                }
                            });
                            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (MyApplication.isForUpdate) {
                                        finish();
                                    }
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                            updateDialog.dismiss();
                            setUpdateClickable();
                            return;
                        }
                        if (isGetPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            writeExternalStorage();
                        } else {
                            download(url);
                        }
                    }
                    updateDialog.dismiss();
                }
            }

        },"更新");

        updateDialog.setCanceledOnTouchOutside(false);
        updateDialog.show();
        updateDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });
    }

    /**
     * 下载apk
     *
     * @param url
     */
    public void download(String url) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configResponseTextCharset("UTF-8");
        httpUtils.download(url, locationSrc, true, false, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                complateDownload();
                setUpdateClickable();
                MyApplication.IS_DOWNLOAD = false;
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(BaseActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                MyApplication.IS_DOWNLOAD = false;
                manager.cancel(NOTIFICATION_ID);
                setUpdateClickable();
            }

            @Override
            public void onLoading(long total, long current, boolean b) {
                try {
                    MyApplication.IS_DOWNLOAD = true;
                    int progress = (int) (100 * current / total);
                    downLoading(progress);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                startDownload();
            }
        });
    }

    /**
     * 开始下载通知
     */
    public void startDownload() {
        clear();
        initNotification();
        builder.setContentText("开始下载...").setContentTitle("下载");
        Notification n = builder.build();
        manager.notify(NOTIFICATION_ID, n);
    }

    /**
     * 正在下载的通知
     *
     * @param progress 进度
     * @throws InterruptedException
     */
    public void downLoading(int progress) throws InterruptedException {
        builder.setContentText("正在下载:" + progress + "%")
                .setProgress(100, progress, false).setContentTitle("下载中");
        Notification n1 = builder.build();
        manager.notify(NOTIFICATION_ID, n1);
        if (android.os.Build.VERSION.SDK_INT > 19) {
            Thread.sleep(50);
        }
    }

    /**
     * 初始化通知
     */
    public void initNotification() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        //适配8.0
//        if (Build.VERSION.SDK_INT >= 26) {
//
//            NotificationChannel channel = new NotificationChannel(channelID, channelName,NotificationManager.IMPORTANCE_HIGH);
//            manager.createNotificationChannel(channel);
//        }
        builder.setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                // .setNumber(number)//显示数量
                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                // .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)// ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                // .setDefaults(Notification.DEFAULT_LIGHTS)//
                // 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                // Notification.DEFAULT_ALL Notification.DEFAULT_SOUND 添加声音 //
                // requires VIBRATE permission
                .setSmallIcon(R.mipmap.dialog_loading_img);

    }

    /**
     * 清除通知
     */
    public void clear() {
        if (manager != null) {
            manager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            manager.cancel(NOTIFICATION_ID);
        }
    }

    /**
     * 完成下载通知
     */
    public void complateDownload() {
        clear();
        initNotification();
        builder.setContentText("下载完成	").setContentTitle("下载")
                .setLights(Color.BLUE, 1000, 1000).setAutoCancel(true);
        if (locationSrc != null) {
            Intent intent = OpenApkfile.openFile(locationSrc,BaseActivity.this);
            /**直接跳转到安装页面 motify Weidong 2018/5/10 start*/
//            PendingIntent pendingIntent = PendingIntent
//                    .getActivity(BaseActivity.this, 0, intent,
//                            PendingIntent.FLAG_CANCEL_CURRENT);
//            builder.setContentIntent(pendingIntent);
            startActivity(intent);
            /**直接跳转到安装页面 motify Weidong 2018/5/10 end*/
            locationSrc = null;
        }
        Notification n2 = builder.build();
        manager.notify(NOTIFICATION_ID, n2);
    }

    /************************************************* 更新apk相关end **********************************************/



    /***
     * 终止正在加载对话框
     * @param  activity 上下文
     */
    public void stopIOSDialogLoading(final Activity activity) {
        if (activity.isFinishing()) {
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadingDailog != null && loadingDailog.isShowing() && !activity.isFinishing()) {
                    loadingDailog.dismiss();
                    loadingDailog = null;
                }
            }
        }, 500);
    }

    /****
     * 启动对话框
     * @param mContext
     */
    public void startIOSDialogLoading(Context mContext,String msg) {
        LoadingDailog.Builder builder = new LoadingDailog.Builder(mContext)
                .setMessage(msg)
                .setCancelable(false).setCancelOutside(true);
        if (null == loadingDailog) {
            loadingDailog = builder.create();
            if (!loadingDailog.isShowing()) {
                loadingDailog.show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        if (mRlChild != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                        || StatusUtils.MIUISetStatusBarLightMode(this.getWindow(), true)
                        || StatusUtils.FlymeSetStatusBarLightMode(this.getWindow(), true)) {
                    StatusUtils.StatusBarLightMode(this);
                    //                    StatusUtils.setStatusBarColor(this, R.color.white);
                    StatusUtils.setStatusBarColor(this, R.color.color_62d);
                    setStatusBar();
                } else {
                    StatusUtils.setStatusBarColor(this, R.color.color_62d);
                    setStatusBar();
                }
            }
        }
    }
    /**
     * android 6.0 设置状态栏颜色
     */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void setStatusBar() {
        if (mRlChild == null) {
            return;
        }
        if (StatusUtils.MIUISetStatusBarLightMode(this.getWindow(), true)) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= 21) {
                window.setStatusBarColor(getResources().getColor(R.color.white));//状态栏颜色
            }
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRlChild.getLayoutParams();
            params.setMargins(0, getStatusBarHeight(), 0, 0);
        } else {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRlChild.getLayoutParams();
            params.setMargins(0, getStatusBarHeight(), 0, 0);
        }
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int statusBarHeight = 0;
        if (statusBarHeight == 0) {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
        }
        return statusBarHeight;
    }


    /***
     * 隐藏标题栏图标
     * @param title  标题
     * @param isVisible 是否隐藏图标 True 隐藏 false 显示
     * @param  isVisibleBack 是否显示返回键
     *
     */
    public void setInVisibleTitleIcon(String title,boolean isVisible,boolean isVisibleBack) {
        if (isVisible) {
            tvTitle.setCompoundDrawables(null, null, null, null);
        }
        if (isVisibleBack) {
            ivBack.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
        }
        tvTitle.setText(title);
    }

    /***
     * 设置标题是否显示跳转个人中心按钮
     */
    public void setVisibleSetting(boolean isVisible) {
        if (isVisible) {
            layoutPersonCenter.setVisibility(View.VISIBLE);
        } else {
            layoutPersonCenter.setVisibility(View.GONE);
        }
    }
    /***
     * 显示设置按钮
     */
    public void setSettingBtn(String text) {
        tvSet.setText(text);
        tvSet.setTextColor(getResources().getColor(R.color.white));
        tvSet.setVisibility(View.VISIBLE);
    }
    /**
     * 返回
     * @param view
     */
    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.finish_activity_back_in, R.anim.finish_activity_back_out);
    }

    /****
     * 弹出Toast
     * @param message
     */
    public void toastMessage(String message) {
        View toastLayout = getLayoutInflater().inflate(R.layout.sys_toast, null);
        TextView tv = (TextView) toastLayout.findViewById(R.id.toast);
        tv.setText(message);
        final Toast toast = new Toast(getApplicationContext());
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
//        toast.setGravity(Gravity.BOTTOM, 0, height / 3);
        toast.setView(toastLayout);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    /***
     * 获取请求返回的code
     * @param json
     * @return
     */
    public int getRequestCode(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.getInteger("flag");
    }

    /**************************************************地图导航begain**********************************************/
    /**
     * 打开高德地图
     */
    private static String APP_NAME = "OPenLocalMapDemo";
    private static String SRC = "thirdapp.navi.beiing.openlocalmapdemo";
    protected void openGaoDeMap(double slat, double slon, String sname, double dlat, double dlon, String dname) {
        try {
            String uri = OpenLocalMapUtil.getGdMapUri(APP_NAME, String.valueOf(slat), String.valueOf(slon),
                    sname, String.valueOf(dlat), String.valueOf(dlon), dname);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.autonavi.minimap");
            intent.setData(Uri.parse(uri));
            startActivity(intent); //启动调用
            //  }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  打开百度地图
     */
    protected void openBaiduMap(double slat, double slon, String sname, double dlat, double dlon, String dname, String city) {
        try {
            String uri = OpenLocalMapUtil.getBaiduMapUri(String.valueOf(slat), String.valueOf(slon), sname,
                    String.valueOf(dlat), String.valueOf(dlon), dname, city, SRC);
            Intent intent = Intent.parseUri(uri, 0);
            startActivity(intent); //启动调用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开浏览器进行百度地图导航
     */
    protected void openWebMap(double slat, double slon, String sname, double dlat, double dlon, String dname, String city){
        Uri mapUri = Uri.parse(OpenLocalMapUtil.getWebBaiduMapUri(String.valueOf(slat), String.valueOf(slon), sname,
                String.valueOf(dlat), String.valueOf(dlon),
                dname, city, APP_NAME));
        Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
        startActivity(loction);
    }
    /******************************************************* end *****************************************/


    /****
     * 设置数据为空显示的view
     * @param pullToRefreshListView
     */
    private View view = null;
    public void setEmptyView(PullToRefreshListView pullToRefreshListView) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_empty, null);
            if (pullToRefreshListView != null) {
                pullToRefreshListView.setEmptyView(view);
            }
        }
    }

    /**
     * 获取软件当前版本信息
     * @return
     */
    public String getAppVersionName() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1.0";
    }

    /****
     * 清除应用所有的缓存数据
     */
    public void clearAppData() {
        SPUtil.remove(mContext, "token");
        SPUtil.remove(mContext, "userId");
        MyApplication.isLogin = "";
        MyApplication.userId = "";
    }

    /**
     * 初始化时间选择器
     */
    protected void initViewDateDialog(OnDateSetListener listener,long currentMillSeconds) {
        dialogDay = new TimePickerDialog.Builder()
                .setCallBack(listener)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - ConfigUtil.TenYears8)
                .setMaxMillseconds(System.currentTimeMillis() +   ConfigUtil.TenYears)
                .setCurrentMillseconds(currentMillSeconds /*System.currentTimeMillis() - ConfigUtil.TenYears*/)//设置当前日期
                .setThemeColor(getResources().getColor(R.color.cccccc))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSize(16)
                .build();
    }


    /****
     * 隐藏虚拟键盘
     */
    public void hideKeyboard() {
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
