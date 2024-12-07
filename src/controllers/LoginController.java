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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
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
           String videoPath = new File("src/ressources/videos/bg.mp4").toURI().toString();


        Media media = new Media(videoPath);
        mediaPlayer = new MediaPlayer(media);
        backgroundVideo.setMediaPlayer(mediaPlayer);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        errorLabel.setText("");

       
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Veuillez remplir tous les champs.");
        } else if ("admin".equals(username) && "password".equals(password)) {
            System.out.println("Connexion réussie!");
             navigateToAccueil(); 
        } else {
            errorLabel.setText("Nom d'utilisateur ou mot de passe incorrect.");
        }
       
    }
    private void navigateToAccueil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/acceuil.fxml"));
            Parent accueilRoot = loader.load();
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(accueilRoot,screenBounds.getWidth(), screenBounds.getHeight());
        
            stage.setScene(scene);
            
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Erreur lors du chargement de la page d'accueil.");
        }
    }
}
