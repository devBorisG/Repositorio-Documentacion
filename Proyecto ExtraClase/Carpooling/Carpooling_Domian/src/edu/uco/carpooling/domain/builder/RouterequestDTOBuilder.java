package edu.uco.carpooling.domain.builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.domain.RouteRequestDTO;

import static edu.uco.carpooling.domain.RouteRequestDTO.create;
import edu.uco.carpooling.domain.RouteStatusDTO;

public class RouterequestDTOBuilder {
	private UUID id;
	private LocalTime serviceRequestTime;
	private LocalDate serviceRequestDate;
	private CustomerDTO customer;
	private RouteStatusDTO status;
	private String routeRequestOrigin;
	private String routeRequestEnd;
	
	private RouterequestDTOBuilder() {
		super();
	}
	
	public static final RouterequestDTOBuilder getRouterequestDTOBuilder() {
		return new RouterequestDTOBuilder();
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	public void setServiceRequestTime(LocalTime serviceRequestTime) {
		this.serviceRequestTime = serviceRequestTime;
	}
	public void setServiceRequestDate(LocalDate serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	public void setStatus(RouteStatusDTO status) {
		this.status = status;
	}
	public void setRouteRequestOrigin(String routeRequestOrigin) {
		this.routeRequestOrigin = routeRequestOrigin;
	}
	public void setRouteRequestEnd(String routeRequestEnd) {
		this.routeRequestEnd = routeRequestEnd;
	}
	
	public final RouteRequestDTO build() {
		return create (id,serviceRequestTime,serviceRequestDate,customer,
				status,routeRequestOrigin,routeRequestEnd);
	}
}
