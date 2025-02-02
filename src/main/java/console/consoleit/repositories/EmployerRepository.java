package console.consoleit.repositories;

import console.consoleit.tools.DataSourceProvider;
import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;
import java.sql.*;

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
}