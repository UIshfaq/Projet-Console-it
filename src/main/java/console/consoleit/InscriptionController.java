package console.consoleit;

import console.consoleit.controllers.EmployerController;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InscriptionController implements Initializable {
    @javafx.fxml.FXML
    private TextField txtVille;
    @javafx.fxml.FXML
    private TextField txtPrenom;
    @javafx.fxml.FXML
    private TextField txtCodePos;
    @javafx.fxml.FXML
    private TextField txtTel;
    @javafx.fxml.FXML
    private TextField txtMdp;
    @javafx.fxml.FXML
    private TextField txtAdresse;
    @javafx.fxml.FXML
    private Button btnValider;
    @javafx.fxml.FXML
    private TextField txtNom;
    @javafx.fxml.FXML
    private DatePicker txtDateNaiss;
    EmployerController employerController;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private Button btnGénérerMdp;
    @javafx.fxml.FXML
    private AnchorPane apNouveuEmploye;

    @javafx.fxml.FXML
    public void btnValiderCliked(Event event) {
        if (txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtAdresse.getText().isEmpty() ||
                txtCodePos.getText().isEmpty() || txtVille.getText().isEmpty() || txtTel.getText().isEmpty() ||
                txtMdp.getText().isEmpty() || txtDateNaiss.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'inscription");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else if (!txtEmail.getText().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'inscription");
            alert.setContentText("Email invalide");
            alert.showAndWait();
        }
        else if (txtCodePos.getText().length() != 5 || !txtCodePos.getText().matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'inscription");
            alert.setContentText("Code postal invalide");
            alert.showAndWait();
        } else if (txtTel.getText().length() != 10 || !txtTel.getText().matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'inscription");
            alert.setContentText("Numéro de téléphone invalide");
            alert.showAndWait();
        } else {
            try {
                employerController.creeEmployer(
                        txtNom.getText(),
                        txtPrenom.getText(),
                        txtEmail.getText(),  
                        txtMdp.getText(),
                        txtTel.getText(),
                        txtVille.getText(),
                        Integer.parseInt(txtCodePos.getText()),
                        java.sql.Date.valueOf(txtDateNaiss.getValue()),
                        txtAdresse.getText()
                );


            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de l'inscription");
                alert.setContentText("Une erreur s'est produite : " + e.getMessage());
                alert.showAndWait();
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employerController = new EmployerController();
    }

    @javafx.fxml.FXML
    public void btnGenererCliked(Event event) {
        txtMdp.setText(employerController.genererMotDePasse());
    }
}
