package console.consoleit.model;

import java.sql.Date;

public class Employer {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Boolean isBlocked;
    private Boolean isSuprimer;



    public Employer(int id, String nom, String prenom, String email, Boolean isBlocked, Boolean isSuprimer) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.isBlocked = isBlocked;
        this.isSuprimer = isSuprimer;
    }
    public Employer(int id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Boolean isSuprimer() {
        return isSuprimer;
    }

    public void setSuprimer(boolean suprimer) {
        isSuprimer = suprimer;
    }
}
