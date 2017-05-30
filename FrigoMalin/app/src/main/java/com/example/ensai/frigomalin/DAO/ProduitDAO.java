package com.example.ensai.frigomalin.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.ensai.frigomalin.MonApplication;
import com.example.ensai.frigomalin.data.BaseHelper;
import com.example.ensai.frigomalin.metier.Produit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ensai on 15/05/17.
 */

public class ProduitDAO {

    public void create(Produit produit){

        BaseHelper helper = new BaseHelper(MonApplication.getAppContext());
        try{
            SQLiteDatabase writableDB = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nom",produit.getNom());
            values.put("quantite",produit.getQuantite());
            values.put("categorie",produit.getTypeNourriture().toString());
            values.put("date", produit.getDatePeremption().getTime());
            writableDB.insert("frigo",null,values);


            // writabeDB.rawQuery("INSERT INTO element VALUES(?,?)",new String[]{nomString,desciptionString});
            writableDB.close();
        }catch(Exception e){
            e.printStackTrace();

        }


    }

    public List<Produit> recupereProduitFrigo(){
        List<Produit> produits = new ArrayList<Produit>();
        BaseHelper helper = new BaseHelper(MonApplication.getAppContext());
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        Cursor c = writableDB.rawQuery("SELECT nom,quantite,categorie,date FROM frigo",new String[]{});

        int nbRows = c.getCount();
        while(c.moveToNext()){
            String nom = c.getString(0);
            int quantite =  c.getInt(1);
            String categorie = c.getString(2);
            Date date = new Date(c.getLong(3));
            Produit produit=new Produit(nom,quantite,date,categorie);
            produits.add(produit);
        }
        c.close();

        return produits;
    }

    public List<Produit> recupereProduitPerimee(){
        List<Produit> produits = new ArrayList<Produit>();
        BaseHelper helper = new BaseHelper(MonApplication.getAppContext());
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        long dateLong = (new Date()).getTime();
        Cursor c = writableDB.rawQuery("SELECT nom,quantite,categorie,date FROM frigo WHERE date<= ?",new String[]{});

        int nbRows = c.getCount();
        while(c.moveToNext()){
            String nom = c.getString(0);
            int quantite =  c.getInt(1);
            String categorie = c.getString(2);
            Date date = new Date(c.getLong(3));
            Produit produit=new Produit(nom,quantite,date,categorie);
            produits.add(produit);
        }
        c.close();

        return produits;
    }
}
