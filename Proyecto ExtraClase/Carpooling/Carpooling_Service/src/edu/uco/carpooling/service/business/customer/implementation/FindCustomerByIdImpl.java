package edu.uco.carpooling.service.business.customer.implementation;

import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.service.business.customer.FindCustomerById;
import edu.uco.carpooling.data.daofactory.DAOFactory;

public class FindCustomerByIdImpl implements FindCustomerById{
	
	private final DAOFactory factory;

	public FindCustomerByIdImpl(DAOFactory factory) {
		this.factory = factory;
	}

	@Override
	public CustomerDTO execute(UUID id) {
		CustomerDTO result = new CustomerDTO();
		final CustomerDTO customer = CustomerDTO.create(id);
		final List<CustomerDTO> results = factory.getUserDAO().find(customer);
		
		if (results != null && !results.isEmpty()) {
			result = results.get(0);
		}
		return result;
	}
}
