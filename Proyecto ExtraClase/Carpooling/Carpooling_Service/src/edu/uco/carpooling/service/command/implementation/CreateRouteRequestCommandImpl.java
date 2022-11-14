package edu.uco.carpooling.service.command.implementation;

import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.RouteRequestDTO;
import edu.uco.carpooling.service.business.usecase.routerequest.CreateRouteRequestUseCase;
import edu.uco.carpooling.service.business.usecase.routerequest.implementation.CreateRouteRequestUsecaseImpl;
import edu.uco.carpooling.service.command.CreateRouteRequestCommand;

public class CreateRouteRequestCommandImpl implements CreateRouteRequestCommand{

	private DAOFactory factory;
	private final CreateRouteRequestUseCase useCase = new CreateRouteRequestUsecaseImpl(factory);
	
	@Override
	public final void execute(RouteRequestDTO routeRequest) {
		try {
			factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
			factory.initTransaction();
			useCase.create(routeRequest);
			factory.confirmTransaction();
		} catch (CarpoolingCustomException e) {
			factory.cancelTransaction();
			throw e;
		} catch (Exception e) {
			factory.cancelTransaction();
		} finally {
			factory.closeConnection();
		}
		
	}
}

