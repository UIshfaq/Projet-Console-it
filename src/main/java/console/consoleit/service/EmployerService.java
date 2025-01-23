package console.consoleit.service;

import console.consoleit.repositories.EmployerRepository;

import java.sql.SQLException;
import java.util.Date;

public class EmployerService {
    private EmployerRepository employerRepository;

    public EmployerService(){
        this.employerRepository = new EmployerRepository();
    }

    public void creeEmployer(String nom, String prenom, String email, String mdp, String tel, String ville, int CodePos, Date dateNaiss,String adresse) throws SQLException {
        employerRepository.CreeEmployer(nom, prenom, email, mdp, tel, ville, CodePos, (java.sql.Date) dateNaiss,adresse);
    }


}
