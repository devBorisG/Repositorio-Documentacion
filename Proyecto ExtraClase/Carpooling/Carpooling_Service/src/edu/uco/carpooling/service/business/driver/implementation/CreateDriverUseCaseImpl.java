package edu.uco.carpooling.service.business.driver.implementation;

import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.service.business.driver.CreateDriverUseCase;

public class CreateDriverUseCaseImpl implements CreateDriverUseCase{

	private final DAOFactory factory;
	
	public CreateDriverUseCaseImpl(DAOFactory factory) {
		super();
		this.factory = factory;
	}
	
	@Override
	public void create(DriverDTO driver) {
		
		driver.setId(UUIDHelper.getNewUUID());
		
		factory.getDriverDAO().create(driver);
		
	}
}
