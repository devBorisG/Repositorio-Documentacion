package edu.uco.carpooling.domain.builder;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;
import edu.uco.carpooling.domain.RouteDetailDTO;

public class RouteDetailDTOBuilder {

	private UUID id;
	private Time creationTime;
	private Time endTime;
	private Date date;

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

	public final RouteDetailDTOBuilder setCreationTime(final Time creationTime) {
		this.creationTime = creationTime;
		return this;
	}

	public final RouteDetailDTOBuilder setEndTime(final Time endTime) {
		this.endTime = endTime;
		return this;
	}

	public final RouteDetailDTOBuilder setDate(final Date date) {
		this.date = date;
		return this;
	}

	public final RouteDetailDTO build() {
		return RouteDetailDTO.create(id, creationTime, endTime, date);
	}
}
