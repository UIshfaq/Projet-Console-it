package console.consoleit.controllers;

import console.consoleit.service.MissionService;

import java.sql.SQLException;

public class MissionController {

    private MissionService missionService;

    public MissionController() {
        this.missionService = new MissionService();
    }

    public void modifierMission(int idMission, String nomMission, String materiel, String site, String description, int prixMission, int prixIntervenant) {
        missionService.modifierMission(idMission, nomMission, materiel, site, description, prixMission, prixIntervenant);
    }

    public void creeMissionsEmployer(String nomMission, String materiel, String site,  String description, int benefice, int cA, int idEmployer) throws SQLException {
        missionService.creeMissionsEmployer(nomMission, materiel, site, description, benefice, cA,idEmployer);
    }

    public void modifierMissionFini(int id) throws SQLException {
        missionService.modifierMissionFini(id);
    }
    public void modifierMissionPasFini(int id) throws SQLException {
        missionService.modifierMissionPasFini(id);
    }
}
