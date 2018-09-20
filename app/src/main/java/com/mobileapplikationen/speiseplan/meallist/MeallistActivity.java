package com.mobileapplikationen.speiseplan.meallist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mobileapplikationenfhws.speiseplan.R;

import java.util.ArrayList;

public class MeallistActivity extends AppCompatActivity {

    ArrayList<Meal> foodData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meallist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // "FETCH" DUMMY-DATA
        foodData = new ArrayList<Meal>();

        /*
        for (int i = 0; i < 10; i++) {
            foodData.add(new Meal());
        }*/


        // Fetch Meal-API-Data here:    (T_ASYNC)




        foodData.add(new Meal("UID", "ID", "GERICHT", "PRICE", "PRICE_B", "PRICE_G", "TYPE", "DATE", "ADD", null));
        foodData.add(new Meal("1", "1", "Hühnerragout", "3.05", "4.25", "5,15", "G", "19-09-2018", "A1", null));
        foodData.add(new Meal("2", "2", "Bifteki", "3.55", "4.55", "5,55", "R", "19-09-2018", "A3", null));
        foodData.add(new Meal("3", "3", "Cordon Bleu", "2.55", "3.70", "4,60", "G", "19-09-2018", "A2", null));
        foodData.add(new Meal("4", "4", "Schweinesteak", "2.55", "3.70", "4,60", "S", "19-09-2018", "A1", null));
        foodData.add(new Meal("5", "5", "Semmelknödel", "2.55", "3.70", "4,60", "FL", "19-09-2018", "A2", null));
        foodData.add(new Meal("6", "6", "Schnitzel", "3.05", "4.25", "5,15", "S", "19-09-2018", "A1", null));
        foodData.add(new Meal("7", "7", "Pizza Tonno", "4.05", "5.25", "6,15", "F", "19-09-2018", "Z1", null));
        foodData.add(new Meal("8", "8", "Spaghetti", "2.05", "3.25", "4,15", "R", "19-09-2018", "A4", null));
        foodData.add(new Meal("9", "9", "Currywurst", "2.55", "3.65", "4,35", "S", "19-09-2018", "Z2", null));
        foodData.add(new Meal("10", "10", "Forelle", "4.05", "5.85", "7,15", "G", "19-09-2018", "Z3", null));


        // Init DATA
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MeallistViewAdapter mAdapter = new MeallistViewAdapter(foodData);
        mRecyclerView.setAdapter(mAdapter);
    }

}
