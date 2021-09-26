package com.crosemont.guichetautomatiqueatm;

public class Client  {
    private String Prenom;
    private String Nom;
    private String numCompte;
    private String Nip;

    public Client(String prenom, String nom, String numCompte, String nip) {
        Prenom = prenom;
        Nom = nom;
        this.numCompte = numCompte;
        this.Nip = nip;
    }
    public Client(){}

    public String getPrenom() {
        return this.Prenom;
    }

    public void setPrenom(String prenom) {
        this.Prenom = prenom;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) { this.numCompte = numCompte; }
}
