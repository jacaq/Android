package com.crosemont.guichetautomatiqueatm;

import java.io.Serializable;
import java.util.ArrayList;

abstract class Compte   {//implements Serializable {

    private int numeroNip;
    private String numeroCompte;
    public double soldeCompte;



    //Constructeur du compte
    public Compte(int numeroNip, String numeroCompte, double soldeCompte){//, int accountType) {
        this.numeroNip = numeroNip;
        this.numeroCompte = numeroCompte;
        this.soldeCompte = soldeCompte;

    }

    //Méthode (retrait et dépot)
    public abstract String retrait(double montant);

    //Méthode qui sert à l'affichage de l'éditText "Etat des comptes"
    public String depot(double montant){
        setSoldeCompte(getSoldeCompte() + montant);
        return "Votre compte a été chargé avec le montant de " + montant + " dollars";
    }





    //NIP
    //Getter du nip
    public int getNumeroNip() {
        return numeroNip;
    }

    //Setter du nip
    public void setNumeroNip(int numeroNip) {
        this.numeroNip = numeroNip;
    }

    //NUMERO COMPTE
    //Gettet du numero de compte
    public String getNumeroCompte() {
        return numeroCompte;
    }
    //Setter du numero de compte
    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    //SOLDE COMPTE
    //getter du solde du compte
    public double getSoldeCompte() {
        return soldeCompte;
    }
    //Setter du solde
    public void setSoldeCompte(double soldeCompte)
    {
        this.soldeCompte = soldeCompte;
    }

    public abstract void paiementsInterets();


}
