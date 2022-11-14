package edu.uco.carpooling.domain.builder;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.domain.RouteRequestDTO;

import static edu.uco.carpooling.domain.RouteRequestDTO.create;

public class RouterequestDTOBuilder {
	private UUID id;
	private Time serviceRequestTime;
	private Date serviceRequestDate;
	private CustomerDTO customer;
	private String status;
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
	public void setServiceRequestTime(Time serviceRequestTime) {
		this.serviceRequestTime = serviceRequestTime;
	}
	public void setServiceRequestDate(Date serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	public void setStatus(String status) {
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
