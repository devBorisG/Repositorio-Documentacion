package edu.uco.carpooling.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.VehicleDTO;

public interface VehicleDAO {

	void create(VehicleDTO vehicle);
	
	List<VehicleDTO> find(VehicleDTO vehicle);
	
	void delete(UUID id);
}
