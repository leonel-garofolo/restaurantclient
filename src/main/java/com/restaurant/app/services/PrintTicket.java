package com.restaurant.app.services;

import com.restaurant.app.model.*;
import com.restaurant.app.persistence.*;
import com.restaurant.app.persistence.impl.jdbc.*;
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
    private CategoriasPersistence categoriasPersistence;
    private ParametrosGlobalesPersistence parametrosGlobalesPersistence;
    private Long ventaId;

    public PrintTicket(@NotNull  Long ventaId) {
        this.ventaId = ventaId;
        this.ventaPersistence = new VentaPersistenceJdbc();
        this.lineaDeVentaPersistence = new LineaDeVentaPersistenceJdbc();
        this.productosPersistence = new ProductosPersistenceJdbc();
        this.categoriasPersistence = new CategoriasPersistenceJdbc();
        this.parametrosGlobalesPersistence = new ParametrosGlobalesPersistenceJdbc();
    }

    public void buildAndPrint(){
        Venta venta = ventaPersistence.findById(ventaId);
        venta.setLineaDeVentaList(lineaDeVentaPersistence.findLineaDeVentaForVentaId(ventaId));

        TicketDocument ticketDocument = new TicketDocument();
        Header h = new Header();

        ParametrosGlobales pg = new ParametrosGlobales();
        pg.setId(ParametrosGlobales.P_EMPRESA_NOMBRE);
        parametrosGlobalesPersistence.load(pg);
        h.setTitle(pg.getValue());

        pg = new ParametrosGlobales();
        pg.setId(ParametrosGlobales.P_EMPRESA_DIR);
        parametrosGlobalesPersistence.load(pg);
        h.setDireccion(pg.getValue().trim());

        pg = new ParametrosGlobales();
        pg.setId(ParametrosGlobales.P_EMPRESA_LOC);
        parametrosGlobalesPersistence.load(pg);
        h.setLocalidad(pg.getValue().trim());

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
        pg = new ParametrosGlobales();
        pg.setId(ParametrosGlobales.P_FOOTER);
        parametrosGlobalesPersistence.load(pg);
        f.setAgradecimiento(pg.getValue().trim());
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
