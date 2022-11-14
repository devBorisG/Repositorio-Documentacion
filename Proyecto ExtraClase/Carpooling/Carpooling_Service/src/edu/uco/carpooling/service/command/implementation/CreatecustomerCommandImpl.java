package edu.uco.carpooling.service.command.implementation;

import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.service.business.customer.CreateCustomerUseCase;
import edu.uco.carpooling.service.business.customer.implementation.CreateCustomerUseCaseImpl;
import edu.uco.carpooling.service.command.CreatecustomerCommand;

public class CreatecustomerCommandImpl implements CreatecustomerCommand{

	private DAOFactory factory;
	private final CreateCustomerUseCase useCase = new CreateCustomerUseCaseImpl(factory);
	
	@Override
	public final void execute(CustomerDTO customer) {
		try {
			factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
			factory.initTransaction();
			
			useCase.create(customer);
			
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