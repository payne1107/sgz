package android.sgz.com.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.bean.FieldErrors;
import android.sgz.com.bean.UploadImgBean;
import android.sgz.com.httpstack.OkHttpStack;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.LoadingDailog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 92457 on 2018/4/16.
 */

public abstract class BaseFragment extends Fragment {
    public View mRootView;
    //加载对话框
    public LoadingDailog loadingDailog;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = onCustomCreateView(inflater, container, savedInstanceState);
        return mRootView;
    }

    /***
     * 子类实现该方法，初始化布局
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public abstract View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    /****************************************网络请求框架Start******************************************************/
    public void httpGetRequest(String url, final int action) {
        url = url + "?appkey=00056cb1d74435076a4c15a490798df9";//每次请求必须添加的参数
        Log.e("Dong", url);
        if (MyApplication.mRequestQueue == null) {
            MyApplication.mRequestQueue = Volley.newRequestQueue(getActivity(), new OkHttpStack());
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
//        params.put("appkey", "00056cb1d74435076a4c15a490798df9");//每次请求必须添加的参数
        Log.d("Dong", "入参---》"+params.toString());
        if (MyApplication.mRequestQueue == null)
            MyApplication.mRequestQueue = Volley.newRequestQueue(getActivity(), new OkHttpStack());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String content) {
                stopIOSDialogLoading(getActivity());
                httpOnResponse(content, action);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                stopIOSDialogLoading(getActivity());
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
//                    headers.put("Cookie", ConfigUtil.sessionId);//添加消息头
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                headers.put("Accept", "application/json");
                Log.d("Dong", "fragment token = " +MyApplication.isLogin);
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
                    String rawCookies = responseHeaders.get("Set-Cookie");//获取消息头
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
     * 返回数据处理
     *
     * @param json
     * @param action
     */
    public void httpOnResponse(String json, int action) {
        Log.e("httpOnResponsejson", json);
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (jsonObject == null) {
                return;
            }
            String code = jsonObject.getString("resultCode");
            FieldErrors error = null;
            if (code.equals("1")) {
                Object data = jsonObject.get("data");
                if (data instanceof JSONObject) {
                    JSONObject jsonData = (JSONObject) data;
                    String jsonString = jsonData.toJSONString();
                    httpResponse(jsonString, action);
                } else if (data instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) data;
                    String jsonString = jSONArray.toJSONString();
                    httpResponse(jsonString, action);
                } else if (data == null) {
                    httpResponse("", action);
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
     * 请求异常
     *
     * @param code
     * @param arg3
     * @param action
     */

    public void errorResponseHandler(int code, Throwable arg3, int action) {
        switch (code) {
            case 0:
                Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_SHORT).show();
                break;
            case 404:
            case 400:
                Toast.makeText(mContext, "未找到请求地址", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(mContext, "code = " + code + " message = " + arg3.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
        httpError(null, action);
    }

    /**
     * 返回数据
     *
     * @param json
     * @param action
     */
    protected void httpResponse(String json, int action) {

    }

    /**
     * 返回Error对象
     *
     * @param error
     * @param action
     */
    protected void httpError(FieldErrors error, int action) {
        if (null != error) {
            Toast.makeText(getActivity(), error.resultMsg, Toast.LENGTH_SHORT).show();
        }
    }
    /****************************************网络请求框架End******************************************************/
    /***
     * 终止正在加载对话框
     * @param  activity 上下文
     */
    public void stopIOSDialogLoading(final Activity activity) {
        if (null != activity && activity.isFinishing()) {
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadingDailog != null && loadingDailog.isShowing() && null != activity && !activity.isFinishing()) {
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
    public void startIOSDialogLoading(Context mContext, String msg) {
        LoadingDailog.Builder builder = new LoadingDailog.Builder(mContext)
                .setMessage(msg)
                .setCancelable(false).setCancelOutside(true);
        if (null == loadingDailog) {
            loadingDailog = builder.create();
            if (loadingDailog != null && !loadingDailog.isShowing()) {
                loadingDailog.show();
            }
        }
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
        httpUtils.send(HttpRequest.HttpMethod.POST, "url------->", params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                stopIOSDialogLoading(getActivity());
                String json = (String) responseInfo.result;
                UploadImgBean uploadImgBean = JSON.parseObject(json, UploadImgBean.class);
                if (uploadImgBean != null) {
                    if (uploadImgBean.getFlag() == 0) { //上传成功
                        UploadImgBean.DataBean data = uploadImgBean.getData();
                        if (data != null) {
                            String path = data.getPath();
                            getImageUrl(path);
                        }
                    } else {
                        Toast.makeText(getActivity(), "图片上传失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                stopIOSDialogLoading(getActivity());
                Toast.makeText(getActivity(), "图片上传失败", Toast.LENGTH_SHORT).show();//请求上传图片接口失败
            }
        });
    }

    protected void getImageUrl(String url) {

    }

    //电话咨询
    public void callPhoneConsult(String phone) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:" + phone));
        intent.setAction(Intent.ACTION_CALL);
        startActivity(intent);
    }

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

    /****
     * 设置数据为空显示的view
     * @param pullToRefreshListView
     * @param  isVisiable 如果等True显示无数据的view反之隐藏
     *                    隐藏无数据的view只需要将listview设置显示即可
     */
    public void setEmptyView(PullToRefreshListView pullToRefreshListView,boolean isVisiable) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_empty, null);
        if (pullToRefreshListView != null && isVisiable) {
            pullToRefreshListView.setEmptyView(view);
        } else {
            pullToRefreshListView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /***
     * 隐藏标题栏图标
     * @param title  标题
     * @param isVisible 是否隐藏图标 True 隐藏 false 显示
     * @param isBack 是否显示back
     */
    public void setInVisibleTitleIcon(String title,boolean isVisible,boolean isBack) {
        TextView tvTitle = (TextView) mRootView.findViewById(R.id.activity_title);
        ImageView ivBack = (ImageView) mRootView.findViewById(R.id.activity_back);
        if (isVisible) {
            tvTitle.setCompoundDrawables(null, null, null, null);
        }
        if (!isBack) {
            ivBack.setVisibility(View.GONE);
        }
        tvTitle.setText(title);
    }
}
