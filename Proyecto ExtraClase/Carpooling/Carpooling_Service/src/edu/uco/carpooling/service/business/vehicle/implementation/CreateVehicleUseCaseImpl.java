package edu.uco.carpooling.service.business.vehicle.implementation;

import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.VehicleDTO;
import edu.uco.carpooling.service.business.vehicle.CreateVehicleUseCase;

public class CreateVehicleUseCaseImpl implements CreateVehicleUseCase{

	@Override
	public void execute(VehicleDTO vehicle) {
		DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL).getVehicleDAO().create(vehicle);
	}

}
