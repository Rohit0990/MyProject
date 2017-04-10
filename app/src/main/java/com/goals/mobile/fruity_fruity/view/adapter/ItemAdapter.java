package com.goals.mobile.fruity_fruity.view.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.goals.mobile.fruity_fruity.R;
import com.goals.mobile.fruity_fruity.controller.viewcontols.TextViewBold;

import java.util.List;

/**
 * Created by Mobile on 11/11/2016.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.viewHolder> {

    private final Activity activity;
    List<String> item;
    private OnItemClickListener mItemClickListener;

    public ItemAdapter(Activity activity,  List<String> item) {
        this.item = item;
        this.activity = activity;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
        holder.textView_item.setText(item.get(position));
        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, position,item.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position,String text);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    
    public class viewHolder extends RecyclerView.ViewHolder {


        private final TextViewBold textView_item;
        private final RelativeLayout layout_item;

        public viewHolder(View itemView) {
            super(itemView);

            textView_item = (TextViewBold) itemView.findViewById(R.id.textView_item);
            layout_item=(RelativeLayout)itemView.findViewById(R.id.layout_item);
        }
    }
}
