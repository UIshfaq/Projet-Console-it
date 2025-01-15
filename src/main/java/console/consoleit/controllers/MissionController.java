package console.consoleit.controllers;

import console.consoleit.service.MissionService;

public class MissionController {

    private MissionService adminTableauBordService;

    public MissionController() {
        this.adminTableauBordService = new MissionService();
    }

    public void supprimerMission(int idMission) {
        adminTableauBordService.supprimerMission(idMission);
    }

    public void ModifierMission(int idMission, String nomMission, String materiel, String site, String description) {
        adminTableauBordService.ModifierMission(idMission, nomMission, materiel, site, description);
    }
}
