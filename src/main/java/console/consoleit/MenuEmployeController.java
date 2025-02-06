package console.consoleit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuEmployeController {
    public Button btnMissions;
    @javafx.fxml.FXML
    private AnchorPane apMenuEmploye;
    @javafx.fxml.FXML
    private Button btnMissionsE;

    @javafx.fxml.FXML
    public void btnMissionsClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == btnMissions) {
            FXMLLoader fxmlLoader = new FXMLLoader(ConnectApplication.class.getResource("employe-tableauBord.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Administrateur");
            stage.setScene(scene);
            stage.show();

        }
    }
}