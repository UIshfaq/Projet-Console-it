module console.consoleit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens console.consoleit to javafx.fxml;
    exports console.consoleit;
}