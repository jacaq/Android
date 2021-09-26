package com.crosemont.guichetautomatiqueatm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Bienvenue_Transactions extends AppCompatActivity implements Serializable {

    //Class pour l'interface des calcul client (retrait/depot/etc)
    private EditText display;
    private int accountType = 1;
    Button btn_Logout;
    Button btn_Etat_Compte;

    //Creation variable pour lier au radio bouton:
    RadioButton rbDepot,rbRetrait,rbCheque,rbEpargne, rbVirement;
    double le_montant=0;
    int laPos;
    private TextView txtSoldeCheque;
    private TextView txtSoldeEpargne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue);



        RadioButton rbDepot=(RadioButton)findViewById(R.id.radioBtnDepot);
        RadioButton rbRetrait=(RadioButton)findViewById(R.id.radioBtnRetrait);
        RadioButton rbCheque=(RadioButton)findViewById(R.id.radioBtnCheque);
        RadioButton rbEpargne=(RadioButton)findViewById(R.id.radioBtnEpargne);
        RadioButton rbVirement=(RadioButton)findViewById(R.id.radioBtnVirement);
        Button btn_Soumettre=(Button)findViewById(R.id.btnSoumetre);

        //On relit nos 2 affichages cachées:
        txtSoldeCheque= (TextView) findViewById(R.id.SoldeCheque);
        txtSoldeEpargne=(TextView) findViewById(R.id.SoldeEpargne);

        //Récupération du qui pour savoir c a qui que le compte appartient.
        Bundle extraQui= getIntent().getExtras();
        int cQui= extraQui.getInt("qui");


        //Création d'un guichet qui crée tout mes comptes.
        Guichet leGuichet=new Guichet();

        //FACULTATIF **** Pour que le client ne soit pas en mesure d'écrire avec le clavier.
        display = findViewById(R.id.montant);
        display.setShowSoftInputOnFocus(false);


        //Quand on clique sur le Bouton SOUMETTRE cest ici que tout se passe:
        btn_Soumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valeur = display.getText().toString();

                if(display.getText().toString().isEmpty()){
                    Toast.makeText(Bienvenue_Transactions.this, "Entrez un montant", Toast.LENGTH_LONG).show();
                }
                else{
                String leNip= String.valueOf(cQui);
                le_montant= Double.parseDouble(valeur);
                //String valeur=String.valueOf(le_montant);
                laPos=leGuichet.getPos(leNip);
                double d;

                //si le montant est supérieur à 0
                if (le_montant > 0){
                    //Validation du type de transaction:
                    if(rbDepot.isChecked()){
                        //Faire le dépot de montant dans le compte.
                        Toast.makeText(Bienvenue_Transactions.this, "Dépôt de : "+ valeur + " dans votre compte", Toast.LENGTH_LONG).show();
                        //Application du dépot par rapport au nip
                        if (rbEpargne.isChecked())
                            d=leGuichet.DepotEpargne(leNip,le_montant);
                        else d=leGuichet.DepotCheque(leNip,le_montant);
                        Toast.makeText(Bienvenue_Transactions.this, "Solde compte cheque : " + (leGuichet.lesCompteCheque.get(laPos).getSoldeCompte()) , Toast.LENGTH_LONG).show();
                        Toast.makeText(Bienvenue_Transactions.this, "Solde compte épargne : " + (leGuichet.lesCompteEpargne.get(laPos).getSoldeCompte()) , Toast.LENGTH_LONG).show();
                        display.setText("");
                    }else if(rbRetrait.isChecked()){
                        //Faire le retrait si montant est divisible par %10 de montant dans le compte.
                        if (le_montant%10==0 && le_montant<=1000) {
                            Toast.makeText(Bienvenue_Transactions.this, "Retait de : " + valeur + " dans votre compte", Toast.LENGTH_LONG).show();
                            //Application du retrait par rapport au nip
                            if (rbEpargne.isChecked() && leGuichet.RetraitEpargnePossible(leNip,le_montant))
                                    d = leGuichet.RetraitEpargne(leNip, le_montant);
                            else if (leGuichet.RetraitChequePossible(leNip, le_montant))
                                 d = leGuichet.RetraitCheque(leNip, le_montant);
                            else {Toast.makeText(Bienvenue_Transactions.this, "Fonds insuffisant!", Toast.LENGTH_LONG).show();}
                            Toast.makeText(Bienvenue_Transactions.this, "Solde compte cheque : " + (leGuichet.lesCompteCheque.get(laPos).getSoldeCompte()) , Toast.LENGTH_LONG).show();
                            Toast.makeText(Bienvenue_Transactions.this, "Solde compte épargne : " + (leGuichet.lesCompteEpargne.get(laPos).getSoldeCompte()) , Toast.LENGTH_LONG).show();
                            display.setText("");
                            //Message erreur si le montant n'est pas retirable puisque modulo 10 donne un reste.
                        }else {Toast.makeText(Bienvenue_Transactions.this, "Le montant " + valeur + " n'est pas retirable ou fonds insufisants!", Toast.LENGTH_LONG).show();}
                    }else if(rbVirement.isChecked() && (le_montant<=100000)){
                        //Validation à partir de quel compte le virement se produit
                        if (rbCheque.isChecked()){
                            //Virement de cheque à épargne, si le retrait n'est pas négatif
                            if (leGuichet.RetraitChequePossible(leNip,le_montant)){
                                Toast.makeText(Bienvenue_Transactions.this, "Virement de : " + valeur + "  de votre compte chèque à épargne!", Toast.LENGTH_LONG).show();
                                d=leGuichet.RetraitCheque(leNip,le_montant);
                                d=leGuichet.DepotEpargne(leNip,le_montant);
                            }else {Toast.makeText(Bienvenue_Transactions.this, "Transaction impossible!", Toast.LENGTH_LONG).show();}
                            Toast.makeText(Bienvenue_Transactions.this, "Solde compte cheque : " + (leGuichet.lesCompteCheque.get(laPos).getSoldeCompte()) , Toast.LENGTH_LONG).show();
                            Toast.makeText(Bienvenue_Transactions.this, "Solde compte épargne : " + (leGuichet.lesCompteEpargne.get(laPos).getSoldeCompte()) , Toast.LENGTH_LONG).show();
                            display.setText("");
                        }else{//virement de épargne à chèque
                            if (leGuichet.RetraitEpargnePossible(leNip,le_montant)){
                                Toast.makeText(Bienvenue_Transactions.this, "Virement de : " + valeur + "  de votre compte épargne à chèque!", Toast.LENGTH_LONG).show();
                                d=leGuichet.RetraitEpargne(leNip,le_montant);
                                d=leGuichet.DepotCheque(leNip,le_montant);
                            }else {
                                Toast.makeText(Bienvenue_Transactions.this, "Transaction impossible!", Toast.LENGTH_LONG).show();}
                            Toast.makeText(Bienvenue_Transactions.this, "Solde compte cheque : " + (leGuichet.lesCompteCheque.get(laPos).getSoldeCompte()) , Toast.LENGTH_LONG).show();
                            Toast.makeText(Bienvenue_Transactions.this, "Solde compte épargne : " + (leGuichet.lesCompteEpargne.get(laPos).getSoldeCompte()) , Toast.LENGTH_LONG).show();
                            display.setText("");
                        }
                    }
                } else{
                    //Message d'erreur si le montant entrée est inférieur de 0;
                    Toast.makeText(Bienvenue_Transactions.this, "Montant : "+ valeur+ " invalide !", Toast.LENGTH_LONG).show();
                }
            }}
        });



        //Quand on clique sur le bouton etat des comptes, on affiche l'état des 2 précédants boutons.
        btn_Etat_Compte=(Button)findViewById(R.id.btnEtatComp);
        btn_Etat_Compte.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                //txtSoldeCheque.setText("Nip : "+String.valueOf(cQui)); // ;
                String leNip= String.valueOf(cQui);
                laPos=leGuichet.getPos(leNip);

                //AFFICHE LE MONTANT DES 2 COMPTES:
                txtSoldeCheque.setText("  Solde compte chèque :"+String.valueOf(leGuichet.lesCompteCheque.get(laPos).getSoldeCompte()));
                txtSoldeEpargne.setText("  Solde compte épargne :"+String.valueOf(leGuichet.lesCompteEpargne.get(laPos).getSoldeCompte()));
            }
        });



        //Bouton logout qui ferme la page.
        btn_Logout = (Button) findViewById(R.id.btn_Logout);
        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ferme la présente page
                finish();
            }
        });
    } //Fin du oncreate



    //Méthode qui ajoute les données à l'affichage après clique sur les boutons de l'interface avec les chiffres.
    public void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.app_name).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }


    //Valeur de chaque BOUTON qui utilise la fonction ci-haut "updateText"
    public void btnZero(View view) {
        updateText("0");
    }

    public void btnOne(View view) {
        updateText("1");
    }

    public void btnTwo(View view) {
        updateText("2");
    }

    public void btnThree(View view) {
        updateText("3");
    }

    public void btnFour(View view) {
        updateText("4");
    }

    public void btnFive(View view) {
        updateText("5");
    }

    public void btnSix(View view) {
        updateText("6");
    }

    public void btnSeven(View view) {
        updateText("7");
    }

    public void btnEight(View view) {
        updateText("8");
    }

    public void btnNine(View view) {
        updateText("9");
    }

    public void btnPoint(View view) {
        //Ajouter test sur la chaine si contient deja un point ne pas permettre d'ajouter. OPTIONEL
        String oldStr= display.getText().toString();
        if (oldStr.indexOf('.')==-1) //retourne -1 si le point n'apparait déjà. Donc si il n'y a pas de point on peut l'ajouter.
                     updateText(".");
    }

    public void btnC(View view) {
        display.setText("");
    }

}