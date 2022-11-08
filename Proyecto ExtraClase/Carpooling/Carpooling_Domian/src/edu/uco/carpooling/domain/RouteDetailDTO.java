package edu.uco.carpooling.domain;

import java.sql.Date;
import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaulDate;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.DEFAULT_DATE;

public class RouteDetailDTO {
	
	private UUID id;
	private Date creationTime;
	private Date endTime;
	private Date date;
	
	public RouteDetailDTO() {
		setId(getNewUUID());
		setCreationTime(DEFAULT_DATE);
		setEndTime(DEFAULT_DATE);
	}
	
	public RouteDetailDTO(final UUID id,final Date creationTime,final Date endTime,
			final Date date) {
		setId(id);
		setCreationTime(creationTime);
		setEndTime(endTime);
	}
	
	public UUID getId() {
		return id;
	}
	public final void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public final void setCreationTime(final Date creationTime) {
		this.creationTime = getDefaulDate(creationTime, getDefaultDate(creationTime));
	}
	public Date getEndTime() {
		return endTime;
	}
	public final void setEndTime(final Date endTime) {
		this.endTime = getDefaulDate(endTime, getDefaultDate(endTime));
	}
	public Date getDate() {
		return date;
	}
	public final void setDate(final Date date) {
		this.date = getDefaulDate(date, getDefaultDate(date));
	}
	
	public static final RouteDetailDTO create (final UUID id,final Date creationTime,
			final Date endTime, final Date date) {
		return new RouteDetailDTO(id, creationTime, endTime, date);
	}
}
