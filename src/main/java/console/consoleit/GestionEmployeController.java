package console.consoleit;

import console.consoleit.controllers.AdminController;
import console.consoleit.model.Employer;
import console.consoleit.tools.DataSourceProvider;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestionEmployeController implements Initializable {
    @javafx.fxml.FXML
    private TableColumn tcPrenomEmploye;
    @javafx.fxml.FXML
    private TableColumn tcIdEmploye;
    @javafx.fxml.FXML
    private TableColumn tcNomEmploye;
    @javafx.fxml.FXML
    private Button btnBloquer;
    @javafx.fxml.FXML
    private TableView<Employer> tvAdmin;
    @javafx.fxml.FXML
    private TableColumn tcEmail;
    @javafx.fxml.FXML
    private Button btnSupprimer;
    private AdminController adminController;
    DataSourceProvider macnx;
    @javafx.fxml.FXML
    private TableColumn tcBloque;
    @javafx.fxml.FXML
    private TableColumn tcChangerMdp;
    @javafx.fxml.FXML
    private AnchorPane apListeEmploye;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        macnx = new DataSourceProvider();
        adminController = new AdminController();

        tcIdEmploye.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomEmploye.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcPrenomEmploye.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcBloque.setCellValueFactory(new PropertyValueFactory<>("isBlocked"));
        tcChangerMdp.setCellValueFactory(new PropertyValueFactory<>("changeMdp"));
        tvAdmin.setEditable(true);
        try {
            tvAdmin.setItems(FXCollections.observableArrayList(adminController.getAllEmploye()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @javafx.fxml.FXML
    public void btnSupprimerCliked(Event event) throws SQLException {
        if (tvAdmin.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez selectionner un employe");
            alert.setContentText("Veuillez selectionner un employe");
            alert.showAndWait();
        } else {
            adminController.supprimerEmploye(tvAdmin.getSelectionModel().getSelectedItem().getId());
            tvAdmin.setItems(FXCollections.observableArrayList(adminController.getAllEmploye()));
        }

    }

    @javafx.fxml.FXML
    public void btnBloquerCliked(Event event) throws SQLException {
        if (tvAdmin.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez selectionner un employe");
            alert.setContentText("Veuillez selectionner un employe");
            alert.showAndWait();
        } else {
            if (tvAdmin.getSelectionModel().getSelectedItem().getIsBlocked()) {
                adminController.debloquerEmploye(tvAdmin.getSelectionModel().getSelectedItem().getId());
                tvAdmin.setItems(FXCollections.observableArrayList(adminController.getAllEmploye()));
            } else {
                adminController.bloquerEmploye(tvAdmin.getSelectionModel().getSelectedItem().getId());
                tvAdmin.setItems(FXCollections.observableArrayList(adminController.getAllEmploye()));
            }
        }
    }
}