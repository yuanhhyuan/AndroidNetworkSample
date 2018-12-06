package com.hy.h5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.hy.h5.model.FriendsZone;
import com.hy.okhttp3.R;

import java.util.ArrayList;
import java.util.List;

/**
 WebView加载自定义的网页，源代码存放在main\assets路径，由html或js实现。
 */
public class H5Activity extends AppCompatActivity {

    private static final String TAG = "H5MainActivity";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h);

        mWebView = (WebView) findViewById(R.id.main_web_view);
        //解决点击链接跳转浏览器问题
        mWebView.setWebViewClient(new WebViewClient());
        //js支持
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //允许访问assets目录
        settings.setAllowFileAccess(true);
        //设置WebView排版算法, 实现单列显示, 不允许横向移动
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //assets文件路径
        String path = "file:///android_asset/index.html";
        //添加Json数据
        addJson();
        //加载Html页面
        mWebView.loadUrl(path);

    }

    private void addJson() {
        JsSupport jsSupport = new JsSupport(this);
        List<FriendsZone> zones = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            zones.add(new FriendsZone("鹿鹿" + i, "images/icon.png", "这里是Html测试数据, 这里是Html测试数据, 这里是Html测试数据" + i));
        }
        Gson gson = new Gson();
        String json = gson.toJson(zones);
        Log.d(TAG, "addJson: json => " + json);
        jsSupport.setJson(json);
        //添加js交互接口, 并指明js中对象的调用名称
        mWebView.addJavascriptInterface(jsSupport, "weichat");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

/**
 * Android混合开发之仿微信朋友圈https://www.jianshu.com/p/0cc665503324
 */