package edu.uco.carpooling.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.RouteDTO;

public interface RouteDAO {
	
	void create(RouteDTO route);
	
	List<RouteDTO> find(RouteDTO route);
	
	void update(RouteDTO route);
	
	void delete(UUID id);
}
