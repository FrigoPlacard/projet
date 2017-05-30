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
 * Created by ensai on 30/05/17.
 */
public class MonAdapter extends BaseAdapter {


    List<Produit> produits;
    Context context;


    public MonAdapter(List<Produit> elements,Context context){
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.layout_produit,null);
        }
        else{
            v = convertView;
        }
        String[] mois= context.getResources().getStringArray(R.array.mois);
        Produit element= (Produit) getItem(position);
        TextView nom = (TextView) v.findViewById(R.id.nom_produit);
        TextView date = (TextView) v.findViewById(R.id.date_produit);
        nom.setText(element.getNom());
        Date date1 = element.getDatePeremption();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int annee = calendar.get(Calendar.YEAR);
        int mois1 = calendar.get(Calendar.MONTH);
        int jour = calendar.get(Calendar.DAY_OF_MONTH);

        date.setText(jour+" "+mois[mois1]+" "+annee);

        return v;
    }
}
