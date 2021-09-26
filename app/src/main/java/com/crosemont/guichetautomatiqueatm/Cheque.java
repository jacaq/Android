package com.crosemont.guichetautomatiqueatm;

import android.widget.Toast;

import java.util.ArrayList;

public class Cheque extends Compte {

    //Constante du type de compte
    private final static int accountType=1;//arbitrairement à 1 et épargne à 2



    //Constucteur qui fait appel au constucteur mère Compte qui
    public Cheque(int numeroNip, String numeroCompte, double soldeCompte){//, int accountType) {
        super(numeroNip, numeroCompte, soldeCompte);//, accountType)
    }

    //Méthode qui permet le retrait dans le compte Chèque
    @Override
    public String retrait(double montant) {
        if(montant <= 1000 && montant%10==0){
            super.setSoldeCompte(super.getSoldeCompte() - montant);
            return "Retrait de " + montant + " sur votre compte Cheque";
        }
        return "Erreur. Fonds insufisants ou le montant n'est pas un multiple de 10.";
    }

    //Méthode qui permet le dépôt dans le compte chèque.
    @Override
    public String depot(double montant){
        setSoldeCompte(getSoldeCompte() + montant);
        return "Votre compte a été chargé avec le montant de " + montant + " dollars";
    }

    @Override
    public void paiementsInterets() {
        //Ne fait rien
    }


}
