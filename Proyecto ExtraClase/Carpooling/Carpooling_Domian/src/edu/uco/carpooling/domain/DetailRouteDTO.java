package edu.uco.carpooling.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import static edu.uco.carpooling.domain.builder.RouteDTOBuilder.getRouteDTOBuilder;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.TimeHelper.getDefaultTimeIfNull;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class DetailRouteDTO {
	
	private UUID id;
	private RouteDTO route;
	private LocalTime creationTime;
	private LocalTime endTime;
	private LocalDate date;
	
	public DetailRouteDTO() {
		setId(getNewUUID());
		setCreationTime(getDefaultTimeIfNull(creationTime));
		setEndTime(getDefaultTimeIfNull(endTime));
		setDate(getDefaultDate(date));
		setRoute(getRouteDTOBuilder().build());
	}
	
	public DetailRouteDTO(final UUID id,final RouteDTO route,final LocalTime creationTime,
			final LocalTime endTime,final LocalDate date) {
		setId(id);
		setCreationTime(creationTime);
		setEndTime(endTime);
		setRoute(route);
		setDate(date);
	}
	
	public static final DetailRouteDTO create(final UUID id,final RouteDTO route,
			final LocalTime creationTime,final LocalTime endTime, 
			final LocalDate date) {
		return new DetailRouteDTO(id, route, creationTime, endTime, date);
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}

	public RouteDTO getRoute() {
		return route;
	}

	public void setRoute(final RouteDTO route) {
		this.route = getDefaultIfNull(route, getRouteDTOBuilder().build());
	}

	public LocalTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
