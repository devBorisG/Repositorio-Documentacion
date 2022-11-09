package edu.uco.carpooling.domain;

import java.util.UUID;


import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;

import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;

import static edu.uco.carpooling.crosscutting.helper.NumberHelper.isLessThan;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.carpooling.domain.builder.DriverDTOBuilder.getDriverDTOBuilder;


public class VehicleDTO {
	
	private UUID id;
	private int model;
	private String brand;
	private String lineup;
	private String plate;
	private DriverDTO owner;
	private int capacity;
	
	public VehicleDTO() {
		setId(getNewUUID());
		setBrand(EMPTY);
		setLineup(EMPTY);
		setPlate(EMPTY);
		setCapacity(ZERO);
		setModel(ZERO);
		setOwner(getDriverDTOBuilder().build());
	}

	public VehicleDTO(UUID id,int model, String brand, String lineup, String plate,
			DriverDTO owner,int capacity) {
		setId(id);
		setModel(model);
		setBrand(brand);
		setLineup(lineup);
		setPlate(plate);
		setOwner(owner);
		setCapacity(capacity);
	}
	
	public static final VehicleDTO create(UUID id, int model, String brand, String lineup, String plate,
			DriverDTO owner,int capacity) {
		return new VehicleDTO(id,model,brand,lineup,plate,owner,capacity);
	}
	
	public static final VehicleDTO create(final String id,final int model,final String brand, 
			final String lineup,final String plate, final DriverDTO owner,
			int capacity) {
		return new VehicleDTO(getUUIDFromString(id), model,brand,lineup,plate,owner,capacity);
	}
	
	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}
	
	public static final String getIntAsString(final int value) {
		return Integer.toString(value);
	}

	public UUID getId() {
		return id;
	}

	public final void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}

	public int getModel() {
		return model;
	}

	public final void setModel(final int model) {
		this.model = isLessThan(model, ZERO)? ZERO : model;
	}

	public String getBrand() {
		return brand;
	}

	public final void setBrand(final String brand) {
		this.brand = applyTrim(brand);
	}

	public String getLineup() {
		return lineup;
	}

	public final void setLineup(final String line) {
		this.lineup = applyTrim(line);
	}

	public String getPlate() {
		return plate;
	}

	public final void setPlate(final String plate) {
		this.plate = applyTrim(plate);
	}

	public int getCapacity() {
		return capacity;
	}

	public final void setCapacity(final int capacity) {
		this.capacity = isLessThan(capacity, ZERO)? ZERO: capacity;
	}
	

	public DriverDTO getOwner() {
		return owner;
	}

	public void setOwner(DriverDTO owner) {
		this.owner = owner;
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
}
