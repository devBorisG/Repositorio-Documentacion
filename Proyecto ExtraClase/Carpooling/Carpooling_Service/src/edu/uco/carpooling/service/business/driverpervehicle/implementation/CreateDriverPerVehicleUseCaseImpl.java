package edu.uco.carpooling.service.business.driverpervehicle.implementation;

import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.domain.DriverPerVehicleDTO;
import edu.uco.carpooling.domain.VehicleDTO;
import edu.uco.carpooling.service.business.driverpervehicle.CreateDriverPerVehicleUseCase;

public class CreateDriverPerVehicleUseCaseImpl implements CreateDriverPerVehicleUseCase {

	private final DAOFactory factory;

	public CreateDriverPerVehicleUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
	}

	@Override
	public final void execute(final VehicleDTO vehicle) {
		DriverPerVehicleDTO driverPerVehicle = new DriverPerVehicleDTO();
		driverPerVehicle.setDriver(vehicle.getOwner());
		driverPerVehicle.setVehicle(vehicle);
		factory.getDriverPerVehicleDAO().create(driverPerVehicle);
	}

}
