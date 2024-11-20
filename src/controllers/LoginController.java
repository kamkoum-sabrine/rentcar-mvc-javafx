package controllers;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private MediaView mediaView;

    @FXML
    private AnchorPane rootPane;

    public void initialize() {
        // Chemin de votre vidéo
        String videoPath = "file:ressources/videos/bg.mp4";  // Assurez-vous que le chemin soit correct
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Connecter le player au MediaView
        mediaView.setMediaPlayer(mediaPlayer);

        // Lancer la vidéo en boucle
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    
    
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin() {
        // Logique de connexion ici
        if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
            // Connexion réussie
            errorLabel.setVisible(false);
        } else {
            // Connexion échouée
            errorLabel.setVisible(true);
        }
    }
}
