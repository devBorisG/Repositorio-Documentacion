package edu.uco.carpooling.domain.builder;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

import edu.uco.carpooling.domain.DetailRouteDTO;

public class DetailRouteDTOBuilder {
	
	private UUID id;
	private Time creationTime;
	private Time endTime;
	private Date date;
	
	private DetailRouteDTOBuilder() {
		super();
	}
	
	public static final DetailRouteDTOBuilder getDetailRouteDTOBuilder() {
		return new DetailRouteDTOBuilder();
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public void setCreationTime(Time creationTime) {
		this.creationTime = creationTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public final DetailRouteDTO build() {
		return DetailRouteDTO.create(id,creationTime, endTime, date);
	}
}
