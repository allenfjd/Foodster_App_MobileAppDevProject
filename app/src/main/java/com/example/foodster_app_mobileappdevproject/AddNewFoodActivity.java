package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AddNewFoodActivity extends AppCompatActivity {
    DataBaseHelper dbh ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_food);


        EditText newName = findViewById(R.id.txtDishName);
        EditText newPrice = findViewById(R.id.txtDishPrice);
        EditText newAmount = findViewById(R.id.txtDishNum);
        EditText newDiscrip = findViewById(R.id.txtDishDisc);
        Button btnPost = findViewById(R.id.btnSaveEdit);
        EditText newtimePickUp = findViewById(R.id.txtDishPickUp);
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        dbh = new DataBaseHelper(this);
        Cursor foodStocksTable = dbh.viewDataFromFoodStocksTable(restaurantId);
        String [] name = new String [foodStocksTable.getCount()];
        String [] price = new String [foodStocksTable.getCount()];
        int count = 0;
        if(foodStocksTable.getCount()>0){
            while (foodStocksTable.moveToNext()){
                name[count] = foodStocksTable.getString(1);
                price[count] = foodStocksTable.getString(4);
                count++;
            }
        }
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        for(int i=0;i<name.length;i++){
            HashMap<String,String> data = new HashMap<>();
            data.put("Name", name[i]);
            data.put("Price", "$"+price[i]);
            list.add(data);
        }
        String [] from = {"Name", "Price"};
        int [] to = {R.id.txtNameDel, R.id.txtPriceDel};
        SimpleAdapter adapter = new SimpleAdapter(AddNewFoodActivity.this,list,R.layout.restaurant_delete_dish_layout,from,to);
        ListView listView = findViewById(R.id.lstDelDish);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> chosenData = list.get(position);
                String chosenname = chosenData.get("Name");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("editDish", chosenname);
                editor.commit();
                startActivity(new Intent(AddNewFoodActivity.this,DeleteConfirmActivity.class));
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newName.getText().toString();
                String price = newPrice.getText().toString();
                String amount = newAmount.getText().toString();
                String discrip = newDiscrip.getText().toString();
                String timePickUp = newtimePickUp.getText().toString();
                DateFormat foodDate = new SimpleDateFormat("yyyy-mm-dd");

                if (name.isEmpty() || amount.isEmpty() || price.isEmpty() || timePickUp.isEmpty()) {
                    Toast.makeText(AddNewFoodActivity.this, "Enter the table, please", Toast.LENGTH_LONG).show();
                } else {
                    if (dbh.addDataFoodStocksTable(restaurantId, name, foodDate.format(new Date()), amount, price, timePickUp, discrip)) {
                        Toast.makeText(AddNewFoodActivity.this, "New Food posted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AddNewFoodActivity.this, "Unsuccessful posting", Toast.LENGTH_LONG).show();
                    }
                }
                startActivity(new Intent(AddNewFoodActivity.this,AddNewFoodActivity.class));
           }
        });
    }
}