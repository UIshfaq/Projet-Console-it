package console.consoleit;

import console.consoleit.controllers.AdminController;
import console.consoleit.controllers.MissionController;
import console.consoleit.model.Mission;
import console.consoleit.model.Session;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.events.Event;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeTableauBordController implements Initializable {
    @javafx.fxml.FXML
    private TableColumn tcMateriels;
    @javafx.fxml.FXML
    private AnchorPane apMissionsAdmin;
    @javafx.fxml.FXML
    private TableView <Mission>tvMissionsEmployer;
    @javafx.fxml.FXML
    private TableColumn tcPrixIntervenant;
    @javafx.fxml.FXML
    private TableColumn tcIdMission;
    @javafx.fxml.FXML
    private TableColumn tcSite;
    @javafx.fxml.FXML
    private TableColumn tcNomMission;
    @javafx.fxml.FXML
    private TableColumn tcDescription;
    @javafx.fxml.FXML
    private TableColumn tcMissionTermine;
    @javafx.fxml.FXML
    private TableColumn tcPrixMission;
    AdminController adminController;
    MissionController missionController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int idEmploye = Session.getIdEmploye(); // Récupère l'ID stocké
        adminController = new AdminController();
        missionController = new MissionController();
        tcIdMission.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomMission.setCellValueFactory(new PropertyValueFactory<>("nomMission"));
        tcSite.setCellValueFactory(new PropertyValueFactory<>("site"));
        tcMateriels.setCellValueFactory(new PropertyValueFactory<>("materiel"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcPrixMission.setCellValueFactory(new PropertyValueFactory<>("benefice"));
        tcPrixIntervenant.setCellValueFactory(new PropertyValueFactory<>("cA"));
        tcMissionTermine.setCellValueFactory(new PropertyValueFactory<>("missionTermine"));

        try {
            tvMissionsEmployer.setItems(FXCollections.observableArrayList(adminController.getMissionById(idEmploye)));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




    public void missionTermineClicked(MouseEvent mouseEvent) throws SQLException {
        Mission missionSelectione = tvMissionsEmployer.getSelectionModel().getSelectedItem();
        Boolean etatMission = missionSelectione.getMissionTermine();
        if (etatMission) {
            missionController.modifierMissionPasFini(missionSelectione.getId());
        } else {
            missionController.modifierMissionFini(missionSelectione.getId());
        }

        // Refresh the table with updated data
        tvMissionsEmployer.setItems(FXCollections.observableArrayList(adminController.getMissionById(Session.getIdEmploye())));
    }
}

