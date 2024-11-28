/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.vehicules;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import models.VehiculesException.CoutException;
import models.vehicules.Vehicule;

/**
 *
 * @author LENOVO
 */

public class Assurance {
    
    private int idAssurance;
    private String nomAssureur;
    private String typeAssurance; 
    private Date dateDebut;        
    private Date dateFin;
    private double coutAssurance; 

    public Assurance(int idAssurance, String nomAssureur, String typeAssurance, Date dateDebut, Date dateFin, double coutAssurance) {
        this.idAssurance = idAssurance;
        this.nomAssureur = nomAssureur;
        this.typeAssurance = typeAssurance;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.coutAssurance = coutAssurance;
    }

    public int getIdAssurance() {
        return idAssurance;
    }

    public String getNomAssureur() {
        return nomAssureur;
    }

    public String getTypeAssurance() {
        return typeAssurance;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public double getCoutAssurance() {
        return coutAssurance;
    }

    public void setIdAssurance(int idAssurance) {
        this.idAssurance = idAssurance;
    }

    public void setNomAssureur(String nomAssureur) {
        this.nomAssureur = nomAssureur;
    }

    public void setTypeAssurance(String typeAssurance) {
        this.typeAssurance = typeAssurance;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setCoutAssurance (double coutAssurance) throws CoutException {
        if(coutAssurance<0) { throw new CoutException();};
        this.coutAssurance = coutAssurance;
    }

    @Override
    public String toString() {
        return "Assurance{" + "idAssurance=" + idAssurance + ", nomAssureur=" + nomAssureur + ", typeAssurance=" + typeAssurance + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", coutAssurance=" + coutAssurance + "}";
    }

    public int dureeAssuranceJours(){
        LocalDate startDate = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Period period = Period.between(startDate, endDate);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        return period.getYears() * 365 + period.getMonths() * 30 + period.getDays();

    }

}
