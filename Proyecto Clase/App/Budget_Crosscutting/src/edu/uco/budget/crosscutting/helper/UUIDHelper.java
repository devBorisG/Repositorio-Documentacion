package edu.uco.budget.crosscutting.helper;

import java.util.UUID;

public final class UUIDHelper {
	
	private UUIDHelper() {
		super();
	}
	
	public static final UUID getDefaultUUID(final UUID value) {
		return ObjectHelper.getDefaultIfNull(value, getNewUUID());	
	}
	
	public static final UUID getNewUUID() {
		return UUID.randomUUID();
	}
}
