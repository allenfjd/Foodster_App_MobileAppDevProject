package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerRestaurantsNorthVan extends AppCompatActivity {

    DataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_restaurants_north_van);

        dbh =new DataBaseHelper(this);

        Cursor c = dbh.viewDataFromRestaurantTableNorthVan();
        String[] restaurantName = new String[c.getCount()];
        String[] address = new String[c.getCount()];
        Integer[] restaurantId = new Integer[c.getCount()];
        int count = 0;
        if(c.getCount()>0){
            while(c.moveToNext()){
                restaurantId[count] = c.getInt(0);
                restaurantName[count] = c.getString(2);
                address[count] = c.getString(7);
                count++;
            }
        }
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<restaurantName.length;i++){
            HashMap<String,String> data = new HashMap<>();
            data.put("Restaurant", restaurantName[i]);
            data.put("Address",address[i]);
            data.put("Id", String.valueOf(restaurantId[i]));
            list.add(data);
        }
        String[]from = {"Restaurant","Address", "Id"};
        int[]to = {R.id.restaurantName, R.id.restaurantAddress, R.id.testText};

        SimpleAdapter adapter = new SimpleAdapter(CustomerRestaurantsNorthVan.this,
                list,R.layout.customer_restaurants_list,from,to);

        ListView listView = findViewById(R.id.lstRestaurants);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                HashMap<String,String> chosenData = list.get(position);
                Integer chosenId = Integer.valueOf(chosenData.get("Id"));
                Intent i = new Intent(CustomerRestaurantsNorthVan.this,CustomerCreateOrder.class);
                i.putExtra("key", chosenId);
                startActivity(i);
            }
        });
    }
}