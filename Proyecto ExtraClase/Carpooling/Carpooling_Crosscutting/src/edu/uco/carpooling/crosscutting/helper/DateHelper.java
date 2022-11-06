package edu.uco.carpooling.crosscutting.helper;

import java.time.LocalDate;

import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class DateHelper {
	
	public static final LocalDate NOTHING = LocalDate.now();
	
	private DateHelper() {
		super();
	}
	
	public static final LocalDate getDefaulDate(LocalDate value, LocalDate defaultValue) {
		return getDefaultIfNull(value, defaultValue);
	}
	
	public static final LocalDate getDefaultDate(LocalDate value) {
		return getDefaulDate(value, NOTHING);
	}
}
