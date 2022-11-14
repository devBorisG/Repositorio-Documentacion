package edu.uco.carpooling.service.command.implementation;

import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.RouteRequestDTO;
import edu.uco.carpooling.service.business.usecase.routerequest.CreateRouteRequestUseCase;
import edu.uco.carpooling.service.business.usecase.routerequest.implementation.CreateRouteRequestUsecaseImpl;
import edu.uco.carpooling.service.command.CreateRouteRequestCommand;

public class CreateRouteRequestCommandImpl implements CreateRouteRequestCommand{

	private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
	private final CreateRouteRequestUseCase useCase = new CreateRouteRequestUsecaseImpl(factory);
	
	@Override
	public final void execute(RouteRequestDTO routeRequest) {
		try {
			factory.initTransaction();
			useCase.create(routeRequest);
			factory.confirmTransaction();
		} catch (CarpoolingCustomException e) {
			factory.cancelTransaction();
			throw e;
		} catch (Exception e) {
			factory.cancelTransaction();
			throw e;
		} finally {
			try {
				factory.closeConnection();				
			}catch(Exception e ) {
				throw e;
			}
		}
		
	}
}

