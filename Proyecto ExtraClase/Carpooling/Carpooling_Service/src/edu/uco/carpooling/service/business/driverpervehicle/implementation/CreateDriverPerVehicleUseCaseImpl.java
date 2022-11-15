package edu.uco.carpooling.service.business.driverpervehicle.implementation;

import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.DriverPerVehicleDTO;
import edu.uco.carpooling.domain.VehicleDTO;
import edu.uco.carpooling.service.business.driver.FindDriverByIdUseCase;
import edu.uco.carpooling.service.business.driver.implementation.FindDriverByIdUseCaseImpl;
import edu.uco.carpooling.service.business.driverpervehicle.CreateDriverPerVehicleUseCase;

public class CreateDriverPerVehicleUseCaseImpl implements CreateDriverPerVehicleUseCase {

	private final DAOFactory factory;
	private final FindDriverByIdUseCase findDriverByIdUseCase;

	public CreateDriverPerVehicleUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
		findDriverByIdUseCase = new FindDriverByIdUseCaseImpl(factory);
	}

	@Override
	public final void execute(final VehicleDTO vehicle) {
		DriverDTO driver =  findDriverByIdUseCase.execute(vehicle.getOwner().getId());
		DriverPerVehicleDTO driverPerVehicle = new DriverPerVehicleDTO();
		driverPerVehicle.setDriver(driver);
		driverPerVehicle.setVehicle(vehicle);
		factory.getDriverPerVehicleDAO().create(driverPerVehicle);
	}

}
