/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package rentcar.mvc;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Facture.Facture;
import models.Personnes.Adresse;
import models.Personnes.Client;
import models.Personnes.Gerant;
import models.VehiculesException.CoutException;
import models.vehicules.Assurance;
import models.vehicules.ContratLocation;
import models.vehicules.CoordonnéesGPS;
import models.vehicules.VoitureCommerciale;
import models.vehicules.VoitureFamiliale;

/**
 *
 * @author LENOVO
 */
public class RentcarMvc extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Gerant gerant = Gerant.getInstance();
            gerant.setNom("Sabrine");
            gerant.setPrenom("Kamkoum");
            gerant.setVehicules(new ArrayList<>());
            gerant.setClients(new ArrayList<>());
            gerant.setEntretiens(new ArrayList<>());
            gerant.setChauffeurs(new ArrayList<>());
            gerant.setLocations(new ArrayList<ContratLocation>());
            gerant.setAssurances(new ArrayList<>());
            gerant.setRemises(new ArrayList<>());
            gerant.setFactures(new ArrayList<Facture>());
          
            java.util.Date dateNaissance1 = new java.util.Date(1990 - 1900, Calendar.MARCH, 25); // 25 mars 1990
            java.util.Date dateCin1 = new java.util.Date(2010 - 1900, Calendar.APRIL, 10);       // 10 avril 2010
            java.util.Date dateNaissance2 = new java.util.Date(1995 - 1900, Calendar.JANUARY, 15); // 15 janvier 1995
            java.util.Date dateCin2 = new java.util.Date(2015 - 1900, Calendar.MAY, 5);             // 5 mai 2015

            // Création de deux adresses fictives en Tunisie
            Adresse adresse1 = new Adresse("20 Rue des Jasmins", "Ariana", "2080", "Tunisie");
            Adresse adresse2 = new Adresse("5 Avenue Farhat Hached", "Sousse", "4000", "Tunisie");

            // Instanciation du premier client
            Client client1 = new Client(
                "Société Al-Amal",
                "4567-1234-5678-9012",
                12345678,                  // CIN
                "Ben Salah",
                "Mohamed",
                21698765432L,              // Téléphone avec indicatif tunisien
                "mohamed.bensalah@example.com",
                adresse1,
                dateNaissance1,
                "Tunisienne",
                dateCin1,
                "Tunis",
                "P9876543",                // Numéro de permis
                "Tunis"
            );

            // Instanciation du deuxième client
            Client client2 = new Client(
                "Société Noor",
                "8765-4321-0987-6543",
                87654321,                  // CIN
                "Trabelsi",
                "Fatma",
                21691234567L,              // Téléphone avec indicatif tunisien
                "fatma.trabelsi@example.com",
                adresse2,
                dateNaissance2,
                "Tunisienne",
                dateCin2,
                "Sousse",
                "P1234567",                // Numéro de permis
                "Sousse"
            );

            // Affichage des informations des clients
            System.out.println("Client 1: " + client1);
            System.out.println("Client 2: " + client2);
            Gerant.getInstance().ajouterClient(client1);
            Gerant.getInstance().ajouterClient(client2);
            
            try {
                // Instanciation de l'assurance
                Assurance assurance1 = new Assurance(
                    101,                            // ID Assurance
                    "AXA",                          // Nom de l'assureur
                    "Assurance Tous Risques",       // Type d'assurance
                    new Date(2023 - 1900, 0, 15),    // Date de début (15 janvier 2023)
                    new Date(2024 - 1900, 0, 15),    // Date de fin (15 janvier 2024)
                    1200.0                          // Coût de l'assurance
                );
                Gerant.getInstance().ajouterAssurance(assurance1);

                // Instanciation de la voiture commerciale
                VoitureCommerciale utilitaire = new VoitureCommerciale(
                    "ABC123XYZ",               // Matricule
                    "Renault",                 // Marque
                    "Kangoo",                  // Modèle
                    "90 CV",                   // Puissance
                    "Diesel",                  // Carburant
                    2022,                      // Année du modèle
                    15000.0,                   // Kilométrage
                    true,                      // Roue de secours
                    true,                      // Cric et outils
                    true,                      // Radio antenne
                    true,                      // Enjolivers
                    true,                      // Rétroviseurs
                    true,                      // Climatiseur en marche
                    800,                       // Capacité de charge en kg
                    false,                     // Toit ouvrant
                    true,                      // Caméra de recul
                    "VoitureCommerciale",              // Type
                    75.0,                      // Coût par jour
                    new CoordonnéesGPS(36.857, 10.202),  // Coordonnées GPS (latitude, longitude)
                    assurance1                  // Objet Assurance
                );
                Gerant.getInstance().ajouterVoiture(utilitaire);

            } catch (CoutException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
           try {
               VoitureFamiliale peugeot5008 = new VoitureFamiliale(
                "200-TN-9012",              // matricule
                "Peugeot",                  // marque
                "5008",                     // modèle
                "130 CV",                   // puissance
                "Essence",                  // carburant
                2023,                       // annéeModèle
                15000.0,                    // kilométrage
                true,                       // roueSecours
                true,                       // CricOutils
                true,                       // RadioAntenne
                true,                       // enjoliveurs
                true,                       // rétroviseurs
                true,                       // climatiseurMarche
                7,                          // nombrePlaces
                true,                       // siègeBébéDisponible
                true,                       // grandCoffre
                "SUV",                      // type
                200.0,                      // coût par jour
                new CoordonnéesGPS(34.7594, 10.8111), // coordonnéesGPS (Sfax)
                new Assurance(
                    101,                            // ID Assurance
                    "AXA",                          // Nom de l'assureur
                    "Assurance Tous Risques",       // Type d'assurance
                    new Date(2023 - 1900, 0, 15),    // Date de début (15 janvier 2023)
                    new Date(2024 - 1900, 0, 15),    // Date de fin (15 janvier 2024)
                    1200.0                          // Coût de l'assurance
                )
            );
           Gerant.getInstance().ajouterVoiture(peugeot5008);

        } catch (CoutException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
           
                

            try {
                // Instanciation de l'assurance
                Assurance assurance2 = new Assurance(
                    102,                            // ID Assurance
                    "Allianz",                      // Nom de l'assureur
                    "Assurance Responsabilité Civile", // Type d'assurance
                    new Date(2023 - 1900, 5, 1),     // Date de début (1 juin 2023)
                    new Date(2025 - 1900, 5, 1),     // Date de fin (1 juin 2025)
                    2000.0                          // Coût de l'assurance
                );
                Gerant.getInstance().ajouterAssurance(assurance2);

                // Instanciation de la voiture commerciale
                VoitureCommerciale hautDeGamme = new VoitureCommerciale(
                    "XYZ456ABC",               // Matricule
                    "Mercedes-Benz",           // Marque
                    "Sprinter",                // Modèle
                    "150 CV",                  // Puissance
                    "Essence",                 // Carburant
                    2023,                      // Année du modèle
                    5000.0,                    // Kilométrage
                    true,                      // Roue de secours
                    true,                      // Cric et outils
                    true,                      // Radio antenne
                    true,                      // Enjolivers
                    true,                      // Rétroviseurs
                    true,                      // Climatiseur en marche
                    1000,                      // Capacité de charge en kg
                    true,                      // Toit ouvrant
                    true,                      // Caméra de recul
                    "VoitureCommerciale",           // Type
                    150.0,                     // Coût par jour
                    new CoordonnéesGPS(48.8566, 2.3522),  // Coordonnées GPS (latitude, longitude)
                    assurance2                  // Objet Assurance
                );
                Gerant.getInstance().ajouterVoiture(hautDeGamme);
            } catch (CoutException e) {
                System.out.println("Erreur : " + e.getMessage());
            }



    
            Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
          

            Scene scene = new Scene(root, 818, 614);
           // scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            //  scene.getStylesheets().add(getClass().getResource("/views/style.css").toExternalForm());
            primaryStage.setTitle("RentCar");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(RentcarMvc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
