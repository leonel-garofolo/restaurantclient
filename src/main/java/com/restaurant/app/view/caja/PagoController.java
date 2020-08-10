package com.restaurant.app.view.caja;

import com.restaurant.app.model.LineaDeVenta;
import com.restaurant.app.model.Venta;
import com.restaurant.app.persistence.VentaPersistence;
import com.restaurant.app.persistence.impl.jdbc.VentaPersistenceJdbc;
import com.restaurant.app.printer.pos.CocinaDocument;
import com.restaurant.app.printer.pos.PrinterService;
import com.restaurant.app.printer.pos.model.Detail;
import com.restaurant.app.printer.pos.model.Footer;
import com.restaurant.app.printer.pos.model.Header;
import com.restaurant.app.printer.pos.model.Line;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
            printTicket();
            posView.showFirstScene(venta);

        }
    }

    private void printTicket(){
        double importe = Double.valueOf(txtPago.getText().trim());
        if(importe > 0){
            CocinaDocument cocinaDocument = new CocinaDocument();
            Header h = new Header();
            h.setTitle("Grun");
            cocinaDocument.setHeader(h);

            Detail d =new Detail();
            List<Line> line = new ArrayList<>();
            for(LineaDeVenta lv: venta.getLineaDeVentaList()){
                Line l = new Line();
                l.setProductName(lv.getProducto().getNombre());
                l.setProductPrice(String.valueOf(lv.getSubTotal().doubleValue()));
                line.add(l);
            }
            d.setLines(line);
            d.setTotal(txtPago.getText().trim());
            cocinaDocument.setDetail(d);

            Footer f = new Footer();
            f.setNote("");
            cocinaDocument.setFooter(f);

            PrinterService printerService = new PrinterService();
            System.out.println(printerService.getPrinters());

            //print some stuff. Change the printer name to your thermal printer name.
            printerService.printString("XP-58", "\n\n " + cocinaDocument.build() + " \n\n\n\n\n");
            // cut that paper!
            byte[] cutP = new byte[] { 0x1d, 'V', 1 };

            printerService.printBytes("XP-58", cutP);
        } else
            Message.error("El importe a pagar debe ser mayor a 0.");
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
