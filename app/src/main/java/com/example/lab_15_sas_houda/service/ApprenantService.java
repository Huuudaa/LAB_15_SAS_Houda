package com.example.lab_15_sas_houda.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.lab_15_sas_houda.classes.Apprenant;
import com.example.lab_15_sas_houda.util.BaseDonnees;

public class ApprenantService {

    private static final String TABLE     = "apprenant";
    private static final String COL_ID    = "id";
    private static final String COL_NOM   = "nom";
    private static final String COL_PRENOM = "prenom";
    private static final String[] COLONNES = {COL_ID, COL_NOM, COL_PRENOM};

    private final BaseDonnees base;

    public ApprenantService(Context context) {
        this.base = new BaseDonnees(context);
    }

    public void ajouter(Apprenant a) {
        SQLiteDatabase db = base.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(COL_NOM,    a.getNom());
        valeurs.put(COL_PRENOM, a.getPrenom());
        db.insert(TABLE, null, valeurs);
        Log.d("AJOUT", a.getNom() + " " + a.getPrenom());
        db.close();
    }

    public void modifier(Apprenant a) {
        SQLiteDatabase db = base.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(COL_NOM,    a.getNom());
        valeurs.put(COL_PRENOM, a.getPrenom());
        db.update(TABLE, valeurs, "id = ?",
                new String[]{String.valueOf(a.getId())});
        db.close();
    }

    public Apprenant rechercherParId(int id) {
        SQLiteDatabase db = base.getReadableDatabase();
        Cursor c = db.query(TABLE, COLONNES,
                "id = ?", new String[]{String.valueOf(id)},
                null, null, null, null);

        Apprenant a = null;
        if (c.moveToFirst()) {
            a = new Apprenant();
            a.setId(c.getInt(0));
            a.setNom(c.getString(1));
            a.setPrenom(c.getString(2));
        }
        c.close();
        db.close();
        return a;
    }

    public void supprimer(Apprenant a) {
        SQLiteDatabase db = base.getWritableDatabase();
        db.delete(TABLE, "id = ?",
                new String[]{String.valueOf(a.getId())});
        db.close();
    }

    public List<Apprenant> tousLesApprenants() {
        List<Apprenant> liste = new ArrayList<>();
        SQLiteDatabase db = base.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE, null);

        if (c.moveToFirst()) {
            do {
                Apprenant a = new Apprenant();
                a.setId(c.getInt(0));
                a.setNom(c.getString(1));
                a.setPrenom(c.getString(2));
                liste.add(a);
                Log.d("LISTE", a.getId() + " — " + a.getNom() + " " + a.getPrenom());
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return liste;
    }
}