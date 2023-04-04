package com.example.foodster_app_mobileappdevproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RestaurantOpeningAdapter extends RecyclerView.Adapter {
    String[]orderNums;
    String[]displayNamesFood;
    int[]reminder;
    ItemClickListener itemClickListener;
    LayoutInflater inflater;
    DataBaseHelper dbh;

    public RestaurantOpeningAdapter(Context context, String[]orderNums, String[]displayNamesFood, int[]reminder){
        this.orderNums = orderNums;
        this.displayNamesFood = displayNamesFood;
        this.reminder = reminder;
        inflater = LayoutInflater.from(context);
    }

    Integer getItem(int id){return reminder[id];}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.restaurant_reminder,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).txtOrderNum.setText("Order #"+orderNums[position]);
        ((ViewHolder)holder).txtNameFood.setText(displayNamesFood[position]);
        ((ViewHolder)holder).imageBell.setImageResource(reminder[position]);

    }

    @Override
    public int getItemCount() {
        return displayNamesFood.length;
    }

    void setClickListener(ItemClickListener mItemClickListener){
        itemClickListener = mItemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtOrderNum;
        TextView txtNameFood;
        ImageView imageBell;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderNum=itemView.findViewById(R.id.txtOrderNum);
            txtNameFood=itemView.findViewById(R.id.txtNameFood);
            imageBell=itemView.findViewById(R.id.imgBell);
            itemView.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null)
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

