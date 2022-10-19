package edu.uco.carpooling.domain.builder;

import java.time.LocalDate;
import java.util.UUID;

import edu.uco.carpooling.domain.RouteDTO;

public final class RouteDTOBuilder {

	private UUID id;
	private int quota;
	private LocalDate creationTime;
	private LocalDate endTime;
	private boolean status;
	private String startRoute;
	private String endRoute;
	//private DriveVehicle conductorVehiculo;
	
	private RouteDTOBuilder() {
		super();
	}
	
	public static final RouteDTOBuilder getRouteDTOBuilder() {
		return new RouteDTOBuilder();
	}
	
	public final RouteDTOBuilder setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public final RouteDTOBuilder setQuota(int quota) {
		this.quota = quota;
		return this;
	}
	
	public final RouteDTOBuilder setCreationTime(LocalDate creationTime) {
		this.creationTime = creationTime;
		return this;
	}
	
	public final RouteDTOBuilder setEndTime(LocalDate endTime) {
		this.endTime = endTime;
		return this;
	}
	
	public final RouteDTOBuilder setStatus(boolean status) {
		this.status = status;
		return this;
	}
	
	public final RouteDTOBuilder setStartRoute(String startRoute) {
		this.startRoute = startRoute;
		return this;
	}
	
	public final RouteDTOBuilder setEndRoute(String endRoute) {
		this.endRoute = endRoute;
		return this;
	}

	public final RouteDTO build() {
		return RouteDTO.create(id, quota, creationTime, endTime, status, startRoute, endRoute);
	}
}
