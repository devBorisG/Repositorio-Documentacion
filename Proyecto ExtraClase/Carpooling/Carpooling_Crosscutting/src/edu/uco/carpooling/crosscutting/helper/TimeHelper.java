package edu.uco.carpooling.crosscutting.helper;

import java.time.LocalTime;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class TimeHelper {
	
	public static final LocalTime TIME = LocalTime.now();

	private TimeHelper() {
		super();
	}
	
	public static final LocalTime getDefaultTime(LocalTime value, LocalTime defaultValue) {
		return getDefaultIfNull(value, defaultValue);
	}
	
	public static final LocalTime getDefaultTimeIfNull(LocalTime value) {
		return getDefaultTime(value, TIME);
	}
}
