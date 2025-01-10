package console.consoleit.service;

import console.consoleit.model.Employer;
import console.consoleit.repositories.AdminRepository;
import console.consoleit.repositories.EmployerRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminService {
    private AdminRepository adminRepository;

    public AdminService(){
        this.adminRepository = new AdminRepository();
    }

    public Boolean verifierIdentifiant(String email, String password) throws SQLException {
        return adminRepository.verifierIdentifiants(email, password);
    }

    public ArrayList<Employer> getAll() throws SQLException {
        return adminRepository.getAll();
    }

    public void bloquerEmploye(int idUser) throws SQLException {
        adminRepository.bloquerEmploye(idUser);
    }

    public void debloquerEmploye(int idUser) throws SQLException {
        adminRepository.debloquerEmploye(idUser);
    }

    public void supprimerUser(int idUser) throws SQLException {
        adminRepository.supprimerUser(idUser);
    }

    public void forcerMdpChange(int idUser) throws SQLException {
        adminRepository.forcerMdpChange(idUser);
    }

    public ArrayList<Employer> getAllEmploye() throws SQLException {
        return adminRepository.getAllEmploye();
    }
}