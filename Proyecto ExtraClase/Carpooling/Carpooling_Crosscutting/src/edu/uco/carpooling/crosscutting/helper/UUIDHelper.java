package edu.uco.carpooling.crosscutting.helper;

import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;
import java.util.UUID;

public class UUIDHelper {

	private UUIDHelper() {
		super();
	}
	
	public static final UUID getDefaultUUID(final UUID value) {
		return getDefaultIfNull(value, getNewUUID());
	}
	
	public static final UUID getNewUUID() {
		return UUID.randomUUID();
	}
}
