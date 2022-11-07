package edu.uco.carpooling.domain;

import java.time.LocalDate;
import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaulDate;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.DEFAULT_DATE;

public class RouteDetailDTO {
	
	private UUID id;
	private LocalDate creationTime;
	private LocalDate endTime;
	private LocalDate date;
	
	public RouteDetailDTO() {
		setId(getNewUUID());
		setCreationTime(DEFAULT_DATE);
		setEndTime(DEFAULT_DATE);
	}
	
	public RouteDetailDTO(final UUID id,final LocalDate creationTime,LocalDate endTime,
			final LocalDate date) {
		setId(id);
		setCreationTime(creationTime);
		setEndTime(creationTime);
	}
	
	public UUID getId() {
		return id;
	}
	public final void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}
	public LocalDate getCreationTime() {
		return creationTime;
	}
	public final void setCreationTime(final LocalDate creationTime) {
		this.creationTime = getDefaulDate(creationTime, getDefaultDate(creationTime));
	}
	public LocalDate getEndTime() {
		return endTime;
	}
	public final void setEndTime(final LocalDate endTime) {
		this.endTime = getDefaulDate(endTime, getDefaultDate(endTime));
	}
	public LocalDate getDate() {
		return date;
	}
	public final void setDate(final LocalDate date) {
		this.date = getDefaulDate(date, getDefaultDate(date));
	}
	
	public static final RouteDetailDTO create (final UUID id,final LocalDate creationTime,
			final LocalDate endTime, final LocalDate date) {
		return new RouteDetailDTO(id, creationTime, endTime, date);
	}
}
