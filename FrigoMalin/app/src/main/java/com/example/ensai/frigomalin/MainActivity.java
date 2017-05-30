package com.example.ensai.frigomalin;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void cliqueScan(View v){
        startActivity(new Intent(MainActivity.this, ScannerCodeBarre.class));
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.francais:
                changeToFrench();
                return true;
            case R.id.english:
                changeToEnglish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeToFrench(){
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "default").commit();
        updateResourcesLegacy("default");
    }
    public void changeToEnglish(){
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "en").commit();
        updateResourcesLegacy("en");
    }

    @SuppressWarnings("deprecation")
    private void updateResourcesLegacy(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        onConfigurationChanged(configuration);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        setContentView(R.layout.activity_main);
        super.onConfigurationChanged(newConfig);
    }




}
