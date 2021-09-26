package com.crosemont.guichetautomatiqueatm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Principal_creation_epargne extends AppCompatActivity implements Serializable {

    public ArrayList<Epargne> listes_epargne_clients = new ArrayList<Epargne>();

    /* Page qui crée les comptes épargnes */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        //On initialise la liste comptes épargnes;
        InitList2(listes_epargne_clients);

        //On la passe dans l'adaptateur de format AndroidAdapteur
        AndroidAdapter_eparne adapter = new AndroidAdapter_eparne(this, R.layout.liste_layout, listes_epargne_clients);

        //On relit encore à la "vue liste_layout"
        final ListView list = (ListView) findViewById(R.id.list);
        final TextView nombreDeClients = (TextView) findViewById(R.id.nombre);
        String nombreClients = "Comptes épargnes, No. Clients:" + " " + adapter.getCount();
        nombreDeClients.setText(nombreClients);
        list.setAdapter(adapter);


    }


    //Méthode qui initialise la liste compte épargne
    protected void InitList2(ArrayList<Epargne> androidList)
    {
        //int numeroNip, int numeroCompte, double soldeCompte
        // CARLOS
        Epargne client1 = new Epargne(1234,"01-0000010",10000 );
        androidList.add(client1);

        // JOANNIE
        Epargne client2 = new Epargne(1235,"01-0000011",5000 );
        androidList.add(client2);

        // CLARK
        Epargne client3 = new Epargne(1236,"01-0000012",6000 );
        androidList.add(client3);

    }



    //Bouton qui retorune à la page administrateur.
    public void BtnRetourner(View view) {
        Intent intent = new Intent(Principal_creation_epargne.this, Administrateur.class);
        startActivity(intent);
    }
}