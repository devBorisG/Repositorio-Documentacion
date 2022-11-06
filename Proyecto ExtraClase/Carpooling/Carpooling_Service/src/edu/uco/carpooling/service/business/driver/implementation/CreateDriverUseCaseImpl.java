package edu.uco.carpooling.service.business.driver.implementation;

import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.service.business.driver.CreateDriverUseCase;

public class CreateDriverUseCaseImpl implements CreateDriverUseCase{

	@Override
	public void execute(DriverDTO driver) {
		DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL).getDriverDAO().create(driver);
	}

}
