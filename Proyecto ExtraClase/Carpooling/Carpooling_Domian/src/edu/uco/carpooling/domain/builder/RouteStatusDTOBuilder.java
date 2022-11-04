package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.RouteStatus;

public class RouteStatusDTOBuilder {

	private UUID id;
	private String status;
	private boolean afirmation;
	private boolean negative;
	private boolean waiting;
	
	private RouteStatusDTOBuilder() {
		super();
	}
	
	public static final RouteStatusDTOBuilder getRouteStatusBuilder() {
		return new RouteStatusDTOBuilder();
	}

	public final RouteStatusDTOBuilder setId(final UUID id) {
		this.id = id;
		return this;
	}

	public final RouteStatusDTOBuilder setStatus(final String status) {
		this.status = status;
		return this;
	}

	public final RouteStatusDTOBuilder setAfirmation(final boolean afirmation) {
		this.afirmation = afirmation;
		return this;
	}

	public final RouteStatusDTOBuilder setNegative(final boolean negative) {
		this.negative = negative;
		return this;
	}

	public final RouteStatusDTOBuilder setWaiting(final boolean waiting) {
		this.waiting = waiting;
		return this;
	}
	
	public final RouteStatus build() {
		return RouteStatus.create(id, status, afirmation, negative, waiting);
	}
	
}
