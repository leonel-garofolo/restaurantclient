package com.restaurant.app.view.caja;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.restaurant.app.utils.UtilView;
import com.restaurant.app.view.IPOSView;
import com.restaurant.app.view.IView;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class POSView extends AnchorPane implements Initializable, IPOSView {
	
	private AnchorPane cajaView;
	private Stage stage;

	private String user;
	private String perfil;	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.user = "Leonel";
		this.perfil = "";
	}
	
	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@Override
	public void showFirstScene() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Caja.fxml"));
			Parent rootPrincipal = (Parent)loader.load();				
			IView controller = (CajaController)loader.getController();
	    	controller.setStage(stage);

	    	Scene scene = new Scene(rootPrincipal);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/style.css").toExternalForm());
			stage.setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		
	}
}
