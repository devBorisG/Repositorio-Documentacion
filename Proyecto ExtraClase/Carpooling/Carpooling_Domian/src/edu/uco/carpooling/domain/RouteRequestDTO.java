package edu.uco.carpooling.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.TimeHelper.getDefaultTimeIfNull;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.domain.builder.CustomerDTOBuilder.getUserDTOBuilder;
import static edu.uco.carpooling.domain.builder.RouteStatusDTOBuilder.getRouteStatusDTOBuilder;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;

public class RouteRequestDTO {

	private UUID id;
	private Time serviceRequestTime;
	private Date serviceRequestDate;
	private CustomerDTO customer;
	private RouteStatusDTO status;
	private String routeRequestOrigin;
	private String routeRequestEnd;
	
	public RouteRequestDTO() {
		setId(getNewUUID());
		setServiceRequestDate(getServiceRequestDate());
		setServiceRequestTime(getServiceRequestTime());
		setRouterequesEnd(EMPTY);
		setRouterequesOrigin(EMPTY);
		setCustomer(getUserDTOBuilder().build());
		setStatus(getRouteStatusDTOBuilder().build());
	}
	
	public  RouteRequestDTO (final UUID id,final Time serviceRequesTime,
			final Date serviceRequestDate,final CustomerDTO customer,
			final RouteStatusDTO status,final String routeRequestOrigin,
			final String routeRequestEnd) {
		setId(id);
		setServiceRequestTime(serviceRequesTime);
		setServiceRequestDate(serviceRequestDate);
		setStatus(status);
		setRouterequesOrigin(routeRequestOrigin);
		setRouterequesEnd(routeRequestEnd);
		setCustomer(customer);
	}
	
	public static final RouteRequestDTO create(final UUID id,final Time serviceRequesTime,
			final Date serviceRequestDate,final CustomerDTO customer,
			final RouteStatusDTO status,final String routeRequestOrigin,
			final String routeRequestEnd) {
		return new RouteRequestDTO(id, serviceRequesTime, serviceRequestDate, customer, 
				status, routeRequestOrigin, routeRequestEnd);
	}
	
	public static final RouteRequestDTO create(final String id,final Time serviceRequesTime,
			final Date serviceRequestDate,final CustomerDTO customer,
			final RouteStatusDTO status,final String routeRequestOrigin,
			final String routeRequestEnd) {
		return new RouteRequestDTO(getUUIDFromString(id), serviceRequesTime, serviceRequestDate, customer, 
				status, routeRequestOrigin, routeRequestEnd);
	}
	
	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}
	public Time getServiceRequestTime() {
		return serviceRequestTime;
	}
	public void setServiceRequestTime(final Time serviceRequestTime) {
		this.serviceRequestTime = getDefaultTimeIfNull(serviceRequestTime);
	}
	public Date getServiceRequestDate() {
		return serviceRequestDate;
	}
	public void setServiceRequestDate(final Date serviceRequestDate) {
		this.serviceRequestDate = getDefaultDate(serviceRequestDate);
	}
	public CustomerDTO getCustomer() {
		return customer;
	}
	public void setCustomer(final CustomerDTO customer) {
		this.customer = getDefaultIfNull(customer, getUserDTOBuilder().build());
	}
	public RouteStatusDTO getStatus() {
		return status;
	}
	public void setStatus(final RouteStatusDTO status) {
		this.status = getDefaultIfNull(status, getRouteStatusDTOBuilder().build());
	}
	public String getRouterequesOrigin() {
		return routeRequestOrigin;
	}
	public void setRouterequesOrigin(final String routeRequestOrigin) {
		this.routeRequestOrigin = applyTrim(routeRequestOrigin);
	}
	public String getRouterequesEnd() {
		return routeRequestEnd;
	}
	public void setRouterequesEnd(final String routeRequestEnd) {
		this.routeRequestEnd = applyTrim(routeRequestEnd);
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
}
