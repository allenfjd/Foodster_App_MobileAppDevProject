package com.example.foodster_app_mobileappdevproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerCreateOrderAdapter extends RecyclerView.Adapter {
    String[]dishNames;
    String[]dishAmount;
    String[]dishPrices;
    LayoutInflater mInflater;

    ItemClickListener itemClickListener;

    public CustomerCreateOrderAdapter(Context context, String[]dishNames, String[]dishAmount, String[]dishPrices){
        this.dishNames = dishNames;
        this.dishAmount = dishAmount;
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
        ((ViewHolder)holder).dishBox.setText(" " + dishNames[position] + " (" + dishAmount[position] + ")");
        ((ViewHolder)holder).txtPrice.setText("$" + dishPrices[position]);
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
        EditText dishNumber;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            dishBox = itemView.findViewById(R.id.dishBox);
            txtPrice = itemView.findViewById((R.id.txtPrice));
            dishNumber = itemView.findViewById(R.id.dishAmount);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener!=null){
                itemClickListener.onItemClick(view,getAdapterPosition());
                dishNumber.getText();
            }
        }
    }
}
