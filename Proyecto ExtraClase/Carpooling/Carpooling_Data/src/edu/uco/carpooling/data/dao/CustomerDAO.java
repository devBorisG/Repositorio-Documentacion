package edu.uco.carpooling.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.CustomerDTO;

public interface CustomerDAO {
	
	void create(CustomerDTO user);
	
	List<CustomerDTO> find(CustomerDTO user);
	List<CustomerDTO> findById(String id);
	
	void update(CustomerDTO user);
	
	void delete(UUID id);
}
