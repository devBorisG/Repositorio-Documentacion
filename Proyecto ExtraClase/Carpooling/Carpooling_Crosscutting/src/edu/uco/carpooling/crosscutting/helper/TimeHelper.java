package edu.uco.carpooling.crosscutting.helper;

import java.sql.Time;
import java.time.LocalTime;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class TimeHelper {
	
	public static final Time TIME = Time.valueOf(LocalTime.now());

	private TimeHelper() {
		super();
	}
	
	public static final Time getDefaultTime(Time value, Time defaultValue) {
		return getDefaultIfNull(value, defaultValue);
	}
	
	public static final Time getDefaultTimeIfNull(Time value) {
		return getDefaultTime(value, TIME);
	}
}
