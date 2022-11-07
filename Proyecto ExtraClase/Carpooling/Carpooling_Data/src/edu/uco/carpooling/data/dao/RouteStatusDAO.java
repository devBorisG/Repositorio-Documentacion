package edu.uco.carpooling.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.RouteStatusDTO;

public interface RouteStatusDAO {
	
	void create(RouteStatusDTO status);
	
	List<RouteStatusDTO> find(RouteStatusDTO status);
	
	void update(RouteStatusDTO status);
	
	void delete(UUID id);
}
