package org.energy_management.energymanagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.*;
public class PaginaPrincipala implements Initializable {
    @FXML
    private Button raportConsumBtn;
    @FXML
    private VBox corpuriPanel, homePanel;
    @FXML
    private Label titluCorpuri;
    @FXML
    private Button lastSelectedButton;
    @FXML
    private TableView<Aparat> tabelView;
    @FXML
    public TableColumn<Aparat, String> L1, L2, L3, I1, I2, I3, p_activa, p_reactiva, p_aparenta, energie_activa, energie_reactiva, data;
    @FXML
    private Pagination paginare;
    @FXML
    private MenuButton optiuniSala;
    @FXML
    private Button stergeFiltrari;
    private  MenuItem selectedMenuItem;
    private final int randuriPerPagina = 50;
    private ObservableList<String> optiuniSelectate = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleTables();
        lastSelectedButton = raportConsumBtn;
        setButtonStyle(lastSelectedButton, true);
        stergeFiltrari.setVisible(false);
    }
    public void handleTables(){
        //Setarea coloanelor cu nume
        L1.setText("L1");
        L2.setText("L2");
        L3.setText("L3");
        I1.setText("I1");
        I2.setText("I2");
        I3.setText("I3");
        p_activa.setText("P Activa");
        p_reactiva.setText("P Reactiva");
        p_aparenta.setText("P Aparente");
        energie_activa.setText("Energie Activa");
        energie_reactiva.setText("Energie Reactiva");
        data.setText("Data");
        L1.setCellValueFactory(new PropertyValueFactory<>("L1"));
        L2.setCellValueFactory(new PropertyValueFactory<>("L2"));
        L3.setCellValueFactory(new PropertyValueFactory<>("L3"));
        I1.setCellValueFactory(new PropertyValueFactory<>("I1"));
        I2.setCellValueFactory(new PropertyValueFactory<>("I2"));
        I3.setCellValueFactory(new PropertyValueFactory<>("I3"));
        p_activa.setCellValueFactory(new PropertyValueFactory<>("P_activa"));
        p_reactiva.setCellValueFactory(new PropertyValueFactory<>("P_reactiva"));
        p_aparenta.setCellValueFactory(new PropertyValueFactory<>("P_aparenta"));
        energie_activa.setCellValueFactory(new PropertyValueFactory<>("Energie_activa"));
        energie_reactiva.setCellValueFactory(new PropertyValueFactory<>("Energie_reactiva"));
        data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        //Cream un vector cu optiuni pentru filtrarea tabelului
        String[] optiuni = {
                "Incarcare faze", "Tensiune faze", "Diagrama",
                "Putere activa", "Putere reactiva", "Putere aparenta",
                "Energie activa", "Energie reactiva", "Energie aparenta", "cos"
        };
        //Cream o bucla for pentru parcurgerea vectorului, iar in functie de optiunea selectata de utilizator, adauga sau stergem optiunile din vector
        for (String optiune : optiuni) {
            MenuItem menuItem = new MenuItem(optiune);
            menuItem.setOnAction(e -> {
                if (optiuniSelectate.contains(optiune)) {
                    optiuniSelectate.remove(optiune);
                    menuItem.setStyle("");
                } else {
                    optiuniSelectate.add(optiune);
                    menuItem.setStyle( "-fx-background-color: #d1cfcf; -fx-text-fill: #ffffff;");
                    stergeFiltrari.setVisible(true);
                }
                actualizeazaColoane(); //Actualizam coloanele
            });
            optiuniSala.getItems().add(menuItem);
        }
        // Configurarea butonului pentru resetarea filtrului
        stergeFiltrari.setOnAction(e -> {
            optiuniSelectate.clear(); // Reseteaza optiunile selectate
            for (MenuItem menuItem : optiuniSala.getItems()) {
                menuItem.setStyle(""); // Reseteaza stilul pentru toate optiunile
            }
            afiseazaToateColoanele(); // Afișează toate coloanele
            stergeFiltrari.setVisible(false); // Ascunde butonul
        });
    }
    //Metoda pentru afisarea tuturor coloanelor
    private void afiseazaToateColoanele() {
        L1.setVisible(true);
        L2.setVisible(true);
        L3.setVisible(true);
        I1.setVisible(true);
        I2.setVisible(true);
        I3.setVisible(true);
        p_activa.setVisible(true);
        p_reactiva.setVisible(true);
        p_aparenta.setVisible(true);
        energie_activa.setVisible(true);
        energie_reactiva.setVisible(true);
    }
    //Metoda pentru ascunderea coloanelor
    private void ascundeToateColoanele() {
        L1.setVisible(false);
        L2.setVisible(false);
        L3.setVisible(false);
        I1.setVisible(false);
        I2.setVisible(false);
        I3.setVisible(false);
        p_activa.setVisible(false);
        p_reactiva.setVisible(false);
        p_aparenta.setVisible(false);
        energie_activa.setVisible(false);
        energie_reactiva.setVisible(false);
    }
    //Metoda pentru actualizarea coloanelor
    private void actualizeazaColoane() {
        //Ascunde toate coloanele la inceput
        ascundeToateColoanele();

        //Afiseaza coloanele in functie de optiunile selectate
        for (String optiune : optiuniSelectate) {
            switch (optiune) {
                case "Incarcare faze":
                    L1.setVisible(true);
                    L2.setVisible(true);
                    L3.setVisible(true);
                    break;
                case "Tensiune faze":
                    I1.setVisible(true);
                    I2.setVisible(true);
                    I3.setVisible(true);
                    break;
                case "Putere activa":
                    p_activa.setVisible(true);
                    break;
                case "Putere reactiva":
                    p_reactiva.setVisible(true);
                    break;
                case "Putere aparenta":
                    p_aparenta.setVisible(true);
                    break;
                case "Energie activa":
                    energie_activa.setVisible(true);
                    break;
                case "Energie reactiva":
                    energie_reactiva.setVisible(true);
                    break;
                case "cos":
                    break;
                default:
                    break;
            }
        }
    }
    //Metoda pentru crearea paginarii pentru tabel
    private TableView<Aparat> createPage(int pageIndex, String tabel) {
        //Calculam offset-ul pentru pagina curenta
        int offset = pageIndex * randuriPerPagina;
        //Aducem numarul de inregistrari din baza de date
        int numarPagini = new DataFetcher().countRows(tabel);
        //Calculam cate pagini avem nevoie sa aiba paginarea
        int totalPages = (int) Math.ceil((double) numarPagini / randuriPerPagina);
        paginare.setPageCount(totalPages); //Setarea paginilor
        //Obtinem datele pentru pagina curenta din baza de date in functie de tabel, limita randurilor si offset-ul
        ObservableList<Aparat> pageData = DataFetcher.getData(tabel, randuriPerPagina, offset);
        //Setam datele in tabel
        tabelView.setItems(pageData);
        return tabelView;
    }
    public void handleButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String clickedButtonId = clickedButton.getId();
        //Afisam paginile in functie de butonul pe care este apasat
        if (clickedButtonId.equals("raportConsumBtn")) {
            setPanelVisibility(homePanel, corpuriPanel);
        } else if (clickedButtonId.equals("salaSportBtn")) {
            setPanelVisibility(corpuriPanel, homePanel);
            titluCorpuri.setText("SALA SPORT");
            paginare.setPageFactory(pageIndex -> this.createPage(pageIndex, "sala_sport"));
        } else if (clickedButtonId.equals("corpABtn")) {
            setPanelVisibility(corpuriPanel, homePanel);
            titluCorpuri.setText("CORP A");
            paginare.setPageFactory(pageIndex -> this.createPage(pageIndex, "corp_a"));
        } else if (clickedButtonId.equals("corpBBtn")) {
            setPanelVisibility(corpuriPanel, homePanel);
            titluCorpuri.setText("CORP B");
            paginare.setPageFactory(pageIndex -> this.createPage(pageIndex, "corp_b"));
        } else if (clickedButtonId.equals("aula1Btn")) {
            setPanelVisibility(corpuriPanel, homePanel);
            titluCorpuri.setText("AULA 1");
            paginare.setPageFactory(pageIndex -> this.createPage(pageIndex, "aula_1"));
        } else if (clickedButtonId.equals("aula2Btn")) {
            setPanelVisibility(corpuriPanel, homePanel);
            titluCorpuri.setText("AULA 2");
            paginare.setPageFactory(pageIndex -> this.createPage(pageIndex, "aula_2"));
        }
        //Resetam srtyle-ul pentru butonul anterior
        setButtonStyle(lastSelectedButton, false);
        //Setam styel-ul pentru butonul curent
        setButtonStyle(clickedButton, true);
        //Actualizam ultimul buton selectat
        lastSelectedButton = clickedButton;
    }
    private void setButtonStyle(Button button, boolean isSelected) {
        if (isSelected) {
            button.setStyle("-fx-background-color: #A8261C;");
        } else {
            button.setStyle("-fx-background-color: transparent;");
        }
    }
    private void setPanelVisibility(VBox visiblePanel, VBox hiddenPanel) {
        visiblePanel.setVisible(true);
        hiddenPanel.setVisible(false);
    }
}