package com.restaurant.app.view.caja;

import com.restaurant.app.model.Venta;
import com.restaurant.app.view.IView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PagoController extends AnchorPane implements Initializable, IView {
    private Venta venta;

    @FXML
    private TextField txtPago;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setStage(Stage stage) {

    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @FXML
    public void handleBtn1(ActionEvent actionEvent) {
        operarPago("1");
    }
    @FXML
    public void handleBtn2(ActionEvent actionEvent) {
        operarPago("2");
    }
    @FXML
    public void handleBtn3(ActionEvent actionEvent) {
        operarPago("3");
    }
    @FXML
    public void handleBtn4(ActionEvent actionEvent) {
        operarPago("4");
    }
    @FXML
    public void handleBtn5(ActionEvent actionEvent) {
        operarPago("5");
    }
    @FXML
    public void handleBtn6(ActionEvent actionEvent) {
        operarPago("6");
    }
    @FXML
    public void handleBtn7(ActionEvent actionEvent) {
        operarPago("7");
    }
    @FXML
    public void handleBtn8(ActionEvent actionEvent) {
        operarPago("8");
    }
    @FXML
    public void handleBtn9(ActionEvent actionEvent) {
        operarPago("9");
    }
    @FXML
    public void handleBtn0(ActionEvent actionEvent) {
        operarPago("0");

    }
    @FXML
    public void handleBtnComa(ActionEvent actionEvent) {
        operarPago("0");

    }
    @FXML
    public void handleBtnDelete(ActionEvent actionEvent) {
        operarPago(null);
    }

    @FXML
    public void handleBtnEfectivo(ActionEvent actionEvent) {
        this.venta.setFormaDePago(Venta.FORMA_PAGO.E.toString());

    }

    @FXML
    public void handleBtnTarjeta(ActionEvent actionEvent) {
        this.venta.setFormaDePago(Venta.FORMA_PAGO.T.toString());
    }

    @FXML
    public void handleBtnImprimir(ActionEvent actionEvent) {

    }

    private void operarPago(String valor){
        if(Venta.FORMA_PAGO.E.toString().equals(venta.getFormaDePago())){


        }
    }


}
