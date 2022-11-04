package edu.uco.carpooling.domain;

import java.time.LocalDate;
import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaulDate;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.NOTHING;

public class RouteDetail {
	
	private UUID id;
	private LocalDate creationTime;
	private LocalDate endTime;
	private LocalDate date;
	private RouteDTO route;
	
	public RouteDetail() {
		setId(getNewUUID());
		setCreationTime(NOTHING);
		setEndTime(NOTHING);
		setRoute(route);//Poner constructor
	}
	
	public RouteDetail(final UUID id,final LocalDate creationTime,LocalDate endTime,
			final LocalDate date, final RouteDTO route) {
		setId(id);
		setCreationTime(creationTime);
		setEndTime(creationTime);
		setRoute(route);//Cambiar el parametro
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
	public RouteDTO getRoute() {
		return route;
	}	
	
	public final void setRoute(final RouteDTO route) {
		Object x = null;//realizar cambios 
		this.route = (RouteDTO) getDefaultIfNull(route, x/*builder de ruta*/);
	}
	
	public static final RouteDetail create (final UUID id,final LocalDate creationTime,
			final LocalDate endTime, final LocalDate date, final RouteDTO route) {
		return new RouteDetail(id, creationTime, endTime, date, route);
	}
	
	/*public static final RouteDetail create (final UUID id,final LocalDate creationTime,
			final LocalDate endTime, final LocalDate date, final RouteDTO route) {
		
	}*/
}
