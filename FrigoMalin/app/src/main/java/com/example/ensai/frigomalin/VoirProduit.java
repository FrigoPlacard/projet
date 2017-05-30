package com.example.ensai.frigomalin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;


public class VoirProduit extends AppCompatActivity {
    TextView nomView,dateView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nomView = (TextView) findViewById(R.id.nom_produit_2);
        dateView = (TextView) findViewById(R.id.date_produit_2);

        //récupérer les données
        Bundle b = getIntent().getExtras();
        String nom = b.getString("nom");
        long dateLong = b.getLong("date");
        Date date = new Date(dateLong);
        nomView.setText(nom);
        dateView.setText(date.getDay() +" "+date.getMonth()+ " "+date.getYear());

        setContentView(R.layout.activity_voir_produit);


    }
}
