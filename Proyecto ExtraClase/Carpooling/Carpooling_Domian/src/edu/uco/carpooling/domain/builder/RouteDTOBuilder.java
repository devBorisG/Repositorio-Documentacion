package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.DriverPerVehicleDTO;
import edu.uco.carpooling.domain.PointInterestDTO;
import edu.uco.carpooling.domain.RouteDTO;
import edu.uco.carpooling.domain.RouteDetailDTO;
import edu.uco.carpooling.domain.RouteStatus;

public class RouteDTOBuilder {

	private UUID id;
	private int quotas;
	private DriverPerVehicleDTO vehiclePerDriver; 
	private RouteDetailDTO routeDetail;
	private PointInterestDTO pointInterest; 
	private RouteStatus routeStatus;
	
	private RouteDTOBuilder() {
		super();
	}
	
	public RouteDTOBuilder getRouteDTOBuilder() {
		return new RouteDTOBuilder();
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setQuotas(int quotas) {
		this.quotas = quotas;
	}

	public void setVehiclePerDriver(DriverPerVehicleDTO vehiclePerDriver) {
		this.vehiclePerDriver = vehiclePerDriver;
	}

	public void setRouteDetail(RouteDetailDTO routeDetail) {
		this.routeDetail = routeDetail;
	}

	public void setPointInterest(PointInterestDTO pointInterest) {
		this.pointInterest = pointInterest;
	}

	public void setRouteStatus(RouteStatus routeStatus) {
		this.routeStatus = routeStatus;
	}
	
	public final RouteDTO build() {
		return RouteDTO.create(id, quotas, vehiclePerDriver, routeDetail, pointInterest, routeStatus);
	}
}
