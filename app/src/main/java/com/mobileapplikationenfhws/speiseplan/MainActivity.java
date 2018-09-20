package com.mobileapplikationenfhws.speiseplan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.mobileapplikationen.speiseplan.meallist.MeallistActivity;

public class MainActivity extends AppCompatActivity {
    CheckBox cb_fisch, cb_fleischlos, cb_alk, cb_geflu, cb_lamm, cb_rind, cb_schwein,
            cb_vegan , cb_vorder, cb_wild, cb_kalb;
    Spinner sp_mensa, sp_datum;
    String  key_fisch = "FISCH", key_fleischlos = "FLEISCHLOS", key_alk = "ALK", key_geflu = "GEFLU",
            key_lamm = "LAMM", key_rind = "RIND", key_schwein = "SCHWEIN", key_vegan = "VEGAN",
            key_vorder = "VORDER", key_kalb = "KALB", key_wild = "WILD",
            key_mensa = "MENSA", key_datum = "DATUM";
    String mensen[] = {"Mensateria", "Hubland-Mensa", "Studentenhaus"};
    String datum[] = {"20-09-2018", "21-09-2018", "23-09-2018"};
    ArrayAdapter<String> adapter_mensa;
    ArrayAdapter<String> adapter_datum;
    private static final String speiseplaner_settings = "SPEISEPLANER_SETTINGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get UserInterface Elements
        getUiElements();

        // Spinner
        adapter_mensa= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mensen);
        adapter_mensa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_mensa.setAdapter(adapter_mensa);

        adapter_datum = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datum);
        adapter_datum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_datum.setAdapter(adapter_datum);

        // Load SPEISEPLAN_SETTINGS
        loadSettings();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Store Settings
                saveSettings();
                Intent intent = new Intent(MainActivity.this, MeallistActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void getUiElements(){
        sp_mensa = (Spinner) findViewById(R.id.spin_mensa);
        sp_datum = (Spinner) findViewById(R.id.spin_datum);
        cb_fisch = (CheckBox) findViewById(R.id.check_fisch);
        cb_fleischlos = (CheckBox) findViewById(R.id.check_fleischlos);
        cb_alk = (CheckBox) findViewById(R.id.check_alk);
        cb_geflu = (CheckBox) findViewById(R.id.check_geflu);
        cb_lamm = (CheckBox) findViewById(R.id.check_lamm);
        cb_rind = (CheckBox) findViewById(R.id.check_rind);
        cb_schwein = (CheckBox) findViewById(R.id.check_schwein);
        cb_vegan = (CheckBox) findViewById(R.id.check_vegan);
        cb_vorder = (CheckBox) findViewById(R.id.check_vorder);
        cb_wild = (CheckBox) findViewById(R.id.check_wild);
        cb_kalb = (CheckBox) findViewById(R.id.check_kalb);
    }
    public void loadSettings(){
        // load Spinner
        SharedPreferences pref_load = getSharedPreferences(MainActivity.speiseplaner_settings, MODE_PRIVATE);
        int sp_mensa_pos = adapter_mensa.getPosition(pref_load.getString(key_mensa, ""));
        sp_mensa.setSelection(sp_mensa_pos);
        int sp_datum_pos = adapter_datum.getPosition(pref_load.getString(key_datum, ""));
        sp_datum.setSelection(sp_datum_pos);
        // load CheckBoxes
        cb_fisch.setChecked(pref_load.getBoolean(key_fisch, false));
        cb_fleischlos.setChecked(pref_load.getBoolean(key_fleischlos, false));
        cb_alk.setChecked(pref_load.getBoolean(key_alk, false));
        cb_geflu.setChecked(pref_load.getBoolean(key_geflu, false));
        cb_lamm.setChecked(pref_load.getBoolean(key_lamm, false));
        cb_rind.setChecked(pref_load.getBoolean(key_rind, false));
        cb_schwein.setChecked(pref_load.getBoolean(key_schwein, false));
        cb_vegan.setChecked(pref_load.getBoolean(key_vegan, false));
        cb_vorder.setChecked(pref_load.getBoolean(key_vorder, false));
        cb_wild.setChecked(pref_load.getBoolean(key_wild, false));
        cb_kalb.setChecked(pref_load.getBoolean(key_kalb, false));
    }
    public void saveSettings(){
        SharedPreferences.Editor pref_save = getSharedPreferences(speiseplaner_settings, MODE_PRIVATE).edit();
        // save Spinner
        pref_save.putString(key_mensa, sp_mensa.getSelectedItem().toString());
        pref_save.putString(key_datum, sp_datum.getSelectedItem().toString());
        // save CheckBoxes
        pref_save.putBoolean(key_fisch, cb_fisch.isChecked());
        pref_save.putBoolean(key_fleischlos, cb_fleischlos.isChecked());
        pref_save.putBoolean(key_alk, cb_alk.isChecked());
        pref_save.putBoolean(key_geflu, cb_geflu.isChecked());
        pref_save.putBoolean(key_lamm, cb_lamm.isChecked());
        pref_save.putBoolean(key_rind, cb_rind.isChecked());
        pref_save.putBoolean(key_schwein, cb_schwein.isChecked());
        pref_save.putBoolean(key_vegan, cb_vegan.isChecked());
        pref_save.putBoolean(key_vorder, cb_vorder.isChecked());
        pref_save.putBoolean(key_wild, cb_wild.isChecked());
        pref_save.putBoolean(key_kalb, cb_kalb.isChecked());
        pref_save.apply();
    }
}
