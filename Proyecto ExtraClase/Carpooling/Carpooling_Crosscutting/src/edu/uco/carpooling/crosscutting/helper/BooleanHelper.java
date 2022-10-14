package edu.uco.carpooling.crosscutting.helper;

import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class BooleanHelper {
	
	public static final boolean BFALSE = false;
	
	private BooleanHelper() {
		super();
	}
	
	public static final boolean getDefaultBoolean(boolean value,boolean defaultValue) {
		return getDefaultIfNull(value, defaultValue);
	}
	
	public static final boolean getDefaultBoolean(boolean value) {
		return getDefaultBoolean(value, BFALSE);
	}
		
}
