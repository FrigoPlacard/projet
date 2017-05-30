package com.example.ensai.frigomalin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ensai.frigomalin.metier.Produit;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ensai on 31/05/17.
 */
/*public class MonAdapterCourses extends BaseAdapter {


    List<Produit> produits;
    Context context;


    public MonAdapterCourses(List<Produit> elements,Context context){
        super();
        this.produits =elements;
        this.context=context;

    }


    @Override
    public int getCount() {
        return this.produits.size();
    }

    @Override
    public Object getItem(int position) {
        return this.produits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           v = inflater.inflate(R.layout.layout_produit_courses,null);
        }
        else{
            v = convertView;
        }
        Produit element= (Produit) getItem(position);

        TextView nom = (TextView) v.findViewById(R.id.nom_produit_c);
        TextView quantite = (TextView) v.findViewById(R.id.quantite_produit_c);
        nom.setText(element.getNom());
        quantite.setText(element.getQuantite());


        return v;
    }
}*/