package edu.uco.carpooling.service.command.implementation;

import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.crosscutting.exception.ServiceCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.VehicleDTO;
import edu.uco.carpooling.service.business.vehicle.CreateVehicleUseCase;
import edu.uco.carpooling.service.business.vehicle.implementation.CreateVehicleUseCaseImpl;
import edu.uco.carpooling.service.command.CreateVehicleCommand;

public class CreateVehicleCommandImpl implements CreateVehicleCommand {

	private DAOFactory factory;

	@Override
	public final void execute(final VehicleDTO vehicle) {
		try {
			factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
			CreateVehicleUseCase useCase = new CreateVehicleUseCaseImpl(factory);
			factory.initTransaction();
			useCase.execute(vehicle);
			factory.confirmTransaction();
		} catch (ServiceCarpoolingException exception) {
			factory.cancelTransaction();
			throw exception;
		} catch (CarpoolingCustomException exception) {
			factory.cancelTransaction();
			throw ServiceCarpoolingException
					.wrapException(Messages.CreateVehicleCommandImpl.USER_PROBLEM_CREATE_VEHICLE, exception);
		} catch (Exception exception) {
			factory.cancelTransaction();
			throw ServiceCarpoolingException.createBussinesException(
					Messages.CreateVehicleCommandImpl.USER_UNEXPECTED_PROBLEM_CREATE_VEHICLE, exception);
		} finally {
			factory.closeConnection();
		}
	}
}