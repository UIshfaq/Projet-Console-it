package console.consoleit.model;

public class Mission {
    private int id;
    private String nomMission;
    private String materiel;
    private String site;


    public Mission(int id, String nomMission, String materiel, String site) {
        this.id = id;
        this.nomMission = nomMission;
        this.materiel = materiel;
        this.site = site;
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
}
