package models.exceptionsPersonne;

public class CinException extends Exception {
    public CinException(){
        super("Le numero de carte d'identité est non valide");
    }
}
