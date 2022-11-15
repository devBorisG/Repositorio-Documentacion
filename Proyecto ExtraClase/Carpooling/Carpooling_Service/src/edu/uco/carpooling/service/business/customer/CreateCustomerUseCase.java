package edu.uco.carpooling.service.business.customer;

import edu.uco.carpooling.domain.CustomerDTO;

public interface CreateCustomerUseCase {

	void create(CustomerDTO user);
}
