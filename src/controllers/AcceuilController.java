/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**added*/
public class AcceuilController {
    @FXML
    private void allerAccueil(ActionEvent event) {
        System.out.println("Vous êtes sur la page d'accueil !");
    }

    @FXML
    private void gererVehicules(ActionEvent event) {
        System.out.println("Naviguer vers la gestion des véhicules !");
        // Naviguer vers une autre scène.
    }

    @FXML
    private void gererLocations(ActionEvent event) {
        System.out.println("Naviguer vers la gestion des locations !");
        // Naviguer vers une autre scène.
    }

    @FXML
    private void voirClients(ActionEvent event) {
        System.out.println("Afficher les clients !");
        // Naviguer vers une autre scène.
    }
}
