# TP Lab 15 — SQLite Android : Gestion des Apprenants

## Description

Application Android de gestion des apprenants basée sur une base de données
**SQLite embarquée**. Le projet couvre une couche métier (modèle `Apprenant`),
une couche d'accès aux données (`BaseDonnees` + `ApprenantService` CRUD),
et une couche présentation avec interface graphique complète.

---
## Demonstration
https://youtu.be/iNOMDXO-3u0
---
## Fonctionnalités

- Ajout d'un apprenant (nom + prénom) avec validation des champs
- Recherche d'un apprenant par son identifiant
- Suppression d'un apprenant par son identifiant
- Affichage du résultat de recherche dans un TextView
- Messages Toast pour chaque action (succès ou erreur)
- Base SQLite locale créée automatiquement au premier lancement
---

## Architecture en couches

```
Couche Présentation
    MainActivity.java
        │
        ▼
Couche Service
    ApprenantService.java  (CRUD)
        │
        ▼
Couche Accès aux données
    BaseDonnees.java  (SQLiteOpenHelper)
        │
        ▼
Base SQLite locale
    campus.db  →  table apprenant
```

---

## Base de données SQLite

| Élément | Valeur |
|---|---|
| Nom de la base | `campus` |
| Nom de la table | `apprenant` |
| Fichier généré | `campus.db` (stocké sur l'appareil) |

### Schéma de la table

| Colonne | Type | Contrainte |
|---|---|---|
| `id` | `INTEGER` | `PRIMARY KEY AUTOINCREMENT` |
| `nom` | `TEXT` | — |
| `prenom` | `TEXT` | — |

---

## Fichiers principaux

### `classes/Apprenant.java`

Modèle métier représentant un apprenant :

| Champ | Type | Rôle |
|---|---|---|
| `id` | `int` | Clé primaire auto-incrémentée |
| `nom` | `String` | Nom de l'apprenant |
| `prenom` | `String` | Prénom de l'apprenant |

- Constructeur avec paramètres : `Apprenant(nom, prenom)`
- Constructeur vide : requis pour reconstruire un objet depuis la base
- `toString()` : utile pour le débogage Logcat

### `util/BaseDonnees.java`

Étend `SQLiteOpenHelper` :

| Méthode | Rôle |
|---|---|
| `onCreate()` | Crée la table `apprenant` au premier lancement |
| `onUpgrade()` | Supprime et recrée la table lors d'une mise à jour de version |

### `service/ApprenantService.java`

Couche CRUD centralisée :

| Méthode | Rôle |
|---|---|
| `ajouter(Apprenant)` | Insère via `ContentValues` + `insert()` |
| `modifier(Apprenant)` | Met à jour via `update()` |
| `rechercherParId(int)` | Lit via `query()` et reconstruit un objet |
| `supprimer(Apprenant)` | Supprime via `delete()` |
| `tousLesApprenants()` | Lit toute la table via `rawQuery()` |

### `MainActivity.java`

Interface graphique avec 3 actions :

| Composant | ID | Rôle |
|---|---|---|
| `EditText` | `editNom` | Saisie du nom |
| `EditText` | `editPrenom` | Saisie du prénom |
| `EditText` | `editId` | Saisie de l'identifiant |
| `Button` | `btnAjouter` | Enregistre un apprenant |
| `Button` | `btnRechercher` | Recherche par ID |
| `Button` | `btnSupprimer` | Supprime par ID |
| `TextView` | `tvResultat` | Affiche le résultat |

---

## Flux des opérations

```
[Enregistrer]
    Saisir nom + prénom → clic Enregistrer
    → ApprenantService.ajouter()
    → INSERT dans SQLite
    → Toast "Apprenant enregistré !"
    → Champs vidés

[Rechercher]
    Saisir un ID → clic Rechercher
    → ApprenantService.rechercherParId()
    → SELECT dans SQLite
    → Affichage dans tvResultat

[Supprimer]
    Saisir un ID → clic Supprimer
    → ApprenantService.rechercherParId() (vérification)
    → ApprenantService.supprimer()
    → DELETE dans SQLite
    → Toast "Apprenant supprimé."
    → Champs vidés
```

---

## Concepts utilisés

| Concept | Description |
|---|---|
| `SQLiteOpenHelper` | Gère la création et la mise à jour de la base |
| `SQLiteDatabase` | Objet d'accès aux opérations SQL |
| `ContentValues` | Conteneur clé/valeur pour INSERT et UPDATE |
| `Cursor` | Itérateur sur les résultats d'une requête SELECT |
| `getWritableDatabase()` | Ouvre la base en écriture |
| `getReadableDatabase()` | Ouvre la base en lecture |
| `rawQuery()` | Exécute une requête SQL brute |
| `query()` | Exécute une requête SELECT paramétrée |
| `moveToFirst()` | Positionne le curseur sur le premier résultat |
| `moveToNext()` | Passe au résultat suivant |
| `c.close() / db.close()` | Libère les ressources après usage |
---

## Vérifier la base SQLite dans Android Studio

1. **View → Tool Windows → App Inspection**
2. Sélectionner l'émulateur
3. Cliquer sur **Database Inspector**
4. La base `campus` apparaît avec la table `apprenant`

---

## Environnement

- **IDE** : Android Studio
- **Langage** : Java
- **Base de données** : SQLite (embarquée)
- **Minimum SDK** : API 24 (Android 7.0)
- **API cible** : 36.1
- **Émulateur** : Medium Phone API 36.1
