/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import models.Personnes.Adresse;
import models.Personnes.Technicien;
import models.vehicules.Vehicule;

/**
 *
 * @author LENOVO
 */
public class Garage {
     private int idGarage;
    private String nom;
    private Adresse adresse;
    private String numeroTelephone;
    private int capacite;
    private ArrayList<Technicien> techniciens;
    private ArrayList<Vehicule> vehiculesEntretien;
    private String horairesOuverture;
    private Set<String> servicesDisponibles;

    public Garage() {
    }

    public Garage(int idGarage, String nom, Adresse adresse, String numeroTelephone, int capacite,  String horairesOuverture, Set<String> servicesDisponibles) {
        this.idGarage = idGarage;
        this.nom = nom;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
        this.capacite = capacite;
        this.horairesOuverture = horairesOuverture;
        this.servicesDisponibles = servicesDisponibles;
        this.techniciens = new ArrayList<Technicien>();        
        this.vehiculesEntretien = new ArrayList<Vehicule>();

    }

    
    public int getIdGarage() {
        return idGarage;
    }

    public String getNom() {
        return nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public int getCapacite() {
        return capacite;
    }

    public ArrayList<Technicien> getTechniciens() {
        return techniciens;
    }

    public String getHorairesOuverture() {
        return horairesOuverture;
    }

    public Set<String> getServicesDisponibles() {
        return servicesDisponibles;
    }

    public void setIdGarage(int idGarage) {
        this.idGarage = idGarage;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setTechniciens(ArrayList<Technicien> techniciens) {
        this.techniciens = techniciens;
    }

    public void setHorairesOuverture(String horairesOuverture) {
        this.horairesOuverture = horairesOuverture;
    }

    public void setServicesDisponibles(Set<String> servicesDisponibles) {
        this.servicesDisponibles = servicesDisponibles;
    }

    public ArrayList<Vehicule> getVehiculesEntretien() {
        return vehiculesEntretien;
    }

    public void setVehiculesEntretien(ArrayList<Vehicule> vehiculesEntretien) {
        this.vehiculesEntretien = vehiculesEntretien;
    }

    @Override
    public String toString() {
        return "Garage{" + "idGarage=" + idGarage + ", nom=" + nom + ", adresse=" + adresse + ", numeroTelephone=" + numeroTelephone + ", capacite=" + capacite + ", techniciens=" + techniciens + ", vehiculesEntretien=" + vehiculesEntretien + ", horairesOuverture=" + horairesOuverture + ", servicesDisponibles=" + servicesDisponibles + '}';
    }
}
