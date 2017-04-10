package com.goals.mobile.fruity_fruity.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.login.LoginManager;
import com.goals.mobile.fruity_fruity.R;
import com.goals.mobile.fruity_fruity.app.MyApplication;
import com.goals.mobile.fruity_fruity.controller.viewcontols.ButtonBold;
import com.goals.mobile.fruity_fruity.controller.viewcontols.EditTextMedium;
import com.goals.mobile.fruity_fruity.controller.viewcontols.SharedPref;
import com.goals.mobile.fruity_fruity.controller.viewcontols.TextViewBold;
import com.goals.mobile.fruity_fruity.controller.viewcontols.TextViewMedium;
import com.goals.mobile.fruity_fruity.controller.viewcontols.handler.Card_Item;
import com.goals.mobile.fruity_fruity.helper.Fruity_Constant;
import com.goals.mobile.fruity_fruity.helper.Fruity_Static;
import com.goals.mobile.fruity_fruity.model.Card_Property;
import com.goals.mobile.fruity_fruity.model.Card_Result;
import com.goals.mobile.fruity_fruity.view.adapter.ItemAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Fruite_Detail extends Activity implements Card_Item {

    private ButtonBold button_cart, button_buy;
    private Fruite_Detail activity;
    private ImageView imageViewback;
    private RelativeLayout layout_basket;


//String[] item ={"box","pcs","bunch"};

    List<String> item = new ArrayList<>();

    private RecyclerView lv_item;
    private ImageView imageView_drop;
    private ItemAdapter itemAdapter;
    private TextViewBold textView_item;
    private ImageView imageView_basket, imageView_logout;
    private String product_prize, product_name, product_image;
    private ImageView imageView_product_image;
    private TextViewBold textView_fruite_name;
    private TextViewBold textView_prize;
    private EditTextMedium textViewdescription;
    private String product_description;
    private String pieceQty;
    private String piecestatus;
    private String boxstatus;
    private String boxQty;
    private String bunchstatus;
    private String bunchQty;
    boolean flag = true;
    private EditTextMedium editText_qnty;
    private TextViewBold textView_totalprize;
    private int Toatalbox,remaingpec;
    private int totalbanchbox;
    private int remaingbunchpec;
    private TextViewBold textView_cout;
    private TextViewBold textView_cardcont;
    private String product_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruite__detail);
        activity = this;
        getIntentData();
        getControl();
        addData();
        onClick();
    }

    private void addData() {

        if (piecestatus.equals("1") && boxstatus.equals("1") && bunchstatus.equals("1")) {
            item.add("box");
            item.add("pcs");
            item.add("bunch");
        } else if (piecestatus.equals("1") && boxstatus.equals("1") && bunchstatus.equals("0")) {
            item.add("pcs");
            item.add("box");

        } else if (piecestatus.equals("1") && boxstatus.equals("0") && bunchstatus.equals("0")) {

            item.add("pcs");

        } else if (piecestatus.equals("1") && boxstatus.equals("0") && bunchstatus.equals("1")) {

            item.add("pcs");
            item.add("bunch");

        } else if (piecestatus.equals("0") && boxstatus.equals("1") && bunchstatus.equals("1")) {
           /* item.add("box");
            item.add("bunch");*/
            imageView_drop.setVisibility(View.GONE);
            textView_item.setText("Out of Stock");

        } else if (piecestatus.equals("0") && boxstatus.equals("0") && bunchstatus.equals("1")) {

          /*  item.add("bunch");*/
            imageView_drop.setVisibility(View.GONE);
            textView_item.setText("Out of Stock");

        } else if (piecestatus.equals("0") && boxstatus.equals("1") && bunchstatus.equals("0")) {

           /* item.add("box");*/
            imageView_drop.setVisibility(View.GONE);
            textView_item.setText("Out of Stock");

        } else if (piecestatus.equals("0") && boxstatus.equals("0") && bunchstatus.equals("0")) {
            imageView_drop.setVisibility(View.GONE);
            textView_item.setText("Out of Stock");

        }
        if(textView_item.getText().toString().equals("Item"))
            {

            Fruity_Static.toastMessage(activity,"Please select item ");
            editText_qnty.setEnabled(false);

        }
        else if(textView_item.getText().toString().equals("Out of Stock"))
        {
            editText_qnty.setEnabled(false);
        }
        else
        {
            editText_qnty.setEnabled(true);
        }


    }

    private void getIntentData() {

        try {

            product_image = getIntent().getExtras().getString("product_image");
            product_id = getIntent().getExtras().getString("product_id");
            product_name = getIntent().getExtras().getString("product_name");
            product_prize = getIntent().getExtras().getString("product_prize");
            product_description = getIntent().getExtras().getString("product_description");
            pieceQty = getIntent().getExtras().getString("pieceQty");
            piecestatus = getIntent().getExtras().getString("piecestatus");
            boxstatus = getIntent().getExtras().getString("boxstatus");
            boxQty = getIntent().getExtras().getString("boxQty");
            bunchstatus = getIntent().getExtras().getString("bunchstatus");
            bunchQty = getIntent().getExtras().getString("bunchQty");
            Toatalbox = Integer.parseInt(pieceQty) / Integer.parseInt(boxQty);
            remaingpec = Integer.parseInt(pieceQty) % Integer.parseInt(boxQty);
            totalbanchbox = Integer.parseInt(pieceQty) / Integer.parseInt(bunchQty);
            remaingbunchpec = Integer.parseInt(pieceQty) % Integer.parseInt(bunchQty);
        }catch (Exception e)
        {

            e.printStackTrace();
        }

    }

    public void onBuyPressed() {
        PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(Fruite_Detail.this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, MyApplication.config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
        startActivityForResult(intent, 1);
    }

    private PayPalPayment getThingToBuy(String paymentIntent) {
        return new PayPalPayment(new BigDecimal("100.0"), "USD", "Sample Item", paymentIntent);
    }

    private void getControl() {
        textView_cardcont=(TextViewBold)findViewById(R.id.textView_cardcont);
        imageView_product_image = (ImageView) findViewById(R.id.imageView_product_image);
        textView_fruite_name = (TextViewBold) findViewById(R.id.textView_fruite_name);
        textView_cout = (TextViewBold) findViewById(R.id.textView_cout);
        textView_totalprize = (TextViewBold) findViewById(R.id.textView_totalprize);
        textViewdescription = (EditTextMedium) findViewById(R.id.textViewdescription);
        editText_qnty=(EditTextMedium)findViewById(R.id.editText_qnty);
        textView_prize = (TextViewBold) findViewById(R.id.textView_prize);
        imageView_basket = (ImageView) findViewById(R.id.imageView_basket);
        imageView_logout = (ImageView) findViewById(R.id.imageView_logout);
        textView_item = (TextViewBold) findViewById(R.id.textView_item);
        layout_basket = (RelativeLayout) findViewById(R.id.layout_basket);
        imageViewback = (ImageView) findViewById(R.id.imageViewback);
        imageView_drop = (ImageView) findViewById(R.id.imageView_drop);
        button_cart = (ButtonBold) findViewById(R.id.button_cart);
        button_buy = (ButtonBold) findViewById(R.id.button_buy);
        lv_item = (RecyclerView) findViewById(R.id.lv_item);
        lv_item.setVisibility(View.GONE);
        textView_totalprize.setVisibility(View.GONE);
        lv_item.setLayoutManager(new LinearLayoutManager(activity));
        itemAdapter = new ItemAdapter(activity, item);
        lv_item.setAdapter(itemAdapter);
        ImageLoader.getInstance().displayImage(Fruity_Constant.FruitImage + product_image, imageView_product_image);
        textView_fruite_name.setText(product_name);
        textView_prize.setText("$" + product_prize);
        textViewdescription.setText(product_description);
        textView_cout.setVisibility(View.GONE);

        if(MyApplication.getInstace().total_card_count==0)
        {
            textView_cardcont.setVisibility(View.GONE);
        }
        else
        {
            textView_cardcont.setVisibility(View.VISIBLE);
            textView_cardcont.setText(MyApplication.getInstace().total_card_count+"");
        }



        editText_qnty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

               String value=s.toString();


                try {
                    if (textView_item.getText().toString().equals("box")) {
                        if (Integer.parseInt(value) > Toatalbox) {
                            textView_cout.setVisibility(View.GONE);

                            textView_totalprize.setVisibility(View.GONE);
                            Fruity_Static.toastMessage(activity, "Only " + Toatalbox + " box available");

                        } else {
                            textView_cout.setVisibility(View.VISIBLE);
                            textView_totalprize.setVisibility(View.VISIBLE);
                            int total_piece = (Integer.parseInt(value) * Integer.parseInt(boxQty));
                            int total_box_prize = total_piece * Integer.parseInt(product_prize);
                            textView_totalprize.setText("Total:HK$ " + total_box_prize + "");
                            textView_cout.setText(total_piece+""+" pcs");
                        }
                    }
                   else if (textView_item.getText().toString().equals("pcs")) {

                        if(Integer.parseInt(value)>Integer.parseInt(pieceQty))
                        {
                            textView_cout.setVisibility(View.GONE);
                            textView_totalprize.setVisibility(View.GONE);
                            Fruity_Static.toastMessage(activity,"Only "+pieceQty +" pcs available");

                        }
                        else
                        {
                            textView_cout.setVisibility(View.GONE);
                            textView_totalprize.setVisibility(View.VISIBLE);
                            int total_amount = (Integer.parseInt(value) * Integer.parseInt(product_prize));
                            textView_totalprize.setText("Total:HK$ "+total_amount+"");
                            textView_cout.setText(value);
                        }
                    }

                    else if (textView_item.getText().toString().equals("bunch")) {

                        if(Integer.parseInt(value)>totalbanchbox)
                        {
                            textView_cout.setVisibility(View.GONE);
                            textView_totalprize.setVisibility(View.GONE);
                            Fruity_Static.toastMessage(activity,"Only "+totalbanchbox +" bunch available");
                        }
                        else
                        {
                            textView_cout.setVisibility(View.VISIBLE);
                            textView_totalprize.setVisibility(View.VISIBLE);
                            int total_piece = (Integer.parseInt(value) * Integer.parseInt(bunchQty));
                            int total_bunch_prize = total_piece * Integer.parseInt(product_prize);
                            textView_totalprize.setText("Total:HK$ " + total_bunch_prize + "");
                            textView_cout.setText(total_piece+""+" pcs");


                        }
                    }


                }
                catch (Exception e)
                {
                   e.printStackTrace();
                    textView_totalprize.setVisibility(View.GONE);
                    textView_cout.setVisibility(View.GONE);
                }



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void onClick() {

        itemAdapter.SetOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String name) {
                editText_qnty.setEnabled(true);
                lv_item.setVisibility(View.GONE);
                textView_item.setText(name + "");
                imageView_drop.setRotation(0);


            }

        });
        imageView_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fruity_Static.hideSoftkeyboard(activity);

                if (flag) {
                    lv_item.setVisibility(View.VISIBLE);
                    imageView_drop.setRotation(180);
                    flag = false;
                } else {
                    lv_item.setVisibility(View.GONE);
                    imageView_drop.setRotation(0);
                    flag = true;
                }
            }
        });
        button_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prize = (textView_totalprize.getText().toString().substring(10));
                if(textView_item.getText().toString().equals("Item"))
                {
                 Fruity_Static.toastMessage(activity,"Please select item");

                }
                else if(editText_qnty.getText().toString().length()<=0)
                {
                    Fruity_Static.toastMessage(activity,"Please enter quentity");

                }

                else {

                    Card_Result card_result = new Card_Result();
                    card_result.setImage(product_image);
                    card_result.setName(product_name);

                    card_result.setProduct_id(product_id);
                    if(cartitem.size()>0){
                        for(int i=0;i<cartitem.size();i++){
                            if(cartitem.get(i).getProduct_id().equals(product_id)){
                               String  previoussquantity=cartitem.get(i).getQuentity();
                               String  previousprize=cartitem.get(i).getPrize();
                                Log.e("previousprize",previousprize+"");
                                int totalquant=Integer.parseInt(textView_cout.getText().toString())+ Integer.parseInt(previoussquantity);
                                int totalprize=Integer.parseInt(prize)+ Integer.parseInt(previousprize);
                                cartitem.get(i).setQuentity(totalquant+"");
                                cartitem.get(i).setPrize(totalprize+"");

                                Log.e("value1",totalquant+"");
                                break;

                            }else{
                                if(i==cartitem.size()-1){
                                   card_result.setQuentity(textView_cout.getText().toString());
                                    card_result.setPrize(prize);
                                    Log.e("value12",textView_cout.getText().toString());

                                   cartitem.add(card_result);
                                    break;
                                }
                            }
                        }

                    }else{
                        card_result.setQuentity(textView_cout.getText().toString());
                        card_result.setPrize(prize);
                        cartitem.add(card_result);
                    }
                    CARD_PROPERTY.setResult(cartitem);
                    Intent intent = new Intent(activity, Cart.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);

                }
            }
        });
        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onBuyPressed();
                if(textView_item.getText().toString().equals("Item"))
                {
                    Fruity_Static.toastMessage(activity,"Please select item");

                }
                else if(editText_qnty.getText().toString().length()<=0)
                {
                    Fruity_Static.toastMessage(activity,"Please enter quentity");
                }
                else {
                    Log.e("mmmm",SharedPref.getString(activity, Fruity_Constant.TYPE));
                    if(SharedPref.getString(activity, Fruity_Constant.TYPE).equals("login")){
                        onBuyPressed();
                    }else{
                        Intent intent = new Intent(activity,Login.class);
                        startActivity(intent);
                    }

                }
            }
        });
        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Home.class);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });
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
    }


}
