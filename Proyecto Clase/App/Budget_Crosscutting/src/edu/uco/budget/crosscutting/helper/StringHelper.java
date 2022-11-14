package edu.uco.budget.crosscutting.helper;

import java.util.Objects;

import static edu.uco.budget.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class StringHelper {
	
	public static final String EMPTY = "";
	
	private StringHelper() {
		super();
	}
	
	public static final boolean isDefaultString(String value) {
		return EMPTY.equals(value);
	}
	
	public static final String getDefaultString(String value, String defaultValue) {
		return getDefaultIfNull(value, defaultValue);
	}
	
	public static final String getDefaultString(String value) {
		return getDefaultString(value, EMPTY);
	}
	
	public static final String applyTrim(String value) {
		return getDefaultString(value).trim();
	}
	
	public static final boolean isEmpty(String value) {
		return Objects.equals(value, EMPTY);
	}
}
