package console.consoleit;

import console.consoleit.controllers.AdminController;
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

    @FXML


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx = new DataSourceProvider();
        adminController = new AdminController();


    }

    public void btnConnectClicked(MouseEvent mouseEvent) {
        if(txtEmail.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de connexion");
            alert.setContentText("Saisir votre email");
            alert.showAndWait();
        }

        else if (txtMdp.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de connexion");
            alert.setContentText("Saisir votre mot de passe");
            alert.showAndWait();
        }
        else
        {
            try {
                if (adminController.verifierIdentifiants(txtEmail.getText(), txtMdp.getText())) {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("inscription-view.fxml"));

                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Hello");
                    stage.setScene(scene);
                    stage.show();
                    ((Stage) btnConnect.getScene().getWindow()).close();
                    System.out.println("c'est bon");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Erreur de connexion");
                    alert.setContentText("Pseudo ou mot de passe incorrect");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

