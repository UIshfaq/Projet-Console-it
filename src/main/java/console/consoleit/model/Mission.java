package console.consoleit.model;

public class Mission {
    private int id;
    private String nomMission;
    private String materiel;
    private String site;
    private int idEmployer;
    private String description;
    private int benefice;
    private int cA;


    public Mission(int id, String nomMission, String materiel, String site, String description, int benefice, int cA) {
        this.id = id;
        this.nomMission = nomMission;
        this.materiel = materiel;
        this.site = site;
        this.description = description;
        this.benefice = benefice;
        this.cA = cA;
    }

    public Mission(int id, String nomMission, String materiel, String site, int idEmployer, String description, int benefice, int cA) {
        this.id = id;
        this.nomMission = nomMission;
        this.materiel = materiel;
        this.site = site;
        this.idEmployer = idEmployer;
        this.description = description;
        this.benefice = benefice;
        this.cA = cA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomMission() {
        return nomMission;
    }

    public void setNomMission(String nomMission) {
        this.nomMission = nomMission;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBenefice() {
        return benefice;
    }

    public void setBenefice(int benefice) {
        this.benefice = benefice;
    }

    public int getCA() {
        return cA;
    }

    public void setCA(int cA) {
        this.cA = cA;
    }
}

