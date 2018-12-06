package com.hy.h5;

import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**

 */
public class JsInterface {
    private WebView mWebView;

    // 构造方法，传入一个参数WebView
    public JsInterface(WebView webView) {
        this.mWebView = webView;
    }

    // 这个方法是js调用java
    @JavascriptInterface
    public void js_call_java() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                // 主线程更新UI
                Toast.makeText(mWebView.getContext(), "I'm a function in java", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 这个方法 是java调用js
    public void java_call_Js(int param) {
        // 这里调用html中的js代码的 java_call_Js 方法
        mWebView.loadUrl(String.format("javascript:java_call_Js(" + param + ")"));
    }
}
