package com.restaurant.app.informes;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class TransaccionesInforme  extends ReportBase{
    private List<String> taras;
    public TransaccionesInforme(List<String> taras){
        this.taras = taras;
    }

    @Override
    public DynamicReport buildReport() throws Exception {
        DynamicReportBuilder drb = new DynamicReportBuilder();

        Style numberColStyle = new Style("col number");
        numberColStyle.setHorizontalAlign(HorizontalAlign.CENTER);

        Style headerVariables = new Style("headerVariables");
        headerVariables.setFont(Font.ARIAL_BIG_BOLD);
        headerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
        headerVariables.setVerticalAlign(VerticalAlign.TOP);
        headerVariables.setStretchWithOverflow(true);

        AbstractColumn coltransaccion = ColumnBuilder.getNew()
                .setColumnProperty("transaccion", String.class.getName())
                .setTitle("Nro").setWidth(10)
                .setStyle(numberColStyle)
                .build();

        AbstractColumn colChasis = ColumnBuilder.getNew()
                .setColumnProperty("patente.patente", String.class.getName())
                .setTitle("Patente").setWidth(15)
                .setStyle(numberColStyle)
                .build();

        AbstractColumn colProducto = ColumnBuilder.getNew()
                .setColumnProperty("producto.nombre", String.class.getName())
                .setTitle("Producto").setWidth(30)
                .build();
        AbstractColumn colCliente = ColumnBuilder.getNew()
                .setColumnProperty("cliente.nombre", String.class.getName())
                .setTitle("Cliente").setWidth(30)
                .build();

        AbstractColumn colTransporte = ColumnBuilder.getNew()
                .setColumnProperty("transporte.nombre", String.class.getName())
                .setTitle("Transporte").setWidth(30)
                .build();
        AbstractColumn colProcedencia = ColumnBuilder.getNew()
                .setColumnProperty("procedencias.nombre", String.class.getName())
                .setTitle("Procedencias").setWidth(30)
                .build();
        AbstractColumn colPesoEntrada = ColumnBuilder.getNew()
                .setColumnProperty("pesoEntrada", BigDecimal.class.getName())
                .setTitle("Entrada").setWidth(17)
                .setPattern("0.00")
                .setStyle(numberColStyle)
                .build();
        AbstractColumn colPesoSalida = ColumnBuilder.getNew()
                .setColumnProperty("pesoSalida", BigDecimal.class.getName())
                .setTitle("Salida").setWidth(17)
                .setPattern("0.00")
                .setStyle(numberColStyle)
                .build();

        AbstractColumn colPesoNeto = ColumnBuilder.getNew()
                .setColumnProperty("pesoNeto", BigDecimal.class.getName())
                .setPattern("0.00").setWidth(17)
                .setTitle("Neto")
                .setStyle(numberColStyle)
                .build();

        Style g1Variables = new Style();
        g1Variables.setFont(Font.ARIAL_MEDIUM_BOLD);
        g1Variables.setTextColor(Color.black);
        g1Variables.setHorizontalAlign(HorizontalAlign.RIGHT);
        g1Variables.setVerticalAlign(VerticalAlign.MIDDLE);

        Style titleStyle = new Style();
        titleStyle.setFont(Font.ARIAL_BIG_BOLD);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);

        drb.addGlobalFooterVariable(colPesoNeto, DJCalculation.SUM, g1Variables);
        drb.addColumn(coltransaccion)
                .addColumn(colChasis)
                .addColumn(colProducto)
                .addColumn(colCliente)
                .addColumn(colTransporte)
                .addColumn(colProcedencia)
                .addColumn(colPesoEntrada)
                .addColumn(colPesoSalida)
                .addColumn(colPesoNeto)
                .setTitle("Consulta de Transaciones")
                .setTitleStyle(titleStyle)
                .setPrintBackgroundOnOddRows(true)
                .setGrandTotalLegend("Total")
                .setGrandTotalLegendStyle(g1Variables)
                .setUseFullPageWidth(true);
        drb.addAutoText(AutoText.AUTOTEXT_CREATED_ON,
                AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_LEFT,
                AutoText.PATTERN_DATE_DATE_TIME);
        drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);
        return drb.build();
    }

    @Override
    public JRDataSource getDataSource() {
        JRDataSource ds = new JRBeanCollectionDataSource(taras);
        return ds;
    }

    public void show(){

        JasperViewer.viewReport(this.jp, false);
    }
}
