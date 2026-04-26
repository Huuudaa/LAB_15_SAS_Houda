package com.example.lab_15_sas_houda.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDonnees extends SQLiteOpenHelper {

    private static final int    VERSION = 1;
    private static final String NOM_BASE = "campus";

    private static final String CREATION_TABLE =
            "CREATE TABLE apprenant(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT," +
                    "prenom TEXT)";

    public BaseDonnees(Context context) {
        super(context, NOM_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS apprenant");
        this.onCreate(db);
    }
}