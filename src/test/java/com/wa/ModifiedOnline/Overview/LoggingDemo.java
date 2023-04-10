package com.wa.ModifiedOnline.Overview;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingDemo {

	
	
	public static final Logger log = LogManager.getLogger(LoggingDemo.class.getName());
	
	public static void main(String[] args) {
		log.trace("Trace msg printed");
		log.debug("drbug msg printed");
		log.info("info msg printed");
		log.error("error msg printed");
		log.fatal("fatal msg printed");
	}
}
