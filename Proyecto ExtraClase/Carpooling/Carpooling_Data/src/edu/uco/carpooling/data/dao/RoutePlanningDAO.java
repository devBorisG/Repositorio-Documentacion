package edu.uco.carpooling.data.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.RoutePlanningDTO;

public interface RoutePlanningDAO {
	
	void create (RoutePlanningDTO routePlanning);

	List<RoutePlanningDTO>find (RoutePlanningDTO routePlanning);

	void update(RoutePlanningDTO routePlanning);

	void delete(UUID id) throws SQLException;

}
