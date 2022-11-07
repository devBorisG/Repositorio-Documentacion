package edu.uco.carpooling.domain;

import java.util.UUID;


import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.isLessThan;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.domain.builder.DetailRouteDTOBuilder.getDetailRouteDTOBuilder;
import static edu.uco.carpooling.domain.builder.DriverPerVehicleDTOBuilder.getDriverPerVehicleDTOBuilder;
import static edu.uco.carpooling.domain.builder.RouteStatusDTOBuilder.getRouteStatusDTOBuilder;
import static edu.uco.carpooling.domain.builder.PointInterestDTOBuilder.getPointInterestDTOBuilder;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class RouteDTO {
	
	private UUID id;
	private int quota;
	private DriverPerVehicleDTO driverPerVehicleDTO;
	private PointInterestDTO pointInterest;
	private DetailRouteDTO detailRouteDTO;
	private RouteStatusDTO routeStatus;

	public RouteDTO(final UUID id,final int quota,final RouteStatusDTO routeStatus,final DriverPerVehicleDTO driverPerVehicleDTO,
			final PointInterestDTO pointInterest,final DetailRouteDTO detailRouteDTO) {
		setId(getDefaultUUID(id));
		setQuota(quota);
		setRouteStatus(routeStatus);
		setDetailRouteDTO(detailRouteDTO);
		setPointInterest(pointInterest);
		setDriverPerVehicleDTO(driverPerVehicleDTO);
	}
	
	public RouteDTO() {
		setId(getNewUUID());
		setQuota(ZERO);
		setDriverPerVehicleDTO(getDriverPerVehicleDTOBuilder().build());
		setDetailRouteDTO(getDetailRouteDTOBuilder().build());
		setRouteStatus(getRouteStatusDTOBuilder().build());
		setPointInterest(getPointInterestDTOBuilder().build());
	}
	
	public static final RouteDTO create(final UUID id,final int quota,
			final RouteStatusDTO routeStatus, final DriverPerVehicleDTO driverPerVehicleDTO, final PointInterestDTO pointInterest,
			final DetailRouteDTO detailRouteDTO) {
		return new RouteDTO(id,quota,routeStatus, driverPerVehicleDTO, pointInterest, detailRouteDTO);
	}

	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}
	
	public static final String getIntAsString(final int value) {
		return Integer.toString(value);
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
	
	public RouteStatusDTO getRouteStatus() {
		return routeStatus;
	}

	public void setRouteStatus(RouteStatusDTO routeStatus) {
		this.routeStatus = getDefaultIfNull(routeStatus, getRouteStatusDTOBuilder().build());
	}


	public DriverPerVehicleDTO getDriverPerVehicleDTO() {
		return driverPerVehicleDTO;
	}

	public void setDriverPerVehicleDTO(DriverPerVehicleDTO driverPerVehicleDTO) {
		this.driverPerVehicleDTO = getDefaultIfNull(driverPerVehicleDTO, getDriverPerVehicleDTOBuilder().build());
	}

	public PointInterestDTO getPointInterest() {
		return pointInterest;
	}

	public void setPointInterest(PointInterestDTO pointInterest) {
		this.pointInterest = getDefaultIfNull(pointInterest, getPointInterestDTOBuilder().build());
	}
	
	public DetailRouteDTO getDetailRouteDTO() {
		return detailRouteDTO;
	}

	public void setDetailRouteDTO(DetailRouteDTO detailRouteDTO) {
		this.detailRouteDTO = getDefaultIfNull(detailRouteDTO, getDetailRouteDTOBuilder().build());
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
	
	public final String getQuotaAsStrin() {
		return getIntAsString(getQuota());
	}
}
