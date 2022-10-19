package edu.uco.carpooling.domain.builder;

import java.util.UUID;
import edu.uco.carpooling.domain.TypeVehicleDTO;

public class TypeVehicleDTOBuilder {
	
	private UUID id;
	private String type;
	
	private TypeVehicleDTOBuilder() {
		super();
	}
	
	public static final TypeVehicleDTO getTypeVehicleDTOBuilder() {
		return new TypeVehicleDTO();
	}
	
	public TypeVehicleDTOBuilder setId(UUID id) {
		this.id = id;
		return this;
	}
	public TypeVehicleDTOBuilder setType(String type) {
		this.type = type;
		return this;
	}
	
	public final TypeVehicleDTO build() {
		return TypeVehicleDTO.create(id, type);
	}
}
