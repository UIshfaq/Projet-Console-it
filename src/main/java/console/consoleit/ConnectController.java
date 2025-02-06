package console.consoleit;

import console.consoleit.controllers.AdminController;
import console.consoleit.controllers.EmployerController;
import console.consoleit.model.Session;
import console.consoleit.tools.DataSourceProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectController implements Initializable {

    @FXML
    private ImageView omgLogo;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtMdp;
    @FXML
    private Button btnConnect;
    DataSourceProvider cnx;
    private AdminController adminController;
    EmployerController employerController;


    @Deprecated
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx = new DataSourceProvider();
        adminController = new AdminController();
        employerController = new EmployerController();
    }

    @FXML
    public void btnConnectClicked(MouseEvent mouseEvent) throws SQLException {
        if (txtEmail.getText().isEmpty()) {
            afficherAlerte("Erreur de connexion", "Saisir votre email");
        } else if (txtMdp.getText().isEmpty()) {
            afficherAlerte("Erreur de connexion", "Saisir votre mot de passe");
        } else {
            try {
                String email = txtEmail.getText().trim();
                String mdp = txtMdp.getText().trim();

                int role = adminController.verifierUtilisateur(email, mdp);
                if (employerController.estBloque(Session.getIdEmploye())) {
                    afficherAlerte("Erreur de connexion", "Votre compte est bloqué");
                } else if (employerController.estSupprimer(Session.getIdEmploye())) {
                    afficherAlerte("Erreur de connexion", "Votre compte a été supprimé");
                } else if (employerController.estChangeMdp(Session.getIdEmploye())) {
                    afficherAlerte("Erreur de connexion", "Vous devez changer votre mot de passe contacter un administrateur");
                } else if (role == 1) { // Admin
                    chargerNouvelleFenetre("menu-admin.fxml");
                    System.out.println("Connexion Admin réussie !");
                } else if (role == 0) { // Employé
                    int idEmploye = Session.getIdEmploye(); // Récupérer l'ID stocké
                    System.out.println("Connexion Employé réussie ! ID Employé : " + idEmploye);
                    chargerNouvelleFenetre("menu-employe.fxml");
                } else { // Identifiants incorrects
                    afficherAlerte("Erreur de connexion", "Pseudo ou mot de passe incorrect");
                }
            } catch (Exception e) {
                e.printStackTrace();
                afficherAlerte("Erreur", "Une erreur est survenue lors de la connexion.");
            }
        }
    }


    // Méthode pour charger une nouvelle fenêtre
    private void chargerNouvelleFenetre(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConnectApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Bienvenue");
        stage.setScene(scene);
        stage.show();
        ((Stage) btnConnect.getScene().getWindow()).close();
    }

    // Méthode pour afficher une alerte
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}