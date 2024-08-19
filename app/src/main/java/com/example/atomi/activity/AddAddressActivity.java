package com.example.atomi.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.atomi.R;


public class AddAddressActivity extends AppCompatActivity {

    EditText name, address, city, postalCode, phone;
    Toolbar toolbar;
    Button addAddressBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address_activity);

        toolbar = findViewById(R.id.add_address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        name = findViewById(R.id.ad_name);
        address = findViewById(R.id.ad_address);
        city = findViewById(R.id.ad_city);
        postalCode = findViewById(R.id.ad_code);
        phone = findViewById(R.id.ad_phone);
        addAddressBtn = findViewById(R.id.add_address_btn);

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String addressUser = address.getText().toString();
                String cityUser = city.getText().toString();
                String postalCodeUser = postalCode.getText().toString();
                String phoneUser = phone.getText().toString();
                String addressId = "";
            }
        });
    }
}
