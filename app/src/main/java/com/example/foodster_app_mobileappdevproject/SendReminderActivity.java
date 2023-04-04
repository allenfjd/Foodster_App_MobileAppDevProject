package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SendReminderActivity extends AppCompatActivity implements RestaurantOpeningAdapter.ItemClickListener{
    DataBaseHelper  dbh;
    RestaurantOpeningAdapter adapter;
    TextView txtTEST;
    String [] orderNums;
    int [] orderReminders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_reminder);
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        String password = preferences.getString("PasswordOfRestaurant", "defaultValue");
        String restaurantName = preferences.getString("RestaurantName", "defaultValue");
        String firstNameOwner = preferences.getString("FirstNameOwner", "defaultValue");
        String lastNameOwner = preferences.getString("LastNameOwner", "defaultValue");
        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "defaultValue");
        String city = preferences.getString("City", "defaultValue");
        String address = preferences.getString("AddressRestaurant", "defaultValue");
        String email = preferences.getString("RestaurantEmail", "defaultValue");
        dbh = new DataBaseHelper(this);
        Cursor cursorOrderTable = dbh.viewNotDeleveredOrders(restaurantId);
        orderNums = new String[cursorOrderTable.getCount()];
        String [] foodNames = new String[cursorOrderTable.getCount()];
        String [] foodAmounts = new String[cursorOrderTable.getCount()];
        orderReminders = new int[cursorOrderTable.getCount()];
        String[] displayNamesFood = new String [cursorOrderTable.getCount()];
        if(cursorOrderTable.getCount()>0){
            int count = 0;
            while(cursorOrderTable.moveToNext()){
                if(cursorOrderTable.getString(7).equals("In progress")){
                    orderNums[count] = cursorOrderTable.getString(0);
                    foodNames[count] = cursorOrderTable.getString(4);
                    foodAmounts[count] = cursorOrderTable.getString(5);
                    if(foodNames[count].contains("|")) {
                        String[] namesSepar = foodNames[count].split("\\|");
                        String[] amountSepar = foodAmounts[count].split("\\|");
                        displayNamesFood[count] = "";
                        for (int i = 0; i < namesSepar.length; i++) {
                            displayNamesFood[count] += namesSepar[i] + " (" + amountSepar[i] + ")\n ";
                        }
                    }else {
                        displayNamesFood[count] = foodNames[count]+" ("+foodAmounts[count]+")";
                    }
                    if(cursorOrderTable.getString(8).equals("1")){
                    orderReminders[count]= R.drawable.icon_bell;
                    }else{
                        orderReminders[count]= R.drawable.black_bell;
                    }
                    count++;
                }
            }
        }
        RecyclerView recyclerView = findViewById(R.id.recycleReminder);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new RestaurantOpeningAdapter(this,orderNums, displayNamesFood, orderReminders);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(this,"Reminder sent", Toast.LENGTH_SHORT).show();
        boolean reminder;
        if(orderReminders[position]==R.drawable.black_bell) {

            reminder = dbh.setTrueReminder(orderNums[position]);
            if(reminder){
                Toast.makeText(this,"Reminder sent", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Unsuccessful reminder", Toast.LENGTH_SHORT).show();
            }
        }else{
            reminder = dbh.setFalseReminder(orderNums[position]);
            if(reminder){
                Toast.makeText(this,"Reminder disabled", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Unsuccessful change", Toast.LENGTH_SHORT).show();
            }
        }
        startActivity(new Intent(SendReminderActivity.this,SendReminderActivity.class));
    }
}