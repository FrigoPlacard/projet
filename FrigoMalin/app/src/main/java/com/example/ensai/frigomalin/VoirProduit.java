package com.example.ensai.frigomalin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ensai.frigomalin.DAO.CoursesDAO;
import com.example.ensai.frigomalin.DAO.ProduitDAO;
import com.example.ensai.frigomalin.metier.Produit;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;


public class VoirProduit extends AppCompatActivity {
    TextView nomView,dateView,categorieView,urlView,quantiteView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //récupérer les données
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_voir_produit);

        String[] mois= getResources().getStringArray(R.array.mois);
        String nom = b.getString("nom");
        long dateLong = b.getLong("date");
        String categorie = b.getString("categorie");
        int quantite = b.getInt("quantite");
        String url = b.getString("url");
        int id = b.getInt("id");
        Date date = new Date(dateLong);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int annee = calendar.get(Calendar.YEAR);
        int mois1 = calendar.get(Calendar.MONTH);
        int jour = calendar.get(Calendar.DAY_OF_MONTH);

        nomView = (TextView) findViewById(R.id.nom_produit_2);
        dateView = (TextView) findViewById(R.id.date_produit_2);
        categorieView = (TextView) findViewById(R.id.categorie);
        urlView = (TextView) findViewById(R.id.url);
        quantiteView = (TextView) findViewById(R.id.quantite_prod);
        nomView.setText(nom);
        categorieView.setText(categorie);
        urlView.setText(url);
        quantiteView.setText(quantite+"");
        dateView.setText(jour+" "+mois[mois1]+" "+annee);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //récupérer les données
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_voir_produit);

        String[] mois= getResources().getStringArray(R.array.mois);
        String nom = b.getString("nom");
        long dateLong = b.getLong("date");
        String categorie = b.getString("categorie");
        int quantite = b.getInt("quantite");
        String url = b.getString("url");
        int id = b.getInt("id");
        Date date = new Date(dateLong);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int annee = calendar.get(Calendar.YEAR);
        int mois1 = calendar.get(Calendar.MONTH);
        int jour = calendar.get(Calendar.DAY_OF_MONTH);

        nomView = (TextView) findViewById(R.id.nom_produit_2);
        dateView = (TextView) findViewById(R.id.date_produit_2);
        categorieView = (TextView) findViewById(R.id.categorie);
        urlView = (TextView) findViewById(R.id.url);
        quantiteView = (TextView) findViewById(R.id.quantite_prod);
        nomView.setText(nom);
        categorieView.setText(categorie);
        urlView.setText(url);
        quantiteView.setText(quantite+"");
        dateView.setText(jour+" "+mois[mois1]+" "+annee);
    }

    public void ajouter_prod(View view) {
        Bundle b = getIntent().getExtras();
        String nom = b.getString("nom");
        long dateLong = b.getLong("date");
        String categorie = b.getString("categorie");
        int quantite = b.getInt("quantite")+1;
        String url = b.getString("url");
        int id = b.getInt("id");
        Date date = new Date(dateLong);
        Produit produit=new Produit(nom,quantite,date,categorie,url,id);
        ProduitDAO produitDAO=new ProduitDAO(this);
        produitDAO.update(produit);
        Toast.makeText(VoirProduit.this, "Produit ajouté", Toast.LENGTH_SHORT).show();

        onResume();
        finish();





    }

    public void supprimer(View view) {
        Bundle b = getIntent().getExtras();
        String nom = b.getString("nom");
        long dateLong = b.getLong("date");
        String categorie = b.getString("categorie");
        int quantite = b.getInt("quantite")-1;
        String url = b.getString("url");
        int id = b.getInt("id");
        Date date = new Date(dateLong);
        if(quantite>0) {

            Produit produit = new Produit(nom, quantite, date, categorie, url, id);
            ProduitDAO produitDAO = new ProduitDAO(this);
            produitDAO.update(produit);
            Toast.makeText(VoirProduit.this, "Produit supprimé", Toast.LENGTH_SHORT).show();
        }
        else{
            Produit produit = new Produit(nom, quantite, date, categorie, url, id);
            ProduitDAO produitDAO = new ProduitDAO(this);
            produitDAO.delete(produit);
            produit = new Produit(nom, quantite+1, date, categorie, url, id);
            CoursesDAO coursesDAO = new CoursesDAO(this);
            coursesDAO.create(produit);

            startActivity(new Intent(VoirProduit.this,VoirPlacard.class));
            Toast.makeText(VoirProduit.this, "Produit supprimé", Toast.LENGTH_SHORT).show();

        }

        onResume();
        finish();
    }
}
