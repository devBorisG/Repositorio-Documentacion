package edu.uco.carpooling.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.carpooling.crosscutting.helper.TimeHelper.getDefaultTimeIfNull;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;

public class DetailRouteDTO {
	
	private UUID id;
	private Time creationTime;
	private Time endTime;
	private Date date;
	
	public DetailRouteDTO() {
		setId(getNewUUID());
		setCreationTime(getDefaultTimeIfNull(creationTime));
		setEndTime(getDefaultTimeIfNull(endTime));
		setDate(getDefaultDate(date));
	}
	
	public DetailRouteDTO(final UUID id,final Time creationTime,
			final Time endTime,final Date date) {
		setId(id);
		setCreationTime(creationTime);
		setEndTime(endTime);
		setDate(date);
	}
	
	public static final DetailRouteDTO create(final UUID id,
			final Time creationTime,final Time endTime, 
			final Date date) {
		return new DetailRouteDTO(id, creationTime, endTime, date);
	}
	
	public static final DetailRouteDTO create(final String id,
			final Time creationTime,final Time endTime, 
			final Date date) {
		return new DetailRouteDTO(getUUIDFromString(id), creationTime, endTime, date);
	}
	
	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}

	public Time getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Time creationTime) {
		this.creationTime = creationTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
}
