package edu.uco.carpooling.crosscutting.helper;

import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class NumberHelper {

	public static final byte ZERO = 0;
	
	private NumberHelper() {
		super();
	}
	
	public static final <T extends Number> T getDefaultNumber(T value, T defaultValue) {
		return getDefaultIfNull(value, defaultValue);	
	}
	
	public static final<T extends Number> Number getDefaultNumber(T value) {
		return getDefaultNumber(value, ZERO);
	}
	
	public static final String getNumberIntAsString(final int value) {
		return getDefaultNumber(value).toString();
	}
	
	public static final <T extends Number> boolean isLessThan(T numberOne, T numberTwo) {
		return getDefaultNumber(numberOne).doubleValue() 
			<
		getDefaultNumber(numberTwo).doubleValue();
	}
	
	public static final <T extends Number> boolean isEqualsThan(T numberOne, T numberTwo) {
		return getDefaultNumber(numberOne).doubleValue() 
			==
		getDefaultNumber(numberTwo).doubleValue();
	}
	
	public static final <T extends Number> boolean isGraterThan(T numberOne, T numberTwo) {
		return getDefaultNumber(numberOne).doubleValue() 
			>
		getDefaultNumber(numberTwo).doubleValue();
	}
	
	public static final <T extends Number> boolean isGraterThanOrEqualsThan(T numberOne, T numberTwo) {
		return getDefaultNumber(numberOne).doubleValue() 
			>=
		getDefaultNumber(numberTwo).doubleValue();
	}
	
	public static final <T extends Number> boolean isLessOrEqualsThan(T numberOne, T numberTwo) {
		return getDefaultNumber(numberOne).doubleValue() 
			<=
		getDefaultNumber(numberTwo).doubleValue();
	}
	
	public static final <T extends Number> boolean isDifferentThan(T numberOne, T numberTwo) {
		return !isEqualsThan(numberOne, numberTwo);
	}
}
