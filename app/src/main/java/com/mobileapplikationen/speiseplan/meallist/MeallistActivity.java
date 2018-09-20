package com.mobileapplikationen.speiseplan.meallist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mobileapplikationenfhws.speiseplan.R;
import com.mobilieapplikationen.speiseplan.mealdetail.MealdetailActivity;

import java.util.ArrayList;

public class MeallistActivity extends AppCompatActivity implements OnMealsClickListener {

    ArrayList<Meal> foodData = DataGen.generate();
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meallist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        /*
        for (int i = 0; i < 10; i++) {
            foodData.add(new Meal());
        }*/


        // Fetch Meal-API-Data here:    (T_ASYNC)


        // Init DATA
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MeallistViewAdapter mAdapter = new MeallistViewAdapter(foodData,this);
        mRecyclerView.setAdapter(mAdapter);

        // IMPORTANT when view is pressed!



    }


    @Override
    public void onMealClick(int position) {
        Intent intent = new Intent(MeallistActivity.this, MealdetailActivity.class);

        intent.putExtra("foodData_name", foodData.get(pos).getName());
        intent.putExtra("foodData_type", foodData.get(pos).getFoodtype());
        // TODO implement mensa-name!!
        intent.putExtra("foodData_date", foodData.get(pos).getValidOnDate());
        intent.putExtra("foodData_price", foodData.get(pos).getPrice());
        intent.putExtra("foodData_price_bed", foodData.get(pos).getPricebed());
        intent.putExtra("foodData_price_guest", foodData.get(pos).getPriceguest());
        startActivity(intent);
    }
}
