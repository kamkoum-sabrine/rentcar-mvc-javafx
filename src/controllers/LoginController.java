package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class LoginController {

    @FXML
    private MediaView backgroundVideo;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    
    @FXML
    private Label errorLabel; // Label for displaying errors

    @FXML
    public void initialize() {
        // Charger la vidéo en arrière-plan
       /**  String videoPath = getClass().getResource("/resources/videos/bg.mp4").toExternalForm();
        Media media = new Media(videoPath);
        mediaPlayer = new MediaPlayer(media);
        backgroundVideo.setMediaPlayer(mediaPlayer);

        // Démarrer la vidéo en boucle
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();**/
       // Charger la vidéo en arrière-plan
       // String videoPath = getClass().getResource("/ressources/videos/bg.mp4").toExternalForm();
        String videoPath = new File("src/ressources/videos/bg.mp4").toURI().toString();


        Media media = new Media(videoPath);
        mediaPlayer = new MediaPlayer(media);
        backgroundVideo.setMediaPlayer(mediaPlayer);

        // Démarrer la vidéo en boucle
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
         // Clear previous error message
        errorLabel.setText("");

        // Validate username and password
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Veuillez remplir tous les champs.");
        } else if ("admin".equals(username) && "password".equals(password)) {
            System.out.println("Connexion réussie!");
             navigateToAccueil(); // Naviguer vers la page d'accueil
        } else {
            errorLabel.setText("Nom d'utilisateur ou mot de passe incorrect.");
        }
       
    }
    private void navigateToAccueil() {
        try {
            // Charger la page Accueil
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/acceuil.fxml"));
            Parent accueilRoot = loader.load();

            // Obtenir la scène actuelle et remplacer son contenu
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(accueilRoot,818, 614);
        
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Erreur lors du chargement de la page d'accueil.");
        }
    }
}
