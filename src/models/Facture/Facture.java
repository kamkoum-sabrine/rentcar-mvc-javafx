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
    private LocalDate dateEmission;
    private double montantTotal;
    private boolean estReglee;
    private ContratLocation contrat;
    
    private List<Remise> remises; // Ajout des remises appliquées à la facture

    public Facture(int idFacture, LocalDate dateEmission, double montantTotal, ContratLocation contrat) {
        this.idFacture = idFacture;
        this.dateEmission = dateEmission;
        this.montantTotal = montantTotal;
        this.estReglee = false; // Par défaut, une facture n'est pas réglée
        this.contrat = contrat;
       // this.paiements = new ArrayList<>();
        this.remises = new ArrayList<>();
    }

    public int getIdFacture() {
        return idFacture;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public double getMontantTotal() {
        double montantAvecRemises = montantTotal;
        for (Remise remise : remises) {
            if (remise.isConditionActive()) {
                montantAvecRemises -= montantTotal * remise.getPourcentageRemise();
            }
        }
        return montantAvecRemises;
    }

    public boolean isEstReglee() {
        return estReglee;
    }

   

    public void ajouterRemise(Remise remise) {
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

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
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
    

}