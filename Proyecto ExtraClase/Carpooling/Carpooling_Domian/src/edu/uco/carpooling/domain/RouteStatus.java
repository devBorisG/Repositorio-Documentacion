package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.BooleanHelper.BFALSE;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.BooleanHelper.getDefaultBoolean;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;;

public class RouteStatus {

	private UUID id;
	private String status;
	private boolean afirmation;
	private boolean negative;
	private boolean waiting;
	
	public RouteStatus() {
		setId(getNewUUID());
		setStatus(EMPTY);
		setAfirmation(BFALSE);
		setNegative(BFALSE);
		setWaiting(isWaiting());
	}
	
	public RouteStatus(final UUID id,final String status, final boolean afirmation,
			final boolean negative, final boolean waiting) {
		setId(id);
		setStatus(status);
		setAfirmation(afirmation);
		setNegative(negative);
		setWaiting(waiting);
	}
	
	public UUID getId() {
		return id;
	}
	public final void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}
	public String getStatus() {
		return status;
	}
	public final void setStatus(final String status) {
		this.status = applyTrim(status);
	}
	public boolean isAfirmation() {
		return afirmation;
	}
	public final void setAfirmation(final boolean afirmation) {
		this.afirmation = getDefaultBoolean(afirmation, BFALSE);
	}
	public boolean isNegative() {
		return negative;
	}
	public final void setNegative(final boolean negative) {
		this.negative = getDefaultBoolean(negative, BFALSE);
	}
	public boolean isWaiting() {
		return waiting;
	}
	public final void setWaiting(final boolean waiting) {
		this.waiting = getDefaultBoolean(waiting, BFALSE);
	}
	
	public static final RouteStatus create(final UUID id,final String status,
			final boolean afirmation, final boolean negative,final boolean waiting) {
		return new RouteStatus(id, status, afirmation,negative,waiting);
	}
	
}
