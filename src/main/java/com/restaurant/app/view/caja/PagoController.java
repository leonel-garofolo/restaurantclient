package com.restaurant.app.view.caja;

import com.restaurant.app.model.Venta;
import com.restaurant.app.persistence.VentaPersistence;
import com.restaurant.app.persistence.impl.jdbc.VentaPersistenceJdbc;
import com.restaurant.app.services.PrintTicket;
import com.restaurant.app.utils.Message;
import com.restaurant.app.view.IView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PagoController extends AnchorPane implements Initializable, IView {
    private POSView posView;
    private Venta venta;
    private VentaPersistence ventaPersistence;

    @FXML
    private Label lblImporte;
    @FXML
    private TextField txtPago;
    @FXML
    private Label lblVuelto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ventaPersistence = new VentaPersistenceJdbc();
        txtPago.textProperty().addListener((observable, oldValue, newValue) -> {
            double vuelto = 0f;
            try{
                vuelto = Double.valueOf(newValue).doubleValue() - Double.valueOf(lblImporte.getText());
                lblVuelto.setText(String.valueOf(vuelto));
            }catch (NumberFormatException e){
            }
        });
        txtPago.setOnKeyTyped(event -> {
            Matcher matcher = Pattern.compile("^-?\\d+(?:,\\d+)?(?:[Ee][-+]?\\d+)?$").matcher(event.getCharacter());
            if (!matcher.find()) {
                event.consume();
            }
        });
    }

    public void setParent(POSView posView){
        this.posView = posView;
    }

    @Override
    public void setStage(Stage stage) {

    }

    public void setVenta(Venta venta) {
        this.venta = venta;
        this.lblImporte.setText(String.valueOf(venta.getImporte().doubleValue()));
        this.lblVuelto.setText("");
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
        operarPago(".");

    }
    @FXML
    public void handleBtnDelete(ActionEvent actionEvent) {
        operarPago(null);
    }

    @FXML
    public void handleBtnEfectivo(ActionEvent actionEvent) {
        this.venta.setFormaDePago(Venta.FORMA_PAGO.E.toString());
        this.txtPago.setDisable(false);
        txtPago.requestFocus();
    }

    @FXML
    public void handleBtnTarjeta(ActionEvent actionEvent) {
        this.venta.setFormaDePago(Venta.FORMA_PAGO.T.toString());
        this.txtPago.setText(String.valueOf(venta.getImporte().doubleValue()));
        this.txtPago.setDisable(true);
        this.lblVuelto.setText("0");
    }

    @FXML
    public void handleBtnImprimir(ActionEvent actionEvent) {
        if(this.venta != null &&
            venta.getFormaDePago() != null &&
                venta.getImporte() != null){
            venta.setPagado(true);
            venta.setVuelto(new BigDecimal(lblVuelto.getText()));
            ventaPersistence.save(venta);
            double importe = Double.valueOf(txtPago.getText().trim());
            if(importe > 0){
                PrintTicket printTicket = new PrintTicket(this.venta.getId());
                printTicket.buildAndPrint();
            } else
                Message.error("El importe a pagar debe ser mayor a 0.");
            posView.showFirstScene(venta);

        }
    }

    private void operarPago(String valor){
        if(Venta.FORMA_PAGO.E.toString().equals(venta.getFormaDePago())){
            if(valor == null){
                if(txtPago.getText().length() > 0){
                    txtPago.setText(txtPago.getText().substring(0, txtPago.getText().length()-1));
                    return;
                }
            } else if(valor.equals(".")){
                if(!txtPago.getText().contains(".")) txtPago.setText(txtPago.getText() + valor);
            } else
                txtPago.setText(txtPago.getText() + valor);
        }
    }
}
