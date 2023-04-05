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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class DeleteConfirmActivity extends AppCompatActivity {
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_confirm);

        Button btnCancel = findViewById(R.id.btnCancelDel);
        Button btnDelete = findViewById(R.id.btnDel);
        TextView txtTitleName = findViewById(R.id.txtNameDishDel);
        TextView txtTitlePrice = findViewById(R.id.txtPriceDishDel);
        TextView txtTitleStock = findViewById(R.id.txtAmountDishDel);
        TextView txtTitleDisc = findViewById(R.id.txtDiscDishDel);
//        TextView txtTEST = findViewById(R.id.txtTest10);
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        String delDish = preferences.getString("editDish", "defaultValue");

        String name = "";
        String price = "";
        String amount = "";
        String disc = "";
        dbh = new DataBaseHelper(this);
        Cursor foodStocksTable = dbh.viewFoodStocksNameFoodRestaurantID(delDish, restaurantId);
        if(foodStocksTable.getCount()>0){
            while(foodStocksTable.moveToNext()){
                name = foodStocksTable.getString(1);
                price = foodStocksTable.getString(4);
                amount = foodStocksTable.getString(3);
                disc = foodStocksTable.getString(6);
            }
        }
        txtTitleName.setText(name);
        txtTitlePrice.setText("$"+ price);
        txtTitleStock.setText(amount);
        txtTitleDisc.setText(disc);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeleteConfirmActivity.this,AddNewFoodActivity.class));
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.deleteItem(delDish, restaurantId);
                startActivity(new Intent(DeleteConfirmActivity.this,AddNewFoodActivity.class));
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationRestDel);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(DeleteConfirmActivity.this, RestaurantOpeningActivity.class));
                        return true;
                    case R.id.navigation_orders:
                        startActivity(new Intent(DeleteConfirmActivity.this, GenerateReportsActivity.class));
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(DeleteConfirmActivity.this, RestaurantProfile.class));
                        return true;
                }
                return false;
            }

        });
    }
}