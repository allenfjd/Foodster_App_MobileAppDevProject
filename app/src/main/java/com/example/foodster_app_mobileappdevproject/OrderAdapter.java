package com.example.foodster_app_mobileappdevproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter {
    Context context;
    List<Stock> stockList;

    public OrderAdapter(Context context, List<Stock> stockList){
        this.context = context;
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_create_order,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Stock stock = stockList.get(position);
        ((ViewHolder)holder).dishBox.setText("  " + stock.getDishName());
        ((ViewHolder)holder).txtPrice.setText("$"+ stock.getDishPrice());
        ((ViewHolder)holder).availableAmount.setText("Available: " + stock.getAvailableAmount());
        ((ViewHolder) holder).bind(stockList.get(position));
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dishBox, txtPrice, availableAmount;
        ImageView checkmark;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            dishBox = itemView.findViewById(R.id.dishBox);
            txtPrice = itemView.findViewById((R.id.txtPrice));
            availableAmount = itemView.findViewById(R.id.amountAvailable);
            checkmark = itemView.findViewById(R.id.checkmark);

        }

        void bind(final Stock stock){
            checkmark.setVisibility(stock.isChecked() ? View.VISIBLE : View.GONE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stock.setChecked(!stock.isChecked());
                    checkmark.setVisibility(stock.isChecked()? View.VISIBLE : View.GONE);
                }
            });

        }
    }

    public List<Stock> getSelected(){
        List<Stock> selected = new ArrayList<>();
        for(int i=0;i < stockList.size(); i++){
            if(stockList.get(i).isChecked()){
                selected.add(stockList.get(i));
            }
        }
        return selected;
    }

}
