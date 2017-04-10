package com.goals.mobile.fruity_fruity.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.goals.mobile.fruity_fruity.R;
import com.goals.mobile.fruity_fruity.controller.viewcontols.ButtonBold;
import com.goals.mobile.fruity_fruity.controller.viewcontols.EditTextMedium;
import com.goals.mobile.fruity_fruity.controller.viewcontols.web.Webhandling;
import com.goals.mobile.fruity_fruity.helper.Fruity_Static;

public class Forgot extends Activity {

    private ImageView imageView_back;
    private ButtonBold buttonSubmit;
    private EditTextMedium editText_email;
    private Forgot activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        activity=this;
        imageView_back=(ImageView)findViewById(R.id.imageView_back);
        editText_email=(EditTextMedium)findViewById(R.id.editText_email);
        buttonSubmit=(ButtonBold)findViewById(R.id.buttonSubmit);
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
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(editText_email.getText().toString().trim().length()<=0 )
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.email_address));


                }
                else if(!(Fruity_Static.isValidEmail(editText_email.getText().toString().trim())))
                {
                    Fruity_Static.toastMessage(activity,getString(R.string.valid_email));


                }
                else
                {

                    Intent intent = new Intent(activity,Login.class);
                    startActivity(intent);
                    startActivity(intent);overridePendingTransition(0,0);

                  //  Webhandling.getInstance().forgetPassword(editText_email.getText().toString().trim());
                }

            }
        });
    }
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        finish();
        overridePendingTransition(0,0);
    }
}
