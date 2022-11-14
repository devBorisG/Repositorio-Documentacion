package edu.uco.carpooling.service.business.vehicle;

import java.util.List;

import edu.uco.carpooling.domain.VehicleDTO;

public interface FindVehicleUseCase {
	
	List<VehicleDTO> execute(VehicleDTO vehicle);
}
