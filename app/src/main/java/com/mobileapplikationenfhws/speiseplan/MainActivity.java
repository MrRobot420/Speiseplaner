package com.mobileapplikationenfhws.speiseplan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.mobileapplikationen.speiseplan.meallist.MeallistActivity;
import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    CheckBox cb_fisch, cb_fleischlos, cb_alk, cb_geflu, cb_lamm, cb_rind, cb_schwein,
            cb_vegan , cb_vorder, cb_wild, cb_kalb;
    CheckBox [] all_cb;
    Spinner sp_mensa, sp_datum;
    String  key_fisch = "FISCH", key_fleischlos = "FLEISCHLOS", key_alk = "ALK", key_geflu = "GEFLU",
            key_lamm = "LAMM", key_rind = "RIND", key_schwein = "SCHWEIN", key_vegan = "VEGAN",
            key_vorder = "VORDER", key_kalb = "KALB", key_wild = "WILD",
            key_mensa = "MENSA", key_datum = "DATUM", key_alle = "ALLE";
    //String mensen[] = {"Mensateria", "Hubland-Mensa", "Studentenhaus"};
    //String mensen[];
    String datum[];
    String dayofweek;
    Map<String, String> date_map = new HashMap<String, String>();
    Switch switch_all;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter_datum;
    public static final String speiseplaner_settings = "SPEISEPLANER_SETTINGS";

    List<Mensa> mensas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Aktuelles Datum
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        datum = new String[7];
        for (int i = 0; i <= 6; i++){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE,i);
            Integer dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            dayofweek = dayOfWeek.toString();
            String next = sdf.format(cal.getTime());
            date_map.put(next, dayofweek);
            datum[i] = next;
        }



        new LoadFromNetwork().execute();

        // get UserInterface Elements
        getUiElements();

        // CheckBox Array
        all_cb = new CheckBox[] {cb_fisch, cb_alk, cb_fleischlos, cb_geflu, cb_kalb, cb_lamm, cb_rind, cb_schwein,
                cb_vegan, cb_vorder, cb_wild};

        // Spinner
        //adapter_mensa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mensen);
        //adapter_mensa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //sp_mensa.setAdapter(adapter_mensa);

        adapter_datum = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datum);
        adapter_datum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_datum.setAdapter(adapter_datum);

        // Load SPEISEPLAN_SETTINGS
        // TODO: eventuell NULL abfangen!!!!
        loadSettings();
        //
        setSwitch();
        setCheckBoxes();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Date mapping
                String date = sp_datum.getSelectedItem().toString();
                String ID = date_map.get(date);

                //Date selected_date = new SimpleDateFormat("dd.MM.yyyy").parse(date);

                String mensaId = null;
                int index = 0;
                String selectedMensaName = sp_mensa.getSelectedItem().toString();

                for (int i = 0; i < mensas.size() -1; i++) {
                    if (selectedMensaName.equals(mensas.get(i).getName())) {
                        mensaId = mensas.get(i).getId();
                        index = i;
                    }
                }


                // Store Settings
                saveSettings();
                Intent intent = new Intent(MainActivity.this, MeallistActivity.class);
                intent.putExtra("mensa_id", mensaId);
                intent.putExtra("mensa_name", mensas.get(index).getName());
                intent.putExtra("day_id", ID);







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

    class LoadFromNetwork extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... p) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("https://apistaging.fiw.fhws.de/fiwis2/api/mensas");
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
            mensas = genson.deserialize(s, new GenericType<List<Mensa>>() {
            });

            sp_mensa = findViewById(R.id.spin_mensa);
            List<String> dropdown_items = new ArrayList<String>();

            for (int i = 0; i < mensas.size() - 1; i++) {
                dropdown_items.add(mensas.get(i).getName());
            }

            adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, dropdown_items);
            sp_mensa.setAdapter(adapter);
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
    public void getUiElements(){
        sp_mensa = findViewById(R.id.spin_mensa);
        sp_datum = findViewById(R.id.spin_datum);
        switch_all = findViewById(R.id.switch_all);
        cb_fisch = findViewById(R.id.check_fisch);
        cb_fleischlos = findViewById(R.id.check_fleischlos);
        cb_alk = findViewById(R.id.check_alk);
        cb_geflu = findViewById(R.id.check_geflu);
        cb_lamm = findViewById(R.id.check_lamm);
        cb_rind = findViewById(R.id.check_rind);
        cb_schwein = findViewById(R.id.check_schwein);
        cb_vegan = findViewById(R.id.check_vegan);
        cb_vorder = findViewById(R.id.check_vorder);
        cb_wild = findViewById(R.id.check_wild);
        cb_kalb = findViewById(R.id.check_kalb);
    }
    public void loadSettings(){
        // load Spinner
        SharedPreferences pref_load = getSharedPreferences(MainActivity.speiseplaner_settings, MODE_PRIVATE);
        //int sp_mensa_pos = adapter.getPosition(pref_load.getString(key_mensa, ""));
        //sp_mensa.setSelection(sp_mensa_pos);
        //int sp_datum_pos = adapter_datum.getPosition(pref_load.getString(key_datum, ""));
        //sp_datum.setSelection(sp_datum_pos);
        // load switch
        switch_all.setChecked(pref_load.getBoolean(key_alle, false));
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
        pref_save.putBoolean(key_alle, switch_all.isChecked());
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

    public void setSwitch(){
        switch_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean is_checked) {
                if (is_checked == true) {
                    cb_fisch.setChecked(true);
                    cb_fleischlos.setChecked(true);
                    cb_geflu.setChecked(true);
                    cb_alk.setChecked(true);
                    cb_kalb.setChecked(true);
                    cb_lamm.setChecked(true);
                    cb_rind.setChecked(true);
                    cb_schwein.setChecked(true);
                    cb_vegan.setChecked(true);
                    cb_vorder.setChecked(true);
                    cb_wild.setChecked(true);
                }
                if(is_checked == false){
                    if(cb_fisch.isChecked() && cb_fleischlos.isChecked() && cb_geflu.isChecked() &&
                            cb_alk.isChecked() && cb_kalb.isChecked() && cb_lamm.isChecked() &&
                            cb_rind.isChecked() && cb_schwein.isChecked() && cb_vorder.isChecked() &&
                            cb_vegan.isChecked() && cb_wild.isChecked()) {
                        for (int i = 0; i< all_cb.length; i++) {
                            all_cb[i].setChecked(false);
                        }

                    }

                }
            }

        });

    }
    public void setCheckBoxes(){
        for (int i = 0; i< all_cb.length; i++) {
            all_cb[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean is_checked) {
                    if (is_checked == true) {
                    }
                    if (is_checked == false) {
                        switch_all.setChecked(false);
                    }
                    if (cb_fisch.isChecked() && cb_fleischlos.isChecked() && cb_geflu.isChecked() &&
                            cb_alk.isChecked() && cb_kalb.isChecked() && cb_lamm.isChecked() &&
                            cb_rind.isChecked() && cb_schwein.isChecked() && cb_vorder.isChecked() &&
                            cb_vegan.isChecked() && cb_wild.isChecked()) {
                        switch_all.setChecked(true);
                    }
                }
            });
        }

    }
}
