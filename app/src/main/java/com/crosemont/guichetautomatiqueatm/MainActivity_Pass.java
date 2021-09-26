package com.crosemont.guichetautomatiqueatm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity_Pass extends AppCompatActivity {
    ///PAGE QUI INSTANCIT L'ENTRÉE PASSWORD
    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eAttempsInfo;

    private String Username;
    private String Password;

    boolean isValid = false;
    int codeStart=0;
    private int counter = 3;
    int qui=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etNom);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttempsInfo = findViewById(R.id.tvAttemptsInfo);

        //Création de la liste de type objet client
        ArrayList<Principal_creation_client> listePrincipalCreationclients = new ArrayList<Principal_creation_client>();
        //Création de la liste de type objet compte
        ArrayList<Compte> ListeCompteCheque= new ArrayList<Compte>();
        //Création de la liste de type objet compte
        ArrayList<Compte> ListeCompteEpargne= new ArrayList<Compte>();

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                //Test si un/les champs sont vides:
                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity_Pass.this, "Remplir tous les champs", Toast.LENGTH_LONG).show();
                }
                //Sinon s'ils sont remplit
                else {
                        //bool qui indique si il y a un match entre les  donnée clients ou admin. Appels des fonctions de validations appropriés.
                        isValid = (validateAdmin(inputName, inputPassword) || validateClient(inputName, inputPassword));
                        if (  (validateAdmin(inputName, inputPassword))  ==true  ) codeStart=1;
                        else codeStart=2;
                        //Si ce n'est pas valide message d'erreur générique.
                        if (!isValid) {
                            //Décrémentation du compteur de tentative
                            counter--;
                            Toast.makeText(MainActivity_Pass.this, "Mauvais username/mot de passe!", Toast.LENGTH_LONG).show();
                            //Mise à jour de l'affichage de tentative restante.
                            eAttempsInfo.setText("Nombre de tentatives: " + counter);
                            //Si le compteur est rendu à zéro, too bad on ferme et indique message revenir plus tard...
                            if (counter == 0) {
                                Toast.makeText(MainActivity_Pass.this, "Veuillez essayer plus tard", Toast.LENGTH_LONG).show();
                                //désactivation du login.... Rendu la ca sert pas à grand chose vu qu'on vide et ferme anyways.***************************
                                eLogin.setEnabled(false);
                                //vide
                                finish();
                                //ferme
                                System.exit(0);
                            }
                        }
                    //Sinon la validation est ok on accède aux compte normalement
                        else {
                            Toast.makeText(MainActivity_Pass.this, "Connexion établit ", Toast.LENGTH_LONG).show();
                            //Toast.makeText(MainActivity_Pass.this, "Connexion établit "+" "+String.valueOf(codeStart), Toast.LENGTH_LONG).show();

                            if (codeStart==1){
                                    Intent intent = new Intent(MainActivity_Pass.this, Administrateur.class);
                                    startActivity(intent);
                            }
                            if (codeStart==2){
                                    Intent intent2 = new Intent(MainActivity_Pass.this, Bienvenue_Transactions.class);
                                    //on doit envoyer le int "qui" dans l'ouverture du intent qui sera récupéré pour l'utilisation du compte de la bonne personne
                                    intent2.putExtra("qui",qui);
                                    startActivity(intent2);


                            }
                        }
                    }
                }
        });
    }

    private boolean validateAdmin(String name, String password) {
        Username = "Admin"; //*******************************A changer: les mots de passe***************************************/
        Password = "D001";
        if (name.equals(Username) && password.equals(Password)) {
            return true;
        }
        return false;
    }
    private boolean validateClient(String name, String password) {
        Username = "Carlos";
        Password = "1234";
        String Username2="Joannie";
        String Password2="1235";
        String Username3="Clark";
        String Password3="1236";
        if (name.equals(Username) && password.equals(Password)) {
            qui=quelClient(Password);
            return true;
        }
        if (name.equals(Username2) && password.equals(Password2)) {
            qui=quelClient(Password2);
            return true;
        }
        if (name.equals(Username3) && password.equals(Password3)) {
            qui=quelClient(Password3);
            return true;
        }
        return false;
    }

    private int quelClient(String password) {
       // retourn en int le string entrée
        return Integer.valueOf(password);
    }
}
