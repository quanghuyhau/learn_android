package com.example.atomi.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.atomi.R;
import com.example.atomi.fragment.HomeFragment;
import com.example.atomi.fragment.ProductFragment;
import com.example.atomi.fragment.ScienceFragment;
import com.example.atomi.fragment.UserFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.iammert.library.readablebottombar.ReadableBottomBar;
//import com.example.atomi.retrofit.ApiRetrofitActivity;


//import com.example.atomi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ReadableBottomBar readableBottomBar;

    Fragment productFragment;

    Toolbar toolbar;

    FirebaseAuth auth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        readableBottomBar = findViewById(R.id.readableBottomBar);


        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home10);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, new HomeFragment());
        fragmentTransaction.commit();
        readableBottomBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (i){

                    case 0:
                        toolbar.setVisibility(View.VISIBLE);
                        fragmentTransaction.replace(R.id.content, new HomeFragment());
                        fragmentTransaction.commit();
                        break;

                    case 1:
                        toolbar.setVisibility(View.VISIBLE);
                        fragmentTransaction.replace(R.id.content, new ScienceFragment());
                        fragmentTransaction.commit();
                        break;

                    case 2:
                        toolbar.setVisibility(View.VISIBLE);
                        fragmentTransaction.replace(R.id.content, new ProductFragment());
                        fragmentTransaction.commit();
                        break;

//                    case 3:
//                        fragmentTransaction.replace(R.id.content, new HealthFragment());
//                        fragmentTransaction.commit();
//                        break;

                    case 3:
                        toolbar.setVisibility(View.GONE);
                        fragmentTransaction.replace(R.id.content, new UserFragment());
                        fragmentTransaction.commit();
                        break;


                }
            }
        });



//        //product
//         productFragment = new ProductFragment();
//         loadFragment(productFragment);
    }
    private void loadFragment(Fragment productFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,productFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_logout) {
            auth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();

        } else if (id == R.id.menu_cart) {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        }
        return true;
    }
}









