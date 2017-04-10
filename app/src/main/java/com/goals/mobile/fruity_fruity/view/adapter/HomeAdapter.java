package com.goals.mobile.fruity_fruity.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.goals.mobile.fruity_fruity.R;
import com.goals.mobile.fruity_fruity.controller.viewcontols.TextViewMedium;
import com.goals.mobile.fruity_fruity.helper.Fruity_Constant;

import com.goals.mobile.fruity_fruity.model.Home_Property;
import com.goals.mobile.fruity_fruity.model.Home_Result;
import com.goals.mobile.fruity_fruity.view.activity.Fruite_Detail;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mobile on 11/7/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    private final Activity activity;
    private List<Home_Result> itemList;
    private String product_image;
    private String product_name, product_prize;
    private String product_description;
    private boolean searchEnabled = false;
    private String searchTerm;

    List<Home_Result> filteredList = new ArrayList<Home_Result>();

    public HomeAdapter(Activity activity, List<Home_Result> itemList) {
        this.activity = activity;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom, null);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Home_Result temp = searchEnabled ? filteredList.get(position) : itemList.get(position);


        holder.textView_fruite_name.setText(temp.getName());
        holder.textView_prize.setText("$" + temp.getPrice());

        ImageLoader.getInstance().displayImage(Fruity_Constant.FruitImage + temp.getImage(), holder.imageView_fruit);

        holder.imageView_fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Fruite_Detail.class);
                product_image = itemList.get(position).getImage();
                product_name = itemList.get(position).getName();
                product_prize = itemList.get(position).getPrice();
                product_description = itemList.get(position).getDescription();
                intent.putExtra("product_image", product_image);
                intent.putExtra("product_name", product_name);
                intent.putExtra("product_prize", product_prize);
                intent.putExtra("product_description", product_description);
                intent.putExtra("pieceQty",itemList.get(position).getPieceQty());
                intent.putExtra("piecestatus",itemList.get(position).getPieceStatus());
                intent.putExtra("boxQty",itemList.get(position).getBoxQty());
                intent.putExtra("boxstatus",itemList.get(position).getBoxStatus());
                intent.putExtra("bunchstatus",itemList.get(position).getBunchStatus());
                intent.putExtra("bunchQty",itemList.get(position).getBunchQty());
                intent.putExtra("product_id",itemList.get(position).getId());
                activity.startActivity(intent);
                activity.overridePendingTransition(0, 0);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (searchEnabled)
            return filteredList.size();
        return itemList.size();

    }


    public void setSearchEnabled(boolean enabled, String text) {
        searchEnabled = enabled;
        if (!searchEnabled) {
            searchTerm = "";
            filteredList.clear();
            notifyDataSetChanged();
            return;
        }
        searchTerm = text.toLowerCase();
        filter();
    }

    private void filter() {
        filteredList.clear();
        if (searchTerm.length() == 0) {
            filteredList.addAll(itemList);
        } else if (searchTerm.length() == 1) {
            for (Home_Result row : itemList) {
                if (row.getName().toLowerCase().charAt(0) == searchTerm.toLowerCase().charAt(0))
                    filteredList.add(row);
            }

        } else {
            for (Home_Result row : itemList) {
                if (row.getName().toLowerCase().contains(searchTerm)) {
                    filteredList.add(row);
                }
            }
        }
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder

    {

        private final ImageView imageView_fruit;
        private final TextViewMedium textView_prize;
        private TextViewMedium textView_fruite_name;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView_fruit = (ImageView) itemView.findViewById(R.id.imageView_fruit);
            textView_fruite_name = (TextViewMedium) itemView.findViewById(R.id.textView_fruite_name);
            textView_prize = (TextViewMedium) itemView.findViewById(R.id.textView_prize);
        }
    }

}
