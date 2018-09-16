package android.sgz.com.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.autolayout.AutoLinearLayout;

/***
 * 安全答题
 */
public class SafeAnwserActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private String shareUrl = "http://www.52sgz.com/share/login.html?type=answer&referee=";
    private Context mContext;
    private Dialog dialog;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_safe_anwser);
        mContext = SafeAnwserActivity.this;
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("安全答题", true, true);
        tvSet.setVisibility(View.VISIBLE);
        tvSet.setText("分享");


        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://52sgz.com/share/answer/");
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        //设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //设置是否出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.addJavascriptInterface(new PersonDetails(), "hello");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        tvSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                showInviteDialog();
                break;
            case R.id.ll_share_weixin:
                shareAction(SHARE_MEDIA.WEIXIN, mContext, shareUrl, MyApplication.userPhone, "蒙一下么可以得10元钱，快来试一下，让你的安全知识变成钱");
                dialog.dismiss();
                break;
            case R.id.ll_share_wx_space:
                shareAction(SHARE_MEDIA.WEIXIN_CIRCLE,mContext,shareUrl, MyApplication.userPhone,"蒙一下么可以得10元钱，快来试一下，让你的安全知识变成钱");
                dialog.dismiss();
                break;
            case R.id.ll_share_qq:
                shareAction(SHARE_MEDIA.QQ, mContext, shareUrl, MyApplication.userPhone, "蒙一下么可以得10元钱，快来试一下，让你的安全知识变成钱");
                dialog.dismiss();
                break;
        }
    }



    /***
     * 邀请对话框
     */
    private void showInviteDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_bdjz_shared, null);
        dialog = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y =this.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        AutoLinearLayout layoutShareWx = (AutoLinearLayout) view.findViewById(R.id.ll_share_weixin);
        AutoLinearLayout layoutShareWXSpace = (AutoLinearLayout) view.findViewById(R.id.ll_share_wx_space);
        AutoLinearLayout layoutShareQQ = (AutoLinearLayout) view.findViewById(R.id.ll_share_qq);
        layoutShareWx.setOnClickListener(this);
        layoutShareWXSpace.setOnClickListener(this);
        layoutShareQQ.setOnClickListener(this);
    }



    public class PersonDetails{
        @JavascriptInterface
        public void javaMethod(String p){
            Log.d("Dong" , "JSHook.JavaMethod() called! + "+p);
        }

        @JavascriptInterface
        public void showAndroid(){
            final String info = "来自手机内的内容！！！";
            toastMessage("" +info);
//            SafeAnwserActivity.this.runOnUiThread(new Runnable(){
//                @Override
//                public void run() {
//                    webView.loadUrl("javascript:show('" + MyApplication.isLogin + "')");
//                }
//            });
        }
        @JavascriptInterface
        public void getToken() {
            SafeAnwserActivity.this.runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    webView.loadUrl("javascript:show('" + MyApplication.isLogin + "')");
                }
            });

        }

        @JavascriptInterface
        public String getInfo(){
            return "获取手机内的信息！！";
        }
    }
}
