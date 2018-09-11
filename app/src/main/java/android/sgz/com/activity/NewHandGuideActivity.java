package android.sgz.com.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/***
 * 新手引导
 */
public class NewHandGuideActivity extends BaseActivity {
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_hand_guide);
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("新手指南", true,true);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://52sgz.com/share/guide.html");
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        //设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //设置是否出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this,"android");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

}
