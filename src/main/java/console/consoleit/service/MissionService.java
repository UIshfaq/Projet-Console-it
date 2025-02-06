package console.consoleit.service;

import console.consoleit.repositories.MissionRepositories;

import java.sql.SQLException;

public class MissionService {
    private MissionRepositories missionRepositories;

    public MissionService() {
        this.missionRepositories = new MissionRepositories();
    }

    public void modifierMission(int idMission, String nomMission, String materiel, String site, String description, int prixMission, int prixIntervenant) {
        missionRepositories.modifierMission(idMission, nomMission, materiel, site, description, prixMission, prixIntervenant);
    }

    public void creeMissionsEmployer(String nomMission, String materiel, String site, String description, int benefice, int cA, int idEmployer) throws SQLException {
        missionRepositories.creeMissionsEmployer(nomMission, materiel, site, description, benefice, cA, idEmployer);
    }

    public void modifierMissionFini(int id) throws SQLException {
        missionRepositories.modifierEtatMissionFini(id);
    }
    public void modifierMissionPasFini(int id) throws SQLException {
        missionRepositories.modifierEtatMissionNonFini(id);
    }
}
