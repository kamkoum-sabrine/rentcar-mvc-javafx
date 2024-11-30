
package models.Personnes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import models.vehicules.ContratLocation;


public final class Client extends Personne implements Comparable<Client> {
    private ArrayList<ContratLocation> historiqueLocation;
    private String societe;
    private String carteCredit;
    private String numPermis;
    private Date datePermis;
    private String lieuPermis;


    public Client() {
        super();
    }

    public Client(String societe, String carteCredit, double cin, String nom, String prenom,
                  double tel, String email, Adresse adresse, Date dateNaissance, String nationalite, Date dateCin, String lieuCin,
                  String numPermis, String lieuPermis) {
        super(cin, nom, prenom, tel, email, adresse, dateNaissance, nationalite, dateCin, lieuCin);
        this.historiqueLocation = new ArrayList<>();
        this.societe = societe;
        this.carteCredit = carteCredit;
        this.numPermis = numPermis;
        this.datePermis = datePermis;
        this.lieuPermis = lieuPermis;
    }

    public void AfficherLesLocations() {
        for (int i = 0; i < historiqueLocation.size(); i++) {
            System.out.println(historiqueLocation.get(i));
        }
    }

    public int CaluculeNbLocations() {
        return this.historiqueLocation.size();
    }

    public boolean VerifierHistorique() {
        return this.historiqueLocation.size() == 1;
    }

    public ArrayList<ContratLocation> getHistoriqueLocation() {
        return historiqueLocation;
    }

    public String getSociete() {
        return societe;
    }

    public String getCarteCredit() {
        return carteCredit;
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

    public void setHistoriqueLocation(ArrayList<ContratLocation> historiqueLocation) {
        this.historiqueLocation = historiqueLocation;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public void setCarteCredit(String carteCredit) {
        this.carteCredit = carteCredit;
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

    @Override
    public String toString() {
        return "Client{" + "historiqueLocation=" + historiqueLocation + ", societe=" + societe + ", carteCredit=" + carteCredit + ", numPermis=" + numPermis + ", datePermis=" + datePermis + ", lieuPermis=" + lieuPermis + '}';
    }

    public void ajouterLocation(ContratLocation location) {
        this.historiqueLocation.add(location);
    }

    public void supprimerLocation(int index) {
        this.historiqueLocation.remove(index);
    }

    @Override
    public int compareTo(Client o) {
        double a = 0;
        double b = 0;
        Iterator<ContratLocation> it = this.historiqueLocation.iterator();
        while (it.hasNext()) {
            a += it.next().calculerCout();
        }
        ;
        Iterator<ContratLocation> it1 = o.historiqueLocation.iterator();
        while (it1.hasNext()) {
            b += it.next().calculerCout();
        }
        ;

        return Double.compare(a, b);
    }


    public void ClientPlusFidele(Client o) {
        int x=this.compareTo(o);
        if (x==1)
        { System.out.println("Le client"+this.nom+" "+this.prenom+"est plus fidele que le client"+o.nom+" "+o.prenom);}
        else if (x==-1)
        {System.out.println("Le client"+o.nom+" "+o.prenom+"est plus fidele que le client"+this.nom+" "+this.prenom);}
        else
        {System.out.println("Les deux clients sont similaires en fidelité");}
    }
    
    public boolean estFidele() {
    // Par exemple, un client est considéré fidèle s'il a effectué au moins 10 locations
    int seuilFidelite = 10;
    return historiqueLocation.size() >= seuilFidelite;
    }

}
