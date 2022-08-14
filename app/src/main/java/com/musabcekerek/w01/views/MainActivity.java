package com.musabcekerek.w01.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.musabcekerek.w01.R;
import com.musabcekerek.w01.adapters.CarListAdapter;
import com.musabcekerek.w01.base.BaseCars;
import com.musabcekerek.w01.brand.BMW;
import com.musabcekerek.w01.brand.MERCEDES;
import com.musabcekerek.w01.enums.BrandEnums;
import com.musabcekerek.w01.manager.CarManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<BaseCars> carsList;
    FirebaseAuth mAuth;
    private Button logOut;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logOut = findViewById(R.id.logOut);
        mAuth = FirebaseAuth.getInstance();
        carsList= new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.listView);

        carsList.add(CarManager.getInstance(BrandEnums.BMW).getCars());
        carsList.add(CarManager.getInstance(BrandEnums.AUDI).getCars());
        carsList.add(CarManager.getInstance(BrandEnums.MERCEDES).getCars());
        /* BaseCars mercedes = new MERCEDES("MERCEDES","4","2","4");
        carsList.add(mercedes); */

        CarListAdapter carListAdapter = new CarListAdapter(this, R.layout.adapter_view_layout, carsList);
        listView.setAdapter(carListAdapter);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

    }



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }




}