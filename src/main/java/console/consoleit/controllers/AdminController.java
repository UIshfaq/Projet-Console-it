package console.consoleit.controllers;

import console.consoleit.model.Employer;
import console.consoleit.model.Mission;
import console.consoleit.service.AdminService;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminController {

    AdminService adminService;

    public AdminController(){
        this.adminService = new AdminService();
    }

    public int verifierUtilisateur(String email, String mdpEntrer) throws SQLException {
        return adminService.verifierUtilisateur(email, mdpEntrer);
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

    public ArrayList<Employer> getAllEmploye() throws SQLException {
        return adminService.getAllEmploye();
    }

    public ArrayList<Mission> getMissionById(int id) throws SQLException {
        return adminService.getMissionById(id);
    }
}