package edu.uco.carpooling.service.command.implementation;

import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.service.business.driver.CreateDriverUseCase;
import edu.uco.carpooling.service.business.driver.implementation.CreateDriverUseCaseImpl;
import edu.uco.carpooling.service.command.CreateDriverCommand;

public class CreateDriverCommandImpl implements CreateDriverCommand{

	private DAOFactory factory;
	private CreateDriverUseCase driverUsecase;
	
	@Override
	public void execute(DriverDTO driver) {
		try {
			factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
			driverUsecase = new CreateDriverUseCaseImpl(factory);
factory.initTransaction();
			
		driverUsecase.create(driver);
			
			factory.confirmTransaction();
			
		} catch (CarpoolingCustomException exception) {
			factory.cancelTransaction();
			throw exception;
		} catch (Exception exception) {
			factory.cancelTransaction();
		} finally {
			factory.closeConnection();
		}
	}
}
