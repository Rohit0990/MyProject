package com.goals.mobile.fruity_fruity.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.login.LoginManager;
import com.goals.mobile.fruity_fruity.R;
import com.goals.mobile.fruity_fruity.app.MyApplication;
import com.goals.mobile.fruity_fruity.controller.viewcontols.SharedPref;
import com.goals.mobile.fruity_fruity.controller.viewcontols.TextViewBold;
import com.goals.mobile.fruity_fruity.controller.viewcontols.handler.Card_Item;
import com.goals.mobile.fruity_fruity.helper.Fruity_Constant;
import com.goals.mobile.fruity_fruity.helper.Fruity_Static;
import com.goals.mobile.fruity_fruity.view.adapter.CartAdapter;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;

public class Cart extends Activity implements Card_Item {
public static RecyclerView recyclerview_items;
    ImageView imageViewback;
 public static    Button btn_payment;
    private Cart activity;
    private ImageView imageView_logout;
    public static TextViewBold textview_value;
    public static RelativeLayout relative_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        activity=this;
        imageView_logout=(ImageView)findViewById(R.id.imageView_logout);
        imageViewback=(ImageView)findViewById(R.id.imageViewback);
        relative_total=(RelativeLayout)findViewById(R.id.relative_total);
        btn_payment=(Button)findViewById(R.id.btn_payment);
        recyclerview_items=(RecyclerView)findViewById(R.id.recyclerview_cart);
        textview_value=(TextViewBold)findViewById(R.id.textview_value);
        recyclerview_items.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerview_items.setAdapter(new CartAdapter(this,CARD_PROPERTY.getResult()));
      MyApplication.getInstace().total_card_count=CARD_PROPERTY.getResult().size();

/*if(car.getResult().size()==0)
{
    recyclerview_items.setVisibility(View.GONE);
    btn_payment.setVisibility(View.GONE);

}
        else
{
    recyclerview_items.setVisibility(View.VISIBLE);
    btn_payment.setVisibility(View.VISIBLE);

}*/
        imageView_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fruity_Static.logOutPopup(activity);

            }
        });
        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,Home.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0,0);
            }
        });
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SharedPref.getString(activity, Fruity_Constant.TYPE).equals("login")){

                    onBuyPressed();

                }
                else{

                    Intent intent = new Intent(activity,Login.class);
                    startActivity(intent);
                }

            }
        });
    }
    public void onBuyPressed() {
        PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(Cart.this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, MyApplication.config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
        startActivityForResult(intent, 1);
    }
    private PayPalPayment getThingToBuy(String paymentIntent) {
        return new PayPalPayment(new BigDecimal("200.0"), "USD", "Sample Item", paymentIntent);
    }
}
