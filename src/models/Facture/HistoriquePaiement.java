/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.Facture;

import java.time.LocalDate;

/**
 *
 * @author LENOVO
 */
public class HistoriquePaiement {
    private int idPaiement;
    private Facture facture; // Lien vers la facture associée
    private ModePaiement modePaiement; // Lien vers le mode de paiement utilisé
    private double montantPaye;
    private LocalDate datePaiement;

    public HistoriquePaiement(int idPaiement, Facture facture, ModePaiement modePaiement, double montantPaye, LocalDate datePaiement) {
        this.idPaiement = idPaiement;
        this.facture = facture;
        this.modePaiement = modePaiement;
        this.montantPaye = montantPaye;
        this.datePaiement = datePaiement;
    }

    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    @Override
    public String toString() {
        return "HistoriquePaiement{" +
                "idPaiement=" + idPaiement +
                ", facture=" + facture +
                ", modePaiement=" + modePaiement +
                ", montantPaye=" + montantPaye +
                ", datePaiement=" + datePaiement +
                '}';
    }
}
