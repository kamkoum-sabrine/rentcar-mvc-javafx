
package models.Personnes;


public record Adresse(String rue, String ville, String codePostal, String pays) {

    public Adresse(String rue, String ville, String pays) {
        this(rue, ville, "Non spécifié", pays); 
    }



    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", rue, ville, codePostal, pays);
    }
}

