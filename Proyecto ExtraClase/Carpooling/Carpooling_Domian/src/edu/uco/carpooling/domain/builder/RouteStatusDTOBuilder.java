package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.RouteStatusDTO;

public class RouteStatusDTOBuilder {

	private UUID id;
	private boolean status;
	private String valueDefault;
	
	private RouteStatusDTOBuilder() {
		super();
	}
	
	public static final RouteStatusDTOBuilder getRouteStatusDTOBuilder() {
		return new RouteStatusDTOBuilder();
	}

	public final RouteStatusDTOBuilder setId(final UUID id) {
		this.id = id;
		return this;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setValueDefault(String valueDefault) {
		this.valueDefault = valueDefault;
	}

	public final RouteStatusDTO build() {
		return RouteStatusDTO.create(id, status,valueDefault);
	}
	
}
