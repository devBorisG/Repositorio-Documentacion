package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.domain.builder.RouteDetailDTOBuilder.getRouteDetailBuilder;
import static edu.uco.carpooling.domain.builder.RouteStatusDTOBuilder.getRouteStatusBuilder;

public class RouteDTO {
	
	private UUID id;
	private int quotas;
	private VehicleDriver vehicleDriver; 
	private RouteDetail routeDetail;
	private PointsInterest pointInterest; 
	private RouteStatus routeStatus;
	
	public RouteDTO() {
		setId(getNewUUID());
		setQuotas(ZERO);
		setVehicleDriver(vehicleDriver);//Cambiar cunado se tenga el objeto
		setRouteDetail(getRouteDetailBuilder().build());
		setRouteStatus(getRouteStatusBuilder().build());
		setPointInterest(pointInterest);//Camiar cuando se tenga el objeto
		
	}
	
	public RouteDTO(final UUID id,final int quotas,final VehicleDriver vehicleDriver,final RouteDetail routeDetail,
			final PointsInterest pointInterest,final RouteStatus routeStatus) {
		setId(id);
		setQuotas(quotas);
		setVehicleDriver(vehicleDriver);
		setRouteDetail(routeDetail);
		setPointInterest(pointInterest);
		setRouteStatus(routeStatus);
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(final UUID id) {
		this.id = id;
	}
	public int getQuotas() {
		return quotas;
	}
	public void setQuotas(final int quotas) {
		this.quotas = quotas;
	}
	public VehicleDriver getVehicleDriver() {
		return vehicleDriver;
	}
	public void setVehicleDriver(final VehicleDriver vehicleDriver) {
		this.vehicleDriver = vehicleDriver;
	}
	public RouteDetail getRouteDetail() {
		return routeDetail;
	}
	public void setRouteDetail(final RouteDetail routeDetail) {
		this.routeDetail = routeDetail;
	}
	public PointsInterest getPointInterest() {
		return pointInterest;
	}
	public void setPointInterest(final PointsInterest pointInterest) {
		this.pointInterest = pointInterest;
	}
	public RouteStatus getRouteStatus() {
		return routeStatus;
	}
	public void setRouteStatus(final RouteStatus routeStatus) {
		this.routeStatus = routeStatus;
	}
	
	public final UUID getIdRouts(UUID id) {
		return getId();
	}
}
