/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.vehicules;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class VoitureFamiliale extends Vehicule{
    private int nombrePlaces;
    private Boolean siegeBebeDisponible;
    private Boolean grandCoffre;
    
    public VoitureFamiliale(String matricule, String marque, String modele, String puissance, String carburant , int anneeModele, 
            Double kilometrage, Boolean roueSecours, Boolean CricOutils, Boolean RadioAntenne, Boolean enjolivers,
            Boolean retroviseurs,Boolean climatiseurMarche, int nombrePlaces, Boolean siegeBebeDisponible, Boolean grandCoffre,String type,Double coutParJour,CoordonnéesGPS coordonneesGPS){
        super(matricule, marque, modele, puissance, carburant, anneeModele, kilometrage, roueSecours, CricOutils, RadioAntenne, enjolivers, retroviseurs, climatiseurMarche,type, coutParJour, coordonneesGPS);
        this.nombrePlaces = nombrePlaces; 
        this.siegeBebeDisponible = siegeBebeDisponible;
        this.grandCoffre = grandCoffre;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public Boolean getSiegeBebeDisponible() {
        return siegeBebeDisponible;
    }

    public Boolean getGrandCoffre() {
        return grandCoffre;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public void setSiegeBebeDisponible(Boolean siegeBebeDisponible) {
        this.siegeBebeDisponible = siegeBebeDisponible;
    }

    public void setGrandCoffre(Boolean grandCoffre) {
        this.grandCoffre = grandCoffre;
    }

    @Override
    public String toString() {
        return "VoitureFamiliale{" +
               "nombrePlaces=" + nombrePlaces +
               ", siegeBebeDisponible=" + siegeBebeDisponible +
               ", grandCoffre=" + grandCoffre +
               ", " + super.toString() + // Appel sécurisé au toString() de Vehicule
               '}';
    }

}
