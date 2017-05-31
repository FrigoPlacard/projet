package com.example.ensai.frigomalin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ensai.frigomalin.DAO.CoursesDAO;
import com.example.ensai.frigomalin.DAO.ProduitDAO;
import com.example.ensai.frigomalin.metier.Produit;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ensai on 31/05/17.
 */

public class Ajout_element_courses extends AppCompatActivity {
    EditText nom,quantite;
    private Spinner categorie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_ajout_element_courses);


        categorie = (Spinner) findViewById(R.id.monSpinner);

    }




    public void ajouter_c(View v){
        nom = (EditText)findViewById(R.id.nom_c);
        quantite = (EditText)findViewById(R.id.quantite_c);
        if(nom.getText().toString().isEmpty() || quantite.getText().toString().isEmpty()) {
            Toast.makeText(Ajout_element_courses.this, R.string.nom_quantite, Toast.LENGTH_SHORT).show();
        }else{
            String[] type = getResources().getStringArray(R.array.monSpinner);
            int pos = categorie.getSelectedItemPosition();
            String cat = type[pos];

            Produit produit = new Produit(nom.getText().toString(), Integer.parseInt(quantite.getText().toString()),cat);
            CoursesDAO coursesDAO = new CoursesDAO(this);
            coursesDAO.create(produit);

            Toast.makeText(Ajout_element_courses.this, R.string.prod_aj, Toast.LENGTH_SHORT).show();

            finish();
        }



    }


}
