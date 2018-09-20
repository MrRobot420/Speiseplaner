package com.mobilieapplikationen.speiseplan.mealdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mobileapplikationenfhws.speiseplan.R;

public class MealdetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealdetail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        //String mensa = intent.getExtras().getString("foodData_mensa");
        String name = intent.getExtras().getString("foodData_name");
        String type = intent.getExtras().getString("foodData_type");
        String date = intent.getExtras().getString("foodData_date");
        String price = intent.getExtras().getString("foodData_price");
        String price_be = intent.getExtras().getString("foodData_price_bed");
        String price_gu = intent.getExtras().getString("foodData_price_guest");


        //TextView mensa_view = findViewById(R.id.mensa_detail);
        TextView name_view = findViewById(R.id.name_detail);
        TextView type_view = findViewById(R.id.type_detail);
        TextView date_view = findViewById(R.id.date_detail);
        TextView price_view = findViewById(R.id.price_detail);
        TextView price_b_view = findViewById(R.id.price_bed_detail);
        TextView price_g_view = findViewById(R.id.price_guest_detail);

        //mensa_view.setText(mensa);
        name_view.setText(name);
        // TODO: Create Function to add pics (Chicken etc...) for certain TYPES -> G = Huhn | S = Schwein | ...
        type_view.setText(type);

        date_view.setText(date);
        price_view.setText(price);
        price_b_view.setText(price_be);
        price_g_view.setText(price_gu);


    }

}
