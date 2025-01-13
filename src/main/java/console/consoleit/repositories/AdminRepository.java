package console.consoleit.repositories;

import console.consoleit.model.Employer;
import console.consoleit.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminRepository {

    private Connection connection;

    public AdminRepository() {
        this.connection = DataSourceProvider.getCnx();
    }

    public Boolean verifierIdentifiants(String email, String mdp) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT email, mdp, admin FROM employe WHERE email = ? AND mdp = ?");
        ps.setString(1, email);
        ps.setString(2, mdp);
        boolean result = false;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getString("mdp").equals(mdp) && rs.getString("email").equals(email)) {
                result = true;
            }
        }
        ps.close();
        rs.close();
        return result;
    }

    public ArrayList<Employer> getAll() throws SQLException {
        ArrayList<Employer> employer = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id , nom , prenom , email  FROM Employe WHERE admin = 0;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employer employers = new Employer(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email"));
            employer.add(employers);
        }
        return employer;
    }

    public void bloquerEmploye(int idEmploye) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET is_blocked = true WHERE id = ?");
        preparedStatement.setInt(1, idEmploye);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void debloquerEmploye(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET is_blocked = false WHERE id = ?");
        preparedStatement.setInt(1, idUser);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void supprimerUser(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET email = CONCAT('anonymous', id, '@veliko.lan'), nom = 'anonymous', prenom = 'anonymous', adresse = 'anonymous', token = NULL WHERE id = ?");
        preparedStatement.setInt(1, idUser);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void forcerMdpChange(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET boolean_changer_mdp = 1 WHERE id = ?");
        preparedStatement.setInt(1, idUser);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public ArrayList<Employer> getAllEmploye() throws SQLException {
        ArrayList<Employer> employer = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id , nom , prenom , email,is_blocked,is_supprimer,changeMdp  FROM Employe WHERE admin = 0;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employer employers = new Employer(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email"),resultSet.getBoolean("is_blocked"),resultSet.getBoolean("is_supprimer"),resultSet.getBoolean("changeMdp"));
            employer.add(employers);
        }
        preparedStatement.close();
        resultSet.close();
        return employer;
    }
}