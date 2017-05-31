package com.example.ensai.frigomalin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ensai.frigomalin.DAO.CoursesDAO;
import com.example.ensai.frigomalin.DAO.ProduitDAO;
import com.example.ensai.frigomalin.metier.Produit;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                scanIntegrator.initiateScan();

                return true;
            case R.id.entrer:
                startActivity(new Intent(ListeCours.this, Ajout_element_courses.class));
                return true;
            case R.id.vider:
                CoursesDAO course = new CoursesDAO(this);
                course.deleteAll();
                finish();
                Toast.makeText(ListeCours.this, R.string.lvidee, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            Intent i = new Intent(this, LireURLcourse.class);
            i.putExtra("code",scanContent);
            startActivity(i);
            finish();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,v.getId(),0,R.string.supprimer);
    }

    public boolean onContextItemSelected(MenuItem item){

        if(item.getTitle().toString().equals(R.string.supprimer)){

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = (int) info.id;
            CoursesDAO course = new CoursesDAO(this);
            final List<Produit> produits =  recupereProduitCourses();
            course.delete(produits.get(position));
            finish();
            Toast.makeText(ListeCours.this,R.string.prod_suppr,Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
