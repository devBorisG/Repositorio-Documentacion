package edu.uco.carpooling.service.business.driver;

import edu.uco.carpooling.domain.DriverDTO;

public interface CreateDriverUseCase {
	
	void execute(DriverDTO driver);
}
