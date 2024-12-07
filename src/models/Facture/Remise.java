/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.Facture;

/**
 *
 * @author LENOVO
 */
public class Remise {
    private int idRemise;
    private String description; 
    private double pourcentageRemise; 
    private boolean conditionActive; 

    public Remise(int idRemise, String description, double pourcentageRemise, boolean conditionActive) {
        this.idRemise = idRemise;
        this.description = description;
        this.pourcentageRemise = pourcentageRemise;
        this.conditionActive = conditionActive;
    }

    public int getIdRemise() {
        return idRemise;
    }

    public void setIdRemise(int idRemise) {
        this.idRemise = idRemise;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPourcentageRemise() {
        return pourcentageRemise;
    }

    public void setPourcentageRemise(double pourcentageRemise) {
        this.pourcentageRemise = pourcentageRemise;
    }

    public boolean isConditionActive() {
        return conditionActive;
    }

    public void setConditionActive(boolean conditionActive) {
        this.conditionActive = conditionActive;
    }

    @Override
    public String toString() {
        return "Remise{" +
                "idRemise=" + idRemise +
                ", description='" + description + '\'' +
                ", pourcentageRemise=" + pourcentageRemise +
                ", conditionActive=" + conditionActive +
                '}';
    }
}
