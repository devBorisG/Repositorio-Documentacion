package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.VehicleDTO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;

public class DriverPerVehicleDTOBuilder {

    private UUID id;
    private DriverDTO driver;
    private VehicleDTO vehicle;

    public static final DriverPerVehicleDTOBuilder getDriverPerVehicleDTOBuilder(){
        return new DriverPerVehicleDTOBuilder();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }

    public void setDriver(DriverDTO driver) {
        this.driver = driver;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
}
