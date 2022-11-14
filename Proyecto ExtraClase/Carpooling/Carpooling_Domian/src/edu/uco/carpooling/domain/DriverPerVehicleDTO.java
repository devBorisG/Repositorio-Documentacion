package edu.uco.carpooling.domain;

import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;
import static edu.uco.carpooling.domain.builder.DriverDTOBuilder.getDriverDTOBuilder;
import static edu.uco.carpooling.domain.builder.VehicleDTOBuilder.getVehicleDTOBuilder;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;

public class DriverPerVehicleDTO {

    private DriverDTO driver;
    private VehicleDTO vehicle;
    private String state;
    
    public DriverPerVehicleDTO() {
    	setState(EMPTY);
        setDriver(getDriverDTOBuilder().build());
        setVehicle(getVehicleDTOBuilder().build());
    }
    public DriverPerVehicleDTO(final DriverDTO driver,final VehicleDTO vehicle,final String state) {
        setDriver(driver);
        setVehicle(vehicle);
        setState(state);
    }
    
    public static final DriverPerVehicleDTO create(final DriverDTO driver,final VehicleDTO vehicle, final String state){
        return new DriverPerVehicleDTO(driver , vehicle, state);
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

	public String getState() {
		return state;
	}

	public final void setState(final String state) {
		this.state = applyTrim(state);
	}
}
