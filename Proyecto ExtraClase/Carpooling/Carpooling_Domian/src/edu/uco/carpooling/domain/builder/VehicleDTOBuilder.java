package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.VehicleDTO;
import static edu.uco.carpooling.domain.VehicleDTO.create;

public class VehicleDTOBuilder {

	private UUID id;
	private int model;
	private String brand;
	private String lineup;
	private String plate;
	private DriverDTO owner;
	private int capacity;
	
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

	public final VehicleDTOBuilder setModel(int model) {
		this.model = model;
		return this;
	}

	public final VehicleDTOBuilder setBrand(String brand) {
		this.brand = brand;
		return this;
	}

	public final VehicleDTOBuilder setLineup(String lineup) {
		this.lineup = lineup;
		return this;
	}

	public final VehicleDTOBuilder setPlate(String plate) {
		this.plate = plate;
		return this;
	}

	public final VehicleDTOBuilder setCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}
	
	public final VehicleDTO build() {
		return create(id,model, brand, lineup, plate, owner,capacity);
	}
	
	
}
