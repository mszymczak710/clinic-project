module org.przychodnia.przychodnia {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens org.przychodnia.przychodnia to javafx.fxml;
    exports org.przychodnia.przychodnia;
    exports org.przychodnia.gui;
    opens org.przychodnia.gui to javafx.fxml;
}