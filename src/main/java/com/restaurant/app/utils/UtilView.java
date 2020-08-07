package com.restaurant.app.utils;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.restaurant.app.view.HerramientasController;
import com.restaurant.app.view.IPOSView;
import com.restaurant.app.view.IView;
import com.restaurant.app.view.PrincipalController;
import com.restaurant.app.view.caja.POSView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UtilView {

	public static Stage openWindows(final Class parent, final String fxmlName, final String title) {
		try {
			FXMLLoader loader = new FXMLLoader(parent.getClassLoader().getResource("fxml/" + fxmlName + ".fxml"));
			Parent rootHerramientas = (Parent)loader.load();
			Stage stage = new Stage();
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.resizableProperty().setValue(Boolean.FALSE);
		    if(title != null) {
		    	stage.setTitle(title);
		    }		    
		    /*
		    Image ico = new Image(App.PATH_ICONO); 
			stage.getIcons().add(ico);
			*/ 
		    Scene scene = new Scene(rootHerramientas);
		    scene.getStylesheets().add(parent.getClassLoader().getResource("fxml/style.css").toExternalForm());
		    stage.setScene(scene);  
		    		    
		    if(loader.getController() instanceof HerramientasController) {
		    	IView controller = (HerramientasController)loader.getController();
		    	controller.setStage(stage);
		    }		    
		    if(loader.getController() instanceof POSView) {
		    	IPOSView controller = (POSView)loader.getController();
		    	controller.setStage(stage);
		    	controller.showFirstScene();
		    }			    
		    stage.show();
		    return stage;
		} catch (IOException e) {			
			e.printStackTrace();
			return null;
		}
	}	
}
