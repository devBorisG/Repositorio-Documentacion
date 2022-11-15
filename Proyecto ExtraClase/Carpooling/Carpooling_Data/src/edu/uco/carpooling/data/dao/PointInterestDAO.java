package edu.uco.carpooling.data.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.PointInterestDTO;

public interface PointInterestDAO {
    void create(PointInterestDTO pointInterest);

    List<PointInterestDTO> find( PointInterestDTO pointInterest);

    void update(PointInterestDTO pointInterest);

    void delete(UUID id) throws SQLException;

}
