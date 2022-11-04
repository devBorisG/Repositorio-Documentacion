package edu.uco.carpooling.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.DriverDTO;

public interface RouteDAO {
void create(DriverDTO driver);
	
	List<DriverDTO> find(DriverDTO driver);
	
	void update(DriverDTO driver);
	
	void delete(UUID id);
}
