package com.mobileapplikationen.speiseplan.meallist;

import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Collections;
import java.util.List;

public class MeallistActivity extends AppCompatActivity implements OnMealsClickListener {

    //ArrayList<Meal> foodData = DataGen.generate();
    int pos;

    //OnMealsClickListener onMealsClickListener;

    List<Meal> meals = new ArrayList<Meal>();

    List<Meal> selected_meals;


    SharedPreferences pref_load;
    String rind = "R";
    String vegan = "V";
    String fleischlos = "FL";
    String geflügel = "G";
    String alk = "A";
    String lamm = "L";
    String kalb = "K";
    String schwein = "S";
    String fisch = "F";
    String wild = "W";
    String vorder = "VO";

    List<String> foodTypes = new ArrayList<>();

    String  key_fisch = "FISCH", key_fleischlos = "FLEISCHLOS", key_alk = "ALK", key_geflu = "GEFLU",
            key_lamm = "LAMM", key_rind = "RIND", key_schwein = "SCHWEIN", key_vegan = "VEGAN",
            key_vorder = "VORDER", key_kalb = "KALB", key_wild = "WILD",
            key_mensa = "MENSA", key_datum = "DATUM";



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


        pref_load = getSharedPreferences(MainActivity.speiseplaner_settings, MODE_PRIVATE);

        Intent intent = getIntent();
        mensa_id = intent.getExtras().getString("mensa_id");
        mensa_name = intent.getExtras().getString("mensa_name");
        day_id = intent.getExtras().getString("day_id");

        URL_meals += (mensa_id + "/meals?day=" + day_id);
        System.out.println(URL_meals);




        selected_meals = new ArrayList<>();




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
            //System.out.println(meals);



            // MEALS RAUS!
            boolean cb_fisch = pref_load.getBoolean(key_fisch, false);
            boolean cb_fleischlos = pref_load.getBoolean(key_fleischlos, false);
            boolean cb_alk = pref_load.getBoolean(key_alk, false);
            boolean cb_geflu = pref_load.getBoolean(key_geflu, false);
            boolean cb_lamm = pref_load.getBoolean(key_lamm, false);
            boolean cb_rind = pref_load.getBoolean(key_rind, false);
            boolean cb_schwein = pref_load.getBoolean(key_schwein, false);
            boolean cb_vegan = pref_load.getBoolean(key_vegan, false);
            boolean cb_vorder = pref_load.getBoolean(key_vorder, false);
            boolean cb_wild = pref_load.getBoolean(key_wild, false);
            boolean cb_kalb = pref_load.getBoolean(key_kalb, false);


            // SYS OUT Here!
            System.out.println("");
            System.out.println(cb_fisch);
            System.out.println("");

            if (cb_fisch == true) {
                foodTypes.add(fisch);
            }
            if (cb_fleischlos == true) {
                foodTypes.add(fleischlos);
            }
            if (cb_alk == true) {
                foodTypes.add(alk);
            }
            if (cb_geflu == true) {
                foodTypes.add(geflügel);
            }
            if (cb_lamm == true) {
                foodTypes.add(lamm);
            }
            if (cb_rind == true) {
                foodTypes.add(rind);
            }
            if (cb_schwein == true) {
                foodTypes.add(schwein);
            }
            if (cb_vegan == true) {
                foodTypes.add(vegan);
            }
            if (cb_vorder == true) {
                foodTypes.add(vorder);
            }
            if (cb_wild == true) {
                foodTypes.add(wild);
            }
            if (cb_kalb) {
                foodTypes.add(kalb);
            }


            selected_meals = new ArrayList<>();

            for (int i = 0; i <= foodTypes.size()-1; i++) {
                for (int j = 0; j <= meals.size()-1; j++) {
                    if (meals.get(j).getFoodtype().toLowerCase().equals(foodTypes.get(i).toLowerCase())) {
                        selected_meals.add(meals.get(j));
                    }
                }
            }



            if (selected_meals.size() == 0) {
                System.out.println("HEUTE GIBT ES KEIN ESSEN FÜR DICH!");
                //Snackbar.make(view_popup, "HEUTE GIBT ES KEIN ESSEN FÜR DICH!", Snackbar.LENGTH_LONG).setAction("Action", null).show();


                Meal meal = new Meal();
                meal.setName("Sie bleiben leider hungrig!");
                meal.setFoodtype("X");
                selected_meals.add(meal);


                RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MeallistActivity.this));

                MeallistViewAdapter mAdapter = new MeallistViewAdapter(selected_meals,MeallistActivity.this);
                mRecyclerView.setAdapter(mAdapter);

            } else {
                // Init DATA
                RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MeallistActivity.this));

                Collections.sort(selected_meals);

                MeallistViewAdapter mAdapter = new MeallistViewAdapter(selected_meals,MeallistActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }





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
