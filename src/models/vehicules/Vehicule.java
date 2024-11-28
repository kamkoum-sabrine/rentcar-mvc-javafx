/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.vehicules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author LENOVO
 */
public class Vehicule implements Comparable<Vehicule>{
    protected String matricule;
    protected String marque;
    protected String modele;
    protected String puissance;
    protected String Carburant;
    protected int AnneeModele;
    protected Double kilometrage;
    protected Boolean roueSecours;
    protected Boolean CricOutils;
    protected Boolean RadioAntenne;
    protected Boolean enjolivers;
    protected Boolean retroviseurs;
    protected Boolean climatiseurMarche;
    private String type; 
    private Double coutParJour;
    private CoordonnéesGPS coordonneesGPS;
    private Assurance assurance;        

    
    public Vehicule(String matricule, String marque, String modele, String puissance, String carburant , int anneeModele, 
            Double kilometrage, Boolean roueSecours, Boolean CricOutils, Boolean RadioAntenne, Boolean enjolivers,
            Boolean retroviseurs,Boolean climatiseurMarche,String type,double coutParJour,CoordonnéesGPS coordonneesGPS, Assurance assurance){
        this.matricule = matricule;
        this.modele = modele;
        this.marque = marque;
        this.puissance = puissance;
        this.Carburant = carburant;
        this.AnneeModele = anneeModele;
        this.kilometrage = kilometrage;
        this.roueSecours = roueSecours;
        this.CricOutils = CricOutils;
        this.RadioAntenne = RadioAntenne;
        this.enjolivers = enjolivers;
        this.retroviseurs = retroviseurs;
        this.climatiseurMarche = climatiseurMarche;
        this.type = type;
        this.coutParJour = coutParJour;
        this.coordonneesGPS = coordonneesGPS;
        this.assurance = assurance;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getPuissance() {
        return puissance;
    }

    public String getCarburant() {
        return Carburant;
    }

    public int getAnneeModele() {
        return AnneeModele;
    }

    public Double getKilometrage() {
        return kilometrage;
    }

    public Boolean getRoueSecours() {
        return roueSecours;
    }

    public Boolean getCricOutils() {
        return CricOutils;
    }

    public Boolean getRadioAntenne() {
        return RadioAntenne;
    }

    public Boolean getEnjolivers() {
        return enjolivers;
    }

    public Boolean getRetroviseurs() {
        return retroviseurs;
    }

    public Boolean getClimatiseurMarche() {
        return climatiseurMarche;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setPuissance(String puissance) {
        this.puissance = puissance;
    }

    public void setCarburant(String Carburant) {
        this.Carburant = Carburant;
    }

    public void setAnneeModele(int AnneeModele) {
        this.AnneeModele = AnneeModele;
    }

    public void setKilometrage(Double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public void setRoueSecours(Boolean roueSecours) {
        this.roueSecours = roueSecours;
    }

    public void setCricOutils(Boolean CricOutils) {
        this.CricOutils = CricOutils;
    }

    public void setRadioAntenne(Boolean RadioAntenne) {
        this.RadioAntenne = RadioAntenne;
    }

    public void setEnjolivers(Boolean enjolivers) {
        this.enjolivers = enjolivers;
    }

    public void setRetroviseurs(Boolean retroviseurs) {
        this.retroviseurs = retroviseurs;
    }

    public void setClimatiseurMarche(Boolean climatiseurMarche) {
        this.climatiseurMarche = climatiseurMarche;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "matricule=" + matricule + ", marque=" + marque + ", modele=" + modele + ", puissance=" + puissance + ", Carburant=" + Carburant + ", AnneeModele=" + AnneeModele + ", kilometrage=" + kilometrage + ", roueSecours=" + roueSecours + ", CricOutils=" + CricOutils + ", RadioAntenne=" + RadioAntenne + ", enjolivers=" + enjolivers + ", retroviseurs=" + retroviseurs + ", climatiseurMarche=" + climatiseurMarche + ", type=" + type + ", coutParJour=" + coutParJour + ", coordonneesGPS=" + coordonneesGPS + '}';
    }

    
    public CoordonnéesGPS getCoordonneesGPS() {
        return coordonneesGPS;
    }

    public void setCoordonneesGPS(CoordonnéesGPS coordonneesGPS) {
        this.coordonneesGPS = coordonneesGPS;
    }
    
    public static List<Vehicule> filtrerVehicules(List<Vehicule> vehicules, FiltreVehicule filtre) {
        List<Vehicule> vehiculesFiltres = new ArrayList<>();
      /**  for (Vehicule vehicule : vehicules) {
            if (filtre.filtrer(vehicule)) {
                vehiculesFiltres.add(vehicule);
            }
        }
      
        return vehiculesFiltres;**/
       return vehicules.stream()  // Crée un Stream à partir de la liste de véhicules
            .filter(filtre::filtrer)  // Applique le filtre fonctionnel
            .collect(Collectors.toList());  // Collecte les résultats dans une nouvelle liste
    }

    public Double getCoutParJour() {
        return coutParJour;
    }

    public void setCoutParJour(Double coutParJour) {
        this.coutParJour = coutParJour;
    }
    
     // Implémentation de l'interface Comparable
    @Override
    public int compareTo(Vehicule other) {
        return Integer.compare(this.AnneeModele, other.AnneeModele); // Tri par année croissante
    }

    public Assurance getAssurance() {
        return assurance;
    }

    public void setAssurance(Assurance assurance) {
        this.assurance = assurance;
    }
    
}
