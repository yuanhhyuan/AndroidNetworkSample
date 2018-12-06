package com.hy.h5;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.hy.okhttp3.R;


/**
 WebView加载第三方的网页，如百度等
 */
public class WebViewActivity extends Activity {
    String TAG = "060_WebViewActivity";
    WebView mwebView;
    String path = "http://baidu.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        init();
    }

    private void init() {
        mwebView = (WebView) findViewById(R.id.mwebView);

        //WebView加载web资源
        mwebView.loadUrl(path);

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mwebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            //通知主程序页面当前开始加载。
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(WebViewActivity.this, "网页开始加载", Toast.LENGTH_SHORT).show();
            }

            //通知主程序页面当前加载完成。
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(WebViewActivity.this, "网页加载完成", Toast.LENGTH_SHORT).show();
            }
        });

        mwebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
//                    Toast.makeText(WebViewActivity.this, "网页加载完成", Toast.LENGTH_SHORT).show();
                } else {
                    // 加载中
//                    Toast.makeText(WebViewActivity.this, "网页加载中", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mwebView.canGoBack()) {
                mwebView.goBack();//返回上一浏览页面
                return true;
            } else {
                finish();//关闭Activity
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}

/**
 WebView、WebSettings、WebChromeClient、WebClient
 */