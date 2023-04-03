package com.example.foodster_app_mobileappdevproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerOrders extends AppCompatActivity {
    DataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String customerEmail = preferences.getString("CustomerEmail","DefaultValue");
        dbh =new DataBaseHelper(this);
        Cursor c = dbh.viewCustomerOrderTable(customerEmail);
        String[] restaurantName = new String[c.getCount()];
        String[] deliveryOrPickup = new String[c.getCount()];
        String[] status = new String[c.getCount()];
        String[] date = new String[c.getCount()];
        int count = 0;
        if(c.getCount()>0){
            while (c.moveToNext()){
                restaurantName[count] = c.getString(4);
                deliveryOrPickup[count] = c.getString(1);
                status[count] = c.getString(3);
                date[count] = c.getString(2);
                count++;
            }
        }

        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<restaurantName.length;i++){
            HashMap<String,String> data = new HashMap<>();
            data.put("Restaurant", restaurantName[i]);
            data.put("deliveryOrPickup",deliveryOrPickup[i]);
            data.put("status",status[i]);
            data.put("date", date[i]);
            list.add(data);
        }

        String[]from = {"Restaurant","deliveryOrPickup","status","date"};
        int[]to = {R.id.txtRestaurantOrdered,R.id.txtDeliveryOrPickup,R.id.txtStatus,R.id.txtOrderDate};

        SimpleAdapter adapter = new SimpleAdapter(CustomerOrders.this,list,R.layout.list_orders,from,to);
        ListView listView = findViewById(R.id.lstOrders);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_orders);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(CustomerOrders.this, CustomerOpeningActivity.class));
                        return true;
                    case R.id.navigation_orders:

                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(CustomerOrders.this, CustomerProfile.class));
                        return true;
                }
                return false;
            }

        });
    }
}