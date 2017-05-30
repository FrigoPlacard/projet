package com.example.ensai.frigomalin.metier;

import java.util.Date;

/**
 * Created by ensai on 09/05/17.
 */

public class Element {

    private long codeBarre;
    private String nom;
    private String quantite;
    private Date datePeremption;
    private TypeNourriture typeNourriture;

    public Element(long codeBarre, String nom, String quantite, Date datePeremption, TypeNourriture typeNourriture) {
        this.codeBarre = codeBarre;
        this.nom = nom;
        this.quantite = quantite;
        this.datePeremption = datePeremption;
        this.typeNourriture = typeNourriture;
    }

    public Element( String nom, String quantite, Date datePeremption, TypeNourriture typeNourriture) {
        this.nom = nom;
        this.quantite = quantite;
        this.datePeremption = datePeremption;
        this.typeNourriture = typeNourriture;
    }



    public long getCodeBarre() {
        return codeBarre;
    }

    public String getNom() {
        return nom;
    }

    public String getQuantite() {
        return quantite;
    }

    public Date getDatePeremption() {
        return datePeremption;
    }

    public TypeNourriture getTypeNourriture() {
        return typeNourriture;
    }
}
