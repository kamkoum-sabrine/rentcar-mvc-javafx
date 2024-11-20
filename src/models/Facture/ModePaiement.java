/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.Facture;

/**
 *
 * @author LENOVO
 */
public class ModePaiement {
    private int idMode;
    private String nomMode; // Exemple: "Carte de crédit", "Espèces", "Chèque", "Virement bancaire"
    private String details; // Pour des informations supplémentaires comme le numéro de transaction

    public ModePaiement(int idMode, String nomMode, String details) {
        this.idMode = idMode;
        this.nomMode = nomMode;
        this.details = details;
    }

    public int getIdMode() {
        return idMode;
    }

    public void setIdMode(int idMode) {
        this.idMode = idMode;
    }

    public String getNomMode() {
        return nomMode;
    }

    public void setNomMode(String nomMode) {
        this.nomMode = nomMode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ModePaiement{" +
                "idMode=" + idMode +
                ", nomMode='" + nomMode + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
