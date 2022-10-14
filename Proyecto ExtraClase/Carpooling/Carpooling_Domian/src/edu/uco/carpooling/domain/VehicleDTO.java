package edu.uco.carpooling.domain;

import java.util.UUID;


import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;

import static edu.uco.carpooling.crosscutting.helper.BooleanHelper.getDefaultBoolean;
import static edu.uco.carpooling.crosscutting.helper.BooleanHelper.BFALSE;

import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;

import static edu.uco.carpooling.crosscutting.helper.NumberHelper.isLessThan;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;

public class VehicleDTO {
	
	private UUID id;
	private boolean state;
	private String registration ;
	private int model;
	private String brand;
	private String lineup;
	private String plate;
	private int capacity;
	
	public VehicleDTO() {
		setId(getNewUUID());
		setState(BFALSE);
		setRegistration(EMPTY);
		setModel(ZERO);
		setBrand(EMPTY);
		setLineup(EMPTY);
		setPlate(EMPTY);
		setCapacity(ZERO);
	}

	public VehicleDTO(UUID id, boolean state, String registration, int model, String brand, String lineup, String plate,
			int capacity) {
		setId(id);
		setState(state);
		setRegistration(registration);
		setModel(model);
		setBrand(brand);
		setLineup(lineup);
		setPlate(plate);
		setCapacity(capacity);
	}
	
	public static final VehicleDTO create(UUID id, boolean state, String registration, int model, String brand, String lineup, String plate,
			int capacity) {
		return new VehicleDTO(
					id,
					state,
					registration,
					model,
					brand,
					lineup,
					plate,
					capacity
				);
	}

	public UUID getId() {
		return id;
	}

	public final void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}

	public boolean isState() {
		return state;
	}

	public final void setState(final boolean state) {
		this.state = getDefaultBoolean(state);
	}

	public String getRegistration() {
		return registration;
	}

	public final void setRegistration(final String registration) {
		this.registration = applyTrim(registration);
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
	
	
}
