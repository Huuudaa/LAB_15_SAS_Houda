package com.example.lab_15_sas_houda;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_15_sas_houda.classes.Apprenant;
import com.example.lab_15_sas_houda.service.ApprenantService;

public class MainActivity extends AppCompatActivity {

    private EditText editNom, editPrenom, editId;
    private Button   btnAjouter, btnRechercher, btnSupprimer;
    private TextView tvResultat;
    private ApprenantService service;

    private void viderChamps() {
        editNom.setText("");
        editPrenom.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new ApprenantService(this);

        editNom       = findViewById(R.id.editNom);
        editPrenom    = findViewById(R.id.editPrenom);
        editId        = findViewById(R.id.editId);
        btnAjouter    = findViewById(R.id.btnAjouter);
        btnRechercher = findViewById(R.id.btnRechercher);
        btnSupprimer  = findViewById(R.id.btnSupprimer);
        tvResultat    = findViewById(R.id.tvResultat);

        btnAjouter.setOnClickListener(v -> {
            String nom    = editNom.getText().toString().trim();
            String prenom = editPrenom.getText().toString().trim();
            if (nom.isEmpty() || prenom.isEmpty()) {
                Toast.makeText(this, "Remplissez tous les champs.", Toast.LENGTH_SHORT).show();
                return;
            }
            service.ajouter(new Apprenant(nom, prenom));
            viderChamps();
            Toast.makeText(this, "Apprenant enregistré !", Toast.LENGTH_SHORT).show();
        });

        btnRechercher.setOnClickListener(v -> {
            String txt = editId.getText().toString().trim();
            if (txt.isEmpty()) {
                Toast.makeText(this, "Saisissez un identifiant.", Toast.LENGTH_SHORT).show();
                return;
            }
            Apprenant a = service.rechercherParId(Integer.parseInt(txt));
            if (a == null) {
                tvResultat.setText("Aucun apprenant trouvé.");
                return;
            }
            tvResultat.setText("Trouvé : " + a.getNom() + " " + a.getPrenom());
        });

        btnSupprimer.setOnClickListener(v -> {
            String txt = editId.getText().toString().trim();
            if (txt.isEmpty()) {
                Toast.makeText(this, "Saisissez un identifiant.", Toast.LENGTH_SHORT).show();
                return;
            }
            Apprenant a = service.rechercherParId(Integer.parseInt(txt));
            if (a == null) {
                Toast.makeText(this, "Apprenant introuvable.", Toast.LENGTH_SHORT).show();
                return;
            }
            service.supprimer(a);
            tvResultat.setText("");
            editId.setText("");
            Toast.makeText(this, "Apprenant supprimé.", Toast.LENGTH_SHORT).show();
        });
    }
}