package edu.uco.carpooling.domain;

import java.time.LocalDate;
import java.util.UUID;

public class RouteDTO {
	
	private UUID id;
	private int cupo;
	private LocalDate creationTime;
	private LocalDate endTime;
	private boolean status;
	private String startRoute;
	private String endRoute;
	
	public RouteDTO() {
		super();
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public LocalDate getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(LocalDate creationTime) {
		this.creationTime = creationTime;
	}
	public LocalDate getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getStartRoute() {
		return startRoute;
	}
	public void setStartRoute(String startRoute) {
		this.startRoute = startRoute;
	}
	public String getEndRoute() {
		return endRoute;
	}
	public void setEndRoute(String endRoute) {
		this.endRoute = endRoute;
	}
	
	
	
}
