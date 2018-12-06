package com.hy.retrofit;

import retrofit2.http.GET;
import rx.Observable;

/**
 RxJava 版本的 Observable 形式 API
 RxJava + Retrofit
 */
public interface GetRequest_Interface1 {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Observable<Translation> getCall();
}
