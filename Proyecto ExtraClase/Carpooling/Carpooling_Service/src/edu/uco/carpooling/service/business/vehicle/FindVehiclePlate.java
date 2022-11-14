package edu.uco.carpooling.service.business.vehicle;

import java.util.List;

import edu.uco.carpooling.domain.VehicleDTO;

public interface FindVehiclePlate {
	
	List<VehicleDTO> execute(VehicleDTO vehicle);
}
