package edu.uco.carpooling.crosscutting.helper;

import java.time.LocalDate;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class DateHelper {
	
	public static final LocalDate DEFAULT_DATE = LocalDate.of(0, 0, 0);
	
	private DateHelper() {
		super();
	}
	
	public static final LocalDate getNewDate() {
		LocalDate date;
		do{
			date = LocalDate.now();
			} while(isDefaultDate(date));
			return date;
	}
	
	public static final LocalDate getDefaulDate(LocalDate value, LocalDate defaultValue) {
		return getDefaultIfNull(value, DEFAULT_DATE);
	}
	
	public static final LocalDate getDefaultDate(LocalDate value) {
		return getDefaulDate(value, DEFAULT_DATE);
	}
	
	public static final boolean isDefaultDate(final LocalDate value) {
		return DEFAULT_DATE.equals(value);
	}
}
