package console.consoleit.controllers;

import console.consoleit.model.Employer;
import console.consoleit.service.AdminService;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminController {
    private AdminService adminService;

    public AdminController() {
        this.adminService = new AdminService();
    }

    public ArrayList<Employer> getLesEmployes() throws SQLException {
        return adminService.getLesEmployes();
    }
}
