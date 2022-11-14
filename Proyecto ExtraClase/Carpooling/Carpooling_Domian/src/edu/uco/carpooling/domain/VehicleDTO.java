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
	private String plate;
	private DriverDTO owner;
	private int capacity;
	private String numberEnrollment;

	public VehicleDTO() {
		setId(getNewUUID());
		setPlate(EMPTY);
		setCapacity(ZERO);
		setOwner(getDriverDTOBuilder().build());
		setNumberEnrollment(EMPTY);
	}

	public VehicleDTO(UUID id,DriverDTO owner,int capacity,final String numberEnrollment) {
		setId(id);
		setPlate(plate);
		setOwner(owner);
		setCapacity(capacity);
		setNumberEnrollment(numberEnrollment);
	}
	
	public static final VehicleDTO create(final UUID id,final String plate,final DriverDTO owner,final int capacity
			,final String numberEnrollment) {
		return new VehicleDTO(id,owner,capacity,numberEnrollment);
	}
	
	public static final VehicleDTO create(final String id,final String plate, final DriverDTO owner,
			int capacity,final String numberEnrollment) {
		return new VehicleDTO(getUUIDFromString(id),owner,capacity,numberEnrollment);
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
	
	public String getNumberEnrollment() {
		return numberEnrollment;
	}

	public final void setNumberEnrollment(final String numberEnrollment) {
		this.numberEnrollment = applyTrim(numberEnrollment);
	}

	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
}
