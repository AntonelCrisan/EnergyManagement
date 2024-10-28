package org.energy_management.energymanagement;
import java.time.LocalDateTime;
//Creeam o clasa pentru putea lucra mai bine cu datele din baza de date pentru contactoare
public class Aparat {
    private double L1;
    private double L2;
    private double L3;
    private double I1;
    private double I2;
    private double I3;
    private double P_activa;
    private double P_reactiva;
    private double P_aparenta;
    private double Energie_activa;
    private double Energie_reactiva;
    private LocalDateTime data;
    // Constructor
    public Aparat( double L1, double L2, double L3, double I1, double I2, double I3,
                 double P_activa, double P_reactiva,
                  double P_aparenta, double Energie_activa, double Energie_reactiva, LocalDateTime data) {
        this.L1 = L1;
        this.L2 = L2;
        this.L3 = L3;
        this.I1 = I1;
        this.I2 = I2;
        this.I3 = I3;
        this.P_activa = P_activa;
        this.P_reactiva = P_reactiva;
        this.P_aparenta = P_aparenta;
        this.Energie_activa = Energie_activa;
        this.Energie_reactiva = Energie_reactiva;
        this.data = data;
    }

    // Getters
    public double getL1() {
        return L1;
    }

    public double getL2() {
        return L2;
    }

    public double getL3() {
        return L3;
    }

    public double getI1() {
        return I1;
    }

    public double getI2() {
        return I2;
    }

    public double getI3() {
        return I3;
    }
    public double getP_activa() {
        return P_activa;
    }

    public double getP_reactiva() {
        return P_reactiva;
    }

    public double getP_aparenta() {
        return P_aparenta;
    }

    public double getEnergie_activa() {
        return Energie_activa;
    }

    public double getEnergie_reactiva() {
        return Energie_reactiva;
    }

    public LocalDateTime getData() {
        return data;
    }
}
