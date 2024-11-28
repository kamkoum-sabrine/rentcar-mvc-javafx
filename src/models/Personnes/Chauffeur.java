
package models.Personnes;

import java.sql.Date;



public final class Chauffeur extends Personne{
    private boolean disponibilite;
    private int experience;
    private String numPermis;
    private Permis categoriePermis;
    private Date datePermis;
    private String lieuPermis;
    private int nbJoursTravaille;
    private double prixJours;

    public Chauffeur(boolean disponibilite, int experience, String numPermis, Date datePermis, String lieuPermis,
            Permis categoriePermis, double cin, String nom, String prenom, double tel, String email, Adresse adresse,
            Date dateNaissance, String nationalite, Date dateCin, String lieuCin) {
        super(cin, nom, prenom, tel, email, adresse, dateNaissance, nationalite, dateCin, lieuCin);
        this.disponibilite = disponibilite;
        this.experience = experience;
        this.numPermis = numPermis;
        this.datePermis = datePermis;
        this.lieuPermis = lieuPermis;
        this.categoriePermis = categoriePermis;
    }

    public boolean getDisponibilite() {
        return disponibilite;
    }

    public int getExperience() {
        return experience;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public String getNumPermis() {
        return numPermis;
    }

    public Date getDatePermis() {
        return datePermis;
    }

    public String getLieuPermis() {
        return lieuPermis;
    }

    public String getCategoriePermis() {
        return categoriePermis.getDescription() ;
    }

    public void setNumPermis(String numPermis) {
        this.numPermis = numPermis;
    }

    public void setDatePermis(Date datePermis) {
        this.datePermis = datePermis;
    }

    public void setLieuPermis(String lieuPermis) {
        this.lieuPermis = lieuPermis;
    }

    public void setCategoriePermis(Permis categoriePermis) {
        this.categoriePermis = categoriePermis;
    }
}
