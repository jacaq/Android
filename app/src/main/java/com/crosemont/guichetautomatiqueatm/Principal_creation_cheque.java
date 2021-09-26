package com.crosemont.guichetautomatiqueatm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class Principal_creation_cheque extends AppCompatActivity implements Serializable {


    public ArrayList<Cheque> listes_cheque_clients = new ArrayList<Cheque>();

    /* Page qui crée les compte cheques */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        //On l'initialise la liste;
        InitList3(listes_cheque_clients);

        //On la passe dans l'adaptateur de format AndroidAdapteur
        AndroidAdapter_cheque adapter = new AndroidAdapter_cheque(this, R.layout.liste_layout, listes_cheque_clients);

        //On relit encore à la "vue liste_layout"
        final ListView list = (ListView) findViewById(R.id.list);
        final TextView nombreDeClients = (TextView) findViewById(R.id.nombre);
        String nombreClients = "Compte chèques, No. Clients:" + " " + adapter.getCount();
        nombreDeClients.setText(nombreClients);
        list.setAdapter(adapter);

        //Essais de faire passer la liste cheques dans bienvenue
        Intent intent = new Intent(Principal_creation_cheque.this, Bienvenue_Transactions.class);
        intent.putExtra("ListeCheque", listes_cheque_clients);
    }


    //Méthode qui initialise la liste compte chèque:
    protected void InitList3(ArrayList<Cheque> androidList)
    {

        // CARLOS
        Cheque client1 = new Cheque(1234,"01-0000010",300 );
        androidList.add(client1);

        // JOANNIE
        Cheque client2 = new Cheque(1235,"01-0000011",5000 );
        androidList.add(client2);

        // CLARK
        Cheque client3 = new Cheque(1236,"01-0000012",3560 );
        androidList.add(client3);
    }

    //Bouton Retour à la page Administrateur
    public void BtnRetourner(View view) {
        Intent intent = new Intent(Principal_creation_cheque.this, Administrateur.class);
        startActivity(intent);
    }
}