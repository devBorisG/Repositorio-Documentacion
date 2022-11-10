package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.BooleanHelper.BFALSE;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.BooleanHelper.getDefaultBoolean;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;

public class RouteStatusDTO {

	private UUID id;
	private boolean status;
	private String valueDefault;
	
	public RouteStatusDTO() {
		setId(getNewUUID());
		setStatus(status);
		setValueDefault(valueDefault);
	}
	
	public RouteStatusDTO(final UUID id,final boolean status,final String valueDefault) {
		setId(getNewUUID());
		setStatus(BFALSE);
		setValueDefault(EMPTY);
	}
	
	public static final RouteStatusDTO create(final UUID id,final boolean status,
			final String valueDefault) {
		return new RouteStatusDTO(id,status,valueDefault);
	}
	
	public static final RouteStatusDTO create(final String id,final boolean status,
			final String valueDefault) {
		return new RouteStatusDTO(getUUIDFromString(id),status,valueDefault);
	}
	
	public UUID getId() {
		return id;
	}
	public final void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = getDefaultBoolean(status);
	}

	public String getValueDefault() {
		return valueDefault;
	}

	public void setValueDefault(String valueDefault) {
		this.valueDefault = applyTrim(valueDefault);
	}
	
	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
}
