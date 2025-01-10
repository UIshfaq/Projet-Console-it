package console.consoleit;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionEmploye implements Initializable {
    @javafx.fxml.FXML
    private TableColumn tcPrenomEmploye;
    @javafx.fxml.FXML
    private TableColumn tcIdEmploye;
    @javafx.fxml.FXML
    private TableColumn tcNomEmploye;
    @javafx.fxml.FXML
    private Button btnBloquer;
    @javafx.fxml.FXML
    private TableView tvAdmin;
    @javafx.fxml.FXML
    private TableColumn tcEmail;
    @javafx.fxml.FXML
    private Button btnSupprimer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @javafx.fxml.FXML
    public void btnSupprimerCliked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnBloquerCliked(Event event) {
    }
}
