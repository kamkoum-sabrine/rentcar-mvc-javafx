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
    private TableColumn<Facture, String> colContrat;

    @FXML
    private TableColumn<Facture, Void> colActions;
    
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

        colContrat.setCellValueFactory(cellData -> {
            if (cellData.getValue().getContrat()!= null) {
                return new SimpleStringProperty(String.valueOf(cellData.getValue().getContrat().getId()));
            } else {
                return new SimpleStringProperty("Aucun contrat");
            }
        });
          colActions.setCellFactory(param -> new TableCell<>() {
            private final HBox actionBox = new HBox(10); // Conteneur horizontal pour les icônes
            private final Button PayeButton = new Button();
        
            {
                PayeButton.setText("Payer");
               
                // (Facultatif) Ajoute des styles pour différencier les boutons
                PayeButton.setStyle("-fx-background-color: #219C90; -fx-text-fill: white; -fx-font-weight: bold;");
               // deleteButton.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");

                // Ajout des actions
                PayeButton.setOnAction(event -> {
                    System.out.println("edit !!");
                    Facture facture = getTableView().getItems().get(getIndex());
                    //System.out.println("Id facture "+ f.getIdRemise());
                    try {
                        //onEditRemise(remise);
                        facture.setEstReglee(true);
                    } catch (Exception e) {
                        System.err.println("Erreur lors de l'édition : " + e.getMessage());
                        e.printStackTrace();
                    }
                });

                actionBox.getChildren().addAll(PayeButton);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(actionBox);
                }
            }
        });
        
       
          tableFacture.refresh();
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
