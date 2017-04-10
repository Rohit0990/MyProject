package com.goals.mobile.fruity_fruity.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.goals.mobile.fruity_fruity.R;
import com.goals.mobile.fruity_fruity.app.MyApplication;
import com.goals.mobile.fruity_fruity.controller.viewcontols.ButtonBold;
import com.goals.mobile.fruity_fruity.controller.viewcontols.EditTextMedium;
import com.goals.mobile.fruity_fruity.controller.viewcontols.TextViewBold;
import com.goals.mobile.fruity_fruity.controller.viewcontols.web.Webhandling;
import com.goals.mobile.fruity_fruity.helper.Fruity_Static;
import com.goals.mobile.fruity_fruity.model.SignUP_Property;
import com.google.firebase.iid.FirebaseInstanceId;

public class SignUp extends Activity {


    private ButtonBold buttonSignUp;
    private EditTextMedium editText_username,editText_password,editText_email,editText_phonenumber;
    private static SignUp activity;
    public ImageView imageView_back,imageView_verfy;
    public TextViewBold textView_verify;
    public boolean verify_status=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        activity=this;
        getControl();
        verifyClick();
        onClick();
    }

    private void verifyClick() {
        textView_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_phonenumber.getText().toString().length() <= 0) {
                    Fruity_Static.toastMessage(activity,"Please fill the phone number first");
                } else {
                    MyApplication.getInstace().varification_type="signup";
                    Digits.clearActiveSession();
                    AuthConfig.Builder authConfigBuilder = new AuthConfig.Builder()
                            .withAuthCallBack(MyApplication.getInstace().callback)
                            .withPhoneNumber("+91" + editText_phonenumber.getText().toString().trim());
                    Digits.authenticate(authConfigBuilder.build());
                }
            }
        });

    }

    private void getControl(){
        editText_username=(EditTextMedium)findViewById(R.id.editText_username);
        editText_password=(EditTextMedium)findViewById(R.id.editText_password);
        editText_email=(EditTextMedium)findViewById(R.id.editText_email);
        editText_phonenumber=(EditTextMedium)findViewById(R.id.editText_phonenumber);
        textView_verify=(TextViewBold)findViewById(R.id.textView_verify);
        buttonSignUp=(ButtonBold)findViewById(R.id.buttonSignUp);
        imageView_back=(ImageView)findViewById(R.id.imageView_back);
        imageView_verfy=(ImageView)findViewById(R.id.imageView_verfy);
        imageView_verfy.setVisibility(View.GONE);
    }
    private void onClick()
    {
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,Login.class);
                startActivity(intent);
                startActivity(intent);overridePendingTransition(0,0);
                finish();
                overridePendingTransition(0,0);
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_username.getText().toString().trim().length()<=0 || editText_password.getText().toString().trim().length()<0||editText_email.getText().toString().trim().length()<0 ||editText_phonenumber.getText().toString().trim().length()<0)
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.all_fileds));


                }
                else if(editText_username.getText().toString().trim().length()<=0 )
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.user_name));


                }
                else if(editText_email.getText().toString().trim().length()<=0 )
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.email_address));


                }
                else if(!(Fruity_Static.isValidEmail(editText_email.getText().toString().trim())))
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.valid_email));


                }
                else if(editText_password.getText().toString().trim().length()<=0 )
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.enter_password));


                }
                else if(editText_phonenumber.getText().toString().trim().length()<=0 )
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.phone_number_validation));


                }
                else if(editText_phonenumber.getText().toString().trim().length()<=0 )
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.valid_phone_number));


                }

                else if(!verify_status)


                {
                    Log.e("status",verify_status+"");
                    Fruity_Static.toastMessage(activity,getString(R.string.veriy));


                }

      else{
                    Log.e("name",editText_username.getText().toString().trim());
                    Log.e("email",editText_email.getText().toString().trim());
                    Log.e("pawd",editText_password.getText().toString().trim());
                    Webhandling.getInstance().doSignUP(editText_username.getText().toString().trim(),editText_email.getText().toString().trim(),editText_password.getText().toString().trim(),editText_phonenumber.getText().toString().trim(), FirebaseInstanceId.getInstance().getToken());
                }
            }
        });

        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
         finish();

         overridePendingTransition(0,0);
    }
    public static SignUp getInstance()
    {

       return  activity;
    }

    public void onSuccess(SignUP_Property body) {

        if(Integer.parseInt(body.getResult().getId())>0)
        {
            Intent intent = new Intent(activity,Home.class);
            startActivity(intent);


        }
        else
        {
          Fruity_Static.toastMessage(activity,body.getResult().getMessage());

        }
    }
}
