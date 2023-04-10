package Utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Ordering;

public class Util {
	public static final Logger log = LogManager.getLogger(Util.class.getName());

	/***
	 * sleep for specified no of miliseconds
	 * 
	 * @param msec
	 * @param info
	 */

	public static void sleep(long msec, String info) {
		if (info != null) {

			System.out.println("waiting" + msec + "seconds" + info);
		}
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/***
	 * sleep for no. of miliseconds
	 * 
	 * @param msec
	 */

	public static void sleep(long msec) {
		sleep(msec, null);
	}

	/***
	 * get random no from min to max number and that we have to mention
	 * 
	 * @param min
	 * @param max
	 * @return
	 */

	public static int getRandomNumber(int min, int max) {
		int diff = max - min;
		int randomnumber = (int) (min + Math.random() * diff);
		log.info("Random Number" + randomnumber + "within the range of" + min + "to" + max);
		return randomnumber;
	}

	/***
	 * get random no from 1 to anym num
	 * 
	 * @param min
	 * @param max
	 * @return
	 * @return
	 */
	public static int getRandomNumber(int num) {
		return getRandomNumber(1, num);
	}

	/***
	 * 0 * get any random string of any length
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890@#$%^";
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * chars.length());
			sb.append(chars.charAt(index));
		}
		String randomString = sb.toString();
		log.info("Random string with length :: " + length + " is " + randomString);
		return randomString;

	}

	/***
	 * get random string with length 10
	 * 
	 * @return
	 */

	public static String getRandomString() {
		return getRandomString(10);
	}

	/***
	 * get simple date from format which we will mention
	 * 
	 * @param format
	 * @return
	 */

	public static String getSimpleDateFormat(String format) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String formattedDate = formatter.format(date);
		log.info("the date with:: " + format + "is" + formattedDate);
		return formattedDate;
	}

	/***
	 * get the date with timestamp also
	 * 
	 * @return
	 */

	public static String getCurrentDateAndTime() {
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String formattedDate = formatter.format(calender.getTime()).replace("/", "_").replace(":", "_");
		log.info("date and time :: " + formattedDate);
		return formattedDate;
	}

	/***
	 * to check to text strings are contains each other not.
	 * 
	 * @param actualText
	 * @param ExpectedText
	 * @return
	 */

	public static boolean verifyTextContains(String actualText, String ExpectedText) {
		if (actualText.toLowerCase().contains(ExpectedText.toLowerCase())) {

			log.info("the actual text from Web Appplication UI is ::" + actualText);
			log.info("expected text from Web Appplication UI is ::" + ExpectedText);
			log.info("### verification contains !!!");

			return true;
		} else {
			log.error("the actual text from Web Appplication UI is ::" + actualText);
			log.error("expected text from Web Appplication UI is ::" + ExpectedText);
			log.error("### verification does not contains !!!");
			return false;
		}
	}

	/***
	 * to check to text matchung are contains each other not.
	 * 
	 * @param actualText
	 * @param ExpectedText
	 * @return
	 */

	public static boolean verifyTextMatches(String actualText, String ExpectedText) {
		if (actualText.equals(ExpectedText)) {

			log.info("the actual text from Web Appplication UI is ::" + actualText);
			log.info("expected text from Web Appplication UI is ::" + ExpectedText);
			log.info("### verification contains !!!");

			return true;
		} else {
			log.error("the actual text from Web Appplication UI is ::" + actualText);
			log.error("expected text from Web Appplication UI is ::" + ExpectedText);
			log.error("### verification does not contains !!!");
			return false;
		}
	}

	/***
	 * 
	 * @param actualList
	 * @param expectedList
	 * @return
	 */

	public void verifyListIsNotEmpty(List<String> listOfItems) {
		int listSize = listOfItems.size();
		log.info("size of list ::" + listSize);
		if (listSize > 0) {
			log.info("List is not empty");
		} else {
			log.error("List is empty");
		}
	}

	public static boolean verifyListContains(List<String> actualList, List<String> expectedList) {
		int expectedListSize = expectedList.size();
		for (int i = 0; i < expectedListSize; i++) {

			if (!actualList.contains(expectedList.get(i))) {
				return false;
			}
		}
		log.info("the acutal list contains expected list");
		return true;
	}

	/***
	 * 
	 * @param actualList
	 * @param expectedList
	 * 
	 * @return
	 */
	public static boolean verifyListMatches(List<String> actualList, List<String> expectedList) {
		boolean found = false;
		int expectedListSize = expectedList.size();
		int actualListSize = actualList.size();
		if (expectedListSize != actualListSize) {
			return false;
		}
		for (int i = 0; i < actualListSize; i++) {
			found = false;
			for (int j = 0; j < expectedListSize; j++) {
				if (verifyTextMatches(actualList.get(i), expectedList.get(j))) {

					found = true;
					break;
				}
			}
		}
		if (found) {
			log.info("Actual list matches expected list!!!");
			return true;

		} else {
			log.error("Actual list does not matches expected list!!!");
			return false;
		}
	}

	/***
	 * 
	 * @param actList
	 * @param item
	 * @return
	 */
	public static boolean verifyitemPresentInList(List<String> actList, String item) {
		int actListSize = actList.size();
		for (int i = 0; i < actListSize; i++) {
			if (!actList.contains(item)) {
				log.error("item not present in list!!!");
				return false;
			}

		}
		log.info("item is present in list!!!");
		return true;
	}

	/***
	 * 
	 * @param actList
	 * @return
	 */
	public static boolean VerifyOrderList(List<String> actList) {
		boolean sorted = Ordering.natural().isOrdered(actList);
		return sorted;
	}
	
	
	public static String getScreenshotName(String methodName, String browserName) {
		String localDateAndTime = Util.getCurrentDateAndTime();
		StringBuilder name = new StringBuilder().append(browserName).append("_").append(methodName).append("_").append(localDateAndTime);
		return name.toString();
		
	}
	
	public static String getExtentReportName() {
		String localDateAndTime = Util.getCurrentDateAndTime();
		StringBuilder name = new StringBuilder().append("Test_Automation").append("_").append(localDateAndTime).append(".Html");
		return name.toString();
	}
	
		


}