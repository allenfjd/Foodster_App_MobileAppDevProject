package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerEditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_profile);
        EditText CustomerFirstNameEditProfile=findViewById(R.id.etxtCNameEdit);
        EditText CustomerLastNameEditProfile=findViewById(R.id.etxtCLastNameEdit);
        EditText CustomerMailEditProfile=findViewById(R.id.etxtCEmailEdit);
        EditText CustomerPhoneEditProfile=findViewById(R.id.etxtCPhoneEdit);
        EditText CustomerAdressEditProfile=findViewById(R.id.etxtCAdressEdit);
        Button buttonSaveChanges =findViewById(R.id.btnRSaveChanges);

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String firstNameOwner = preferences.getString("FirstNameOwner", "");
        String lastNameOwner = preferences.getString("LastNameOwner", "");
        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "");
        String address = preferences.getString("AddressRestaurant", "");

        CustomerFirstNameEditProfile.setText(firstNameOwner);
        CustomerLastNameEditProfile.setText(lastNameOwner);
        CustomerPhoneEditProfile.setText(phoneNumberRestaurant);
        CustomerAdressEditProfile.setText(address);

        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String newFirstName = CustomerFirstNameEditProfile.getText().toString();
//                String newLastName = RCustomerLastNameEditProfile.getText().toString();
//                String newPhone = CustomerPhoneEditProfile.getText().toString();
//                String newAddress = CustomerAdressEditProfile.getText().toString();
//
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("FirstNameOwner", newFirstName);
//                editor.putString("LastNameOwner", newLastName);
//                editor.putString("PhoneNumberRestaurant", newPhone);
//                editor.putString("AddressRestaurant", newAddress);
//                editor.apply();


                Toast.makeText(CustomerEditProfile.this, "Related field is updated", Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }
}