package edu.uco.carpooling.service.business.customer.implementation;

import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.service.business.customer.CreateCustomerUseCase;

public final class CreateCustomerUseCaseImpl implements CreateCustomerUseCase{

	@Override
	public final void execute(final CustomerDTO user) {
		DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL).getUserDAO().create(user);
	}
}
