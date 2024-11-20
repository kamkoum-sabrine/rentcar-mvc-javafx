/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.VehiculesException;

/**
 *
 * @author LENOVO
 */
public class CoutException extends Exception{
    public CoutException(){
        super("Le cout ne doit jamais etre négatif !");
    }
}
