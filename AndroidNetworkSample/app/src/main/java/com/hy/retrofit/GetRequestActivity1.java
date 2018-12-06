package com.hy.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hy.okhttp3.R;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**

 */
public class GetRequestActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getrequest);

        request();
        // 使用Retrofit封装的方法
    }

    public void request() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的配置
                .baseUrl("http://fy.iciba.com/")
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface1 service = retrofit.create(GetRequest_Interface1.class);

        //对 发送请求 进行封装
        service.getCall()               //获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .doOnNext(new Action1<Translation>() {
                    @Override
                    public void call(Translation userInfo) {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<Translation>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("GetRequestActivity1 onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //请求失败
                        System.out.println("GetRequestActivity1 onError");
                    }

                    @Override
                    public void onNext(Translation userInfo) {
                        //请求成功
                        System.out.println("GetRequestActivity1 onNext");

                        userInfo.show();
                    }
                });

    }
}
