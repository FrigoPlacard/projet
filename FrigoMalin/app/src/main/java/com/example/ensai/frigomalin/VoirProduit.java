package com.example.ensai.frigomalin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;


public class VoirProduit extends AppCompatActivity {
    TextView nomView,dateView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //récupérer les données
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_voir_produit);

        String[] mois= getResources().getStringArray(R.array.mois);
        String nom = b.getString("nom");
        long dateLong = b.getLong("date");
        Date date = new Date(dateLong);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int annee = calendar.get(Calendar.YEAR);
        int mois1 = calendar.get(Calendar.MONTH);
        int jour = calendar.get(Calendar.DAY_OF_MONTH);

        nomView = (TextView) findViewById(R.id.nom_produit_2);
        dateView = (TextView) findViewById(R.id.date_produit_2);
        nomView.setText(nom);
        dateView.setText(jour+" "+mois[mois1]+" "+annee);




    }
}
