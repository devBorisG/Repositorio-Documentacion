package edu.uco.carpooling.domain;

import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;
import static edu.uco.carpooling.domain.builder.DriverDTOBuilder.getDriverDTOBuilder;
import static edu.uco.carpooling.domain.builder.VehicleDTOBuilder.getVehicleDTOBuilder;

public class DriverPerVehicleDTO {

    private DriverDTO driver;
    private VehicleDTO vehicle;
    
    public DriverPerVehicleDTO() {
        setDriver(getDriverDTOBuilder().build());
        setVehicle(getVehicleDTOBuilder().build());
    }

    public DriverPerVehicleDTO(final DriverDTO driver,final VehicleDTO vehicle) {
        setDriver(driver);
        setVehicle(vehicle);
    }
    
    public static final DriverPerVehicleDTO create(final DriverDTO driver,final VehicleDTO vehicle){
        return new DriverPerVehicleDTO(driver , vehicle);
    }

    public DriverDTO getDriver() {
    	return driver;
    	}

    public final void setDriver(final DriverDTO driver) {
        this.driver = getDefaultIfNull(driver, getDriverDTOBuilder().build());
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public final void setVehicle(final VehicleDTO vehicle) {
        this.vehicle = getDefaultIfNull(vehicle, getVehicleDTOBuilder().build() );
    }
}
