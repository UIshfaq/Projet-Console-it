package console.consoleit.repositories;

import console.consoleit.model.Mission;
import console.consoleit.tools.DataSourceProvider;
import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;

public class EmployerRepository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public EmployerRepository(){
        this.connection = DataSourceProvider.getCnx();
    }

    public void CreeEmployer(String nom, String prenom, String email, String mdp, String tel, String ville, int CodePos, Date dateNaiss,String adresse) throws SQLException {

        String hashedPassword = BCrypt.hashpw(mdp, BCrypt.gensalt());

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Employe (nom, prenom, ville, codePostal, dateNais, telephone, mdp, email,adresse) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)")) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, ville);
            preparedStatement.setInt(4, CodePos);
            preparedStatement.setDate(5, dateNaiss);
            preparedStatement.setString(6, tel);
            preparedStatement.setString(7, hashedPassword);
            preparedStatement.setString(8, email);
            preparedStatement.setString(9, adresse);
            preparedStatement.executeUpdate();
        }
    }

    public static String genererMotDePasse() {
        String majuscules = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minuscules = "abcdefghijklmnopqrstuvwxyz";
        String chiffres = "0123456789";
        String caracteresSpeciaux = "!@#$%^&*()-_=+";
        String allCharacters = majuscules + minuscules + chiffres + caracteresSpeciaux;

        SecureRandom random = new SecureRandom();
        StringBuilder mdp = new StringBuilder();

        mdp.append(majuscules.charAt(random.nextInt(majuscules.length())));
        mdp.append(minuscules.charAt(random.nextInt(minuscules.length())));
        mdp.append(chiffres.charAt(random.nextInt(chiffres.length())));
        mdp.append(caracteresSpeciaux.charAt(random.nextInt(caracteresSpeciaux.length())));

        for (int i = 4; i < 12; i++) {
            mdp.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return mdp.toString();
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

    public int getEmployerId(String email) throws SQLException {
        int id = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM Employe WHERE email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        }
        return id;
    }

    public boolean estBloque(int idEmploye) throws SQLException {
        boolean isBlocked = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT is_blocked FROM Employe WHERE id = ?")) {
            preparedStatement.setInt(1, idEmploye);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isBlocked = resultSet.getBoolean("is_blocked");
            }
        }
        return isBlocked;
    }

    public boolean estSupprimer(int idEmploye) throws SQLException {
        boolean isSupprimer = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT is_supprimer FROM Employe WHERE id = ?")) {
            preparedStatement.setInt(1, idEmploye);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isSupprimer = resultSet.getBoolean("is_supprimer");
            }
        }
        return isSupprimer;
    }

    public boolean estChangeMdp(int idEmploye) throws SQLException {
        boolean changeMdp = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT changeMdp FROM Employe WHERE id = ?")) {
            preparedStatement.setInt(1, idEmploye);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                changeMdp = resultSet.getBoolean("changeMdp");
            }
        }
        return changeMdp;
    }
}