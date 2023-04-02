package com.example.foodster_app_mobileappdevproject;

import android.content.Context;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomerCreateOrderAdapter extends RecyclerView.Adapter {
    String[]dishNames;
    String[]availableAmount;
    String[]dishPrices;

    LayoutInflater mInflater;
    ItemClickListener itemClickListener;
    public CustomerCreateOrderAdapter(Context context, String[]dishNames, String[]availableAmount, String[]dishPrices){
        this.dishNames = dishNames;
        this.availableAmount = availableAmount;
        this.dishPrices = dishPrices;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_create_order,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).dishBox.setText("  " + dishNames[position]);
        ((ViewHolder)holder).txtPrice.setText("$" + dishPrices[position]);
        ((ViewHolder)holder).availableAmount.setText("Available: " + availableAmount[position]);
    }

    @Override
    public int getItemCount() {
        return dishNames.length;
    }

    void setClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView dishBox;
        TextView txtPrice;
        TextView availableAmount;
        EditText amountOrdered;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            dishBox = itemView.findViewById(R.id.dishBox);
            txtPrice = itemView.findViewById((R.id.txtPrice));

            availableAmount = itemView.findViewById(R.id.amountAvailable);
//            amountOrdered = itemView.findViewById(R.id.orderedAmount);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(itemClickListener!=null){
                itemClickListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
}
