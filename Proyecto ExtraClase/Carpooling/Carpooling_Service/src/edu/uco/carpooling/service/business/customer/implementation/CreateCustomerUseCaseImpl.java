package edu.uco.carpooling.service.business.customer.implementation;

import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.UseCaseCustomException;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.service.business.customer.CreateCustomerUseCase;

public final class CreateCustomerUseCaseImpl implements CreateCustomerUseCase{

	private final DAOFactory factory;
	private final FindCustomerByIdImpl findCustomerByIdImpl;
	
	
	public CreateCustomerUseCaseImpl(DAOFactory factory) {
		super();
		this.factory = factory;
		this.findCustomerByIdImpl = new FindCustomerByIdImpl(factory);
	}
	
	@Override
	public final void create(CustomerDTO user) {
		
		user.setId(UUIDHelper.getNewUUID());
		
		factory.getUserDAO().create(user);
		
	}
	
	/*private final CustomerDTO findCustomer(final UUID id) {
		final CustomerDTO customer = findCustomerByIdImpl.execute(id);
		
		if (customer.notExist()) {
			throw UseCaseCustomException.createUserException(Messages.CreateRouteRequestUseCaseImpl.BUSSINES_CUSTOMER_DOES_NOT_EXIST);
		}
		return customer;
	}*/
}
