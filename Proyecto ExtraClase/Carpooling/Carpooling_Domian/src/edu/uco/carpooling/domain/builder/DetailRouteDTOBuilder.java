package edu.uco.carpooling.domain.builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import edu.uco.carpooling.domain.DetailRouteDTO;
import edu.uco.carpooling.domain.RouteDTO;

public class DetailRouteDTOBuilder {
	
	private UUID id;
	private RouteDTO route;
	private LocalTime creationTime;
	private LocalTime endTime;
	private LocalDate date;
	
	private DetailRouteDTOBuilder() {
		super();
	}
	
	public static final DetailRouteDTOBuilder getDetailRouteDTOBuilder() {
		return new DetailRouteDTOBuilder();
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setRoute(RouteDTO route) {
		this.route = route;
	}

	public void setCreationTime(LocalTime creationTime) {
		this.creationTime = creationTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public final DetailRouteDTO build() {
		return DetailRouteDTO.create(id, route, creationTime, endTime, date);
	}
}
