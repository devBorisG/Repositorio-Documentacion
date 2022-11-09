package edu.uco.carpooling.data.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.DriverPerVehicleDTO;

public interface DriverPerVehicleDAO {
	
    void create (DriverPerVehicleDTO driverPerVehicle);

    List<DriverPerVehicleDTO> find(DriverPerVehicleDTO driverPerVehicle);

    void update(DriverPerVehicleDTO driverPerVehicle);

    void delete(UUID id) throws SQLException;

}
