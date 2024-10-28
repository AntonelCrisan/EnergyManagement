package org.energy_management.energymanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//Clasa pentru aducerea datelor din baza de date
public class DataFetcher {
    //Metoda de tip ObservableList pentru a aduce date din baza de date
    public static ObservableList<Aparat> getData(String tabel, int limit, int offset) {
        ObservableList<Aparat> valori = FXCollections.observableArrayList(); //Variabila in care stocam valorile din baza de date
        try {
            //Se incearca conectarea la baza de date, executarea unui query pentru returnarea datelor din tabelul luat ca si paramentru din metoda
            Connection conn = ConectareBazaDate.getConnection();
            String query = "SELECT L1, L2, L3, I1, I2, I3, P_activa, P_reactiva, P_aparenta, Energie_activa, Energie_reactiva, Data FROM " + tabel  + " LIMIT " + limit + " OFFSET " + offset;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //Bucla while pentru atribuirea datelor din baza de date
            while (rs.next()) {
                double L1 = rs.getDouble("L1");
                double L2 = rs.getDouble("L2");
                double L3 = rs.getDouble("L3");
                double I1 = rs.getDouble("I1");
                double I2 = rs.getDouble("I2");
                double I3 = rs.getDouble("I3");
                double P_activa = rs.getDouble("P_activa");
                double P_reactiva = rs.getDouble("P_reactiva");
                double P_aparenta = rs.getDouble("P_aparenta");
                double Energie_activa = rs.getDouble("Energie_activa");
                double Energie_reactiva = rs.getDouble("Energie_reactiva");
                // Parsing 'Data' as LocalDateTime
                String dataString = rs.getString("Data");
                LocalDateTime data = LocalDateTime.parse(dataString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                // Adaugăm obiectul Aparat în lista de valori
                valori.add(new Aparat( L1, L2, L3, I1, I2, I3, P_activa, P_reactiva, P_aparenta, Energie_activa, Energie_reactiva, data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valori;
    }
    //Metoda pentru numararea randurilor din coloana pentru afisarea corecta a paginilor din tabel in aplicatie
    public static int countRows(String tabel){
        //Initializam o variabila cu 0, rulam queriul pentru obtinerea numarului total de valorin din baza de date si atribuim valoarea variabile
        int numberRow = 0;
        try {
            Connection conn = ConectareBazaDate.getConnection();
            String query = "SELECT count(*) FROM " + tabel;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                numberRow = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberRow;
    }
}
