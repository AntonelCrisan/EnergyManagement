module org.energy_management.energymanagement {
    requires javafx.fxml;
    requires java.sql;
    requires javafx.controls;
    requires j2mod;
    opens org.energy_management.energymanagement to javafx.fxml;
    exports org.energy_management.energymanagement;
}