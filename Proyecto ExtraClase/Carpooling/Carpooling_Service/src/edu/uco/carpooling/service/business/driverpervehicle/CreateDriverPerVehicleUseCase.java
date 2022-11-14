package edu.uco.carpooling.service.business.driverpervehicle;

import edu.uco.carpooling.domain.VehicleDTO;

public interface CreateDriverPerVehicleUseCase {
	void execute(VehicleDTO vehicle);
}
