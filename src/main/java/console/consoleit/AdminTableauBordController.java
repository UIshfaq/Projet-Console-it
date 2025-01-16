package console.consoleit;

import console.consoleit.controllers.AdminController;
import console.consoleit.controllers.MissionController;
import console.consoleit.model.Employer;
import console.consoleit.model.Mission;
import console.consoleit.tools.DataSourceProvider;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private TableView<Mission> tvMissionsEmployer;
    @FXML
    private TableView<Employer> tvAdminEmployer;
    @FXML
    private TableColumn tcDescription;
    MissionController missionController;
    @FXML
    private TableColumn tcPrixIntervenant;
    @FXML
    private TableColumn tcPrixMission;


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
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcPrixMission.setCellValueFactory(new PropertyValueFactory<>("benefice"));
        tcPrixIntervenant.setCellValueFactory(new PropertyValueFactory<>("cA"));


        try {
            tvAdminEmployer.setItems(FXCollections.observableArrayList(adminController.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnModifCliked(Event event) throws SQLException {
        Employer tvAdminSelectione = tvAdminEmployer.getSelectionModel().getSelectedItem();
        Mission missionSelectione = tvMissionsEmployer.getSelectionModel().getSelectedItem();
        if (tvAdminSelectione == null || missionSelectione == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez selectionner un employe et une mission");
            alert.showAndWait();
        }
        if (txtNomMission.getText().isEmpty() || txtMateriel.getText().isEmpty() || txtSite.getText().isEmpty() || txtDescriptionMission.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else {
            int idMission = tvMissionsEmployer.getSelectionModel().getSelectedItem().getId();
            String nomMission = txtNomMission.getText();
            String materiel = txtMateriel.getText();
            String site = txtSite.getText();
            String description = txtDescriptionMission.getText();
            int prixMission = Integer.parseInt(txtPrixMission.getText());
            int prixIntervenant = Integer.parseInt(txtPrixIntervenant.getText());
            if (missionController == null) {
                missionController = new MissionController();
            }
            missionController.ModifierMission(idMission, nomMission, materiel, site, description, prixMission, prixIntervenant);
            tvMissionsEmployer.setItems(FXCollections.observableArrayList(adminController.getMissionById(tvAdminSelectione.getId())));
        }
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