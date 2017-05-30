package com.example.ensai.frigomalin.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ensai.frigomalin.data.BaseHelper;
import com.example.ensai.frigomalin.metier.Produit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ensai on 31/05/17.
 */

public class CoursesDAO {

    Context context;

    public CoursesDAO(Context context){
        this.context = context;
    }


    public void create(Produit produit){

        BaseHelper helper = new BaseHelper(context);
        try{
            SQLiteDatabase writableDB = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nom",produit.getNom());
            values.put("quantite",produit.getQuantite());
            values.put("categorie",produit.getTypeNourriture().toString());
            writableDB.insert("courses",null,values);

            writableDB.close();
        }catch(Exception e){
            e.printStackTrace();

        }



    }

    public void update(Produit produit){

        BaseHelper helper = new BaseHelper(context);
        try{
            SQLiteDatabase writableDB = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nom",produit.getNom());
            values.put("quantite",produit.getQuantite());
            values.put("categorie",produit.getTypeNourriture().toString());
            writableDB.update("courses",values,"id=?",new String[]{produit.getId()+""});
            writableDB.close();
        }catch(Exception e){
            e.printStackTrace();

        }



    }

    public void delete(Produit produit){
        BaseHelper helper = new BaseHelper(context);
        try{
            SQLiteDatabase writableDB = helper.getWritableDatabase();
            writableDB.delete("courses","id=?",new String[]{produit.getId()+""});
            writableDB.close();
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    public  void deleteAll(){
        BaseHelper helper = new BaseHelper(context);
        try{
            SQLiteDatabase writableDB = helper.getWritableDatabase();
            writableDB.delete("courses","id<>-1",new String[]{});
            writableDB.close();
        }catch(Exception e){
            e.printStackTrace();

        }
    }


    public List<Produit> recupereProduitCourses(){
        List<Produit> produits = new ArrayList<Produit>();
        BaseHelper helper = new BaseHelper(context);
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        Cursor c = writableDB.rawQuery("SELECT nom,quantite,categorie,id FROM courses",new String[]{});

        int nbRows = c.getCount();
        while(c.moveToNext()){
            String nom = c.getString(0);
            int quantite =  c.getInt(1);
            String categorie = c.getString(2);
            int id = c.getInt(3);
            Produit produit=new Produit(nom,quantite,categorie,id);
            produits.add(produit);
        }
        c.close();

        return produits;
    }

}
