package console.consoleit;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @javafx.fxml.FXML
    private PieChart pieChart;
    @javafx.fxml.FXML
    private AnchorPane apMenuAdmin;
    @javafx.fxml.FXML
    private Button btnAttribution;
    @javafx.fxml.FXML
    private Button btnEmploye;




    @javafx.fxml.FXML
    public void btnClickeddd(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == btnEmploye) {
            FXMLLoader fxmlLoader = new FXMLLoader(ConnectApplication.class.getResource("gestion-Employe.fxml"));
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
        if (mouseEvent.getSource() == btnAttribution) {
            FXMLLoader fxmlLoader = new FXMLLoader(ConnectApplication.class.getResource("admin-tableauBord.fxml"));
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
        /*if (mouseEvent.getSource() == btnMissions) {
            FXMLLoader fxmlLoader = new FXMLLoader(ConnectApplication.class.getResource(""));
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

        }*/
    }



}
