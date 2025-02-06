package console.consoleit.repositories;

import console.consoleit.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MissionRepositories {

    private Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public MissionRepositories() {
        this.connection = DataSourceProvider.getCnx();
    }


    public void modifierMission(int idEmploye, String nomMission, String materiel, String site, String description, int prixMission, int prixIntervenant) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE mission SET nomMission = ?, matériel = ?, site = ?, descriptionMission = ?, cA = ?, benefice = ? WHERE idEmploye = ?")) {
            preparedStatement.setString(1, nomMission);
            preparedStatement.setString(2, materiel);
            preparedStatement.setString(3, site);
            preparedStatement.setString(4, description);
            preparedStatement.setInt(5, prixMission); // cA
            preparedStatement.setInt(6, prixIntervenant); // benefice calculé
            preparedStatement.setInt(7, idEmploye);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void creeMissionsEmployer(String nomMission, String materiel, String site , String description, int benefice, int cA, int idEmployer) throws SQLException {
        String query = "INSERT INTO `mission` (`nomMission`, `matériel`, `site`, `descriptionMission`, `benefice`, `cA`,`idEmploye`) " +
                "VALUES (?, ?, ?, ?, ?, ?,? )";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Remplissage des valeurs pour les colonnes
        preparedStatement.setString(1, nomMission);
        preparedStatement.setString(2, materiel );
        preparedStatement.setString(3, site);
        preparedStatement.setDouble(5,benefice );
        preparedStatement.setDouble(6, cA );
        preparedStatement.setString(4, description);
        preparedStatement.setInt(7, idEmployer);


        preparedStatement.executeUpdate();
        preparedStatement.close();
    }



    public void modifierEtatMissionFini(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE mission SET missionTermine = 1 WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void modifierEtatMissionNonFini(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE mission SET missionTermine = 0 WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
