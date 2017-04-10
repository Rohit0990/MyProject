package com.goals.mobile.fruity_fruity.controller.viewcontols.web;

import android.content.Context;
import android.util.Log;

import com.goals.mobile.fruity_fruity.controller.viewcontols.SharedPref;
import com.goals.mobile.fruity_fruity.helper.Fruity_Constant;
import com.goals.mobile.fruity_fruity.model.Home_Property;
import com.goals.mobile.fruity_fruity.model.Login_Property;
import com.goals.mobile.fruity_fruity.model.SignUP_Property;
import com.goals.mobile.fruity_fruity.model.SocialLogin_Property;
import com.goals.mobile.fruity_fruity.view.activity.Home;
import com.goals.mobile.fruity_fruity.view.activity.Login;
import com.goals.mobile.fruity_fruity.view.activity.SignUp;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Mobile on 11/25/2016.
 */

public class Webhandling {

    public static Webhandling webHandling;
    private static Context context;
    private static API api;

    public Webhandling(Context context)

    {

        webHandling = this;
        this.context = context;
        api = new Retrofit.Builder()
                .baseUrl(Fruity_Constant.BaseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(API.class);
    }

    public static Webhandling getInstance() {


        return  webHandling;
    }

    public void doSignUP(String username,String email,String password,String phone_number,String device_token )

    {

        Call<SignUP_Property> response = api.signUp(username,email,password,phone_number,device_token);
        response.enqueue(new Callback<SignUP_Property>() {
            @Override
            public void onResponse(Response<SignUP_Property> response, Retrofit retrofit) {
             Log.e("Sddd","Success");

                SignUp.getInstance().onSuccess(response.body());

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Sddd","Fail");
                t.printStackTrace();
            }
        });
    }

    public void nativeLogin(String user_name, String email, String password, String phone_number, String Native, String social_id, String device_token) {

        Call<Login_Property> response = api.nativeLogin(user_name,email,password,phone_number,Native,social_id,device_token);
        response.enqueue(new Callback<Login_Property>() {
            @Override
            public void onResponse(Response<Login_Property> response, Retrofit retrofit) {
                Log.e("Sddd","Success");
                Login.getInstance().onSuccess(response.body());

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Sddd","Fail");
                t.printStackTrace();
            }
        });
    }

    public void socialLogin(String user_name, String email, String password, String phone_number, String facebook, String fb_id, String device_token) {

        Call<SocialLogin_Property> response = api.socialLogin(user_name,email,password,phone_number,facebook,fb_id,device_token);
        response.enqueue(new Callback<SocialLogin_Property>() {
            @Override
            public void onResponse(Response<SocialLogin_Property> response, Retrofit retrofit) {
                Log.e("Sdddbbbb",response.body().getResult().getType());
                Login.getInstance().onSocialSuccess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Sddd","Fail");
                t.printStackTrace();
            }
        });

    }

    public void getAllProduct() {
        Call<Home_Property> response = api.allProduct();
        response.enqueue(new Callback<Home_Property>() {
            @Override
            public void onResponse(Response<Home_Property> response, Retrofit retrofit) {
                Log.e("Sdddbbbb","Done");
Home.getInstance().onSucess(response.body());

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Sddd","Fail");
                t.printStackTrace();
            }
        });
    }

    public void forgetPassword(String email) {

      /*  Call<Home_Property> response = api.allProduct();
        response.enqueue(new Callback<Home_Property>() {
            @Override
            public void onResponse(Response<Home_Property> response, Retrofit retrofit) {
                Log.e("Sdddbbbb","Done");

                Home.getInstance().onSucess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Sddd","Fail");
                t.printStackTrace();
            }
        });*/



    }
}
