package com.crosemont.guichetautomatiqueatm;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public  class Guichet {//implements Serializable {//public abstract class Guichet {
    //Classe qui revient au main pour les transactions
    int pos=0;
    double soldeCompte=0;
    //Création de la liste de type objet client
    ArrayList<Client> lesClients= new ArrayList<Client>();
    //Création de la liste de type objet compte
    ArrayList<Compte> lesCompteCheque = new ArrayList<Compte>();
    //Création de la liste de type objet compte
    ArrayList<Compte> lesCompteEpargne= new ArrayList<Compte>();


    //Constructeur par défault;
    public Guichet() {
        //On initialise les données des clients.
        InitilisationDonneesClients();
    }

    //Les getter: non utilisé mais pourrait devenir utile à postériory.
    public ArrayList <Client> getClients() {
        return lesClients;
    }

    public ArrayList <Compte> getComptesCheque() {
        return lesCompteCheque;
    }

    public ArrayList<Compte> getComptesEpargne() {
        return lesCompteEpargne;
    }



    public double RetraitCheque (String nip, double montant){
        pos=getPos(nip);
        soldeCompte=lesCompteCheque.get(pos).getSoldeCompte();
        lesCompteCheque.get(pos).setSoldeCompte(soldeCompte-montant);
        return soldeCompte-montant;
    }

    public boolean RetraitChequePossible (String nip, double montant){
        pos=getPos(nip);
        soldeCompte=lesCompteCheque.get(pos).getSoldeCompte();
        if (soldeCompte-montant>=0)
            return true;// ;
        else return false;
    }



    public double RetraitEpargne (String nip, double montant){
        pos=getPos(nip);
        soldeCompte=lesCompteEpargne.get(pos).getSoldeCompte();
        lesCompteEpargne.get(pos).setSoldeCompte(soldeCompte-montant);
        return soldeCompte-montant;
    }

    public boolean RetraitEpargnePossible (String nip, double montant){
        pos=getPos(nip);
        soldeCompte=lesCompteEpargne.get(pos).getSoldeCompte();
        if (soldeCompte-montant>=0)
            return true;
        else return false;
    }



    public double DepotCheque (String nip, double montant){
        pos=getPos(nip);
        soldeCompte=lesCompteCheque.get(pos).getSoldeCompte();
        lesCompteCheque.get(pos).setSoldeCompte(soldeCompte+montant);
        return soldeCompte+montant;
    }


    public double DepotEpargne (String nip, double montant) {
        pos=getPos(nip);
        soldeCompte=lesCompteEpargne.get(pos).getSoldeCompte();
        lesCompteEpargne.get(pos).setSoldeCompte(soldeCompte+montant);
        return soldeCompte+montant;
    }



    public void InitilisationDonneesClients(){

        //Client(String prenom, String nom, String numCompte, String nip)
        Client Carlos = new Client("Carlos","Lozano","01-0000010","1234");
        lesClients.add(Carlos);
        Epargne eCarlos = new Epargne(1234,"01-0000010",10000 );
        lesCompteEpargne.add(eCarlos);
        Cheque cCarlos= new Cheque(1234,"01-0000010",10000 );
        lesCompteCheque.add(cCarlos);

        // JOANNIE
        Client Joannie = new Client("Joannie","Ranger","01-0000011","1235");
        lesClients.add(Joannie);
        Epargne eJoannie= new Epargne(1235,"01-0000011",5000 );
        lesCompteEpargne.add(eJoannie);
        Cheque cJoannie = new Cheque(1235,"01-0000011",5000 );
        lesCompteCheque.add(cJoannie);

        // CLARK
        Client Clark = new Client("Clark","Kent","01-0000012","1236");
        lesClients.add(Clark);
        Epargne eClark = new Epargne(1236,"01-0000012",6000 );
        lesCompteEpargne.add(eClark);
        Cheque cClark= new Cheque(1236,"01-0000012",6000 );
        lesCompteCheque.add(cClark);


    }

    public int getPos(String nip){
        //Retourne la position du client dans la liste.
        for (int i=0;i<lesClients.size();i++){
            String unNip= String.valueOf(lesCompteCheque.get(i).getNumeroNip());
            if (nip.equals(unNip))
                return i;
        }
        return -1;
    }

}
