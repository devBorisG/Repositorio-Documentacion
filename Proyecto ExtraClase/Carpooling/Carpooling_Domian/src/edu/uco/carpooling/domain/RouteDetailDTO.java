package edu.uco.carpooling.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.TimeHelper.getDefaultTimeIfNull;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaulDate;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.uco.carpooling.crosscutting.helper.TimeHelper.TIME;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.DEFAULT_DATE;

public class RouteDetailDTO {
	
	private UUID id;
	private Time creationTime;
	private Time endTime;
	private Date date;
	
	public RouteDetailDTO() {
		setId(getNewUUID());
		setCreationTime(TIME);
		setEndTime(TIME);
		setDate(DEFAULT_DATE);
	}
	
	public RouteDetailDTO(final UUID id,final Time creationTime,final Time endTime,
			final Date date) {
		setId(id);
		setCreationTime(creationTime);
		setEndTime(endTime);
		setDate(date);
	}
	
	public static final RouteDetailDTO create (final UUID id,final Time creationTime,
			final Time endTime, final Date date) {
		return new RouteDetailDTO(id, creationTime, endTime, date);
	}
	
	public static final RouteDetailDTO create (final String id,final Time creationTime,
			final Time endTime, final Date date) {
		return new RouteDetailDTO(getUUIDFromString(id), creationTime, endTime, date);
	}
	
	public UUID getId() {
		return id;
	}
	public final void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}
	public Time getCreationTime() {
		return creationTime;
	}
	public final void setCreationTime(final Time creationTime) {
		this.creationTime = getDefaultTimeIfNull(creationTime);
	}
	public Time getEndTime() {
		return endTime;
	}
	public final void setEndTime(final Time endTime) {
		this.endTime = getDefaultTimeIfNull(endTime);
	}
	public Date getDate() {
		return date;
	}
	public final void setDate(final Date date) {
		this.date = getDefaulDate(date, getDefaultDate(date));
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
}
