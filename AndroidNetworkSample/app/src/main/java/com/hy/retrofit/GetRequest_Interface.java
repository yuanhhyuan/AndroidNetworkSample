package com.hy.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 单独使用Retrofit
 传统的 Callback 形式的 API
 */
public interface GetRequest_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();
}
