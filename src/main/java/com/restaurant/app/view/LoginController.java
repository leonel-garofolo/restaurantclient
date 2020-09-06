package com.restaurant.app.view;

import com.restaurant.app.model.Usuarios;
import com.restaurant.app.persistence.UsuariosPersistence;
import com.restaurant.app.persistence.impl.jdbc.UsuariosPersistenceJdbc;
import com.restaurant.app.utils.Message;
import com.restaurant.app.utils.Utils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, IView, EventHandler<WindowEvent>{
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtClave;
	@FXML
	private Button btnIngresar;
	private UsuariosPersistence usuariosPersistence;
	
	private Stage stage;
	
	@FXML
	public void handleIngresar(Event e) {
		if(!txtUsuario.getText().isEmpty()
				&& !txtClave.getText().isEmpty()) {
			Usuarios usuario = this.usuariosPersistence.loadForNombre(txtUsuario.getText());
			if(usuario == null) {
				Message.error("Usuario incorrecto.");
				return;
			} else if(!usuario.getClave().equals(txtClave.getText())) {
				Message.error("Clave incorrecta.");
				return;
			}
			
			Usuarios.setUsuarioLogeado(usuario.getNombre());
			switch (String.valueOf(usuario.getIdPerfil())) {
			case "1":
				Usuarios.setPerfilLogeado(Usuarios.P_ADMINISTRADOR);
				break;
			case "2":
				Usuarios.setPerfilLogeado(Usuarios.P_SUPERVISOR);
				break;
			case "3":
				Usuarios.setPerfilLogeado(Usuarios.P_CAJA);
				break;

			default:
				break;
			}
			
			//Stage stage = new Stage();
			Screen screen = Screen.getPrimary();
		    Rectangle2D bounds = screen.getVisualBounds();
		    stage.setX(0);
		    stage.setY(0);
		    stage.setWidth(bounds.getWidth());
		    stage.setHeight(bounds.getHeight());
		    
		    		    
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/PrincipalView.fxml"));
				Parent rootPrincipal = (Parent)loader.load();				
				IView controller = (PrincipalController)loader.getController();
		    	controller.setStage(stage);		    			    	
				Scene scene = new Scene(rootPrincipal);
				scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/style.css").toExternalForm());
				
				stage.setScene(scene);
				stage.resizableProperty().set(false);
				/*
				Image ico = new Image(App.PATH_ICONO); 
				stage.getIcons().add(ico);
				*/ 
				stage.show();
				
				stage.setOnHiding(new EventHandler<WindowEvent>() {

		            public void handle(WindowEvent event) {
		            	System.exit(0);
		            }
		        });		
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		if(Utils.isDebug()) {
			txtUsuario.setText("admin");
			txtClave.setText("123456");
		}
		txtUsuario.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));
		
		this.usuariosPersistence = new UsuariosPersistenceJdbc();		
		txtUsuario.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	handleIngresar(ke);
	            }
	        }
	    });
		txtClave.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	handleIngresar(ke);
	            }
	        }
	    });
	}


	@Override
	public void setStage(Stage stage) {
		this.stage = stage;		
		this.stage.setOnShowing(this);
	}
	
	@Override
	public void handle(WindowEvent e) {
		txtUsuario.requestFocus();		
	}
}
