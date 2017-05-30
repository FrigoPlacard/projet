package com.example.ensai.frigomalin.metier;

import java.util.Date;

/**
 * Created by ensai on 09/05/17.
 */

public class Produit {

    private long codeBarre;
    private String nom;
    private int quantite;
    private Date datePeremption;
    private String typeNourriture;

    public Produit(long codeBarre, String nom, int quantite, Date datePeremption, String typeNourriture) {
        this.codeBarre = codeBarre;
        this.nom = nom;
        this.quantite = quantite;
        this.datePeremption = datePeremption;
        this.typeNourriture = typeNourriture;
    }

    public Produit(String nom, int quantite, Date datePeremption, String typeNourriture) {
        this.nom = nom;
        this.quantite = quantite;
        this.datePeremption = datePeremption;
        this.typeNourriture = typeNourriture;
       // this.typeNourriture="viande";
    }



    public long getCodeBarre() {
        return codeBarre;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public Date getDatePeremption() {
        return datePeremption;
    }

    public String getTypeNourriture() {
        return typeNourriture;
    }
}
