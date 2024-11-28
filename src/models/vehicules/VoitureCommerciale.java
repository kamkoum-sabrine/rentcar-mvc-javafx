/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.vehicules;

import java.util.Date;
import models.vehicules.Assurance;

/**
 *
 * @author LENOVO
 */
public class VoitureCommerciale extends Vehicule{
     private int capaciteCharge;//En kg
    private Boolean toitOuvrant;
    private Boolean cameraRecul;
    
    public VoitureCommerciale(String matricule, String marque, String modele, String puissance, String carburant , int anneeModele, 
            Double kilometrage, Boolean roueSecours, Boolean CricOutils, Boolean RadioAntenne, Boolean enjolivers,
            Boolean retroviseurs,Boolean climatiseurMarche, int capaciteCharge, Boolean toitOuvrant, Boolean cameraRecul,String type,Double coutParJour, CoordonnéesGPS  coordonneesGPS, Assurance assurance){
        super(matricule, marque, modele, puissance, carburant, anneeModele, kilometrage, roueSecours, CricOutils, RadioAntenne, enjolivers, retroviseurs, climatiseurMarche,type,coutParJour, coordonneesGPS, assurance);
        this.capaciteCharge = capaciteCharge;
        this.toitOuvrant = toitOuvrant;
        this.cameraRecul = cameraRecul;
    }

    public int getCapaciteCharge() {
        return capaciteCharge;
    }

    public Boolean getToitOuvrant() {
        return toitOuvrant;
    }

    public Boolean getCameraRecul() {
        return cameraRecul;
    }

    public void setCapaciteCharge(int capaciteCharge) {
        this.capaciteCharge = capaciteCharge;
    }

    public void setToitOuvrant(Boolean toitOuvrant) {
        this.toitOuvrant = toitOuvrant;
    }

    public void setCameraRecul(Boolean cameraRecul) {
        this.cameraRecul = cameraRecul;
    }

    @Override
    public String toString() {
        return "VoitureCommerciale{" +
               "capaciteCharge=" + capaciteCharge +
               ", toitOuvrant=" + toitOuvrant +
               ", cameraRecul=" + cameraRecul +
               ", " + super.toString() + // Appel sécurisé au toString() de Vehicule
               '}';
    }

    
}
