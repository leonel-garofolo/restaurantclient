package com.restaurant.app;


import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.restaurant.app.db.UpdateDB;
import com.restaurant.app.model.ParametrosGlobales;
import com.restaurant.app.persistence.ParametrosGlobalesPersistence;
import com.restaurant.app.persistence.impl.jdbc.ParametrosGlobalesPersistenceJdbc;
import com.restaurant.app.quartz.SimpleJob;
import com.restaurant.app.utils.MiPrinterJob;

public class App {
	final static Logger logger = Logger.getLogger(App.class);
	private ParametrosGlobalesPersistence parametrosGlobalesPersistence;
	
	public static final String PATH_ICONO = "images/icono/icono.jpg";
	@SuppressWarnings("static-access")
	private void iniciar(){				
		logger.debug("Debug Message Logged !!!");
		logger.info("Info Message Logged !!!");
		GargareCollection gargare = new GargareCollection();
		gargare.start();
		
		String bkpAutmatico = "";
		parametrosGlobalesPersistence = new ParametrosGlobalesPersistenceJdbc();
		ParametrosGlobales pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_AUTOMATICO);
		parametrosGlobalesPersistence.load(pg);		
		if(pg!= null) {
			bkpAutmatico = pg.getValue();
			
			if(bkpAutmatico != null && !bkpAutmatico.isEmpty()) {	
				String[] hm = bkpAutmatico.split(":");
				SchedulerFactory sf = new StdSchedulerFactory();
				try {
					Scheduler sched = sf.getScheduler();
					JobDetail job = JobBuilder.newJob(SimpleJob.class)
						    .withIdentity("job1", "group1")
						    .build();

					CronTrigger trigger = TriggerBuilder.newTrigger()
					    .withIdentity("trigger1", "group1")
					    .withSchedule(CronScheduleBuilder.cronSchedule("0 " + hm[1] + " " + hm[0] + " * * ?"))
					    .build();

					sched.scheduleJob(job, trigger);
					sched.start();
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}
				
			}								
		}
		
		UpdateDB updateDb = new UpdateDB();
		updateDb.run();
		MiPrinterJob.preparedPrinter();
		AppClient.iniciar();			
	}
	
	public static void main(String[] args) throws Exception {
		App app = new App();		
		app.iniciar();		
	}
}
