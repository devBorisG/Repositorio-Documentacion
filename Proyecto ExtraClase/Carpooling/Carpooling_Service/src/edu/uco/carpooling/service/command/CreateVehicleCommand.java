package edu.uco.carpooling.service.command;

import edu.uco.carpooling.domain.VehicleDTO;

public interface CreateVehicleCommand {
	
	void execute(VehicleDTO vehicle);
}
