package com.example.ensai.frigomalin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ensai.frigomalin.DAO.ProduitDAO;
import com.example.ensai.frigomalin.metier.Produit;

import java.util.List;

public class ProduitPerime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView liste = (ListView) findViewById(R.id.listefrigo);
        final List<Produit> produits =  recupereProduitBase();


        MonAdapter adapter = new MonAdapter(produits,this);
        liste.setAdapter(adapter);
        setContentView(R.layout.activity_voir_placard);
    }



    public List<Produit> recupereProduitBase(){
        ProduitDAO produitDAO =new ProduitDAO(this);
        List<Produit> produits=produitDAO.recupereProduitPerimee();



        return produits;
    }
}
