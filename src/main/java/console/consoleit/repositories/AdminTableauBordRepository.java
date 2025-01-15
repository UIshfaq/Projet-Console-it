package console.consoleit.repositories;

import console.consoleit.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminTableauBordRepository {

    private Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    public AdminTableauBordRepository() {
        this.connection = DataSourceProvider.getCnx();
    }



}
