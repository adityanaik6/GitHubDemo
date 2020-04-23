package com.learning.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class TestProperties {
	public static void main(String[] args) throws IOException{
		Properties config=new Properties();
		String log4jConfPath =System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		Logger log = Logger.getLogger("devpinoyLogger");
		String configpath=System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties";
		FileInputStream fis= new FileInputStream(configpath);
		config.load(fis);
		log.debug("config file loaded");
		//tesing git hub
		//push-pull-clone-commit-add(stage)
		
	}
}
