package com.mobilieapplikationen.speiseplan.mealdetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileapplikationenfhws.speiseplan.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MealdetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealdetail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Use data from previous activities:
        Intent intent = getIntent();
        String mensa = fetchIntentData(intent,"mensa_name");
        String name = fetchIntentData(intent, "foodData_name");
        String type = fetchIntentData(intent, "foodData_type");
        String additives = fetchIntentData(intent, "foodData_additives");
        String date = fetchIntentData(intent, "foodData_date");
        String price = fetchIntentData(intent, "foodData_price");
        String price_be = fetchIntentData(intent, "foodData_price_bed");
        String price_gu = fetchIntentData(intent, "foodData_price_guest");


        // INIT UI - Elements
        TextView mensa_view = findViewById(R.id.mensa_detail);
        TextView name_view = findViewById(R.id.name_detail);
        TextView add_view = findViewById(R.id.add_detail);
        TextView date_view = findViewById(R.id.date_detail);
        TextView price_view = findViewById(R.id.price_detail);
        TextView price_b_view = findViewById(R.id.price_bed_detail);
        TextView price_g_view = findViewById(R.id.price_guest_detail);
        ImageView type_view = findViewById(R.id.type_detail);
        ConstraintLayout name_layout = findViewById(R.id.name_cons_layout);

        // Set TEXT of UI - Elements
        mensa_view.setText(mensa);
        name_view.setText(name);
        add_view.setText(additives);
        date_view.setText(cutString(date));
        price_view.setText(price + "€");
        price_b_view.setText(price_be + "€");
        price_g_view.setText(price_gu + "€");



        // Handle IMAGE for food-type:
        if (type.toLowerCase().equals("r")) {
            type_view.setImageResource(R.drawable.cow_100);
            name_layout.setBackgroundColor(Color.rgb(252, 244, 244));
        } else if (type.toLowerCase().equals("v")) {
            type_view.setImageResource(R.drawable.vegan_100);
            name_layout.setBackgroundColor(Color.rgb(233, 241, 234));
        } else if (type.toLowerCase().equals("fl")) {
            type_view.setImageResource(R.drawable.veget_100);
            name_layout.setBackgroundColor(Color.rgb(236, 247, 233));
        } else if (type.toLowerCase().equals("g")) {
            type_view.setImageResource(R.drawable.chicken_100);
            name_layout.setBackgroundColor(Color.rgb(247, 233, 233));
        } else if (type.toLowerCase().equals("l")) {
            type_view.setImageResource(R.drawable.sheep_100);
            name_layout.setBackgroundColor(Color.rgb(245, 245, 235));
        } else if (type.toLowerCase().equals("k")) {
            type_view.setImageResource(R.drawable.calf_52);
            name_layout.setBackgroundColor(Color.rgb(245, 245, 235));
        } else if (type.toLowerCase().equals("s")) {
            type_view.setImageResource(R.drawable.pig_100);
            name_layout.setBackgroundColor(Color.rgb(252, 244, 236));
        } else if (type.toLowerCase().equals("f")) {
            type_view.setImageResource(R.drawable.fish_100);
            name_layout.setBackgroundColor(Color.rgb(243, 245, 252));
        } else if (type.toLowerCase().equals("w")) {
            type_view.setImageResource(R.drawable.deer_100);
            name_layout.setBackgroundColor(Color.rgb(245, 245, 235));
        } else if (type.toLowerCase().equals("vo")) {
            type_view.setImageResource(R.drawable.ham_90);
            name_layout.setBackgroundColor(Color.rgb(245, 245, 235));
        } else if (type.toLowerCase().equals("a")) {
            type_view.setImageResource(R.drawable.alcohol_100);
            name_layout.setBackgroundColor(Color.rgb(245, 245, 235));
        } else {
            name_layout.setBackgroundColor(Color.rgb(255, 255, 255));
        }
    }


    // "Shortens" the intent .getExtras().getString(key) (?)
    private String fetchIntentData(Intent intent, String key) {
        String data = intent.getExtras().getString(key);
        return data;
    }


    // CUTS OFF a Date at ...T... & transforms it into different (european) TIME FORMAT
    public String cutString(String date) {
        char[] array = date.toCharArray();
        String end_date = "";

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == 'T') {
                break;
            } else {
                end_date += array[i];
            }
        }
        SimpleDateFormat dtf_before = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt = dtf_before.parse(end_date);
            SimpleDateFormat dtf_after = new SimpleDateFormat("dd.MM.yyyy");
            end_date = dtf_after.format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return end_date;
    }

}
