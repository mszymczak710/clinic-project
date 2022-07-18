module org.clinic.clinic {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.persistence;
    requires java.sql;
    requires jakarta.persistence;

    exports org.clinic.server;
    opens org.clinic.server to javafx.fxml;

    exports org.clinic.gui;
    opens org.clinic.gui to javafx.fxml;
    exports org.clinic.database.functions;
    opens org.clinic.database.functions to javafx.fxml;
    exports org.clinic.database.tables;
    opens org.clinic.database.tables to javafx.fxml;
}