package edu.uco.carpooling.service.command;

import edu.uco.carpooling.domain.RouteRequestDTO;

public interface CreateRouteRequestCommand {

	void execute(RouteRequestDTO routeRequest);
}
