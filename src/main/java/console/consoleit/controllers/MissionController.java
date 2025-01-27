package console.consoleit.controllers;

import console.consoleit.service.MissionService;

import java.sql.SQLException;

public class MissionController {

    private MissionService adminTableauBordService;

    public MissionController() {
        this.adminTableauBordService = new MissionService();
    }

    public void modifierMission(int idMission, String nomMission, String materiel, String site, String description, int prixMission, int prixIntervenant) {
        adminTableauBordService.modifierMission(idMission, nomMission, materiel, site, description, prixMission, prixIntervenant);
    }

    public void creeMissionsEmployer(String nomMission, String materiel, String site,  String description, int benefice, int cA, int idEmployer) throws SQLException {
        adminTableauBordService.creeMissionsEmployer(nomMission, materiel, site, description, benefice, cA,idEmployer);
    }
}
