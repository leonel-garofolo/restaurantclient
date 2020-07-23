package com.restaurant.app.informes;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class ReportBase {
 
	protected static final Log log = LogFactory.getLog(ReportBase.class);
	protected JasperPrint jp;
	protected JasperReport jr;
	protected Map params = new HashMap();
	protected DynamicReport dr;
 
	
	public abstract DynamicReport buildReport() throws Exception;
	
	public abstract JRDataSource getDataSource();
 
	public void generateReport() throws Exception {
 			
		dr = buildReport();
  
		/**
		 * Ontenemos la fuente de datos en base a una colleccion de objetos
		 */
  		JRDataSource ds = getDataSource();
  
	  	/**
		 * Creamos el objeto JasperReport que pasamos como parametro a 
		 * DynamicReport,junto con una nueva instancia de ClassicLayoutManager 
		 * y el JRDataSource
		 */
  		jr = DynamicJasperHelper.generateJasperReport(dr, getLayoutManager(), params);
  
	  	/**
	  	 * Creamos el objeto que imprimiremos pasando como parametro
		 * el JasperReport object, y el JRDataSource
		 */
  		log.debug("Filling the report");
  		if (ds != null){
  			jp = JasperFillManager.fillReport(jr, params, ds);
  		}else{
  			jp = JasperFillManager.fillReport(jr, params);
  			log.debug("Filling done!");
  			log.debug("Exporting the report (pdf, xls, etc)");
  		}
  			
  		exportReport();
 
        log.debug("test finished");
		 
	}
 
	
 
	protected LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}
 
	
	
	protected void exportReport() throws Exception {
 
			final String path=System.getProperty("user.dir")+ "/target/reports/" + this.getClass().getCanonicalName()+ ".pdf";
			
			log.debug("Exporing report to: " + path);
			JRPdfExporter exporter = new JRPdfExporter();
			File outputFile = new File(path);
			File parentFile = outputFile.getParentFile();
			if (parentFile != null)
			  			parentFile.mkdirs();
			
	  		FileOutputStream fos = new FileOutputStream(outputFile);
			  
	  		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
	  		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
	  
	  		exporter.exportReport();
	  		log.debug("Report exported: " + path);
			
	}
		 	
	
	protected void exportToJRXML() throws Exception {
		if (this.jr != null){
			DynamicJasperHelper.generateJRXML(this.jr, "UTF-8",System.getProperty("user.dir")+ "/target/reports/" + this.getClass().getCanonicalName() + ".jrxml");
			
		} else {
			DynamicJasperHelper.generateJRXML(this.dr, this.getLayoutManager(), this.params, "UTF-8",System.getProperty("user.dir")+ "/target/reports/" + this.getClass().getCanonicalName() + ".jrxml");
		}
	}	
	
	
}