package edu.uco.carpooling.service.business.usecase.routerequest;

import java.util.UUID;

import edu.uco.carpooling.domain.RouteRequestDTO;

public interface FindRouteRequestById {

	RouteRequestDTO execute(UUID id);
}
