package edu.uco.carpooling.crosscutting.helper;

import java.sql.Date;
import java.time.LocalDate;

import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class DateHelper {
	
	public static final Date NOTHING = Date.valueOf(LocalDate.of(1, 1, 1));
	
	private DateHelper() {
		super();
	}
	
	public static final Date getDefaulDate(Date value, Date defaultValue) {
		return getDefaultIfNull(value, defaultValue);
	}
	
	public static final Date getDefaultDate(Date value) {
		return getDefaulDate(value, NOTHING);
	}
	
}
