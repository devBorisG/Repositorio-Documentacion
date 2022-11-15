package edu.uco.carpooling.service.command;

import java.util.List;

import edu.uco.carpooling.domain.DriverDTO;

public interface GetDriverByIdCommand {

	List<DriverDTO> getById(String id);
}
