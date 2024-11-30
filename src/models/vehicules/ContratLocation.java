/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.vehicules;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import models.VehiculesException.CoutException;
import models.VehiculesException.DateLocationException;
import models.Personnes.Client;


public class ContratLocation {
    private int id;
    //private Set<Client> clients ;
    private Client conducteur1;
    private Client conducteur2;
    private Vehicule vehicule;
    private Date dateDebut;
    private Date dateFin;

    public ContratLocation() {
    }
  
    
    public ContratLocation(int id,Client conducteur1, Client conducteur2, Vehicule vehicule, Date dateDebut, Date dateFin) throws DateLocationException{
       // this.clients =new HashSet<Client>();
        this.id = id;
        this.conducteur1 = conducteur1;
        this.conducteur2 = conducteur2;
        this.vehicule = vehicule;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
       // if (this.dateDebut.compareTo(this.dateFin) > 0) throw new DateLocationException(); 

    }

    public int getId() {
        return id;
    }

  /**  public Set<Client> getConducteurs() {
        return this.clients;
    }**/

    public Vehicule getVehicule() {
        return vehicule;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDatFin() {
        return dateFin;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**public void setClients(Client c1,Client c2) {
        this.clients.add(c1) ;
        this.clients.add(c2);
    }**/

    public void changerVoiture(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void prolongerLocation(Date datFin) throws DateLocationException {
        this.dateFin = datFin;
        if (this.dateDebut.compareTo(this.dateFin) > 0) throw new DateLocationException(); 
    }

    public Client getConducteur1(){
        return conducteur1;
    }

    public Client getConducteur2() {
        return conducteur2;
    }

    public void setConducteur1(Client conducteur1) {
        this.conducteur1 = conducteur1;
    }

    public void setConducteur2(Client conducteur2) {
        this.conducteur2 = conducteur2;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "ContratLocation{" + "id=" + id + ", conducteur1=" + conducteur1 + ", conducteur2=" + conducteur2 + ", vehicule=" + vehicule + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }
    /** @Override
    public String toString() {
    List<String> list = new ArrayList(clients);
    return "ContratLocation{" + "id=" + id + ", conducteur1=" + list.get(0) + ", conducteur2=" + list.get(1) + ", vehicule=" + vehicule + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin +'}';
    }**/
    public Date getDateFin() {
        return dateFin;
    }

    public double calculerCout() {
        long differenceEnMillisecondes = dateFin.getTime() - dateDebut.getTime();
        long differenceEnJours = differenceEnMillisecondes / (1000 * 60 * 60 * 24); // Conversion en jours
        return differenceEnJours * vehicule.getCoutParJour();
    }
}
