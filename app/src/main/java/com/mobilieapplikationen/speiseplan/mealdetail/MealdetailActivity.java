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
        String name = intent.getExtras().getString("foodData_name");

        TextView name_view = findViewById(R.id.name_detail);
        name_view.setText(name);

    }

}
