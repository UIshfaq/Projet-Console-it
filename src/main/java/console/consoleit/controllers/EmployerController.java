package console.consoleit.controllers;

import console.consoleit.model.Mission;
import console.consoleit.service.EmployerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class EmployerController {
    private EmployerService employerService;

    public EmployerController(){
        this.employerService = new EmployerService();
    }

    public void creeEmployer(String nom, String prenom, String email, String mdp, String tel, String ville, int CodePos, Date dateNaiss,String adresse) throws SQLException, SQLException {
        employerService.creeEmployer(nom, prenom, email, mdp, tel, ville, CodePos, dateNaiss,adresse);
    }

    public String genererMotDePasse() {
        return employerService.genererMotDePasse();
    }

    public ArrayList<Mission> getMissionById(int id) throws SQLException {
        return employerService.getMissionById(id);
    }

    public boolean estBloque(int id) throws SQLException {
        return employerService.estBloque(id);
    }

    public boolean estSupprimer(int id) throws SQLException {
        return employerService.estSupprimer(id);
    }

    public boolean estChangeMdp(int id) throws SQLException {
        return employerService.estChangeMdp(id);
    }
}
