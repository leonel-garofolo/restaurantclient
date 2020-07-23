package com.restaurant.app.quartz;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.restaurant.app.persistence.impl.jdbc.ParametrosGlobalesPersistenceJdbc;
import com.restaurant.app.model.ParametrosGlobales;
import com.restaurant.app.persistence.ParametrosGlobalesPersistence;
import com.restaurant.app.utils.Utils;

public class SimpleJob implements Job {
	final static Logger logger = Logger.getLogger(SimpleJob.class);
	private ParametrosGlobalesPersistence parametrosGoblalesPersistence;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 JobKey jobKey = context.getJobDetail().getKey();
		 logger.info("SimpleJob says: " + jobKey + " executing at " + new Date());
		 
		 parametrosGoblalesPersistence = new ParametrosGlobalesPersistenceJdbc();
		 ParametrosGlobales pg = new ParametrosGlobales();
		 pg.setId(ParametrosGlobales.P_EMPRESA_BACKUP);
		 parametrosGoblalesPersistence.load(pg);		
		 if(pg!= null) {
			 try {
				Utils.generarBackup(pg.getValue());
				logger.info("BACKUP GENERADO Hora: " + new Date());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
}
