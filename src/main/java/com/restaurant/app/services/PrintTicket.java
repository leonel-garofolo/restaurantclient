package com.restaurant.app.services;

import com.restaurant.app.model.LineaDeVenta;
import com.restaurant.app.model.Productos;
import com.restaurant.app.model.Venta;
import com.restaurant.app.persistence.LineaDeVentaPersistence;
import com.restaurant.app.persistence.ProductosPersistence;
import com.restaurant.app.persistence.VentaPersistence;
import com.restaurant.app.persistence.impl.jdbc.LineaDeVentaPersistenceJdbc;
import com.restaurant.app.persistence.impl.jdbc.ProductosPersistenceJdbc;
import com.restaurant.app.persistence.impl.jdbc.VentaPersistenceJdbc;
import com.restaurant.app.printer.pos.PrinterService;
import com.restaurant.app.printer.pos.TicketDocument;
import com.restaurant.app.printer.pos.model.Detail;
import com.restaurant.app.printer.pos.model.Footer;
import com.restaurant.app.printer.pos.model.Header;
import com.restaurant.app.printer.pos.model.Line;
import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PrintTicket {
    final static Logger logger = Logger.getLogger(PrintTicket.class);

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
        this.productosPersistence = new ProductosPersistenceJdbc();
        Venta venta = ventaPersistence.findById(ventaId);
        venta.setLineaDeVentaList(lineaDeVentaPersistence.findLineaDeVentaForVentaId(ventaId));

        TicketDocument ticketDocument = new TicketDocument();
        Header h = new Header();
        h.setTitle("Cerveceria Grun");
        h.setMesa(venta.getMesa());
        ticketDocument.setHeader(h);

        Detail d =new Detail();
        List<Line> line = new ArrayList<>();
        Line l;
        for(LineaDeVenta lv: venta.getLineaDeVentaList()){
            l = new Line();
            Productos p = productosPersistence.findById(lv.getProductoId().longValue());
            l.setProductName(p.getNombre());
            l.setProductPrice(String.valueOf(lv.getSubTotal().doubleValue()));
            line.add(l);
        }
        d.setLines(line);
        d.setTotal(venta.getImporte().toString());
        ticketDocument.setDetail(d);

        Footer f = new Footer();
        f.setNote("");
        ticketDocument.setFooter(f);

        PrinterService printerService = new PrinterService();

        logger.info(ticketDocument.build());
        //print some stuff. Change the printer name to your thermal printer name.
        printerService.printString("XP-58", ticketDocument.build());
        // cut that paper!
        byte[] cutP = new byte[] { 0x1d, 'V', 1 };

        printerService.printBytes("XP-58", cutP);
    }
}
