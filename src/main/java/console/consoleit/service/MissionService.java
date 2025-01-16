package console.consoleit.service;

import console.consoleit.repositories.MissionRepositories;

import java.sql.SQLException;

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

    public void creeMissionsEmployer(String nomMission, String materiel, String site, String description, int benefice, int cA, int idEmployer) throws SQLException {
        adminTableauBordRepository.creeMissionsEmployer(nomMission, materiel, site, description, benefice, cA, idEmployer);
    }
}
