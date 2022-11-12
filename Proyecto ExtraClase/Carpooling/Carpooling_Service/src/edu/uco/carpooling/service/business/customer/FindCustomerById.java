package edu.uco.carpooling.service.business.customer;


import java.util.UUID;

import edu.uco.carpooling.domain.CustomerDTO;

public interface FindCustomerById {

	CustomerDTO execute(UUID id); 
}
