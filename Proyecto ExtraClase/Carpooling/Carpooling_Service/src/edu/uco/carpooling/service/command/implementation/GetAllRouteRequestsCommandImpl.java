package edu.uco.carpooling.service.command.implementation;
import java.util.List;

import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.RouteRequestDTO;
import edu.uco.carpooling.service.command.GetAllRouteRequestsCommand;

public class GetAllRouteRequestsCommandImpl implements GetAllRouteRequestsCommand{

	private DAOFactory factory;
	
	@Override
	public List<RouteRequestDTO> get() {
		try {
			factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);			
			return factory.getRouteRequestDAO().find(null);
		}catch(CarpoolingCustomException carpoolingException) {
			factory.cancelTransaction();
			throw carpoolingException;
		}catch(Exception e){
			factory.cancelTransaction();
			throw e;
		}finally {
			factory.closeConnection();
		}
	}

}
