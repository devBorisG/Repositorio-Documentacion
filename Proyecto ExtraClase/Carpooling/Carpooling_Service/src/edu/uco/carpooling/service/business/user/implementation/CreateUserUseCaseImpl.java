package edu.uco.carpooling.service.business.user.implementation;

import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;
import edu.uco.carpooling.domain.UserDTO;
import edu.uco.carpooling.service.business.user.CreateUserUseCase;

public final class CreateUserUseCaseImpl implements CreateUserUseCase{

	@Override
	public final void execute(final UserDTO user) {
		DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL).getUserDAO().create(user);
	}
}
