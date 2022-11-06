package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.DetailRouteDTO;
import edu.uco.carpooling.domain.DriverPerVehicleDTO;
import edu.uco.carpooling.domain.PointInterestDTO;
import edu.uco.carpooling.domain.RouteDTO;
import edu.uco.carpooling.domain.RouteStatusDTO;

public final class RouteDTOBuilder {

	private UUID id;
	private int quota;
	private DriverPerVehicleDTO driverPerVehicleDTO;
	private PointInterestDTO pointInterest;
	private DetailRouteDTO detailRouteDTO;
	private RouteStatusDTO routeStatus;

	private RouteDTOBuilder() {
		super();
	}
	
	public static final RouteDTOBuilder getRouteDTOBuilder() {
		return new RouteDTOBuilder();
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public void setDriverPerVehicleDTO(DriverPerVehicleDTO driverPerVehicleDTO) {
		this.driverPerVehicleDTO = driverPerVehicleDTO;
	}

	public void setPointInterest(PointInterestDTO pointInterest) {
		this.pointInterest = pointInterest;
	}

	public void setDetailRouteDTO(DetailRouteDTO detailRouteDTO) {
		this.detailRouteDTO = detailRouteDTO;
	}

	public void setRouteStatus(RouteStatusDTO routeStatus) {
		this.routeStatus = routeStatus;
	}

	public final RouteDTO build() {
		return RouteDTO.create(id, quota, routeStatus, driverPerVehicleDTO, 
				pointInterest, detailRouteDTO);
	}
}
