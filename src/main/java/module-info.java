module console.consoleit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;
    requires jbcrypt;

    opens console.consoleit to javafx.fxml;

    exports console.consoleit;
    exports console.consoleit.model;
    opens console.consoleit.model to javafx.base, javafx.fxml;
}