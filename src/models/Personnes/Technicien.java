
package models.Personnes;

import models.management.Garage;


public class Technicien {
    private int idTechnicien;
    private String nom;
    private String specialite;
    private int experience;
    private int numeroTelephone;
    private Garage garage;

    public Technicien() {}

    public Technicien(int idTechnicien, String nom, String specialite, int experience, int numeroTelephone, Garage garage) {
        this.idTechnicien = idTechnicien;
        this.nom = nom;
        this.specialite = specialite;
        this.experience = experience;
        this.numeroTelephone = numeroTelephone;
        this.garage = garage;
    }

    public int getIdTechnicien() {
        return idTechnicien;
    }

    public String getNom() {
        return nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public int getExperience() {
        return experience;
    }

    public int getNumeroTelephone() {
        return numeroTelephone;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setIdTechnicien(int idTechnicien) {
        this.idTechnicien = idTechnicien;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setNumeroTelephone(int numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    @Override
    public String toString() {
        return "Technicien{" + "idTechnicien=" + idTechnicien + ", nom=" + nom + ", specialite=" + specialite + ", experience=" + experience + ", numeroTelephone=" + numeroTelephone + ", garage=" + garage + '}';
    }
    
    
    
    
}
