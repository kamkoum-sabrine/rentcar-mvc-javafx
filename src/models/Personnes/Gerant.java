/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.Personnes;

import java.util.ArrayList;
import java.util.Date;
import models.Facture.Facture;
import models.Facture.Remise;
import models.Personnes.Chauffeur;
import models.Personnes.Client;
import models.management.Entretien;
import models.vehicules.Assurance;
import models.vehicules.ContratLocation;
import models.vehicules.Vehicule;
import models.vehicules.VoitureCommerciale;
import models.vehicules.VoitureFamiliale;

/**
 *
 * @author LENOVO
 */
public final class Gerant extends Personne {

    public ArrayList<Vehicule> vehicules;
    public ArrayList<Client> clients;
    public ArrayList<Chauffeur> chauffeurs;
    public ArrayList<ContratLocation> locations;
    public ArrayList<Entretien> entretiens;    
    public ArrayList<Assurance> assurances;
    public ArrayList<Remise> remises;
    public ArrayList<Facture> factures;
   
    private static Gerant instance;

    public static Gerant getInstance() {
        if (instance == null) {
            instance = new Gerant(); 
        }
        return instance;
    }


    public Gerant(ArrayList<Vehicule> vehicules, ArrayList<Client> clients, ArrayList<Chauffeur> chauffeurs, ArrayList<ContratLocation> locations, ArrayList<Entretien> entretiens,ArrayList<Assurance> assurances, double cin, String nom, String prenom, double tel, String email, Adresse adresse, Date dateNaissance, String nationalite, Date dateCin, String lieuCin) {
        super(cin, nom, prenom, tel, email, adresse, dateNaissance, nationalite, dateCin, lieuCin);
        this.vehicules = new ArrayList<Vehicule>();
        this.clients = new ArrayList<Client>();
        this.chauffeurs = new ArrayList<Chauffeur>();
        this.locations = new ArrayList<ContratLocation>();
        this.entretiens = new ArrayList<Entretien>();
        this.assurances = new ArrayList<Assurance>();
        this.factures = new ArrayList<Facture>();
    }

    public Gerant() {
    }
    
     public void ajouterVoiture(Vehicule vehicule){
        vehicules.add(vehicule);
    }
     
     public void ajouterClient(Client client){
        clients.add(client);
    }
     
     public void ajouterAssurance(Assurance assurance){
        assurances.add(assurance);
    }
    
    public void ajouterChauffeur(Chauffeur chauffeur){
        chauffeurs.add(chauffeur);
    }
    
     public void ajouterLocation(ContratLocation location){
        locations.add(location);
    }
      public void ajouterEntretien(Entretien entretien){
        entretiens.add(entretien);
    }

    public void setVehicules(ArrayList<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void setChauffeurs(ArrayList<Chauffeur> chauffeurs) {
        this.chauffeurs = chauffeurs;
    }

    public void setLocations(ArrayList<ContratLocation> locations) {
        this.locations = locations;
    }

    public void setEntretiens(ArrayList<Entretien> entretiens) {
        this.entretiens = entretiens;
    }

    public void setCin(double cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(double tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public void setDateCin(Date dateCin) {
        this.dateCin = dateCin;
    }

    public void setLieuCin(String lieuCin) {
        this.lieuCin = lieuCin;
    }

    public ArrayList<Vehicule> getVehicules() {
        return vehicules;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Chauffeur> getChauffeurs() {
        return chauffeurs;
    }

    public ArrayList<ContratLocation> getLocations() {
        return locations;
    }

    public ArrayList<Entretien> getEntretiens() {
        return entretiens;
    }
    
     public ArrayList<Assurance> getAssurances() {
        return assurances;
    }
     

    public double getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public double getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public Date getDateCin() {
        return dateCin;
    }

    public String getLieuCin() {
        return lieuCin;
    }
    
    public boolean supprimerVehicule(Vehicule vehicule) {
        if (vehicules.contains(vehicule)) {
            vehicules.remove(vehicule);
            return true; 
        }
        return false; 
    }  
    public boolean supprimerAssurance(Assurance assurance) {
        if (assurances.contains(assurance)) {
            assurances.remove(assurance);
            return true; 
        }
        return false; 
    }   


    public void setAssurances(ArrayList<Assurance> assurances) {
        this.assurances = assurances;
    }

     public int nombrevehicules(){return vehicules.size();}
    public int nombreclients(){return clients.size();}
    public int nombrechauffeurs(){return chauffeurs.size();}
    public int nombrelocations(){return locations.size();}
    public int nombreentretiens(){return entretiens.size();}
    public ArrayList<Remise> getRemises() {
        return remises;
    }

    public void setRemises(ArrayList<Remise> remises) {
        this.remises = remises;
    }

    public static void setInstance(Gerant instance) {
        Gerant.instance = instance;
    }
    
    public void ajouterRemise(Remise remise){
        remises.add(remise);
    }
    public boolean supprimerRemise(Remise remise) {
      if (remises.contains(remise)) {
          remises.remove(remise);
          return true; 
      }
      return false;
    }   
    
   
    public boolean supprimerLocation(ContratLocation contratLocation) {
      if (locations.contains(contratLocation)) {
          locations.remove(contratLocation);
          return true; 
      }
      return false; 
    }   

    public ArrayList<Facture> getFactures() {
        return factures;
    }

    public void setFactures(ArrayList<Facture> factures) {
        this.factures = factures;
    }
     public void ajouterFacture(Facture facture){
        factures.add(facture);
    }
    public boolean supprimerFacture(Facture facture) {
      if (factures.contains(facture)) {
          factures.remove(facture);
          return true; 
      }
      return false; 
    }   
    
     public int getNombreVoituresFamiliales() {
        return (int) vehicules.stream()
                .filter(v -> v instanceof VoitureFamiliale)
                .count();
    }

    public int getNombreVoituresCommerciales() {
        return (int) vehicules.stream()
                .filter(v -> v instanceof VoitureCommerciale)
                .count();
    }

}
