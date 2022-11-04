package edu.uco.carpooling.domain;

import java.sql.Date;
import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaulDate;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.NOTHING;

public class RouteDetailDTO {
	
	private UUID id;
	private Date creationTime;
	private Date endTime;
	private Date date;
	private RouteDTO route;
	
	public RouteDetailDTO() {
		setId(getNewUUID());
		setCreationTime(NOTHING);
		setEndTime(NOTHING);
		setRoute(route);//Poner constructor
	}
	
	public RouteDetailDTO(final UUID id,final Date creationTime,Date endTime,
			final Date date, final RouteDTO route) {
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
	public RouteDTO getRoute() {
		return route;
	}	
	
	public final void setRoute(final RouteDTO route) {
		Object x = null;//realizar cambios 
		this.route = (RouteDTO) getDefaultIfNull(route, x/*builder de ruta*/);
	}
	
	public static final RouteDetailDTO create (final UUID id,final Date creationTime,
			final Date endTime, final Date date, final RouteDTO route) {
		return new RouteDetailDTO(id, creationTime, endTime, date, route);
	}
	
	/*public static final RouteDetail create (final UUID id,final LocalDate creationTime,
			final LocalDate endTime, final LocalDate date, final RouteDTO route) {
		
	}*/
}
