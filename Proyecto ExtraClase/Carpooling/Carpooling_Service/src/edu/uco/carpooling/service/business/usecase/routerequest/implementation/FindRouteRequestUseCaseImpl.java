package edu.uco.carpooling.service.business.usecase.routerequest.implementation;

import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.RouteRequestDTO;
import edu.uco.carpooling.service.business.usecase.routerequest.FindRouteRequestById;
import edu.uco.carpooling.data.daofactory.DAOFactory;

public class FindRouteRequestUseCaseImpl implements FindRouteRequestById{

	private final DAOFactory factory;
	
	public FindRouteRequestUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
	}

	@Override
	public RouteRequestDTO execute(UUID id) {
		
		RouteRequestDTO result = new RouteRequestDTO();
		
		final RouteRequestDTO routeRequest = RouteRequestDTO.create(id);
		
		final List<RouteRequestDTO> results = factory.getRouteRequestDTO().find(routeRequest);
		
		if(results.isEmpty()) {
			result = results.get(0);
		}
		return null;
	}

}
