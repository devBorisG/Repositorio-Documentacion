package edu.uco.carpooling.domain.builder;

import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.DriverPerVehicleDTO;
import edu.uco.carpooling.domain.VehicleDTO;
import static edu.uco.carpooling.domain.DriverPerVehicleDTO.create;


public class DriverPerVehicleDTOBuilder {
    private DriverDTO driver;
    private VehicleDTO vehicle;
    private String state;

    private DriverPerVehicleDTOBuilder() {
    	super();
    }

    public static final DriverPerVehicleDTOBuilder getDriverPerVehicleDTOBuilder(){
        return new DriverPerVehicleDTOBuilder();
    }


    public void setDriver(DriverDTO driver) {
        this.driver = driver;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
    
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public final DriverPerVehicleDTO build() {
    	return create(driver,vehicle,state);
    }
}
