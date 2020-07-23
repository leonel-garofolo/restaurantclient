package com.restaurant.app;

import org.apache.log4j.Logger;

public class GargareCollection extends Thread {
	final static Logger logger = Logger.getLogger(GargareCollection.class);
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		do{
			try {
				this.sleep(100000);
			} catch (InterruptedException e) {
				logger.error("error thead", e);
				// TODO Auto-generated catch block
				logger.error(e);
			}
			Runtime basurero = Runtime.getRuntime();
			basurero.gc(); // Solicitando ...			
		}while(true);
	}
}
