package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;


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

    private static final DriverPerVehicleDTO create(final UUID id, final DriverDTO driver,final VehicleDTO vehicle){
        return new DriverPerVehicleDTO(id, driver , vehicle);
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
        //Toca castearlo??
        this.driver = DriverDTO.getDefaultIfNull(driver,getDriverDTOBuilder().build());

    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(final VehicleDTO vehicle) {
        this.vehicle = getDefaultIfNull(vehicle, getVehicleDTOBuiler().build() );

    }
}
