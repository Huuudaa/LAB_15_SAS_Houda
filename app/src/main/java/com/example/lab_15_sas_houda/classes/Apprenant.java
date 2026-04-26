package com.example.lab_15_sas_houda.classes;

public class Apprenant {
    private int id;
    private String nom;
    private String prenom;

    public Apprenant(String nom, String prenom) {
        this.nom    = nom;
        this.prenom = prenom;
    }

    public Apprenant() {}

    public int getId()             { return id; }
    public void setId(int id)      { this.id = id; }
    public String getNom()         { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom()              { return prenom; }
    public void setPrenom(String prenom)   { this.prenom = prenom; }

    @Override
    public String toString() {
        return "Apprenant{id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' + '}';
    }
}