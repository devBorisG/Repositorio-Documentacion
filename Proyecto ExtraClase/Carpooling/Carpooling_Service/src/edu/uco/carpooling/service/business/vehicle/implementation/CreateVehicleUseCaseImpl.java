package edu.uco.carpooling.service.business.vehicle.implementation;

import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.ServiceCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.VehicleDTO;
import edu.uco.carpooling.service.business.driver.FindDriverByIdUseCase;
import edu.uco.carpooling.service.business.driver.implementation.FindDriverByIdUseCaseImpl;
import edu.uco.carpooling.service.business.driverpervehicle.CreateDriverPerVehicleUseCase;
import edu.uco.carpooling.service.business.driverpervehicle.implementation.CreateDriverPerVehicleUseCaseImpl;
import edu.uco.carpooling.service.business.vehicle.CreateVehicleUseCase;
import edu.uco.carpooling.service.business.vehicle.FindVehiclePlate;
import edu.uco.carpooling.service.business.vehicle.FormatNumEnrollment;
import edu.uco.carpooling.service.business.vehicle.FormatPlate;

public class CreateVehicleUseCaseImpl implements CreateVehicleUseCase {

	private final DAOFactory factory;
	private final FindDriverByIdUseCase findDriverById;
	private final FormatPlate formatPlateUseCase;
	private final FormatNumEnrollment formatNumEnrollment;
	private final FindVehiclePlate findVehicleUseCase;
	private final CreateDriverPerVehicleUseCase createDriverPerVehicleUseCase;
	
	public CreateVehicleUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
		findDriverById = new FindDriverByIdUseCaseImpl(factory);
		formatPlateUseCase = new FormatPlateImpl();
		formatNumEnrollment = new FormatNumEnrollmentImpl();
		findVehicleUseCase = new FindVehiclePlateImpl(factory);
		createDriverPerVehicleUseCase = new CreateDriverPerVehicleUseCaseImpl(factory);
	}

	@Override
	public final void execute(final VehicleDTO vehicle) {
		final DriverDTO driver = findDriver(vehicle.getOwner().getId());
		formatPlateUseCase.execute(vehicle.getPlate());
		formatNumEnrollment.execute(vehicle.getNumberEnrollment());
		validateIfVehicleExist(vehicle.getPlate());
		vehicle.setOwner(driver);
		vehicle.setId(UUIDHelper.getNewUUID());
		factory.getVehicleDAO().create(vehicle);
		createDriverPerVehicleUseCase.execute(vehicle);
	}
	
	private final DriverDTO findDriver(final UUID id) {
		final DriverDTO driver = findDriverById.execute(id);

		if (driver.notExist()) {
			throw ServiceCarpoolingException
					.createUserException(Messages.CreateVehicleUseCaseImpl.BUSSINES_DRIVER_DOES_NOT_EXISTS);
		}
		
		return driver;
	}
	
	private final void validateIfVehicleExist(final String plate) {
		final VehicleDTO vehicle = findVehicleUseCase.execute(plate);
		
		if(vehicle.exist()) {
			throw ServiceCarpoolingException.createUserException(Messages.CreateVehicleUseCaseImpl.BUSSINES_VEHICLE_EXIST);
		}
	}

}
