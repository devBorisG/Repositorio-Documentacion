package edu.uco.carpooling.service.command;

import java.util.List;

import edu.uco.carpooling.domain.RouteRequestDTO;

public interface GetAllRouteRequestsCommand {
	List<RouteRequestDTO> get();
}
