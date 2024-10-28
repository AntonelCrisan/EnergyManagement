package org.energy_management.energymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class AppRun extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Citirea fisierului .fxml din resources/org.energy_management.energymanagement
        FXMLLoader fxmlLoader = new FXMLLoader(AppRun.class.getResource("homePage.fxml"));
        //Crearea scenei de 1450px latime si 700px inaltime
        Scene scene = new Scene(fxmlLoader.load(), 1450, 700);
        //Citirea fisierului .css din resources/styles
        scene.getStylesheets().add(getClass().getResource("/styles/paginaPrincipala.css").toExternalForm());
        //Adaugarea logo-ului UTCN pentru aplicatie
        stage.getIcons().add(new Image(AppRun.class.getResourceAsStream("/img/utcn.png")));
        stage.setTitle("EnergyManagement"); //Titlul aplicatiei
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}