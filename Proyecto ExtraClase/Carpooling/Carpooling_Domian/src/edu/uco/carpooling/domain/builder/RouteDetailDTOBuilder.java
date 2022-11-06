package edu.uco.carpooling.domain.builder;

import java.time.LocalDate;
import java.util.UUID;
import edu.uco.carpooling.domain.RouteDTO;
import edu.uco.carpooling.domain.RouteDetailDTO;

public class RouteDetailDTOBuilder {

	private UUID id;
	private LocalDate creationTime;
	private LocalDate endTime;
	private LocalDate date;
	private RouteDTO route;

	private RouteDetailDTOBuilder() {
		super();
	}

	public static final RouteDetailDTOBuilder getRouteDetailDTOBuilder() {
		return new RouteDetailDTOBuilder();
	}

	public final RouteDetailDTOBuilder setId(final UUID id) {
		this.id = id;
		return this;
	}

	public final RouteDetailDTOBuilder setCreationTime(final LocalDate creationTime) {
		this.creationTime = creationTime;
		return this;
	}

	public final RouteDetailDTOBuilder setEndTime(final LocalDate endTime) {
		this.endTime = endTime;
		return this;
	}

	public final RouteDetailDTOBuilder setDate(final LocalDate date) {
		this.date = date;
		return this;
	}

	public final RouteDetailDTOBuilder setRoute(final RouteDTO route) {
		this.route = route;
		return this;
	}

	public final RouteDetailDTO build() {
		return RouteDetailDTO.create(id, creationTime, endTime, date, route);
	}
}
