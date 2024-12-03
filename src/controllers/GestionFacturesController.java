/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import models.Facture.Facture;
import models.Facture.Remise;
import models.Personnes.Gerant;
import models.vehicules.ContratLocation;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionFacturesController  {
    
   
    @FXML
    private TableView<Facture> tableFacture;

    @FXML
    private TableColumn<Facture, Integer> colIdFacture;

    @FXML
    private TableColumn<Facture, Date> colDateEmission;
    
    @FXML
    private TableColumn<Facture, Boolean> colEstReglee;
    
    @FXML
    private TableColumn<Facture, ArrayList<Remise>> colRemises;

 
    @FXML
    private TableColumn<Facture, Integer> colContrat;


    private final ObservableList<Facture> factures = FXCollections.observableArrayList();

    /**@FXML
    private TableColumn<Remise, Void> colActions;**/

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        System.out.println("Gestion des factures chargée !");

        colIdFacture.setCellValueFactory(new PropertyValueFactory<>("idFacture"));
        colDateEmission.setCellValueFactory(new PropertyValueFactory<>("dateEmission"));
        colEstReglee.setCellValueFactory(new PropertyValueFactory<>("estReglee"));

       /** colContrat.setCellValueFactory(cellData -> {
            if (cellData.getValue().getContrat()!= null) {
                return new SimpleStringProperty(cellData.getValue().getContrat().getId());
            } else {
                return new SimpleStringProperty("Aucun contrat");
            }
        });**/
        // Remises : afficher la description de chaque remise
    /**    colRemises.setCellFactory(column -> new TableCell<>() {
            
            @Override
            protected void updateItem(ArrayList<Remise> remises, boolean empty) {
                
                
                super.updateItem(remises, empty);
                 StringBuilder descriptionsBuilder = new StringBuilder();
                   for (Remise remise : remises) {
                         //   if (remise != null && remise.getDescription() != null) {
                                System.out.println("+++++++++++++++++++++++remise.getDescription() "+remise.getDescription());
                             //   if (descriptionsBuilder.length() > 0) {
                                    descriptionsBuilder.append(", ");
                              //  }
                                descriptionsBuilder.append(remise.getDescription());
                         //   }
                        }
                if (empty || remises == null) {
                    setText(null);
                    System.out.println("nuuuuuuuuuuuuuuuuuuuuuuuuuuulllllllllllllllll");
                } else {
                    // Convertir la liste des descriptions en chaîne de texte
                 /**   System.out.println("remises "+remises.toString());
                    String descriptions = remises.stream()
                                                 .map(Remise::getDescription) // Assurez-vous que la méthode getDescription() existe
                                                 .reduce((desc1, desc2) -> desc1 + ", " + desc2)
                                                 .orElse("Aucune remise");
                    System.out.println("Les desciptions "+descriptions);
                    setText(descriptions);/
                 try {
                    


                  
                    if (remises != null && !remises.isEmpty()) {
                        for (Remise remise : remises) {
                            if (remise != null && remise.getDescription() != null) {
                                System.out.println("remise.getDescription() "+remise.getDescription());
                                if (descriptionsBuilder.length() > 0) {
                                    descriptionsBuilder.append(", ");
                                }
                                descriptionsBuilder.append(remise.getDescription());
                            }
                        }
                    }

                    String descriptions = descriptionsBuilder.length() > 0 ? descriptionsBuilder.toString() : "Aucune remise";
                    System.out.println("Les descriptions : " + descriptions);
                    setText(descriptions);


                } catch (Exception e) {
                    System.err.println("Erreur lors de la récupération des descriptions : " + e.getMessage());
                    e.printStackTrace();
                }

                }
            }
        });

*  * **/
     
        
       
        // Charger des données initiales
        factures.addAll(getFacturesInitiaux());
        tableFacture.setItems(factures);
    }
    
     public List<Facture> getFacturesInitiaux() {
        // Récupère les véhicules stockés dans Gerant
         for (int i = 0; i < Gerant.getInstance().getFactures().size(); i++) {
             System.out.println("Gerant.getInstance().getFactures() "+Gerant.getInstance().getFactures().get(i).getRemises().toString());
         }
        return Gerant.getInstance().getFactures();    
    }


    
}
