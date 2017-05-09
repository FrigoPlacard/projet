package com.example.ensai.frigomalin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LireURL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //récupérer le code
        Bundle b = getIntent().getExtras();
        String code = b.getString("code");


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlDebut ="https://fr.openfoodfacts.org/api/v0/produit/";
        String urlFin = ".json";
        String codeProduit = code;
        String url=urlDebut+codeProduit+urlFin;

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //response.substring(0,500);
                        Toast.makeText(LireURL.this, "J'ai réussi à lire l'url", Toast.LENGTH_SHORT).show();
                        try{
                            JSONObject j = new JSONObject(response);
                            String statut = (String) j.get("status_verbose");
                            if(statut.contentEquals("product found")) {

                                String a = (String) j.get("code");

                                Toast.makeText(LireURL.this, a, Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LireURL.this, "produit not found", Toast.LENGTH_SHORT).show();
                            }

                        }
                        catch(Exception e){
                            Toast.makeText(LireURL.this, "pb", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LireURL.this, "Je n'ai pas réussi à lire l'url", Toast.LENGTH_SHORT).show();

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

        setContentView(R.layout.activity_lire_url);


    }
}
