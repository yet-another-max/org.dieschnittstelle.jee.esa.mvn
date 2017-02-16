package org.dieschnittstelle.jee.esa.utils;

import org.apache.log4j.Logger;

import java.io.IOException;

/*
 * well, this util class is rather small, so far...
 */
public class Utils {

	protected static Logger logger = Logger.getLogger(Utils.class);

	/**
	 * this method has been proved very useful for live demos in WS14/15
	 * @param content
	 */
	public static void show(Object content) {
		logger.info("------------ " + content + "\n");
	}

	/** 
	 * also this method is useful for demos
	 */
	public static void step() {
		try {
			System.out.println("/>");
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
