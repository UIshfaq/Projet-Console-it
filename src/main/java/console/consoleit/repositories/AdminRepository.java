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
    private PreparedStatement ps;
    private ResultSet rs;

    public AdminRepository() {
        this.connection = DataSourceProvider.getCnx();
    }

    public ArrayList<Employer> getLesEmployes() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT Employe.id,Employe.nom,Employe.prenom FROM `Employe`");
        ResultSet rs = ps.executeQuery();
        ArrayList<Employer> employers = new ArrayList<>();
        while (rs.next()) {
            Employer employer = new Employer(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"));
            employers.add(employer);
        }
        ps.close();
        rs.close();
        return employers;
    }

}