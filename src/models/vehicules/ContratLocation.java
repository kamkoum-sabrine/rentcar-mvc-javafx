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
    private Set<Client> clients ;
    private Vehicule vehicule;
    private Date dateDebut;
    private Date dateFin;
  
    public ContratLocation(int id, Vehicule vehicule, Date dateDebut, Date datFin) throws DateLocationException, CoutException{
        this.clients =new HashSet<Client>();
        this.id = id;
        this.vehicule = vehicule;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        if (this.dateDebut.compareTo(this.dateFin) > 0) throw new DateLocationException(); 

    }

    public int getId() {
        return id;
    }

    public Set<Client> getConducteurs() {
        return this.clients;
    }

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

    public void setClients(Client c1,Client c2) {
        this.clients.add(c1) ;
        this.clients.add(c2);
    }

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

    

    @Override
    public String toString() {
        List<String> list = new ArrayList(clients);
        return "ContratLocation{" + "id=" + id + ", conducteur1=" + list.get(0) + ", conducteur2=" + list.get(1) + ", vehicule=" + vehicule + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin +'}';
    }
    
    public double calculerCout(){
        long differenceEnMillisecondes = dateFin.getTime() - dateDebut.getTime();
        long differenceEnJours = differenceEnMillisecondes / (1000 * 60 * 60 * 24); // Conversion en jours
        return differenceEnJours * vehicule.getCoutParJour();
    }
}
