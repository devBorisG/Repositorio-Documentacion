package edu.uco.carpooling.service.command.implementation;

import java.util.List;

import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.service.command.GetCustomerByIdCommand;

public class GetCustomerByIdCommandImpl implements GetCustomerByIdCommand{

	DAOFactory factory;
	
	@Override
	public List<CustomerDTO> getById(String id) {
		try {
			factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
			return factory.getUserDAO().findById(id);
			
		} catch (CarpoolingCustomException e) {
			factory.cancelTransaction();
			throw e;
		}
		catch (Exception e) {
			factory.cancelTransaction();
			throw DataCarpoolingException.createTechnicalException(e.getMessage());
		}finally {
			factory.closeConnection();
		}
	}

}
