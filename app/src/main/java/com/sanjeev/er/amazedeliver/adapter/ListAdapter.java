package com.sanjeev.er.amazedeliver.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sanjeev.er.amazedeliver.MapsActivity;
import com.sanjeev.er.amazedeliver.R;
import com.sanjeev.er.amazedeliver.model.Constant;
import com.sanjeev.er.amazedeliver.model.Model;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private  CardView cardView;
    private List<Model> deliveryList;
    private Context context;

    public ListAdapter(List<Model> deliveryList, Context context) {
        this.deliveryList = deliveryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListAdapter.ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.descriptionText.setText(deliveryList.get(position).getDescription() +" at "+deliveryList.get(position).getLocation().getAddress());

        Glide.with(context).load(deliveryList.get(position).getImageUrl()).into(holder.carImage);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(context.getApplicationContext(), MapsActivity.class);
                Bundle bb = new Bundle();
                bb.putString(Constant.IMAGEURL,deliveryList.get(position).getImageUrl());
                bb.putDouble(Constant.LATITUDE,deliveryList.get(position).getLocation().getLat());
                bb.putDouble(Constant.LONGITUDE,deliveryList.get(position).getLocation().getLng());
                bb.putString(Constant.DESCRIPTION,deliveryList.get(position).getDescription());
                bb.putString(Constant.ADDRESS,deliveryList.get(position).getLocation().getAddress());
                activity.putExtras(bb);
                context.getApplicationContext().startActivity(activity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public TextView descriptionText;
        public ImageView carImage;
        public ViewHolder(CardView itemView) {
            super(itemView);
            cardView = itemView;
            carImage =(ImageView)cardView.findViewById(R.id.image);
            descriptionText = (TextView)cardView.findViewById(R.id.description);
        }
    }
}
