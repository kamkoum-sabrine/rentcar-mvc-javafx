/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.vehicules;

/**
 *
 * @author LENOVO
 */
public record CoordonnéesGPS(double latitude, double longitude) {
    // Vous pouvez ajouter des méthodes utiles, par exemple pour calculer la distance
    public double distanceTo(CoordonnéesGPS autreCoordonnee) {
        // Implémentation simplifiée pour la distance en km (vous pouvez utiliser la formule de Haversine)
        double earthRadius = 6371.0; // Rayon de la Terre en kilomètres
        double deltaLat = Math.toRadians(autreCoordonnee.latitude - this.latitude);
        double deltaLon = Math.toRadians(autreCoordonnee.longitude - this.longitude);
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                   Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(autreCoordonnee.latitude)) *
                   Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }
}

