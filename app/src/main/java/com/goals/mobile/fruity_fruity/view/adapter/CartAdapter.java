package com.goals.mobile.fruity_fruity.view.adapter;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.goals.mobile.fruity_fruity.R;
import com.goals.mobile.fruity_fruity.app.MyApplication;
import com.goals.mobile.fruity_fruity.controller.viewcontols.TextViewBold;
import com.goals.mobile.fruity_fruity.helper.Fruity_Constant;
import com.goals.mobile.fruity_fruity.model.Card_Property;
import com.goals.mobile.fruity_fruity.model.Card_Result;
import com.goals.mobile.fruity_fruity.view.activity.Cart;
import com.goals.mobile.fruity_fruity.view.activity.Fruite_Detail;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.twitter.sdk.android.core.models.Card;

import java.util.List;


/**
 * Created by Mobile on 11/7/2016.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {


    List<Card_Result> card_results;
    private final Activity activity;
    private String product_id;
     public int totalsum=0;
    public CartAdapter(Activity activity,List<Card_Result> card_results) {
        this.activity = activity;
        this.card_results=card_results;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, null);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ImageLoader.getInstance().displayImage(Fruity_Constant.FruitImage + card_results.get(position).getImage(), holder.iv_fruitimage);
        holder.textView_fruitname.setText(card_results.get(position).getName());
        holder.textView_total_prize.setText("HK$"+card_results.get(position).getPrize());
        holder.textView_qty.setText("Qty:"+card_results.get(position).getQuentity());

        product_id=card_results.get(position).getProduct_id();
        totalsum=totalsum+Integer.parseInt(card_results.get(position).getPrize());
        Cart.textview_value.setText("Total: HK$"+totalsum+"");
holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        showPopup(position);

    }
});
    }

    @Override
    public int getItemCount() {
        return card_results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView_delete;
        private final ImageView iv_fruitimage;
        private final TextViewBold textView_fruitname,textView_total_prize,textView_qty;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView_delete=(ImageView)itemView.findViewById(R.id.imageView_delete);
            iv_fruitimage=(ImageView)itemView.findViewById(R.id.iv_fruitimage);
            textView_fruitname=(TextViewBold)itemView.findViewById(R.id.textView_fruitname);
            textView_total_prize=(TextViewBold)itemView.findViewById(R.id.textView_total_prize);
            textView_qty=(TextViewBold)itemView.findViewById(R.id.textView_qty);
        }
    }
    public void showPopup(final int position){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

        // Setting Dialog Title
        alertDialog.setTitle("Alert!");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want delete this?");

        // Setting Icon to Dialog
        // Setting Positive "Yes" Button

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

              //  dialog.cancel();

                card_results.remove(position);
                MyApplication.getInstace().total_card_count=card_results.size();
                notifyDataSetChanged();
                if(card_results.size()==0)
                {
                    Cart.btn_payment.setVisibility(View.GONE);
                    Cart.recyclerview_items.setVisibility(View.GONE);
                    Cart.relative_total.setVisibility(View.GONE);
                }

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

}
