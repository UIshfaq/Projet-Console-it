package console.consoleit.repositories;

import console.consoleit.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MissionRepositories {

    private Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public MissionRepositories() {
        this.connection = DataSourceProvider.getCnx();
    }

    public void supprimerMission(int idMission) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM mission WHERE id = ?");
            preparedStatement.setInt(1, idMission);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ModifierMission(int idMission, String nomMission, String materiel, String site, String description) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE mission SET nomMission = ?, materiel = ?, site = ?, description = ? WHERE id = ?")) {
            preparedStatement.setString(1, nomMission);
            preparedStatement.setString(2, materiel);
            preparedStatement.setString(3, site);
            preparedStatement.setString(4, description);
            preparedStatement.setInt(5, idMission);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}