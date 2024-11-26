package org.energy_management.energymanagement;
//Creeam o clasa pentru putea lucra mai bine cu datele din baza de date pentru a putea fi afisate in aplicatie
public class Monitorizare {
    private float consumSalaSport;
    private float umiditateSalaSport;
    private float consumCorpA;
    private float umiditateCorpA;
    private float consumCorpB;
    private float umiditateCorpB;
    private float consumAula1;
    private float umiditateAula1;
    private float consumAula2;
    private float umiditateAula2;
    private float temperaturaExterioara;
    private float umiditateExterioara;
//Constructor
    public Monitorizare(float consumSalaSport, float umiditateSalaSport, float consumCorpA, float umiditateCorpA, float consumCorpB, float umiditateCorpB, float consumAula1, float umiditateAula1, float consumAula2, float umiditateAula2, float temperaturaExterioara, float umiditateExterioara) {
        this.consumSalaSport = consumSalaSport;
        this.umiditateSalaSport = umiditateSalaSport;
        this.consumCorpA = consumCorpA;
        this.umiditateCorpA = umiditateCorpA;
        this.consumCorpB = consumCorpB;
        this.umiditateCorpB = umiditateCorpB;
        this.consumAula1 = consumAula1;
        this.umiditateAula1 = umiditateAula1;
        this.consumAula2 = consumAula2;
        this.umiditateAula2 = umiditateAula2;
        this.temperaturaExterioara = temperaturaExterioara;
        this.umiditateExterioara = umiditateExterioara;
    }
//Gettere si Settere
    public float getConsumSalaSport() {
        return consumSalaSport;
    }

    public void setConsumSalaSport(float consumSalaSport) {
        this.consumSalaSport = consumSalaSport;
    }

    public float getUmiditateSalaSport() {
        return umiditateSalaSport;
    }

    public void setUmiditateSalaSport(float umiditateSalaSport) {
        this.umiditateSalaSport = umiditateSalaSport;
    }

    public float getConsumCorpA() {
        return consumCorpA;
    }

    public void setConsumCorpA(float consumCorpA) {
        this.consumCorpA = consumCorpA;
    }

    public float getUmiditateCorpA() {
        return umiditateCorpA;
    }

    public void setUmiditateCorpA(float umiditateCorpA) {
        this.umiditateCorpA = umiditateCorpA;
    }

    public float getConsumCorpB() {
        return consumCorpB;
    }

    public void setConsumCorpB(float consumCorpB) {
        this.consumCorpB = consumCorpB;
    }

    public float getUmiditateCorpB() {
        return umiditateCorpB;
    }

    public void setUmiditateCorpB(float umiditateCorpB) {
        this.umiditateCorpB = umiditateCorpB;
    }

    public float getConsumAula1() {
        return consumAula1;
    }

    public void setConsumAula1(float consumAula1) {
        this.consumAula1 = consumAula1;
    }

    public float getUmiditateAula1() {
        return umiditateAula1;
    }

    public void setUmiditateAula1(float umiditateAula1) {
        this.umiditateAula1 = umiditateAula1;
    }

    public float getConsumAula2() {
        return consumAula2;
    }

    public void setConsumAula2(float consumAula2) {
        this.consumAula2 = consumAula2;
    }

    public float getUmiditateAula2() {
        return umiditateAula2;
    }

    public void setUmiditateAula2(float umiditateAula2) {
        this.umiditateAula2 = umiditateAula2;
    }

    public float getTemperaturaExterioara() {
        return temperaturaExterioara;
    }

    public void setTemperaturaExterioara(float temperaturaExterioara) {
        this.temperaturaExterioara = temperaturaExterioara;
    }

    public float getUmiditateExterioara() {
        return umiditateExterioara;
    }

    public void setUmiditateExterioara(float umiditateExterioara) {
        this.umiditateExterioara = umiditateExterioara;
    }
}
