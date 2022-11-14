package edu.uco.carpooling.service.business.customer.implementation;

import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.service.business.customer.CreateCustomerUseCase;

public final class CreateCustomerUseCaseImpl implements CreateCustomerUseCase{

	private final DAOFactory factory;
	
	
	public CreateCustomerUseCaseImpl(DAOFactory factory) {
		super();
		this.factory = factory;
	}
	
	@Override
	public final void create(CustomerDTO user) {
		
		user.setId(UUIDHelper.getNewUUID());
		
		factory.getUserDAO().create(user);
		
	}
}
