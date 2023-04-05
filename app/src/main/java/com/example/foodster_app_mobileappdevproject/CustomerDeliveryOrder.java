package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerDeliveryOrder extends AppCompatActivity {

    DataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_delivery_order);

        TextView txtRestaurant = findViewById(R.id.txtRestaurantAdd);
        TextView txtCustomer = findViewById(R.id.txtCustomerAddress);
        TextView txtOrders = findViewById(R.id.txtOrder);
        TextView totalPrice = findViewById(R.id.txtFinalPrice);
        Button finalOrderBtn = findViewById(R.id.finalOrderBtn);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(new Date());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String customerEmail = preferences.getString("CustomerEmail","DefaultValue");
        dbh =new DataBaseHelper(this);
        Cursor c = dbh.viewDataFromCustomerTable(customerEmail);
        String customerAddress = "";
        if(c.getCount()>0){
            while(c.moveToNext()){
                customerAddress = c.getString(4);
            }
        }

        Bundle extra = getIntent().getExtras();
        String address = "";
        String order = "";
        String price="";
        String id = "";
        if (extra != null) {
            address = extra.getString("address");
            order = extra.getString("order");
            price = extra.getString("price");
            id = extra.getString("Id");
        }

        String formattedPrice ="";
        if (price.matches("")) {
            formattedPrice ="no orders";
        }else{
            formattedPrice = String.format("%.2f", Double.valueOf(price));
        }
        txtRestaurant.setText(address);
        txtOrders.setText(order);
        totalPrice.setText("Total price: $" + formattedPrice);

        txtCustomer.setText(customerAddress);

        String finalId = id;
        String delivery = "Delivery";
        String finalOrders = order;

        finalOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalOrders.matches("no orders")){
                    Toast.makeText(getApplicationContext(),"Please create an order",Toast.LENGTH_SHORT).show();
                }else{
                    String[] theOrder = finalOrders.split("\n");
                    String result = "";
                    for (int i=0;i < theOrder.length; i++){
                        String line = theOrder[i].trim();
                        int spaceIndex = line.indexOf(" ");
                        if(spaceIndex != -1){
                            String firstWord = line.substring(0, spaceIndex);
                            result += firstWord;
                            if(i< theOrder.length -1){
                                result += "|";
                            }
                        }
                    }

                    dbh.addDataOrderTable(customerEmail, finalId, currentDate, result, String.valueOf(1), delivery, "In Progress", 0);
                    Toast.makeText(getApplicationContext(),"Order created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CustomerDeliveryOrder.this, CustomerOpeningActivity.class));
                }

            }
        });
    }
}