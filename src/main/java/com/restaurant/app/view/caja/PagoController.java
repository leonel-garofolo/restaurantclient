package com.restaurant.app.view.caja;

import com.restaurant.app.model.Venta;
import com.restaurant.app.persistence.VentaPersistence;
import com.restaurant.app.persistence.impl.jdbc.VentaPersistenceJdbc;
import com.restaurant.app.printer.pos.CocinaDocument;
import com.restaurant.app.printer.pos.PrinterService;
import com.restaurant.app.printer.pos.model.Detail;
import com.restaurant.app.printer.pos.model.Footer;
import com.restaurant.app.printer.pos.model.Header;
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
    private VentaPersistence ventaPersistence;

    @FXML
    private TextField txtPago;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ventaPersistence = new VentaPersistenceJdbc();
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
        if(this.venta != null &&
            venta.getFormaDePago() != null &&
                venta.getImporte() != null){
            ventaPersistence.save(venta);
            printTicket();
        }
    }

    private void printTicket(){
        CocinaDocument cocinaDocument = new CocinaDocument();
        Header h = new Header();
        h.setTitle("Grun");
        cocinaDocument.setHeader(h);
        Detail d =new Detail();
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
    }

    private void operarPago(String valor){
        if(Venta.FORMA_PAGO.E.toString().equals(venta.getFormaDePago())){



        }
    }
}
