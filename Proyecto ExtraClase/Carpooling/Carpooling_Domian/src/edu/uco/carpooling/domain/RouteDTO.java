package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.domain.builder.RouteDetailDTOBuilder.getRouteDetailDTOBuilder;
import static edu.uco.carpooling.domain.builder.RouteStatusDTOBuilder.getRouteStatusDTOBuilder;
import static edu.uco.carpooling.domain.builder.DriverPerVehicleDTOBuilder.getDriverPerVehicleDTOBuilder;

public class RouteDTO {
	
	private UUID id;
	private int quotas;
	private DriverPerVehicleDTO vehiclePerDriver; 
	private RouteDetailDTO routeDetail;
	private PointInterestDTO pointInterest; 
	private RouteStatus routeStatus;
	
	public RouteDTO() {
		setId(getNewUUID());
		setQuotas(ZERO);
		setVehicleDriverDTO(getDriverPerVehicleDTOBuilder().build());
		setRouteDetailDTO(getRouteDetailDTOBuilder().build());
		setRouteStatus(getRouteStatusDTOBuilder().build());
		setPointInterest(pointInterest);//Camiar cuando se tenga el objeto
		
	}
	
	public RouteDTO(final UUID id,final int quotas,final DriverPerVehicleDTO vehiclePerDriver,
			final RouteDetailDTO routeDetail, final PointInterestDTO pointInterest,final RouteStatus routeStatus) {
		setId(id);
		setQuotas(quotas);
		setVehicleDriverDTO(vehiclePerDriver);
		setRouteDetailDTO(routeDetail);
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
	public DriverPerVehicleDTO getVehicleDriver() {
		return vehiclePerDriver;
	}
	public void setVehicleDriverDTO(final DriverPerVehicleDTO vehiclePerDriver) {
		this.vehiclePerDriver = vehiclePerDriver;
	}
	public RouteDetailDTO getRouteDetailDTO() {
		return routeDetail;
	}
	public void setRouteDetailDTO(final RouteDetailDTO routeDetail) {
		this.routeDetail = routeDetail;
	}
	public PointInterestDTO getPointInterest() {
		return pointInterest;
	}
	public void setPointInterest(final PointInterestDTO pointInterest) {
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
	
	public static final RouteDTO create (final UUID id, final int quotas, 
			final DriverPerVehicleDTO vehiclePerDriver, final RouteDetailDTO routeDetail,
			final PointInterestDTO pointInterest,final RouteStatus routeStatus) {
		return new RouteDTO(id, quotas,vehiclePerDriver, routeDetail, pointInterest, routeStatus);
	}
}
