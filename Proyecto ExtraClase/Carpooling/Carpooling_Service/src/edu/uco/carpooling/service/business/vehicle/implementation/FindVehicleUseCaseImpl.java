package edu.uco.carpooling.service.business.vehicle.implementation;

import java.util.List;

import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.domain.VehicleDTO;
import edu.uco.carpooling.service.business.vehicle.FindVehicleUseCase;

public class FindVehicleUseCaseImpl implements FindVehicleUseCase {

	private final DAOFactory factory;
	
	public FindVehicleUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public List<VehicleDTO> execute(VehicleDTO vehicle) {
		return factory.getVehicleDAO().find(vehicle);
	}

}
