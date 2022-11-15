package edu.uco.carpooling.service.command;

import java.util.List;

import edu.uco.carpooling.domain.CustomerDTO;

public interface GetCustomerByIdCommand {
	
	public List<CustomerDTO> getById(String id);

}
