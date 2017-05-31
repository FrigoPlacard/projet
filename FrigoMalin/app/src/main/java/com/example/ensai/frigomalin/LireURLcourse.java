package com.example.ensai.frigomalin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ensai.frigomalin.DAO.CoursesDAO;
import com.example.ensai.frigomalin.metier.Produit;

import org.json.JSONObject;

public class LireURLcourse extends AppCompatActivity {
    EditText nom, quantite;
    private Spinner categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //récupérer le code
        Bundle b = getIntent().getExtras();
        String code = b.getString("code");


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlDebut = "https://fr.openfoodfacts.org/api/v0/produit/";
        String urlFin = ".json";
        String codeProduit = code;
        String url = urlDebut + codeProduit + urlFin;

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //response.substring(0,500);
                        try {
                            JSONObject j = new JSONObject(response);
                            String statut = (String) j.get("status_verbose");
                            if (statut.contentEquals("product found")) {
                                JSONObject p = (JSONObject) j.get("product");
                                String code = (String) j.get("code");
                                String nomProduit = (String) p.get("product_name");
                                String categorie = (String) p.get("pnns_groups_1");
                                String quantiteProduit = (String) p.get("quantity");

                                Toast.makeText(LireURLcourse.this, nomProduit, Toast.LENGTH_SHORT).show();
                                nom = (EditText)findViewById(R.id.nom_c);
                                nom.setText(nomProduit+" - "+quantiteProduit);





                            } else {
                                Toast.makeText(LireURLcourse.this, "produit not found", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            Intent i = new Intent(LireURLcourse.this,ScannerCodeBarre.class);
                            Toast.makeText(LireURLcourse.this, "Réessayez", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LireURLcourse.this, R.string.erreur, Toast.LENGTH_SHORT).show();


            }
        });
        queue.add(stringRequest);
        setContentView(R.layout.activity_ajout_element_courses);
        categorie = (Spinner) findViewById(R.id.monSpinner);
    }

    public void ajouter_c(View v){
        nom = (EditText)findViewById(R.id.nom_c);
        if(nom.getText().toString().isEmpty()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ) {
            Toast.makeText(LireURLcourse.this, R.string.nom_dem, Toast.LENGTH_SHORT).show();
        }else{
            Produit produit = new Produit(nom.getText().toString());
            CoursesDAO coursesDAO = new CoursesDAO(this);
            coursesDAO.create(produit);

            Toast.makeText(LireURLcourse.this, R.string.prod_aj, Toast.LENGTH_SHORT).show();

            finish();
        }



    }
}
