package com.restaurant.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CajaController extends AnchorPane implements Initializable, IView {
	private Stage stage;
	private String user;
	private String perfil;
	
	@Override
	public void initialize(URL url, ResourceBundle resouces) {
		this.user = "Leonel";
		this.perfil = "";
		
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
		
	}
		
}
