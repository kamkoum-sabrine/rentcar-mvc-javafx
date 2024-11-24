package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Personnes.Chauffeur;

public class GestionChauffeursController {
    @FXML
    private TableView<Chauffeur> tableViewChauffeurs;

    @FXML
    private TableColumn<Chauffeur, Double> colCin;

    @FXML
    private TableColumn<Chauffeur, String> colNom;

    @FXML
    private TableColumn<Chauffeur, String> colPrenom;

    @FXML
    private TableColumn<Chauffeur, Boolean> colDisponibilite;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPrenom;

    @FXML
    private TextField tfCin;

    @FXML
    private CheckBox cbDisponibilite;

    @FXML
    private Label statusBar;

    private ObservableList<Chauffeur> chauffeurList;

  /*  @FXML
    public void initialize() {
        chauffeurList = FXCollections.observableArrayList();

        // Liaison des colonnes
        colCin.setCellValueFactory(data -> data.getValue().cinProperty().asObject());
        colNom.setCellValueFactory(data -> data.getValue().nomProperty());
        colPrenom.setCellValueFactory(data -> data.getValue().prenomProperty());
        colDisponibilite.setCellValueFactory(data -> data.getValue().isDisponibilite().asObject());

        tableViewChauffeurs.setItems(chauffeurList);
    }*/

    @FXML
    private void ajouterChauffeur() {
        try {
            double cin = Double.parseDouble(tfCin.getText());
            String nom = tfNom.getText();
            String prenom = tfPrenom.getText();
            boolean disponibilite = cbDisponibilite.isSelected();

            if (nom.isEmpty() || prenom.isEmpty()) {
                statusBar.setText("Veuillez remplir tous les champs !");
                return;
            }

            Chauffeur chauffeur = new Chauffeur(disponibilite, 0, "0000", null, "", null, cin, nom, prenom, 0, "", null, null, "", null, "");
            chauffeurList.add(chauffeur);

            clearFields();
            statusBar.setText("Chauffeur ajouté avec succès !");
        } catch (NumberFormatException e) {
            statusBar.setText("Erreur : CIN invalide !");
        }
    }

    @FXML
    private void modifierChauffeur() {
        Chauffeur selectedChauffeur = tableViewChauffeurs.getSelectionModel().getSelectedItem();
        if (selectedChauffeur == null) {
            statusBar.setText("Sélectionnez un chauffeur à modifier !");
            return;
        }

        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        boolean disponibilite = cbDisponibilite.isSelected();

        selectedChauffeur.setNom(nom);
        selectedChauffeur.setPrenom(prenom);
        selectedChauffeur.setDisponibilite(disponibilite);

        tableViewChauffeurs.refresh();
        clearFields();
        statusBar.setText("Chauffeur modifié avec succès !");
    }

    @FXML
    private void supprimerChauffeur() {
        Chauffeur selectedChauffeur = tableViewChauffeurs.getSelectionModel().getSelectedItem();
        if (selectedChauffeur == null) {
            statusBar.setText("Sélectionnez un chauffeur à supprimer !");
            return;
        }

        chauffeurList.remove(selectedChauffeur);
        statusBar.setText("Chauffeur supprimé avec succès !");
    }

    private void clearFields() {
        tfCin.clear();
        tfNom.clear();
        tfPrenom.clear();
        cbDisponibilite.setSelected(false);
    }
}
