package com.restaurant.app.utils;

import java.util.Optional;

import com.restaurant.app.view.custom.PasswordDialog;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class Message {

    public static void info(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void error(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean option(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
           return false;
        }
    }
    
    public static boolean optionYesNo(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText(null);
        alert.setContentText(message);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Sí");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
           return false;
        }
    }
    
    public static String optionSecurity(){
    	PasswordDialog alert = new PasswordDialog();
      
        // Traditional way to get the response value.
        Optional<String> result = alert.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        return "";
    }
    
    public static String addElement(String element){
    	TextInputDialog alert = new TextInputDialog("Ingrese el texto aqui...");
        alert.setTitle("Nuevo Elemento");
        alert.setHeaderText(null);
        alert.setContentText(element);
        
        // Traditional way to get the response value.
        Optional<String> result = alert.showAndWait();
        if (result.isPresent()){
            return result.get().toUpperCase();
        }
        return "";
    }
}
