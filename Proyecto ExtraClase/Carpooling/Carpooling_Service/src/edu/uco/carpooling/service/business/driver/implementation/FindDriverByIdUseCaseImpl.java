package edu.uco.carpooling.service.business.driver.implementation;

import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.service.business.driver.FindDriverByIdUseCase;

public class FindDriverByIdUseCaseImpl implements FindDriverByIdUseCase{

	private final DAOFactory factory;

	public FindDriverByIdUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
	}

 	@Override
	public DriverDTO execute(final UUID id) {
		final DriverDTO driver = DriverDTO.create(id);
		DriverDTO result = new DriverDTO();

		final List<DriverDTO> results = factory.getDriverDAO().find(driver);

		if(!results.isEmpty()) {
			result = results.get(0);
		}

		return result;
	}

}
