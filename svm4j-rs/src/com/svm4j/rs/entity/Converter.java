package com.svm4j.rs.entity;

/**
 * Utility class to convert data from the format used by the SVM library to that used by the web service.
 * @author Tommer
 *
 */
public class Converter {
	
	/**
	 * Build an array of doubles from a string representing vector in input space.
	 * 
	 * @param str
	 * @return
	 */
	public static double[] buildArray(String str) {
		String[] strArr = str.split(",\\s+");
		double[] result = new double[strArr.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Double.parseDouble(strArr[i]);
		}
		return result;
	}
	
}
