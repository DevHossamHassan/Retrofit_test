package me.branded.hossamhassan.retrofit_test.API;

import me.branded.hossamhassan.retrofit_test.Models.ResultPosts;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by HossamHassan on 4/1/2016.
 */
public interface HossRetroApi {
    @GET("getposts.php")
    Call<ResultPosts> getPosts();
    //http://developerhendy.16mb.com/getposts.php

    @FormUrlEncoded
    @POST("insertuserwithpost.php")
    Call<ResponseBody> addUser(@Field("username")String userName,
                               @Field("password")String password,
                               @Field("email")String email,
                               @Field("adress")String address
                               );
}
