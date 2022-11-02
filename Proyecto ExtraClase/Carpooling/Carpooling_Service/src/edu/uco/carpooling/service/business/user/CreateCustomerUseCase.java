package edu.uco.carpooling.service.business.user;

import edu.uco.carpooling.domain.CustomerDTO;

public interface CreateCustomerUseCase {

	void execute(CustomerDTO user);
}
