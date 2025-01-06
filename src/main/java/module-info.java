module console.consoleit {
    requires javafx.controls;
    requires javafx.fxml;


    opens console.consoleit to javafx.fxml;
    exports console.consoleit;
}