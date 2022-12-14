package edu.uco.carpooling.domain.builder;

import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.DriverPerVehicleDTO;
import edu.uco.carpooling.domain.VehicleDTO;
import static edu.uco.carpooling.domain.DriverPerVehicleDTO.create;


public class DriverPerVehicleDTOBuilder {
    private DriverDTO driver;
    private VehicleDTO vehicle;

    private DriverPerVehicleDTOBuilder() {
    	super();
    }

    public static final DriverPerVehicleDTOBuilder getDriverPerVehicleDTOBuilder(){
        return new DriverPerVehicleDTOBuilder();
    }


    public final DriverPerVehicleDTOBuilder setDriver(DriverDTO driver) {
        this.driver = driver;
        return this;
    }

    public final DriverPerVehicleDTOBuilder setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
        return this;
    }
    

	public final DriverPerVehicleDTO build() {
    	return create(driver,vehicle);
    }
}
