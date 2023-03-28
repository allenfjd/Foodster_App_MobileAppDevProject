package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class CustomerCreateOrder extends AppCompatActivity implements CustomerCreateOrderAdapter.ItemClickListener{

    DataBaseHelper dbh;
    CustomerCreateOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_order);
        Button btnOrder = findViewById(R.id.btnOrder);
        RadioButton radioPickup = findViewById(R.id.pickupRadio);
        RadioButton radioDelivery = findViewById(R.id.deliveryRadio);
        Bundle extras = getIntent().getExtras();
        String restaurantIds = null;
        String address = "";
        if (extras != null) {
            restaurantIds = extras.getString("id");
            address = extras.getString("address");
        }
        dbh = new DataBaseHelper(this);

        Cursor c = dbh.viewDataFromFoodStocksTable(String.valueOf(restaurantIds));
        String[] dishName = new String[c.getCount()];
        String[] dishAmount = new String[c.getCount()];
        String[] dishPrices = new String[c.getCount()];
        String pickupTime = new String();
        int count = 0;
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                dishName[count] = c.getString(1);
                dishAmount[count] = c.getString(3);
                dishPrices[count] = c.getString(4);
                pickupTime = c.getString(5);
                count++;
            }
        }

        RecyclerView recyclerView = findViewById(R.id.foodRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomerCreateOrderAdapter(this, dishName, dishAmount, dishPrices);
        recyclerView.setAdapter(adapter);
        String finalAddress = address;
        String pickup = pickupTime;
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioPickup.isChecked()){
                    Intent i = new Intent(CustomerCreateOrder.this,CustomerPickupOrder.class);
                    Bundle extras = new Bundle();
                    extras.putString("address", finalAddress);
                    extras.putString("pickup", pickup);
                    i.putExtras(extras);
                    startActivity(i);
                }else if(radioDelivery.isChecked()){
                    Intent a = new Intent(CustomerCreateOrder.this,CustomerDeliveryOrder.class);
                    a.putExtra("address", finalAddress);
                    startActivity(a);
                }

            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}