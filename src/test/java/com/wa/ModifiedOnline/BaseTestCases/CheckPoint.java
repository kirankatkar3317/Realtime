package com.wa.ModifiedOnline.BaseTestCases;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CheckPoint {
	
	public static final Logger log = LogManager.getLogger(CheckPoint.class.getName());

	public static HashMap<String, String> resultMap = new HashMap<String, String>();

	private static String PASS = "PASS";
	private static String FAIL = "FAIL";

	public static void clearMap() {
		resultMap.clear();
	}


	
	private static void setStatus(String mapKey, String status) {
		resultMap.put(mapKey, status);
		log.info(mapKey + "::" + resultMap.get(mapKey));

	}

	public static void check(String testName, boolean result, String resultMessege) {
		testName = testName.toLowerCase();
		String mapKey = testName + "." + resultMessege;
		try {
			if (result) {
				setStatus(mapKey, PASS);
			} else {
				setStatus(mapKey, FAIL);
			}
		} catch (Exception e) {
			log.error("exception occurs");
			setStatus(mapKey, FAIL);
			e.printStackTrace();
			
		}
	}

	public static void checkFinal(String testName, boolean result, String resultMessege) {

		testName = testName.toLowerCase();
		String mapKey = testName + "." + resultMessege;
		try {
			if (result) {
				setStatus(mapKey, PASS);
			} else {
				setStatus(mapKey, FAIL);
			}
		} catch (Exception e) {
			log.error("exception occurs");
			setStatus(mapKey, FAIL);
			e.printStackTrace();
		}

		ArrayList<String> resultList = new ArrayList<String>();

		for (String key : resultMap.keySet()) {

			resultList.add(resultMap.get(key));
		}

		for (int i = 0; i < resultList.size(); i++) {

			if (resultList.contains(FAIL)) {
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}

	}

}
