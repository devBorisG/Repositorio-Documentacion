package edu.uco.carpooling.crosscutting.helper;

import java.time.LocalDate;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class DateHelper {
	
	public static final LocalDate NOTHING = LocalDate.of(1,1,1);
	
	private DateHelper() {
		super();
	}
	
	public static final LocalDate getDefaulDate(LocalDate value, LocalDate defaultValue) {
		return getDefaultIfNull(value, defaultValue);
	}
	
	public static final LocalDate getDefaultDate(LocalDate value) {
		return getDefaulDate(value, NOTHING);
	}
	
	public static final String getDateAsString(final LocalDate value) {
		return getDefaultDate(value).toString();
	}
}
