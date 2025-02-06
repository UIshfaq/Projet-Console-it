package console.consoleit.service;

import console.consoleit.model.Mission;
import console.consoleit.repositories.EmployerRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class EmployerService {
    private EmployerRepository employerRepository;

    public EmployerService(){
        this.employerRepository = new EmployerRepository();
    }

    public void creeEmployer(String nom, String prenom, String email, String mdp, String tel, String ville, int CodePos, Date dateNaiss,String adresse) throws SQLException {
        employerRepository.CreeEmployer(nom, prenom, email, mdp, tel, ville, CodePos, (java.sql.Date) dateNaiss,adresse);
    }
    public String genererMotDePasse() {
        return EmployerRepository.genererMotDePasse();
    }

    public ArrayList<Mission> getMissionById(int id) throws SQLException {
        return employerRepository.getMissionById(id);
    }

    public boolean estBloque(int id) throws SQLException {
        return employerRepository.estBloque(id);
    }

    public boolean estSupprimer(int id) throws SQLException {
        return employerRepository.estSupprimer(id);
    }

    public boolean estChangeMdp(int id) throws SQLException {
        return employerRepository.estChangeMdp(id);
    }

}
