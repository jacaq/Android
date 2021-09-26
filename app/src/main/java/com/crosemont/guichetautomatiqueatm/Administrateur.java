package com.crosemont.guichetautomatiqueatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Administrateur extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrateur);
    }

    //Ceci est en réaction au bouton "liste des clients" administrateur
    public void Clients(View view) {
        Intent intent = new Intent(Administrateur.this, Principal_creation_client.class);
        startActivity(intent);
    }

    //android:onClick="Epargnes"
    public void Epargnes(View view) {
        Intent intent2=new Intent(Administrateur.this, Principal_creation_epargne.class);
        startActivity(intent2);
    }

    public void Cheques(View view) {
        Intent intent3=new Intent(Administrateur.this, Principal_creation_cheque.class);
        startActivity(intent3);
    }

    //Methode qui applique le methode des intérets
    public void PaiementInterets(View view) {

        //Création d'un guichet
        Guichet unGuichet= new Guichet();
        //Juste des variable qui vont nous permettre de faire des affichages avant après pour montrer les changements dans les comptes épargnes
        String a,b,c,A,B,C;
        //AVANT
        a=String.valueOf(unGuichet.lesCompteEpargne.get(0).getSoldeCompte());
        b=String.valueOf(unGuichet.lesCompteEpargne.get(1).getSoldeCompte());
        c=String.valueOf(unGuichet.lesCompteEpargne.get(2).getSoldeCompte());

        Toast.makeText(Administrateur.this, "Les intérêts ont été appliqué!", Toast.LENGTH_SHORT).show();
        //Les paiements ici
        unGuichet.lesCompteEpargne.get(0).paiementsInterets();
        unGuichet.lesCompteEpargne.get(1).paiementsInterets();
        unGuichet.lesCompteEpargne.get(2).paiementsInterets();

        //APRÈS
        A=String.valueOf(unGuichet.lesCompteEpargne.get(0).getSoldeCompte());
        B=String.valueOf(unGuichet.lesCompteEpargne.get(1).getSoldeCompte());
        C=String.valueOf(unGuichet.lesCompteEpargne.get(2).getSoldeCompte());

        //Les toast d'affichages des montant comptes épargnes des clients avant/après:
        Toast.makeText(Administrateur.this, "Carlos : "+a+" à "+A+" ", Toast.LENGTH_SHORT).show();
        Toast.makeText(Administrateur.this, "Joannie : "+b+" à "+B+" ", Toast.LENGTH_SHORT).show();
        Toast.makeText(Administrateur.this, "Clark : "+c+" à "+C+" ", Toast.LENGTH_SHORT).show();
    }

    public void Terminer(View view) {
        //Ferme la page actuelle
        finish();
        //Et ouvre l'activité password à zéro.
        Intent intent4 = new Intent(Administrateur.this, MainActivity_Pass.class);
        startActivity(intent4);
    }
}