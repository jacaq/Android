package com.crosemont.guichetautomatiqueatm;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* Page qui crée les clients */
public class Principal_creation_client extends AppCompatActivity implements Serializable {

    public ArrayList<Client> listes_de_clients = new ArrayList<Client>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        //On initialise la liste client;
        InitList(listes_de_clients);

        //On la passe dans l'adaptateur de format AndroidAdapteur
        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.liste_layout, listes_de_clients);

        //On relit encore à la "liste_layout "
        final ListView list = (ListView) findViewById(R.id.list);
        final TextView nombreDeClients = (TextView) findViewById(R.id.nombre);
        //String nombreClients = getString(R.string.nombre) + adapter.getCount();
        String nombreClients = "Nombre de clients:" + " " + adapter.getCount();
        nombreDeClients.setText(nombreClients);
        list.setAdapter(adapter);


    }

    protected void InitList(ArrayList<Client> androidList)
    {
        // CARLOS
        Client client1 = new Client();
        client1.setPrenom("Carlos");
        client1.setNom("Lozano");
        client1.setNumCompte("01-0000010");
        androidList.add(client1);

        // JOANNIE
        Client client2 = new Client();
        client2.setPrenom("Joannie");
        client2.setNom("Ranger");
        client2.setNumCompte("01-0000011");
        androidList.add(client2);

        // CLARK
        Client client3 = new Client();
        client3.setPrenom("Clark");
        client3.setNom("Kent");
        client3.setNumCompte("01-0000012");
        androidList.add(client3);
    }

    public void BtnRetourner(View view) {
        Intent intent = new Intent(Principal_creation_client.this, Administrateur.class);
        startActivity(intent);
    }
}


