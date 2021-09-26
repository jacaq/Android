package com.crosemont.guichetautomatiqueatm;


import java.util.ArrayList;

public class Epargne extends Compte {

    //Constante du taux d'intérêts
    private final static double tauxInteret = 1.25;


    //Constucteur qui fait appel au constucteur mère Compte qui
    public Epargne(int numeroNip, String numeroCompte, double soldeCompte){//, int accountType) {
        super(numeroNip, numeroCompte, soldeCompte);//, accountType)
    }

    //Methode qui permet le retrait dans le compte Epargne.
    @Override
    public String retrait(double montant) {
        if(super.getSoldeCompte() - montant > 100000){
            super.setSoldeCompte(super.getSoldeCompte() - montant);
            return "Retrait de " + montant + " sur votre compte Epargne";
        }
        return "Fonds insufisants";
    }


    //Méthode qui permet de payer les intérêts. (n'est pas appliqué sur chèque mais pourrait un jour être utilisé.)
    public void paiementsInterets(){
        soldeCompte*=tauxInteret;   //solde *1.25
    }

    }


