package console.consoleit.repositories;

import console.consoleit.model.Employer;
import console.consoleit.model.Mission;
import console.consoleit.model.Session;
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

    public int verifierUtilisateur(String email, String mdpEntrer) throws SQLException {
        String query = "SELECT id, mdp, admin FROM employe WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("mdp");
                    boolean isAdmin = rs.getBoolean("admin");
                    int idEmploye = rs.getInt("id");

                    if (BCrypt.checkpw(mdpEntrer, hashedPassword)) {
                        if (isAdmin) {
                            return 1; // Admin
                        } else {
                            Session.setIdEmploye(idEmploye); // Stocker l'ID
                            return 0; // Employé
                        }
                    }
                }
            }
        }
        return -1; // Mauvais identifiants
    }




    public ArrayList<Employer> getAll() throws SQLException {
        ArrayList<Employer> employer = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id , nom , prenom , email  FROM Employe WHERE admin = 0 and is_supprimer = 0;");
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
        String query = "SELECT id, nomMission, matériel, site ,descriptionMission,benefice,cA,missionTermine FROM mission WHERE idEmploye = ?";
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
                        rs.getInt("cA"),
                        rs.getBoolean("missionTermine")
                ));
            }
        }
        return missions;
    }

}
