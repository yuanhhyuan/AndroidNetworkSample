package com.hy.h5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.hy.okhttp3.R;


/**
 android与web交互，安卓界普遍称为JS交互
 http://blog.csdn.net/lebang08/article/details/52848221
 */
public class JSInterfaceTestActivity extends Activity {
    private EditText mEditText;
    private Button mButton;
    private WebView webview;
    private JsInterface jsInterface;

    String path = "file:///android_asset/jsinterfacetest.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsinterface);

        mEditText =  findViewById(R.id.text_edit);
        mButton =  findViewById(R.id.btn_java);
        webview =  findViewById(R.id.mWebView1);

        // 实例化接口JsInterface
        jsInterface = new JsInterface(webview);
        // 初始化WebSetting
        initWebSetting();

        webview.loadUrl(path);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebSetting() {
        // 允许JS交互
        webview.getSettings().setJavaScriptEnabled(true);
        // 设置JS的接口
        webview.addJavascriptInterface(jsInterface, "jsInterface");

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int param = Integer.parseInt(mEditText.getText().toString());
                        jsInterface.java_call_Js(param);
                    }
                });
            }
        });
    }

}
