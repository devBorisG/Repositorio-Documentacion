package edu.uco.carpooling.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.RouteDetailDTO;

public interface DetailRouteDAO {

	void create(RouteDetailDTO routeDetail);
	
	List<RouteDetailDTO> find(RouteDetailDTO routeDetail);
	
	void update(RouteDetailDTO routeDetail);
	
	void delete(UUID id);
}
