package com.hy.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hy.h5.H5Activity;
import com.hy.h5.JSInterfaceTestActivity;
import com.hy.h5.JsBridgeActivity;
import com.hy.h5.WebViewActivity;

import com.hy.okhttp3.Okhttp3TestActivity;
import com.hy.okhttp3.R;
import com.hy.retrofit.GetRequestActivity;
import com.hy.retrofit.GetRequestActivity1;
import com.hy.retrofit.PostRequestActivity;
import com.hy.volley.VolleyTestActivity;

public class MainActivity extends AppCompatActivity{
    Button mwebview;
    Button btactivity;
    Button bth5;
    Button mJSInterfaceTestActivity;
    Button mJsBridgeActivity;

    Button mVolley;

    Button mOkhttp3TestActivity;


    Button mGetRequest;
    Button mGetRequest1;
    Button mPostRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();  //初始化view
        initListener();  //初始化多个监听事件
    }

    private void initView(){
        mwebview = (Button) findViewById(R.id.mwebview);
        btactivity = (Button) findViewById(R.id.btactivity);
        bth5 = (Button) findViewById(R.id.bth5);
        mJSInterfaceTestActivity = (Button) findViewById(R.id.mJSInterfaceTestActivity);
        mJsBridgeActivity = (Button) findViewById(R.id.mJsBridgeActivity);
        mVolley = (Button) findViewById(R.id.mVolley);
        mOkhttp3TestActivity = (Button) findViewById(R.id.mOkhttp3TestActivity);
        mGetRequest = (Button) findViewById(R.id.mGetRequest);
        mGetRequest1 = (Button) findViewById(R.id.mGetRequest1);
        mPostRequest = (Button) findViewById(R.id.mPostRequest);
    }

    private void initListener(){
        mwebview.setOnClickListener(new MyListener());
        btactivity.setOnClickListener(new MyListener());
        bth5.setOnClickListener(new MyListener());
        mJSInterfaceTestActivity.setOnClickListener(new MyListener());
        mJsBridgeActivity.setOnClickListener(new MyListener());
        mVolley.setOnClickListener(new MyListener());
        mOkhttp3TestActivity.setOnClickListener(new MyListener());

        mGetRequest.setOnClickListener(new MyListener());
        mGetRequest1.setOnClickListener(new MyListener());
        mPostRequest.setOnClickListener(new MyListener());
    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {  //同时监听多个事件
            switch (v.getId()) {
                case R.id.btactivity:
                    newActivity();
                    break;
                case R.id.mwebview:
                    newWebViewActivity();
                    break;
                //h5混合开发
                case R.id.bth5:
                    newH5Activity();
                    break;
                case R.id.mJSInterfaceTestActivity:
                    newJSInterfaceTestActivity();
                    break;
                case R.id.mJsBridgeActivity:
                    newJsBridgeActivity();
                    break;
                case R.id.mVolley:
                    newVolleyTestActivity();
                    break;
                case R.id.mOkhttp3TestActivity:
                    newOkhttp3TestActivity();
                    break;
                case R.id.mGetRequest:
                    newGetRequest();
                    break;
                case R.id.mGetRequest1:
                    newGetRequest1();
                    break;
                case R.id.mPostRequest:
                    newPostRequest();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     *  需要调用系统的浏览器来打开这个网页
     */
    private void newActivity(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    /**
     *  android原始webview
     */
    private void newWebViewActivity(){
        Intent i = new Intent(MainActivity.this,WebViewActivity.class);
        startActivity(i);
    }

    /**
     *  android h5
     */
    private void newH5Activity(){
        Intent i2 = new Intent(MainActivity.this, H5Activity.class);
        startActivity(i2);
    }

    /**
     *  JSInterface：android 与 h5交互
     */
    private void newJSInterfaceTestActivity(){
        Intent i2 = new Intent(MainActivity.this, JSInterfaceTestActivity.class);
        startActivity(i2);
    }

    /**
     *  JsBridge：android 与 h5交互
     */
    private void newJsBridgeActivity(){
        Intent i2 = new Intent(MainActivity.this, JsBridgeActivity.class);
        startActivity(i2);
    }

    private void newVolleyTestActivity(){
        Intent i2 = new Intent(MainActivity.this, VolleyTestActivity.class);
        startActivity(i2);
    }

    private void newOkhttp3TestActivity(){
        Intent i = new Intent(MainActivity.this,Okhttp3TestActivity.class);
        startActivity(i);
    }
    private void newGetRequest(){
        Intent i = new Intent(MainActivity.this,GetRequestActivity.class);
        startActivity(i);
    }
    private void newGetRequest1(){
        Intent i = new Intent(MainActivity.this,GetRequestActivity1.class);
        startActivity(i);
    }
    private void newPostRequest(){
        Intent i = new Intent(MainActivity.this,PostRequestActivity.class);
        startActivity(i);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        showExitDialog();
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗");
        builder.setNegativeButton("留下来", null);
//        builder.setPositiveButton("残忍地弄死", App.getInstance().exitApp());
        builder.show();
    }
}
