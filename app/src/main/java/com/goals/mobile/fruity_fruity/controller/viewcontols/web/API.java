package com.goals.mobile.fruity_fruity.controller.viewcontols.web;

import com.goals.mobile.fruity_fruity.model.Home_Property;
import com.goals.mobile.fruity_fruity.model.Login_Property;
import com.goals.mobile.fruity_fruity.model.SignUP_Property;
import com.goals.mobile.fruity_fruity.model.SocialLogin_Property;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;


/**
 * Created by Mobile on 11/25/2016.
 */
 // userName,email,password,deviceToken"


public interface API {
    @FormUrlEncoded
    @POST("register.php")
    retrofit.Call<SignUP_Property> signUp(@Field("userName") String userName,
                                          @Field("email") String email,
                                          @Field("password") String password,
                                          @Field("phone") String phone,
                                          @Field("deviceToken") String deviceToken);
// userName,email,password,phone,socialType(native|facebook),socialId,deviceToken"}
    @FormUrlEncoded
    @POST("login.php")
    retrofit.Call<Login_Property> nativeLogin(@Field("userName") String user_name,
                                              @Field("email") String email,
                                              @Field("password") String password,
                                              @Field("phone")  String phone_number,
                                              @Field("socialType") String aNative,
                                              @Field("socialId")  String social_id,
                                              @Field("deviceToken") String device_token);

    @FormUrlEncoded
    @POST("login.php")
    retrofit.Call<SocialLogin_Property> socialLogin(@Field("userName") String user_name,
                                                    @Field("email") String email,
                                                    @Field("password") String password,
                                                    @Field("phone")  String phone_number,
                                                    @Field("socialType") String aNative,
                                                    @Field("socialId")  String social_id,
                                                    @Field("deviceToken") String device_token);
   /* @FormUrlEncoded
    @POST("allProducts.php")
    retrofit.Call<Home_Property> allProduct();*/
   @GET("allProducts.php")
   public Call<Home_Property> allProduct();
}


