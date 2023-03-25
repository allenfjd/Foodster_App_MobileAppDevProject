package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class CustomerCreateOrder extends AppCompatActivity implements CustomerCreateOrderAdapter.ItemClickListener{

    DataBaseHelper dbh;
    CustomerCreateOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_order);
        Bundle extras = getIntent().getExtras();
        Integer restaurantIds = null;
        if (extras != null) {
            restaurantIds = extras.getInt("key");
        }
        dbh = new DataBaseHelper(this);

        Cursor c = dbh.viewDataFromFoodStocksTable(String.valueOf(restaurantIds));
        String[] dishName = new String[c.getCount()];
        String[] dishAmount = new String[c.getCount()];
        int count = 0;
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                dishName[count] = c.getString(1);
                dishAmount[count] = c.getString(3);
                count++;
            }
        }

        RecyclerView recyclerView = findViewById(R.id.foodRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomerCreateOrderAdapter(this, dishName, dishAmount);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}