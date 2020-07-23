package com.restaurant.app.informes;

import java.util.List;

import org.apache.log4j.Logger;


import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class TransaccionesDetalleInforme extends ReportBase{
	final static Logger logger = Logger.getLogger(TransaccionesDetalleInforme.class);
 
    public TransaccionesDetalleInforme(List<String> taras){        
    }

    @Override
    public DynamicReport buildReport() throws Exception {
        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle("TEST");
        drb.setSubtitle("This is a report with many concatenated subreports which should start in a new page");
        drb.setWhenNoDataAllSectionNoDetail();    
      
       
        return drb.build();
    }

    @Override
    public JRDataSource getDataSource() {
        JRDataSource ds = new JRBeanCollectionDataSource(null);
        return ds;
    }

    public void show(){

        JasperViewer.viewReport(this.jp, false);
    }
}
