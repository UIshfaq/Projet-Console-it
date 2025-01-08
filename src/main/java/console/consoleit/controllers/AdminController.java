package console.consoleit.controllers;

import console.consoleit.model.Employer;
import console.consoleit.service.AdminService;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminController {

    AdminService adminService;

    public AdminController(){
        this.adminService = new AdminService();
    }

    public Boolean verifierIdentifiants(String email, String enteredPassword) throws SQLException {
        return adminService.verifierIdentifiant(email, enteredPassword);
    }

    public ArrayList<Employer> getAll() throws SQLException {
        return adminService.getAll();
    }

    public void bloquerEmploye(int idUser) throws SQLException {
        adminService.bloquerEmploye(idUser);
    }

    public void debloquerEmploye(int idUser) throws SQLException {
        adminService.debloquerEmploye(idUser);
    }

    public void supprimerEmploye(int idUser) throws SQLException {
        adminService.supprimerUser(idUser);
    }

    public void forcerMdpChange(int idUser) throws SQLException {
        adminService.forcerMdpChange(idUser);
    }
}