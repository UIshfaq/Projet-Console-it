package console.consoleit.repositories;

import console.consoleit.model.Employer;
import console.consoleit.model.Mission;
import console.consoleit.tools.DataSourceProvider;
import console.consoleit.tools.HasherMdp;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminRepository {

    private Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public AdminRepository() {
        this.connection = DataSourceProvider.getCnx();
    }

    public Boolean verifierAdmin(String email, String mdpEntrer) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT mdp FROM employe WHERE email = ?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        boolean result = false;
        if (rs.next()) {
            String hashedPassword = rs.getString("mdp");

            // Ajout de logs pour debug
            System.out.println("Hash récupéré depuis la base : " + hashedPassword);
            System.out.println("Mot de passe entré : " + mdpEntrer);

            if (BCrypt.checkpw(mdpEntrer, hashedPassword)) { // Vérification correcte
                System.out.println("Mot de passe correct !");
                result = true;
            } else {
                System.out.println("Mot de passe incorrect !");
            }
        }

        rs.close();
        ps.close();
        return result;
    }

    public Boolean verifierEmployer(String email, String mdp) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT email, mdp, admin FROM employe WHERE email = ? AND mdp = ? AND admin = false");
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
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Employe SET is_blocked = true WHERE id = ?");
        preparedStatement.setInt(1, idEmploye);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void debloquerEmploye(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Employe SET is_blocked = false WHERE id = ?");
        preparedStatement.setInt(1, idUser);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void supprimerUser(int idUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Employe SET email = CONCAT('anonymous', id, '@veliko.lan'), nom = 'anonymous', prenom = 'anonymous', adresse = 'anonymous', is_supprimer = 1 WHERE id = ?");
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
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id , nom , prenom , email,is_blocked,changeMdp  FROM Employe WHERE admin = 0 and is_supprimer = 0;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employer employers = new Employer(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email"),resultSet.getBoolean("is_blocked"),resultSet.getBoolean("changeMdp"));
            employer.add(employers);
        }
        preparedStatement.close();
        resultSet.close();
        return employer;
    }

    public ArrayList<Mission> getMissionById(int employerId) throws SQLException {
        ArrayList<Mission> missions = new ArrayList<>();
        String query = "SELECT id, nomMission, matériel, site ,descriptionMission,benefice,cA FROM mission WHERE idEmploye = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, employerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                missions.add(new Mission(
                        rs.getInt("id"),
                        rs.getString("nomMission"),
                        rs.getString("matériel"),
                        rs.getString("site"),
                        rs.getString("descriptionMission"),
                        rs.getInt("benefice"),
                        rs.getInt("cA")
                ));
            }
        }
        return missions;
    }


}
