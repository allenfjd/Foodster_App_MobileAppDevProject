package com.example.foodster_app_mobileappdevproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerCreateOrderAdapter extends RecyclerView.Adapter {
    String[]dishNames;
    String[]dishAmount;
    LayoutInflater mInflater;

    ItemClickListener itemClickListener;

    public CustomerCreateOrderAdapter(Context context, String[]dishNames, String[]dishAmount){
        this.dishNames = dishNames;
        this.dishAmount = dishAmount;
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
        ((ViewHolder)holder).dishBox.setText(" " + dishNames[position] + " (" + dishAmount[position] + ")");
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

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            dishBox = itemView.findViewById(R.id.dishBox);
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
