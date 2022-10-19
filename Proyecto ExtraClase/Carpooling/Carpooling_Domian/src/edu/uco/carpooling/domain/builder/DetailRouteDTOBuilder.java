package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.DetailRouteDTO;

public class DetailRouteDTOBuilder {
	private UUID id;
	private int quota;
	//private Route route;
	//private Location location;
	
	private DetailRouteDTOBuilder() {
		super();
	}
	
	public static final DetailRouteDTOBuilder getDetailRouteDTOBuilder() {
		return new DetailRouteDTOBuilder();
	}

	public DetailRouteDTOBuilder setId(UUID id) {
		this.id = id;
		return this;
	}

	public DetailRouteDTOBuilder setQuota(int quota) {
		this.quota = quota;
		return this;
	}
	
	public final DetailRouteDTO build() {
		return DetailRouteDTO.create(id, quota);
	}
}
