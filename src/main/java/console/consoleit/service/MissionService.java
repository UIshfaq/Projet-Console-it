package console.consoleit.service;

import console.consoleit.repositories.MissionRepositories;

public class MissionService {
    private MissionRepositories adminTableauBordRepository;

    public MissionService() {
        this.adminTableauBordRepository = new MissionRepositories();
    }

    public void supprimerMission(int idMission) {
        adminTableauBordRepository.supprimerMission(idMission);
    }

    public void ModifierMission(int idMission, String nomMission, String materiel, String site, String description, int prixMission, int prixIntervenant) {
        adminTableauBordRepository.ModifierMission(idMission, nomMission, materiel, site, description, prixMission, prixIntervenant);
    }
}
