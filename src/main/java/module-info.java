module org.energy_management.energymanagement {
    requires javafx.fxml;
    requires java.sql;
    requires javafx.controls;


    opens org.energy_management.energymanagement to javafx.fxml;
    exports org.energy_management.energymanagement;
}