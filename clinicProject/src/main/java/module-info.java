module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires json.simple;
    requires org.hibernate.orm.core;


    opens gui to javafx.fxml;
    exports gui;
}