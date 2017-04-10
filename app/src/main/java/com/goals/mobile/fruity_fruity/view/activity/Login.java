package com.goals.mobile.fruity_fruity.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.facebook.CallbackManager;
import com.facebook.CustomTabActivity;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.goals.mobile.fruity_fruity.R;
import com.goals.mobile.fruity_fruity.app.MyApplication;
import com.goals.mobile.fruity_fruity.controller.viewcontols.ButtonBold;
import com.goals.mobile.fruity_fruity.controller.viewcontols.EditTextMedium;
import com.goals.mobile.fruity_fruity.controller.viewcontols.SharedPref;
import com.goals.mobile.fruity_fruity.controller.viewcontols.TextViewBold;
import com.goals.mobile.fruity_fruity.controller.viewcontols.TextViewMedium;
import com.goals.mobile.fruity_fruity.controller.viewcontols.web.Webhandling;
import com.goals.mobile.fruity_fruity.helper.Fruity_Constant;
import com.goals.mobile.fruity_fruity.helper.Fruity_Static;
import com.goals.mobile.fruity_fruity.model.Login_Property;
import com.goals.mobile.fruity_fruity.model.SocialLogin_Property;
import com.google.firebase.iid.FirebaseInstanceId;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Activity {
    private ButtonBold buttonlogin;
    private EditTextMedium editText_username,editText_password;
    public static Login activity;
    private TextViewMedium textView_forgotpassword,textView_signup;
    private LoginButton button_fb;
    private CallbackManager callbackmanager;
    private String email="";
    private String fb_id="";
    private String user_name="";
    public EditTextMedium editText_phonenumber;
    public TextViewBold textView_verify;
    public ImageView imageView_verfy;
    private ButtonBold button_done;
    public boolean verify_status=false;
    private String phone_number="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity=this;
        getControl();
        onClick();

    }
    public void initializeFb() {
        callbackmanager  = CallbackManager.Factory.create();
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    email = object.getString("email");
                                    fb_id = object.getString("id");
                                    user_name = object.getString("name");
                                    Log.e("emailface",email);
                                    Log.e("Fb_id",fb_id);
                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        };
        button_fb.setReadPermissions("email");
        button_fb.registerCallback(callbackmanager, callback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== FacebookActivity.RESULT_OK){
            callbackmanager.onActivityResult(requestCode,resultCode,data);
            Webhandling.getInstance().socialLogin(user_name,email,"","","facebook",fb_id,FirebaseInstanceId.getInstance().getToken());
        }
    }

    private void getControl() {
        button_fb=(LoginButton)findViewById(R.id.button_fb);
        editText_username=(EditTextMedium)findViewById(R.id.editText_username);
        editText_password=(EditTextMedium)findViewById(R.id.editText_password);
        textView_forgotpassword=(TextViewMedium)findViewById(R.id.textView_forgotpassword);
        textView_signup=(TextViewMedium)findViewById(R.id.textView_signup);
        buttonlogin=(ButtonBold)findViewById(R.id.buttonlogin);
    }

    private void onClick() {
        initializeFb();
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_username.getText().toString().trim().length()<=0 || editText_password.getText().toString().trim().length()<0)
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.all_fileds));
                }
                else if(editText_username.getText().toString().trim().length()<=0 )
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.user_name));
            }
                else if(editText_password.getText().toString().trim().length()<=0 ) {
                    Fruity_Static.toastMessage(activity,getString(R.string.enter_password));
                }
                else{

Fruity_Static.startDialog(activity);
                    Webhandling.getInstance().nativeLogin(editText_username.getText().toString().trim(),"",editText_password.getText().toString().trim(),"","native","", FirebaseInstanceId.getInstance().getToken());
                }
            }
        });
        textView_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,Forgot.class);
                startActivity(intent);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        textView_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,SignUp.class);
                startActivity(intent);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
        ActivityCompat.finishAffinity(activity);
    }
public static Login getInstance()
{

    return activity;
}

    public void onSuccess(Login_Property body) {

        Fruity_Static.stopDialog();
        if(Integer.parseInt(body.getResult().getId())>0)

        {
            SharedPref.setString(activity,Fruity_Constant.TYPE,body.getResult().getType());
            Intent intent = new Intent(activity,Home.class);
            startActivity(intent);
        }
        else
        {
            Fruity_Static.toastMessage(activity,body.getResult().getMessage());

        }

    }


    public void onSocialSuccess(SocialLogin_Property body) {
        if(Integer.parseInt(body.getResult().getId())>0)
        {
            if(body.getResult().getType().equals("login"))
            {
                SharedPref.setString(activity, Fruity_Constant.TYPE,body.getResult().getType());
                SharedPref.setString(activity, Fruity_Constant.USERID,body.getResult().getId());
                Intent intent = new Intent(activity,Home.class);
                startActivity(intent);
            }
            else
            {
                showChangeLangDialog();

            }
        }
        else
        {
            Fruity_Static.toastMessage(activity,body.getResult().getMessage());

        }

    }

    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();
      //  final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        editText_phonenumber=(EditTextMedium) dialogView.findViewById(R.id.editText_phonenumber);
        textView_verify=(TextViewBold)dialogView.findViewById(R.id.textView_verify);
        imageView_verfy=(ImageView)dialogView.findViewById(R.id.imageView_verfy);
        button_done=(ButtonBold)dialogView.findViewById(R.id.button_done);
        textView_verify.setVisibility(View.VISIBLE);
        imageView_verfy.setVisibility(View.GONE);

        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (editText_phonenumber.getText().toString().length() <= 0) {
                    Fruity_Static.toastMessage(activity,"Please fill the phone number first");
                }
                else if(!verify_status)


                {
                    Log.e("status",verify_status+"");
                    Fruity_Static.toastMessage(activity,getString(R.string.veriy));


                }
                else
                {

                    Webhandling.getInstance().socialLogin(user_name,email,"",editText_phonenumber.getText().toString(),"facebook",fb_id,FirebaseInstanceId.getInstance().getToken());
                 //   b.dismiss();
                }
            }
        });
        textView_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phone_number=editText_phonenumber.getText().toString();
                if (editText_phonenumber.getText().toString().length() <= 0) {
                    Fruity_Static.toastMessage(activity,"Please fill the phone number first");
                } else {
                    MyApplication.getInstace().varification_type="login";

                    Digits.clearActiveSession();
                    AuthConfig.Builder authConfigBuilder = new AuthConfig.Builder()
                            .withAuthCallBack(MyApplication.getInstace().callback)
                            .withPhoneNumber("+91" + editText_phonenumber.getText().toString().trim());
                    Digits.authenticate(authConfigBuilder.build());

                }


            }
        });

        b.show();
    }

}
