package edu.uco.carpooling.service.business.driver;

import java.util.UUID;

import edu.uco.carpooling.domain.DriverDTO;

public interface FindDriverByIdUseCase {
	DriverDTO execute(UUID id);
}
