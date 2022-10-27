package edu.uco.carpooling.service.business.user;

import edu.uco.carpooling.domain.UserDTO;

public interface CreateUserUseCase {

	void execute(UserDTO user);
}
