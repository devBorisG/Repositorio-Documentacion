package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.VehicleDTO;
import static edu.uco.carpooling.domain.VehicleDTO.create;

public class VehicleDTOBuilder {

	private UUID id;
	private DriverDTO owner;
	private String plate;
	private int capacity;
	private String numberEnrollment;
	
	private VehicleDTOBuilder() {
		super();
	}
	
	public static final VehicleDTOBuilder getVehicleDTOBuilder() {
		return new VehicleDTOBuilder();
	}

	public final VehicleDTOBuilder setId(UUID id) {
		this.id = id;
		return this;
	}

	public void setOwner(DriverDTO owner) {
		this.owner = owner;
	}
	
	public final VehicleDTOBuilder setCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public void setNumberEnrollment(String numberEnrollment) {
		this.numberEnrollment = numberEnrollment;
	}
	

	public void setPalte(String plate) {
		this.plate = plate;
	}
	
	public final VehicleDTO build() {
		return create(id,plate, owner,capacity,numberEnrollment);
	}
	
	
}
