package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;
import static edu.uco.carpooling.domain.builder.DriverDTOBuilder.getDriverDTOBuilder;
import static edu.uco.carpooling.domain.builder.VehicleDTOBuilder.getVehicleDTOBuilder;

public class DriverPerVehicleDTO {

    private UUID id;
    private DriverDTO driver;
    private VehicleDTO vehicle;
    
    public DriverPerVehicleDTO() {
        setId(getNewUUID());
        setDriver(driver);
        setVehicle(vehicle);
    }

    public DriverPerVehicleDTO(final UUID id,final DriverDTO driver,final VehicleDTO vehicle) {
        setId(id);
        setDriver(driver);
        setVehicle(vehicle);
    }
    
    public static final DriverPerVehicleDTO create(final UUID id, final DriverDTO driver,final VehicleDTO vehicle){
        return new DriverPerVehicleDTO(id, driver , vehicle);
    }
    
    public static final DriverPerVehicleDTO create(final String id, final DriverDTO driver,final VehicleDTO vehicle){
        return new DriverPerVehicleDTO(getUUIDFromString(id), driver , vehicle);
    }
    
	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}

    public UUID getId() {
        return id;
    }

    public final void setId(final UUID id) {
        this.id = getDefaultUUID(id);
    }

    public DriverDTO getDriver() {
    	return driver;
    	}

    public void setDriver(final DriverDTO driver) {
        this.driver = getDefaultIfNull(driver, getDriverDTOBuilder().build());
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(final VehicleDTO vehicle) {
        this.vehicle = getDefaultIfNull(vehicle, getVehicleDTOBuilder().build() );
    }
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
}
