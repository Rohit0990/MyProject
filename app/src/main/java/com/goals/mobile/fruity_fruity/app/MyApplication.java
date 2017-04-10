package com.goals.mobile.fruity_fruity.app;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.goals.mobile.fruity_fruity.controller.viewcontols.ImageLoaderClass;
import com.goals.mobile.fruity_fruity.controller.viewcontols.web.Webhandling;
import com.goals.mobile.fruity_fruity.view.activity.Login;
import com.goals.mobile.fruity_fruity.view.activity.SignUp;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalService;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Mobile on 11/11/2016.
 */

public class MyApplication extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "tPqsp7mmgndJelyQw8rKGcy4n";
    private static final String TWITTER_SECRET = "j8NSfPbTR4sGGSssSgwStcqxfzomRc2p7wSfVOROM1BnEYMVDL";

    public static PayPalConfiguration config;
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    private static final String CONFIG_CLIENT_ID = "ARsMkbw4rZw_1ufVIMw_2MXRbCSrnLG8aWGyEOMsC7mNn-bbadANxe16vVAX1Z9wjpbddc9nKWTX1WbV";
    public AuthCallback callback;
    public  String varification_type="";
    public boolean isNumberVerified = false;
    public  int total_card_count=0;
    public static MyApplication activity;
    @Override
    public void onCreate() {
        super.onCreate();
        activity = this;
        new Webhandling(this);
        new ImageLoaderClass(this);


        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig), new Digits.Builder().build());
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        config = new PayPalConfiguration()
                .environment(CONFIG_ENVIRONMENT)
                .clientId(CONFIG_CLIENT_ID)
                .merchantName("Example Merchant")
                .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
                .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());

        callback = new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {


                if (varification_type.equals("login")) {
                    Login.getInstance().verify_status = true;
                    Login.getInstance().imageView_verfy.setVisibility(View.VISIBLE);
                    Login.getInstance().textView_verify.setVisibility(View.GONE);


                } else {
                    SignUp.getInstance().verify_status = true;
                    SignUp.getInstance().imageView_verfy.setVisibility(View.VISIBLE);
                    SignUp.getInstance().textView_verify.setVisibility(View.GONE);
                    Log.e("valid", "valid");
                }

            }

            @Override
            public void failure(DigitsException error) {
                if (varification_type.equals("login")) {
                    Login.getInstance().textView_verify.setVisibility(View.VISIBLE);
                    Login.getInstance().imageView_verfy.setVisibility(View.GONE);
                } else {
                    SignUp.getInstance().textView_verify.setVisibility(View.VISIBLE);
                    SignUp.getInstance().imageView_verfy.setVisibility(View.GONE);
                    Log.e("valid", "unvalid");
                }
            }
        };

    }

    public static MyApplication getInstace() {


        return activity;

    }
}
