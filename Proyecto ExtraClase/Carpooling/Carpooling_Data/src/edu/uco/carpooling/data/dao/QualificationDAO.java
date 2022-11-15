package edu.uco.carpooling.data.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.domain.QualificationDTO;

public interface QualificationDAO {
    void create (QualificationDTO qualification);

    List<QualificationDTO>find (QualificationDTO qualification);

    void update(QualificationDTO qualification);

    void delete(UUID id) throws SQLException;


}
