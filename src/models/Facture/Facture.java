/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.Facture;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.vehicules.ContratLocation;
import models.Personnes.Client;


/**
 *
 * @author LENOVO
 */
public class Facture {
    private int idFacture;
    private Date dateEmission;
   
    private boolean estReglee;
    private ContratLocation contrat;
    
    private List<Remise> remises; 

    public Facture(int idFacture, Date dateEmission,  ContratLocation contrat) {
        this.idFacture = idFacture;
        this.dateEmission = dateEmission;
        this.estReglee = false; 
        this.contrat = contrat;
        this.remises = new ArrayList<>();
    }
    
    public Facture(){
        this.remises = new ArrayList<>();
    };

    public int getIdFacture() {
        return idFacture;
    }

    public Date getDateEmission() {
        return dateEmission;
    }
    public double calculerMontantTotalAvecRemise() {
       
        double coutLocation = contrat.calculerCout();

        for (Remise remise : remises) {
            double pourcentageRemise = remise.getPourcentageRemise();
            coutLocation -= coutLocation * (pourcentageRemise / 100);
        }
        System.out.println("Cout "+coutLocation);

        return coutLocation;
    }

    public boolean isEstReglee() {
        return estReglee;
    }

   

    public void ajouterRemise(Remise remise) {
        System.out.println("remise "+remise.toString());
        remises.add(remise);
    }

    public ContratLocation getContrat() {
        return contrat;
    }

    public List<Remise> getRemises() {
        return remises;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public void setEstReglee(boolean estReglee) {
        this.estReglee = estReglee;
    }

    public void setContrat(ContratLocation contrat) {
        this.contrat = contrat;
    }

    public void setRemises(List<Remise> remises) {
        this.remises = remises;
    }
    
    public void videsLesRemises(){
        this.remises = new ArrayList<>();
    }
    

}