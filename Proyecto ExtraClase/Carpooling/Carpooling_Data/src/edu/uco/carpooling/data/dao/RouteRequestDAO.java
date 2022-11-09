package edu.uco.carpooling.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.RouteRequestDTO;

public interface RouteRequestDAO {
	
	void create(RouteRequestDTO routeRequest);
	
	List<RouteRequestDTO> find(RouteRequestDTO routeRequest);
	
	void update(RouteRequestDTO routeRequest);
	
	void delete(UUID id);
}
