package console.consoleit.repositories;

import console.consoleit.model.Employer;
import console.consoleit.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminRepository implements RepositoryInterface<Employer, Integer> {

    private Connection connection;

    public AdminRepository() {
        this.connection = DataSourceProvider.getCnx();
    }

    public Boolean verifierIdentifiant(String email, String mdp) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT email, mdp, admin from employe where email = ? and mdp = ?");
        ps.setString(1, email);
        ps.setString(2, mdp);
        boolean result = false;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getString("password").equals(mdp) && rs.getString("email").equals(email)) {
                result = true;
            }
        }
        ps.close();
        rs.close();
        return result;
    }

    @Override
    public ArrayList<Employer> getAll() throws SQLException {
        ArrayList<Employer> user = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id , nom , prenom , email , is_blocked FROM user WHERE roles = '[]'; ");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            Employer users = new Employer(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email"), resultSet.getBoolean("is_blocked") );
            user.add(users);
        }
        return user;
    }

    public void bloquerEmploye(int idEmploye) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employe SET is_blocked = true WHERE id = ?");
        preparedStatement.setInt(1, idEmploye);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void debloquerEmploye(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employe SET is_blocked = false WHERE id = ?");
        preparedStatement.setInt(1, idUser);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void supprimerEmploye(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employe SET email = CONCAT('anonymous', id, '@veliko.lan'), nom = 'anonymous', prenom = 'anonymous', adresse = 'anonymous', WHERE id = ?");
        preparedStatement.setInt(1, idUser);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void forcerMdpChange(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employe SET boolean_changer_mdp = 1 WHERE id = ?");
        preparedStatement.setInt(1, idUser);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}