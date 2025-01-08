package console.consoleit;

import console.consoleit.controllers.AdminController;
import console.consoleit.model.Employer;
import console.consoleit.tools.DataSourceProvider;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminTableauBordController implements Initializable {
    @FXML
    private TableView<Employer> tvAdmin;
    @FXML
    private TableColumn<Employer, Integer> tcIdEmploye;
    @FXML
    private TableColumn<Employer, String> tcNomEmploye;
    @FXML
    private TableColumn<Employer, String> tcPrenomEmploye;

    private AdminController adminController;
    DataSourceProvider macnx;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        macnx = new DataSourceProvider();
        adminController = new AdminController();

        tcIdEmploye.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomEmploye.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcPrenomEmploye.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        try {

            tvAdmin.setItems(FXCollections.observableArrayList(adminController.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}