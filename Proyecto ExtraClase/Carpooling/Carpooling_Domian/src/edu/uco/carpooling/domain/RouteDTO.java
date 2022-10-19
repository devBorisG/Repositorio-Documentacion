package edu.uco.carpooling.domain;

import java.time.LocalDate;
import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.isLessThan;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.uco.carpooling.crosscutting.helper.BooleanHelper.getDefaultBoolean;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.NOTHING;
import static edu.uco.carpooling.crosscutting.helper.BooleanHelper.BFALSE;

public class RouteDTO {
	
	private UUID id;
	private int quota;
	private LocalDate creationTime;
	private LocalDate endTime;
	private boolean status;
	private String startRoute;
	private String endRoute;
	//private DriverVehicle conductorVehiculo;

	public RouteDTO() {
		setId(getNewUUID());
		setQuota(ZERO);
		setCreationTime(NOTHING);
		setEndTime(NOTHING);
		setStatus(BFALSE);
		setStartRoute(EMPTY);
		setEndRoute(EMPTY);
	}
	
	public RouteDTO(final UUID id,final int quota,final LocalDate creationTime,final LocalDate endTime,
			final boolean status, final String starRoute,final String endRoute) {
		setId(getDefaultUUID(id));
		setQuota(quota);
		setCreationTime(creationTime);
		setEndTime(endTime);
		setStatus(status);
		setStartRoute(startRoute);
		setEndRoute(endRoute);
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}
	public int getQuota() {
		return quota;
	}
	public void setQuota(final int quota) {
		this.quota = isLessThan(quota, ZERO)? ZERO : quota;
	}
	public LocalDate getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(final LocalDate creationTime) {
		this.creationTime = getDefaultDate(creationTime);
	}
	public LocalDate getEndTime() {
		return endTime;
	}
	public void setEndTime(final LocalDate endTime) {
		this.endTime = getDefaultDate(endTime);
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(final boolean status) {
		this.status = getDefaultBoolean(status);
	}
	public String getStartRoute() {
		return startRoute;
	}
	public void setStartRoute(final String startRoute) {
		this.startRoute = applyTrim(startRoute);
	}
	public String getEndRoute() {
		return endRoute;
	}
	public void setEndRoute(final String endRoute) {
		this.endRoute = applyTrim(endRoute);
	}
}
