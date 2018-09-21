package com.mobilieapplikationen.speiseplan.mealdetail;

import android.content.Intent;
import android.os.Bundle;
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

        Intent intent = getIntent();
        String mensa = fetchIntentData(intent,"mensa_name");
        String name = fetchIntentData(intent, "foodData_name");
        String type = fetchIntentData(intent, "foodData_type");
        String date = fetchIntentData(intent, "foodData_date");
        String price = fetchIntentData(intent, "foodData_price");
        String price_be = fetchIntentData(intent, "foodData_price_bed");
        String price_gu = fetchIntentData(intent, "foodData_price_guest");


        TextView mensa_view = findViewById(R.id.mensa_detail);
        TextView name_view = findViewById(R.id.name_detail);
        ImageView type_view = findViewById(R.id.type_detail);
        TextView date_view = findViewById(R.id.date_detail);
        TextView price_view = findViewById(R.id.price_detail);
        TextView price_b_view = findViewById(R.id.price_bed_detail);
        TextView price_g_view = findViewById(R.id.price_guest_detail);

        mensa_view.setText(mensa);
        name_view.setText(name);
        date_view.setText(cutString(date));
        price_view.setText(price + "€");
        price_b_view.setText(price_be + "€");
        price_g_view.setText(price_gu + "€");


        if (type.toLowerCase().equals("r")) {
            type_view.setImageResource(R.drawable.cow_100);
        } else if (type.toLowerCase().equals("v")) {
            type_view.setImageResource(R.drawable.vegan_100);
        } else if (type.toLowerCase().equals("fl")) {
            type_view.setImageResource(R.drawable.veget_100);
        } else if (type.toLowerCase().equals("g")) {
            type_view.setImageResource(R.drawable.chicken_100);
        } else if (type.toLowerCase().equals("l")) {
            type_view.setImageResource(R.drawable.sheep_100);
        } else if (type.toLowerCase().equals("k")) {
            type_view.setImageResource(R.drawable.calf_52);
        } else if (type.toLowerCase().equals("s")) {
            type_view.setImageResource(R.drawable.pig_100);
        } else if (type.toLowerCase().equals("f")) {
            type_view.setImageResource(R.drawable.fish_100);
        } else if (type.toLowerCase().equals("w")) {
            type_view.setImageResource(R.drawable.deer_100);
        } else if (type.toLowerCase().equals("vo")) {
            type_view.setImageResource(R.drawable.ham_90);
        } else if (type.toLowerCase().equals("a")) {
        type_view.setImageResource(R.drawable.alcohol_100);
        }
    }




    private String fetchIntentData(Intent intent, String key) {
        String data = intent.getExtras().getString(key);
        return data;
    }

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
