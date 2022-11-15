package edu.uco.carpooling.service.command.implementation;

import java.util.List;

import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.service.command.GetDriverByIdCommand;

public class GetDriverByIdCommandImpl implements GetDriverByIdCommand {

	private DAOFactory factory;

	@Override
	public List<DriverDTO> getById(String id) {
		try {
			factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
			List<DriverDTO> list = factory.getDriverDAO().findById(id);	
			return list;
		} catch (Exception e) {
			factory.cancelTransaction();
			throw e;
		}finally {
			factory.closeConnection();
		}
	}

}
