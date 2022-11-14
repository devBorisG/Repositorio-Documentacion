package edu.uco.carpooling.service.command;

import edu.uco.carpooling.domain.DriverDTO;

public interface CreateDriverCommand {

	void execute(DriverDTO driver);
}
