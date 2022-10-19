package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;

public class TypeVehicleDTO {
	
	private UUID id;
	private String type;
	
	public TypeVehicleDTO() {
		setId(getNewUUID());
		setType(EMPTY);
	}
	
	public TypeVehicleDTO(final UUID id, final String type) {
		setId(id);
		setType(type);
	}
	
	public static final TypeVehicleDTO create(final UUID id,final String type) {
		return new TypeVehicleDTO(id,type);
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}
	public String getType() {
		return type;
	}
	public void setType(final String type) {
		this.type = applyTrim(type);
	}
}
