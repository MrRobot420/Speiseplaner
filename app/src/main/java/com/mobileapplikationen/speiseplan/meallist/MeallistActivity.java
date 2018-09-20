package com.mobileapplikationen.speiseplan.meallist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobileapplikationenfhws.speiseplan.MainActivity;
import com.mobileapplikationenfhws.speiseplan.Mensa;
import com.mobileapplikationenfhws.speiseplan.R;
import com.mobilieapplikationen.speiseplan.mealdetail.MealdetailActivity;
import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MeallistActivity extends AppCompatActivity implements OnMealsClickListener {

    //ArrayList<Meal> foodData = DataGen.generate();
    int pos;

    //OnMealsClickListener onMealsClickListener;

    List<Meal> meals = new ArrayList<Meal>();



    String mensa_id = null;
    String mensa_name = null;
    String day_id = null;
    String URL_meals = "https://apistaging.fiw.fhws.de/fiwis2/api/mensas/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meallist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        mensa_id = intent.getExtras().getString("mensa_id");
        mensa_name = intent.getExtras().getString("mensa_name");
        day_id = intent.getExtras().getString("day_id");

        URL_meals += (mensa_id + "/meals?day=" + day_id);
        System.out.println(URL_meals);



        /*
        for (int i = 0; i < 10; i++) {
            foodData.add(new Meal());
        }*/


        // Fetch Meal-API-Data here:    (T_ASYNC)
        new LoadFromNetwork().execute();
        //System.out.println();

        /*// Init DATA
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MeallistViewAdapter mAdapter = new MeallistViewAdapter(meals,this);
        mRecyclerView.setAdapter(mAdapter);

        // IMPORTANT when view is pressed!*/



    }

    class LoadFromNetwork extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... p) {
            HttpURLConnection urlConnection = null;
            try {
                java.net.URL url = new URL(URL_meals);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream is = urlConnection.getInputStream();
                return IOUtils.toString(is);
            } catch (Exception ex) {
                Log.e("TAG", "" + ex.getMessage());
            } finally {
                urlConnection.disconnect();
            }
            return "Error";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Genson genson = new Genson();
            //
            //System.out.println(s);
            meals = genson.deserialize(s, new GenericType<List<Meal>>() {
            });
            System.out.println(meals);

            // Init DATA
            RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(MeallistActivity.this));

            MeallistViewAdapter mAdapter = new MeallistViewAdapter(meals,MeallistActivity.this);
            mRecyclerView.setAdapter(mAdapter);

        }
    }


    @Override
    public void onMealClick(Meal meal) {
        Intent intent = new Intent(MeallistActivity.this, MealdetailActivity.class);

        intent.putExtra("foodData_name", meal.getName());
        intent.putExtra("foodData_type", meal.getFoodtype());
        // TODO implement mensa-name!!
        intent.putExtra("mensa_name", mensa_name);
        intent.putExtra("foodData_date", meal.getValidOnDate());
        intent.putExtra("foodData_price", meal.getPrice());
        intent.putExtra("foodData_price_bed", meal.getPricebed());
        intent.putExtra("foodData_price_guest", meal.getPriceguest());
        startActivity(intent);
    }
}
