package br.com.autoservice.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {

	public LogUtil(){
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public static Logger logger = Logger.getLogger(LogUtil.class);
    
	
//	public static void main(String[] args)
//    {
//        //PropertiesConfigurator is used to configure logger from properties file
//        
// 
//        //Log in console in and log file
//        logger.debug("Log4j appender configuration is successful !!");
//    }
//	
}
