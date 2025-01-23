package console.consoleit.repositories;

import console.consoleit.tools.DataSourceProvider;

import java.sql.*;

public class EmployerRepository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public EmployerRepository(){
        this.connection = DataSourceProvider.getCnx();
    }

    public void CreeEmployer(String nom, String prenom, String email, String mdp, String tel, String ville, int CodePos, Date dateNaiss,String adresse) throws SQLException {
        String sql = "INSERT INTO Employe (nom, prenom, ville, codePostal, dateNais, telephone, mdp, email,adresse) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, ville);
            preparedStatement.setInt(4, CodePos);
            preparedStatement.setDate(5, dateNaiss);
            preparedStatement.setString(6, tel);
            preparedStatement.setString(7, mdp);
            preparedStatement.setString(8, email);
            preparedStatement.setString(9, adresse);
            preparedStatement.executeUpdate();
        }


    }





}
