package console.consoleit.service;

import console.consoleit.model.Employer;
import console.consoleit.repositories.AdminRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminService {
    private AdminRepository adminRepository;

    public AdminService() {
        this.adminRepository = new AdminRepository();
    }

    public ArrayList<Employer> getLesEmployes() throws SQLException {
        return adminRepository.getLesEmployes();
    }
}
