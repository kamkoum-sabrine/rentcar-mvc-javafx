package models.Personnes;

public enum Permis {
    A("Moto"),
    B("Véhicules légers (voitures)"),
    C("Poids lourds"),
    D("Transports en commun"),
    E("Véhicules nécessitant une remorque"),
    AM("Cyclomoteurs"),
    B1("Quadricycles légers à moteur"),
    C1("Poids lourds intermédiaires"),
    D1("Minibus");

    private final String description;

    // Constructeur pour associer une description à chaque catégorie
    Permis(String description) {
        this.description = description;
    }

    // Méthode pour obtenir la description
    public String getDescription() {
        return description;
    }
}
