package com.hy.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hy.okhttp3.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyTestActivity extends AppCompatActivity {

    String TAG = "060_VolleyTestActivity";

    RequestQueue mQueue;
    RequestQueue mImageQueue;
    Button btStringRequestget;
    Button btStringRequestpost;
    Button btjsonObjectRequestget;
    Button btjsonObjectRequestpost;
    Button btimageRequestGet;
    ImageView imageRequestGetTV;
    Button btimageLoaderGet;
    ImageView imageLoaderGetTV;

    ImageView networkImageViewGetTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volleytest);

        initView();  //初始化view
        initListener();  //初始化多个监听事件
        initRequestQueue(); //初始化volley的RequestQueue
    }

    private void initView() {
        btStringRequestget = (Button) findViewById(R.id.btStringRequestget);
        btStringRequestpost = (Button) findViewById(R.id.btStringRequestpost);
        btjsonObjectRequestget = (Button) findViewById(R.id.btjsonObjectRequestget);
        btjsonObjectRequestpost = (Button) findViewById(R.id.btjsonObjectRequestpost);
        btimageRequestGet = (Button) findViewById(R.id.btimageRequestGet);
        imageRequestGetTV = (ImageView) findViewById(R.id.imageRequestGetTV);
        btimageLoaderGet = (Button) findViewById(R.id.btimageLoaderGet);
        imageLoaderGetTV = (ImageView) findViewById(R.id.imageLoaderGetTV);

        networkImageViewGetTV = (ImageView) findViewById(R.id.networkImageViewGetTV);
    }

    private void initListener() {
        btStringRequestget.setOnClickListener(new MyListener());
        btStringRequestpost.setOnClickListener(new MyListener());
        btjsonObjectRequestget.setOnClickListener(new MyListener());
        btjsonObjectRequestpost.setOnClickListener(new MyListener());
        btimageRequestGet.setOnClickListener(new MyListener());
        btimageLoaderGet.setOnClickListener(new MyListener());
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {  //同时监听多个事件
            switch (v.getId()) {
                case R.id.btStringRequestget:
                    stringRequestGet();
                    break;
                case R.id.btStringRequestpost:
                    stringRequestPost();
                    break;
                case R.id.btjsonObjectRequestget:
                    jsonObjectRequestGet();
                    break;
                case R.id.btjsonObjectRequestpost:
                    jsonObjectRequestPost();
                    break;
                case R.id.btimageRequestGet:
                    imageRequestGet();
                    break;
                case R.id.btimageLoaderGet:
                    imageLoaderGet();
                    break;
                default:
                    break;
            }
        }
    }

    private void initRequestQueue() {
        mQueue = Volley.newRequestQueue(this);
        mImageQueue = Volley.newRequestQueue(this);
    }

    /**
     * volley应用一： HTTP网络请求
     */
    //method请求方法 url请求路径，Listener请求成功的监听的回调，ErrorListener请求失败的监听回调
    private void stringRequestGet() {
        StringRequest mStringRequestGet = new StringRequest("https://www.baidu.com/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response);
                        Log.e(TAG, "stringRequestGet thread id  : " + Thread.currentThread().getId());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });

        //请求加入到RequestQueue
        mQueue.add(mStringRequestGet);
    }

    private void stringRequestPost() {
        StringRequest mStringRequestPost = new StringRequest(Request.Method.POST, "http://api.24ht.net/app/signin", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("user", "jt1024");
                map.put("psw", "111111");
                return map;
            }
        };

        mStringRequestPost.setTag("volley_StringRequestGetPost");
        mQueue.add(mStringRequestPost);
    }

    private void jsonObjectRequestGet() {
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest("http://route.showapi.com/213-3", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });

        mQueue.add(mJsonObjectRequest);
    }

    private void jsonObjectRequestPost() {

    }

    /**
     * volley应用二：在界面上显示网络图片
     */
    /**
     * ImageRequest用法
     */
    private void imageRequestGet() {
        ImageRequest mImageRequestGet = new ImageRequest(
                "https://www.baidu.com/img/bd_logo1.png",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Log.e(TAG, "imageRequestGet suc");
                        imageRequestGetTV.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                imageRequestGetTV.setImageResource(R.drawable.ic_launcher);
            }
        });

        mImageQueue.add(mImageRequestGet);
        mImageQueue.start();
    }

    /**
     * ImageLoader用法
     */
    private void imageLoaderGet() {
        ImageLoader mImageLoaderGet = new ImageLoader(mImageQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                // TODO Auto-generated method stub

            }
        });

        ImageLoader.ImageListener imageListener = mImageLoaderGet.getImageListener(imageLoaderGetTV, R.drawable.ic_launcher, R.drawable.ic_launcher);
        mImageLoaderGet.get("https://www.baidu.com/img/bd_logo1.png", imageListener);
    }


}

/**
 * Volley
 * 1、配置
 * Android Studio使用Volley
 * http://blog.csdn.net/peng_hong_fu/article/details/52334639
 * <p>
 * 2、基础
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * <p>
 * 2.1 适合去进行数据量不大，但通信频繁的网络操作
 * <p>
 * 2.2
 * 2.2.1 StringRequest
 * <p>
 * 2.2.2 JsonRequest
 * 有两个直接的子类，JsonObjectRequest和JsonArrayRequest.
 * 一个是用于请求一段JSON数据的，一个是用于请求一段JSON数组的。
 * <p>
 * <p>
 * 2.3
 * 一个最基本的HTTP发送与响应的功能：
 * 1. 创建一个RequestQueue对象。
 * 2. 创建一个StringRequest对象。
 * 3. 将StringRequest对象添加到RequestQueue里面。
 * 4. 在你的AndroidManifest.xml中添加如下权限：
 * <uses-permission android:name="android.permission.INTERNET" />
 * 5.  androidstudio build.gradle添加依赖
 * //    添加volley依赖
 * compile 'com.mcxiaoke.volley:library:1.0.+'
 */ 