package com.restaurant.app.view.caja;

import com.restaurant.app.model.Venta;
import com.restaurant.app.view.IPOSView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class POSView extends AnchorPane implements Initializable, IPOSView {
	
	private CajaController cajaController;
	private PagoController pagoController;

	private Stage stage;

	private Map<String, Venta> ventas;

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
			this.cajaController = (CajaController)loader.getController();
			cajaController.setStage(stage);
			cajaController.setParent(this, ventas);

	    	Scene scene = new Scene(rootPrincipal);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/style.css").toExternalForm());
			stage.setScene(scene);
				/*
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
			stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

				 */
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		
	}

	public void showFirstScene(Venta v) {
		ventas.remove(String.valueOf(v.getId().longValue()));
		showFirstScene();
	}

	public void showSecondScene(final Map<String, Venta> ventas, final Venta vApagar) {
		this.ventas = ventas;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Pago.fxml"));
			Parent rootPrincipal = (Parent)loader.load();
			this.pagoController = (PagoController)loader.getController();
			pagoController.setStage(stage);
			pagoController.setParent(this);
			pagoController.setVenta(vApagar);

			Scene scene = new Scene(rootPrincipal);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/style.css").toExternalForm());
			stage.setScene(scene);
			/*
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
			stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

			 */
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
