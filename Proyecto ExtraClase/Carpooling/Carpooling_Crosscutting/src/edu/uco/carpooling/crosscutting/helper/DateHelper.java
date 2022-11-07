package edu.uco.carpooling.crosscutting.helper;

import java.sql.Date;
import java.time.LocalDate;

import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class DateHelper {
	
	public static final Date DEFAULT_DATE = Date.valueOf(LocalDate.now());
	
	private DateHelper() {
		super();
	}
	
	public static final Date getNewDate() {
		Date date;
		do{
			date = Date.valueOf(LocalDate.now());
			} while(isDefaultDate(date));
			return date;
	}
	
	public static final Date getDefaulDate(Date value, Date defaultValue) {
		return getDefaultIfNull(value, DEFAULT_DATE);
	}
	
	public static final Date getDefaultDate(Date value) {
		return getDefaulDate(value, DEFAULT_DATE);
	}
	
	public static final boolean isDefaultDate(final Date value) {
		return DEFAULT_DATE.equals(value);
	}
}
