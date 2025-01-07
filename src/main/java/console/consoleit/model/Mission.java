package console.consoleit.model;

public class Mission {
    private int id;
    private String nomMission;


    public Mission(int id, String nomMission) {
        this.id = id;
        this.nomMission = nomMission;
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

}
