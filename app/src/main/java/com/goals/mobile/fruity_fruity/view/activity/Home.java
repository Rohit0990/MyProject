package com.goals.mobile.fruity_fruity.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.login.LoginManager;
import com.goals.mobile.fruity_fruity.R;
import com.goals.mobile.fruity_fruity.app.MyApplication;
import com.goals.mobile.fruity_fruity.controller.viewcontols.EditTextMedium;
import com.goals.mobile.fruity_fruity.controller.viewcontols.TextViewBold;
import com.goals.mobile.fruity_fruity.controller.viewcontols.web.Webhandling;
import com.goals.mobile.fruity_fruity.helper.Fruity_Static;
import com.goals.mobile.fruity_fruity.helper.ItemDecorationAlbumColumns;
import com.goals.mobile.fruity_fruity.model.Home_Property;
import com.goals.mobile.fruity_fruity.view.adapter.HomeAdapter;


public class Home extends Activity {


    private Toolbar toolbar;
    private RecyclerView rv_home;
    public static Home activity;
    private RelativeLayout layout_basket;
    private ImageView imageView_logout,imageView_basket;
    private EditTextMedium editTextSearch;
    private HomeAdapter homeAdapter;
    private TextViewBold textView_cardcont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity=this;
        layout_basket=(RelativeLayout)findViewById(R.id.layout_basket);
        editTextSearch=(EditTextMedium)findViewById(R.id.editTextSearch);
        imageView_basket=(ImageView)findViewById(R.id.imageView_basket);
        imageView_logout=(ImageView)findViewById(R.id.imageView_logout);
        textView_cardcont=(TextViewBold)findViewById(R.id.textView_cardcont);

        // Call Webservice
        Fruity_Static.startDialog(activity);
        Webhandling.getInstance().getAllProduct();


        imageView_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(MyApplication.getInstace().total_card_count==0)
                {


                }
                else {

                    Intent intent = new Intent(activity, Cart.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            }
        });
        imageView_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Fruity_Static.logOutPopup(activity);

            }
        });


        if(MyApplication.getInstace().total_card_count==0)
        {
            textView_cardcont.setVisibility(View.GONE);

        }
        else
        {

            textView_cardcont.setVisibility(View.VISIBLE);
            textView_cardcont.setText(MyApplication.getInstace().total_card_count+"");
        }

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                homeAdapter.setSearchEnabled(true, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    public static Home getInstance()
    {

        return  activity;
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        showPopup();
    }
    public void showPopup( ){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

        // Setting Dialog Title
        alertDialog.setTitle("Alert!");

        // Setting Dialog Message
        alertDialog.setMessage("Do you want to exit?");

        // Setting Icon to Dialog

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                ActivityCompat.finishAffinity(activity);
                dialog.cancel();

            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event

                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public void onSucess(Home_Property body) {

        rv_home=(RecyclerView)findViewById(R.id.rv_home);
        rv_home.setLayoutManager(new GridLayoutManager(activity,2));
        rv_home.addItemDecoration(new ItemDecorationAlbumColumns(10, 10));
         homeAdapter = new HomeAdapter(activity,body.getResult());
        rv_home.setAdapter(homeAdapter);
        Fruity_Static.stopDialog();
    }
}
