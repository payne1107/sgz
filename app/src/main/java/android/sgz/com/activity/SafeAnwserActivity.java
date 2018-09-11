package android.sgz.com.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/***
 * 安全答题
 */
public class SafeAnwserActivity extends BaseActivity{

    private WebView webView;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_safe_anwser);
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("安全答题", true, true);

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
        webView.addJavascriptInterface(new PersonDetails(), "hello");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public class PersonDetails{
        @JavascriptInterface
        public void javaMethod(String p){
            Log.d("Dong" , "JSHook.JavaMethod() called! + "+p);
        }

        @JavascriptInterface
        public void showAndroid(){
            String info = "来自手机内的内容！！！";
            webView.loadUrl("javascript:show('" + info + "')");
        }

        @JavascriptInterface
        public void getToken() {
            webView.loadUrl("javascript:show('" + "22222222222" + "')");
        }

        @JavascriptInterface
        public String getInfo(){
            return "获取手机内的信息！！";
        }
    }
}
