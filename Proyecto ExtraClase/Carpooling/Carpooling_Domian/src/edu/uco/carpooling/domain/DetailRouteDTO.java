package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.isLessThan;

public class DetailRouteDTO {
	
	private UUID id;
	private int quota;
	//private Route route;
	//private Location location;
	
	public DetailRouteDTO() {
		setId(getNewUUID());
		setQuota(ZERO);
	}
	
	public DetailRouteDTO(final UUID id, final int quota) {
		setId(id);
		setQuota(quota);
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
		this.quota = isLessThan(quota, ZERO)?ZERO : quota;
	}
}
