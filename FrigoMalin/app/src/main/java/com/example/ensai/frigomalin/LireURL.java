package com.example.ensai.frigomalin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ensai.frigomalin.DAO.ProduitDAO;
import com.example.ensai.frigomalin.metier.Produit;

import org.json.JSONObject;
import java.util.Calendar;
import java.util.Date;

public class LireURL extends AppCompatActivity implements View.OnClickListener {
    Button bDate;
    EditText txtDate, nom, quantite;
    int year_x,month_x,day_x;
    private Spinner categorie;
    static final int DIALOG_ID=0;

     /*{"janv","févr","mars","avr","mai","juin","juil","août","sept","oct","nov","dec"}*/;


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

                                Toast.makeText(LireURL.this, nomProduit, Toast.LENGTH_SHORT).show();
                                nom = (EditText)findViewById(R.id.nom);
                                nom.setText(nomProduit+" - "+quantiteProduit);





                            } else {
                                Toast.makeText(LireURL.this, "produit not found", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            Intent i = new Intent(LireURL.this,ScannerCodeBarre.class);
                            Toast.makeText(LireURL.this, R.string.reessayer, Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LireURL.this, R.string.erreur, Toast.LENGTH_SHORT).show();


            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

        final Calendar c = Calendar.getInstance();
        day_x = c.get(Calendar.DAY_OF_MONTH);
        month_x = c.get(Calendar.MONTH);
        year_x = c.get(Calendar.YEAR);


        setContentView(R.layout.activity_ajout_element);
        categorie = (Spinner) findViewById(R.id.monSpinner);

        showDialogOnButtonClick();
    }


    public void ajouter(View v){
        nom = (EditText)findViewById(R.id.nom);
        quantite = (EditText)findViewById(R.id.quantite);
        if(quantite.getText().toString().isEmpty()) {
            Toast.makeText(LireURL.this, R.string.aj_q, Toast.LENGTH_SHORT).show();
        }
        else {
            String[] type = getResources().getStringArray(R.array.monSpinner);
            int pos = categorie.getSelectedItemPosition();
            String cat = type[pos];
            Date date = new Date((month_x + 1) + "/" + day_x + "/" + year_x);
            Bundle b = getIntent().getExtras();
            String code = b.getString("code");
            String url = "https://fr.openfoodfacts.org/produit/" + code;
            Produit produit = new Produit(nom.getText().toString(), Integer.parseInt(quantite.getText().toString()), date, cat, url);
            ProduitDAO produitDAO = new ProduitDAO(this);
            produitDAO.create(produit);
            Toast.makeText(LireURL.this, R.string.prod_aj, Toast.LENGTH_SHORT).show();
            finish();
        }

    }
    public void showDialogOnButtonClick(){
        String[] mois= getResources().getStringArray(R.array.mois);
        bDate = (Button)findViewById(R.id.bDate);
        txtDate = (EditText)findViewById(R.id.txtDate);
        txtDate.setText(day_x+" "+mois[month_x]+" "+year_x);

            
        }


    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String[] mois= getResources().getStringArray(R.array.mois);
            year_x=year;
            month_x=month;
            day_x=dayOfMonth;
            txtDate.setText(day_x+" "+mois[month_x]+" "+year_x);


        }
    };
    @Override
    public void onClick(View v) {
        showDialog(DIALOG_ID);
    }
}