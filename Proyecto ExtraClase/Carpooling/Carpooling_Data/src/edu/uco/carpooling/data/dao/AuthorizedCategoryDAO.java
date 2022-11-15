package edu.uco.carpooling.data.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.AuthorizedCategoryDTO;

public interface AuthorizedCategoryDAO {

	    void create(AuthorizedCategoryDTO authorizedCategory);

	    List<AuthorizedCategoryDTO> find(AuthorizedCategoryDTO authorizedCategory);

	    void update(AuthorizedCategoryDTO authorizedCategory);

	    void delete(UUID id) throws SQLException;
	}
