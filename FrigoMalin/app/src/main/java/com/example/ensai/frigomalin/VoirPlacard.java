package com.example.ensai.frigomalin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ensai.frigomalin.DAO.ProduitDAO;
import com.example.ensai.frigomalin.metier.Produit;

import java.util.ArrayList;
import java.util.List;

public class VoirPlacard extends AppCompatActivity {
    ListView liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_placard);
        liste = (ListView) findViewById(R.id.listefrigo);
        final List<Produit> produits =  recupereProduitBase();


        MonAdapter adapter = new MonAdapter(produits,this);
        liste.setAdapter(adapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(VoirPlacard.this, VoirProduit.class);
                i.putExtra("nom",produits.get(position).getNom());
                i.putExtra("date",produits.get(position).getDatePeremption().getTime());
                startActivity(i);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.placardmenu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.vider:
                ProduitDAO prod = new ProduitDAO(this);
                prod.deleteAll();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onResume(){
        super.onResume();
        setContentView(R.layout.activity_voir_placard);

        liste = (ListView) findViewById(R.id.listefrigo);
        final List<Produit> produits =  recupereProduitBase();

        MonAdapter adapter = new MonAdapter(produits,this);
        liste.setAdapter(adapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(VoirPlacard.this, VoirProduit.class);
                Produit produit = produits.get(position);
                i.putExtra("nom",produit.getNom());
                i.putExtra("date",produit.getDatePeremption().getTime());

                startActivity(i);

            }

        });

    }


    public List<Produit> recupereProduitBase(){
        ProduitDAO produitDAO =new ProduitDAO(this);
        List<Produit> produits=produitDAO.recupereProduitFrigo();



        return produits;
    }
}
