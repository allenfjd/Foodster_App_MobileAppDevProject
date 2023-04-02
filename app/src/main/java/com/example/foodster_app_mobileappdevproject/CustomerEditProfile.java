package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerEditProfile extends AppCompatActivity {
    DataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbh = new DataBaseHelper( this);

        setContentView(R.layout.activity_customer_edit_profile);
        EditText CustomerFirstNameEditProfile=findViewById(R.id.etxtCNameEdit);
        EditText CustomerLastNameEditProfile=findViewById(R.id.etxtCLastNameEdit);
        EditText CustomerMailEditProfile=findViewById(R.id.etxtCEmailEdit);
        EditText CustomerPhoneEditProfile=findViewById(R.id.etxtCPhoneEdit);
        EditText CustomerAdressEditProfile=findViewById(R.id.etxtCAdressEdit);
        Button buttonSaveChanges =findViewById(R.id.btnRSaveChanges);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Retrieve updated preferences
        String email = preferences.getString("CustomerEmail", "defaultValue");
        String firstNameCustomer = preferences.getString("FirstNameCustomer", "defaultValue");
        String lastNameCustomer = preferences.getString("LastNameCustomer", "defaultValue");
        String phoneNumberCustomer = preferences.getString("PhoneNumberCustomer", "defaultValue");
        String addressCustomer = preferences.getString("AddressCustomer", "defaultValue");



        CustomerFirstNameEditProfile.setText(firstNameCustomer);
        CustomerLastNameEditProfile.setText(lastNameCustomer);
        CustomerMailEditProfile.setText(email);
        CustomerPhoneEditProfile.setText(phoneNumberCustomer);
        CustomerAdressEditProfile.setText(addressCustomer);

        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newFirstName = CustomerFirstNameEditProfile.getText().toString();
                String newLastName = CustomerLastNameEditProfile.getText().toString();
                String newPhone = CustomerPhoneEditProfile.getText().toString();
                String newAddress = CustomerAdressEditProfile.getText().toString();
                dbh.updateInfoCustomer(email,newFirstName,newLastName,newPhone, newAddress);

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("FirstNameCustomer", newFirstName);
                editor.putString("LastNameCustomer", newLastName);
                editor.putString("PhoneNumberCustomer", newPhone);
                editor.putString("AddressCustomer", newAddress);
                editor.apply();


                Toast.makeText(CustomerEditProfile.this, "Related field is updated", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}