package com.restaurant.app.view;

import com.restaurant.app.model.Venta;
import com.restaurant.app.persistence.VentaPersistence;
import com.restaurant.app.persistence.impl.jdbc.VentaPersistenceJdbc;
import com.restaurant.app.utils.Utils;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
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
    private TableColumn<Venta, String> colFecha;

    @FXML
    private TableColumn<Venta, String> colFormaPago;
    @FXML
    private TableColumn<Venta, Double> colImporte;
    @FXML
    private TableColumn<Venta, Double> colVuelto;

    @FXML
    private TableColumn<Venta, Double> colSubTotal;


    @FXML
    private Label lblTotal;

    private VentaPersistence ventaPersistence;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ventaPersistence = new VentaPersistenceJdbc();
        colFecha.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

            @Override
            public String getValue() {
                SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
                return dFormat.format(cellData.getValue().getFecha().getTime());
            }

        });

        colFormaPago.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

            @Override
            public String getValue() {
                return cellData.getValue().getFormaDePago();
            }
        });

        colImporte.setCellValueFactory(cellData -> new ObservableValueBase<Double>() {

            @Override
            public Double getValue() {
                return cellData.getValue().getImporte().doubleValue();
            }

        });
        colVuelto.setCellValueFactory(cellData -> new ObservableValueBase<Double>() {

            @Override
            public Double getValue() {
                return cellData.getValue().getVuelto().doubleValue();
            }
        });
        colSubTotal.setCellValueFactory(cellData -> new ObservableValueBase<Double>() {

            @Override
            public Double getValue() {
                return cellData.getValue().getImporte().doubleValue();
            }
        });
        cleanFormSearch();
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
        Calendar cDesde = Calendar.getInstance();
        cDesde.setTime(Utils.convertToDate(timeFechaDesde.getValue()));
        cDesde.set(Calendar.HOUR_OF_DAY, 0);
        cDesde.set(Calendar.MINUTE, 0);

        Calendar cHasta = Calendar.getInstance();
        cHasta.setTime(Utils.convertToDate(timeFechaHasta.getValue()));
        cHasta.set(Calendar.HOUR_OF_DAY, 23);
        cHasta.set(Calendar.MINUTE, 59);

        final List<Venta> ventaList = ventaPersistence.findVentasForDate(cDesde.getTime(), cHasta.getTime());
        tblVentas.getItems().clear();
        tblVentas.getItems().addAll(ventaList);

        double total = 0d;
        for(Venta v: ventaList){
            total += v.getImporte().doubleValue();
        }
        lblTotal.setText(String.valueOf(total));
    }

    @FXML
    private void handleLimpiar(ActionEvent event) {
        tblVentas.getItems().clear();
        cleanFormSearch();
    }

    private void cleanFormSearch(){
        timeFechaDesde.setValue(LocalDate.now());
        timeFechaHasta.setValue(LocalDate.now());
    }
}
