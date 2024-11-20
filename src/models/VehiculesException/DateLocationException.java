/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.VehiculesException;

/**
 *
 * @author LENOVO
 */
public class DateLocationException extends Exception{
    public DateLocationException() {
        super("La date de début doit etre inferieure à la date de fin !");
    }
}
