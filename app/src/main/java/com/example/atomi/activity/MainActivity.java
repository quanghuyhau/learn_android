package com.example.atomi.activity;

import  static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.FrameLayout;

import com.example.atomi.R;
import com.example.atomi.api.ApiActivity;
import com.example.atomi.fragment.EnterFragment;
import com.example.atomi.fragment.HealthFragment;
import com.example.atomi.fragment.HomeFragment;
import com.example.atomi.fragment.ScienceFragment;
import com.example.atomi.fragment.SportsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.iammert.library.readablebottombar.ReadableBottomBar;
//import com.example.atomi.retrofit.ApiRetrofitActivity;


//import com.example.atomi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ReadableBottomBar readableBottomBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readableBottomBar = findViewById(R.id.readableBottomBar);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, new HomeFragment());
        fragmentTransaction.commit();


        readableBottomBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (i){

                    case 0:
                        fragmentTransaction.replace(R.id.content, new HomeFragment());
                        fragmentTransaction.commit();
                        break;

                    case 1:
                        fragmentTransaction.replace(R.id.content, new ScienceFragment());
                        fragmentTransaction.commit();
                        break;

                    case 2:
                        fragmentTransaction.replace(R.id.content, new SportsFragment());
                        fragmentTransaction.commit();
                        break;

                    case 3:
                        fragmentTransaction.replace(R.id.content, new HealthFragment());
                        fragmentTransaction.commit();
                        break;

                    case 4:
                        fragmentTransaction.replace(R.id.content, new EnterFragment());
                        fragmentTransaction.commit();
                        break;


                }
            }
        });
    }
}








