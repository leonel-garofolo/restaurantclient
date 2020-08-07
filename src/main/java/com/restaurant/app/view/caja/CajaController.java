package com.restaurant.app.view.caja;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import com.restaurant.app.view.IView;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CajaController extends AnchorPane implements Initializable, IView {
	
	private enum OPERACIONES{
		OP_CANTIDAD,
		OP_DESCUENTO,
		OP_PRECIO,
		OP_ELIMINAR
	}
	
	private OPERACIONES currentOp;
	
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
	
	@FXML
	public void handleBtn1(Event e) {
		operationItem(1);
	}
	
	@FXML
	public void handleBtn2(Event e) {
		operationItem(2);
	}
	
	@FXML
	public void handleBtn3(Event e) {
		operationItem(3);	
	}
	
	
	@FXML
	public void handleBtn4(Event e) {
		operationItem(4);
	}
	
	
	@FXML
	public void handleBtn5(Event e) {
		operationItem(5);
	}
	
	
	@FXML
	public void handleBtn6(Event e) {
		operationItem(6);
	}
	
	@FXML
	public void handleBtn7(Event e) {
		operationItem(7);
	}
	
	@FXML
	public void handleBtn8(Event e) {
		operationItem(8);
	}
	
	@FXML
	public void handleBtn9(Event e) {
		operationItem(9);
	}
	
	@FXML
	public void handleBtn0(Event e) {
		operationItem(0);
	}
	
	@FXML
	public void handleBtnComa(Event e) {
		operationItem(null);
	}
	
	@FXML
	public void handleBtnPrecio(Event e) {
		currentOp = OPERACIONES.OP_PRECIO;
	}
	
	
	@FXML
	public void handleBtnDelete(Event e) {
		currentOp = OPERACIONES.OP_ELIMINAR;
	}
	
	
	@FXML
	public void handleBtnCant(Event e) {
		currentOp = OPERACIONES.OP_CANTIDAD;
	}
	
	
	@FXML
	public void handleBtnDesc(Event e) {
		currentOp = OPERACIONES.OP_DESCUENTO;		
	}
	
	@FXML
	public void handleBtnPagar(Event e) {
		
	}
	
	@FXML
	public void handleBtnNota(Event e) {
		
	}
	
	@FXML
	public void handleBtnPedir(Event e) {
		
	}
	
	private void operationItem(Integer valor) {
		switch (currentOp) {
		case OP_CANTIDAD:
			
			break;
		case OP_DESCUENTO:
			
			break;
		case OP_PRECIO:
			
			break;
		case OP_ELIMINAR:
			
			break;
			
		default: //coma
			
			break;
		}
	}
}
