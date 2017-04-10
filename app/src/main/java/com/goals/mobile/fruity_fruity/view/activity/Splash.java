package com.goals.mobile.fruity_fruity.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import com.goals.mobile.fruity_fruity.R;
import com.goals.mobile.fruity_fruity.controller.viewcontols.SharedPref;
import com.goals.mobile.fruity_fruity.helper.Fruity_Constant;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setSplash();
    }
    private void setSplash()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Splash.this,Home.class);
                startActivity(intent);
             /*   if(SharedPref.getString(Splash.this, Fruity_Constant.TYPE).equals("login")){
                    Intent intent = new Intent(Splash.this,Home.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Splash.this,Login.class);
                    startActivity(intent);
                }*/
            }
        },2000);
    }
}
