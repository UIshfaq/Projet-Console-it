package console.consoleit.model;

import java.sql.Date;

public class Employer {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Boolean isBlocked;
    private Boolean isSuprimer;
    private Boolean changeMdp;



    public Employer(int id, String nom, String prenom, String email, Boolean isBlocked, Boolean changeMdp
    ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.isBlocked = isBlocked;
        this.changeMdp = changeMdp;
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

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public Boolean getChangeMdp() {
        return changeMdp;
    }

    public void setChangeMdp(Boolean changeMdp) {
        this.changeMdp = changeMdp;
    }
    public Boolean getIsSuprimer() {
        return isSuprimer;
    }

    public void setSuprimer(Boolean suprimer) {
        isSuprimer = suprimer;
    }
}
