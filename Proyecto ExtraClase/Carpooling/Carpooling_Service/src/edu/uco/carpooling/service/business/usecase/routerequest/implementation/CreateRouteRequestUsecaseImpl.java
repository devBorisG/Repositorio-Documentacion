package edu.uco.carpooling.service.business.usecase.routerequest.implementation;

import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.domain.RouteRequestDTO;
import edu.uco.carpooling.service.business.customer.implementation.FindCustomerByIdImpl;
import edu.uco.carpooling.service.business.usecase.routerequest.CreateRouteRequestUseCase;
import edu.uco.carpooling.crosscutting.exception.UseCaseCustomException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;

import java.util.UUID;

import edu.uco.carpooling.data.daofactory.DAOFactory;

public class CreateRouteRequestUsecaseImpl implements CreateRouteRequestUseCase{

	private final DAOFactory factory;
	private final FindCustomerByIdImpl findCustomerByIdImpl;
	
	public CreateRouteRequestUsecaseImpl(DAOFactory factory) {
		super();
		this.factory = factory;
		this.findCustomerByIdImpl = new FindCustomerByIdImpl(factory);
	}

	@Override
	public void create(RouteRequestDTO routeRequest) {
		final CustomerDTO customer = findCustomer(routeRequest.getCustomer().getId());
		
		routeRequest.setCustomer(customer);
		
		routeRequest.setId(UUIDHelper.getNewUUID());
		
		factory.getRouteRequestDAO().create(routeRequest);
	}
	
	private final CustomerDTO findCustomer(final UUID id) {
		final CustomerDTO customer = findCustomerByIdImpl.execute(id);
		
		if (customer!= null && customer.notExist()) {
			throw UseCaseCustomException.createUserException(Messages.CreateRouteRequestUseCaseImpl.BUSSINES_CUSTOMER_DOES_NOT_EXIST);
		}
		return customer;
	}
}