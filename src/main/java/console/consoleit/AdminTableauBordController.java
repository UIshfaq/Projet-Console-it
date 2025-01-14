package console.consoleit;

import console.consoleit.controllers.AdminController;
import console.consoleit.model.Employer;
import console.consoleit.tools.DataSourceProvider;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminTableauBordController implements Initializable {
    @FXML
    private TableColumn<Employer, Integer> tcIdEmploye;
    @FXML
    private TableColumn<Employer, String> tcNomEmploye;
    @FXML
    private TableColumn<Employer, String> tcPrenomEmploye;

    private AdminController adminController;
    DataSourceProvider macnx;
    @FXML
    private TableColumn tcMateriels;
    @FXML
    private TableColumn tcIdMission;
    @FXML
    private TableColumn tcSite;
    @FXML
    private TableColumn tcEmail;
    @FXML
    private TableColumn tcNomMission;
    @FXML
    private Button brnSupp;
    @FXML
    private Button btnCreer;
    @FXML
    private TextField txtMateriel;
    @FXML
    private Button btnModif;
    @FXML
    private TextField txtSite;
    @FXML
    private TextField txtNomMission;
    @FXML
    private TextField txtPrixMission;
    @FXML
    private TextField txtDescriptionMission;
    @FXML
    private TextField txtPrixIntervenant;
    @FXML
    private TableView tvMissionsEmployer;
    @FXML
    private TableView<Employer> tvAdminEmployer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        macnx = new DataSourceProvider();
        adminController = new AdminController();

        tcIdEmploye.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomEmploye.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcPrenomEmploye.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tcIdMission.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomMission.setCellValueFactory(new PropertyValueFactory<>("nomMission"));
        tcSite.setCellValueFactory(new PropertyValueFactory<>("site"));
        tcMateriels.setCellValueFactory(new PropertyValueFactory<>("materiel"));


        try {
            tvAdminEmployer.setItems(FXCollections.observableArrayList(adminController.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnModifCliked(Event event) {
    }

    @FXML
    public void btnCreerCliked(Event event) {
    }

    @FXML
    public void BtnSuppCliked(Event event) {
    }

    @FXML
    public void tvEmployeCliked(Event event) throws SQLException {
        int employerId = tvAdminEmployer.getSelectionModel().getSelectedItem().getId();
        tvMissionsEmployer.setItems(FXCollections.observableArrayList(adminController.getMissionById(employerId)));
    }
}