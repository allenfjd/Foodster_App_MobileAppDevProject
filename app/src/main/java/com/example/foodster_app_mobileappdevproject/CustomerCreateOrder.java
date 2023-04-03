package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerCreateOrder extends AppCompatActivity{

    DataBaseHelper dbh;
    List<Stock> stockList;
    OrderAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_order);
        Button btnOrder = findViewById(R.id.btnOrder);
        RadioButton radioPickup = findViewById(R.id.pickupRadio);
        RadioButton radioDelivery = findViewById(R.id.deliveryRadio);
        Bundle extras = getIntent().getExtras();
        stockList=new ArrayList<>();
        String restaurantIds = null;
        String address = "";
        if (extras != null) {
            restaurantIds = extras.getString("id");
            address = extras.getString("address");
        }
        dbh = new DataBaseHelper(this);

        Cursor c = dbh.viewDataFromFoodStocksTable(String.valueOf(restaurantIds));
        String pickupTime = new String();


        for(int i=0;i <c.getCount();i++){
            while (c.moveToNext()) {
                Stock stock = new Stock();
                stock.setDishName(c.getString(1));
                stock.setAvailableAmount(c.getString(3));
                stock.setDishPrice( c.getString(4));
                pickupTime = c.getString(5);
                stockList.add(stock);
            }
        }

        RecyclerView recyclerView = findViewById(R.id.foodRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        adapter1 = new OrderAdapter(this, stockList);
        recyclerView.setAdapter(adapter1);

        String finalAddress = address;
        String pickup = pickupTime;
        String finalRestaurantIds = restaurantIds;
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioPickup.isChecked()){
                    String order = getOrders();
                    String totalPrice = calculatePrice();
                    Intent i = new Intent(CustomerCreateOrder.this,CustomerPickupOrder.class);
                    Bundle extras = new Bundle();
                    extras.putString("Id", finalRestaurantIds);
                    extras.putString("address", finalAddress);
                    extras.putString("pickup", pickup);
                    extras.putString("order", order);
                    extras.putString("price", totalPrice);
                    i.putExtras(extras);
                    startActivity(i);
                }else if(radioDelivery.isChecked()){
                    String order = getOrders();
                    String totalPrice = calculatePrice();
                    Intent a = new Intent(CustomerCreateOrder.this,CustomerDeliveryOrder.class);
                    Bundle extra = new Bundle();
                    extra.putString("address", finalAddress);
                    extra.putString("order", order);
                    extra.putString("price", totalPrice);
                    extra.putString("Id", finalRestaurantIds);
                    a.putExtras(extra);
                    startActivity(a);
                }

            }
        });

    }


    private String getOrders() {
        if (adapter1.getSelected().size() > 0) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < adapter1.getSelected().size(); i++) {
                str.append(adapter1.getSelected().get(i).getDishName() + " - Price: " + adapter1.getSelected().get(i).getDishPrice());
                str.append("\n");
            }
            return str.toString().trim();
        }
        else{return "no orders";}
    }

    private String calculatePrice(){
        if (adapter1.getSelected().size() > 0) {
            Double totalPrice = 0.0;
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < adapter1.getSelected().size(); i++) {
                Double initialPrice = Double.parseDouble(adapter1.getSelected().get(i).getDishPrice());
                 totalPrice += initialPrice;
            }
            str.append(totalPrice);
            return str.toString().trim();
        }
        else{
            return "";
        }
    }

}