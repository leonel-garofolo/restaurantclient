package com.restaurant.app.view;

import com.restaurant.app.model.Venta;
import com.restaurant.app.persistence.VentaPersistence;
import com.restaurant.app.persistence.impl.jdbc.VentaPersistenceJdbc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InformesController extends AnchorPane implements Initializable {

    @FXML
    private DatePicker timeFechaDesde;
    @FXML
    private DatePicker timeFechaHasta;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TableView<Venta> tblVentas;

    @FXML
    private Label lblTotal;

    private VentaPersistence ventaPersistence;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ventaPersistence = new VentaPersistenceJdbc();
    }

    @FXML
    private void handleImprimirTicket(ActionEvent event) {

    }

    @FXML
    private void handleImprimir(ActionEvent event) {

    }

    @FXML
    private void handleImprimirDetalle(ActionEvent event) {

    }

    @FXML
    private void handleBuscar(ActionEvent event) {

    }

    @FXML
    private void handleLimpiar(ActionEvent event) {

    }

}
