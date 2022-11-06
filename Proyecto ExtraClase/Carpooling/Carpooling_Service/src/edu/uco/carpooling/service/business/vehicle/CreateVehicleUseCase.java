package edu.uco.carpooling.service.business.vehicle;

import edu.uco.carpooling.domain.VehicleDTO;

public interface CreateVehicleUseCase {
	
	void execute(VehicleDTO vehicle);
}
