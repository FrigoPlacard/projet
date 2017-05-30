package com.example.ensai.frigomalin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.ensai.frigomalin.DAO.CoursesDAO;
import com.example.ensai.frigomalin.DAO.ProduitDAO;
import com.example.ensai.frigomalin.metier.Produit;

import java.util.List;

public class ListeCours extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_cours);
        ListView listView = (ListView) findViewById(R.id.listecourse);
        registerForContextMenu(listView);
        final List<Produit> produits =  recupereProduitCourses();
        MonAdapterCourses adapter = new MonAdapterCourses(produits,this);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_liste_cours);
        ListView listView = (ListView) findViewById(R.id.listecourse);
        registerForContextMenu(listView);
        final List<Produit> produits =  recupereProduitCourses();
        MonAdapterCourses adapter = new MonAdapterCourses(produits,this);
        listView.setAdapter(adapter);

    }



    private List<Produit> recupereProduitCourses() {
        CoursesDAO coursesDAO =new CoursesDAO(this);
        List<Produit> produits=coursesDAO.recupereProduitCourses();
        return produits;
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.listecourse,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.scanner:
                startActivity(new Intent(ListeCours.this, ScannerCodeBarre.class));
                return true;
            case R.id.entrer:
                startActivity(new Intent(ListeCours.this, Ajout_element_courses.class));
                return true;
            case R.id.vider:
                return true;
            case R.id.actualiser:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,v.getId(),0,R.string.supprimer);
    }
}
