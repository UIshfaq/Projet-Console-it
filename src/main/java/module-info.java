module console.consoleit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;
    requires jbcrypt;

    opens console.consoleit to javafx.fxml;
    opens console.consoleit.model to javafx.base;

    exports console.consoleit;
}