package com.restaurant.app.services;

import com.restaurant.app.model.LineaDeVenta;
import com.restaurant.app.model.Venta;
import com.restaurant.app.persistence.LineaDeVentaPersistence;
import com.restaurant.app.persistence.ProductosPersistence;
import com.restaurant.app.persistence.VentaPersistence;
import com.restaurant.app.persistence.impl.jdbc.LineaDeVentaPersistenceJdbc;
import com.restaurant.app.persistence.impl.jdbc.VentaPersistenceJdbc;
import com.restaurant.app.printer.pos.CocinaDocument;
import com.restaurant.app.printer.pos.PrinterService;
import com.restaurant.app.printer.pos.model.Detail;
import com.restaurant.app.printer.pos.model.Footer;
import com.restaurant.app.printer.pos.model.Header;
import com.restaurant.app.printer.pos.model.Line;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PrintTicket {
    private VentaPersistence ventaPersistence;
    private LineaDeVentaPersistence lineaDeVentaPersistence;
    private ProductosPersistence productosPersistence;
    private Long ventaId;

    public PrintTicket(@NotNull  Long ventaId) {
        this.ventaId = ventaId;
    }

    public void buildAndPrint(){
        this.ventaPersistence = new VentaPersistenceJdbc();
        this.lineaDeVentaPersistence = new LineaDeVentaPersistenceJdbc();
        Venta venta = ventaPersistence.findById(ventaId);
        venta.setLineaDeVentaList(lineaDeVentaPersistence.findAll());

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
        d.setTotal(venta.getImporte().toString());
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
}
