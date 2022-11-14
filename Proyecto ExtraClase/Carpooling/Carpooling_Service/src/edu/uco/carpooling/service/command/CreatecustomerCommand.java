package edu.uco.carpooling.service.command;

import edu.uco.carpooling.domain.CustomerDTO;

public interface CreatecustomerCommand {

	void execute(CustomerDTO customer);
}
