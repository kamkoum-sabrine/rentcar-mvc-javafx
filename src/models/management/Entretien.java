/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.management;

import models.Personnes.Technicien;
import models.vehicules.Vehicule;
import java.util.Date;
/**
 *
 * @author LENOVO
 */
public class Entretien {
    
    private int idEntretien;
    private java.sql.Date dateEntretien;
    private String typeEntretien;
    private double cout;
    private int kilometrage;
    private Technicien technicienEnCharge;
    private String notes;
    private Vehicule vehicule;
    private Garage garage; 

    public Entretien() {
    }

    public Entretien(int idEntretien, java.sql.Date dateEntretien, String typeEntretien, double cout, int kilometrage, Technicien technicienEnCharge, String notes, Vehicule vehicule, Garage garage) {
        this.idEntretien = idEntretien;
        this.dateEntretien = dateEntretien;
        this.typeEntretien = typeEntretien;
        this.cout = cout;
        this.kilometrage = kilometrage;
        this.technicienEnCharge = technicienEnCharge;
        this.notes = notes;
        this.vehicule = vehicule;
        this.garage = garage;
    }

    
    public int getIdEntretien() {
        return idEntretien;
    }

    public java.sql.Date getDateEntretien() {
        return dateEntretien;
    }

    public String getTypeEntretien() {
        return typeEntretien;
    }

    public double getCout() {
        return cout;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public Technicien getTechnicienEnCharge() {
        return technicienEnCharge;
    }

    public String getNotes() {
        return notes;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setIdEntretien(int idEntretien) {
        this.idEntretien = idEntretien;
    }

    public void setDateEntretien(java.sql.Date dateEntretien) {
        this.dateEntretien = dateEntretien;
    }

    public void setTypeEntretien(String typeEntretien) {
        this.typeEntretien = typeEntretien;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public void setTechnicienEnCharge(Technicien technicienEnCharge) {
        this.technicienEnCharge = technicienEnCharge;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    @Override
    public String toString() {
        return "Entretien{" + "idEntretien=" + idEntretien + ", dateEntretien=" + dateEntretien + ", typeEntretien=" + typeEntretien + ", cout=" + cout + ", kilometrage=" + kilometrage + ", technicienEnCharge=" + technicienEnCharge + ", notes=" + notes + ", vehicule=" + vehicule + ", garage=" + garage + '}';
    }
    
    
    
}
