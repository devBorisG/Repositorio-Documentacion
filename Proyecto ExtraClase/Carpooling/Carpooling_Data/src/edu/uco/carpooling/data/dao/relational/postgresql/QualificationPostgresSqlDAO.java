package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.ObjectHelper;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.QualificationDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.AuthorizedCategoryDTO;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.QualificationDTO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;;

public class QualificationPostgresSqlDAO extends DAORelational implements QualificationDAO {


    protected QualificationPostgresSqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(QualificationDTO qualification) {
        final var sql = "INSERT INTO SCORE(idQualification,score,comment,user)"
                + "VALUES(?,?,?,?)";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, qualification.getIdAsString());
            preparedStatement.setDouble(2, qualification.getScore());
            preparedStatement.setString(3, qualification.getComment());
            preparedStatement.setString(4, qualification.getdriver().getIdAsString());

            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            String message = Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_CREATE_ROUTE_REQUEST.concat(qualification.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ROUTE_REQUEST, exception);
        }

    }

    @Override
    public List<QualificationDTO> find(QualificationDTO qualification) {
        var sqlBuilder = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        creatSelectFrom(sqlBuilder);
        creatWhere(sqlBuilder, qualification, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);
    }

    private final List<QualificationDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){

            SetParameterValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (final DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_PREPARED_STAMENT, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
        }
    }

    private final void creatSelectFrom(final StringBuilder sqlBuilder) {
        sqlBuilder.append("SELECT     SC.Id AS IdQualification");
        sqlBuilder.append("           SC.Score AS Puntaje");
        sqlBuilder.append("           SC.Description AS comment");
        sqlBuilder.append("           SC.User AS user");



        sqlBuilder.append("FROM       Score SC");
        sqlBuilder.append("INNER JOIN User Us");
        sqlBuilder.append("ON         Us.Id = SC.IdUser");

    }

    private final void creatWhere(final StringBuilder sqlBuilder, final QualificationDTO qualification, final List<Object> parameters) {
        if(!ObjectHelper.isNull(qualification)) {
            var setWhere = true;

            if (!UUIDHelper.isDefaultUUID(qualification.getId())) {
                sqlBuilder.append("WHERE SC.id = ? ");
                setWhere = false;
                parameters.add(qualification.getIdAsString());
            }

            if (!UUIDHelper.isDefaultUUID(qualification.getdriver().getId())) {
                sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("Us.IdUser = ? ");
                parameters.add(qualification.getdriver().getIdAsString());
            }

        }
    }

    private void  createOrderBy(final StringBuilder sqlBuilder) {
        sqlBuilder.append("ORDER BY   SC.id ASC,");
        sqlBuilder.append("           US.Id ASC");
    }

    private final List<QualificationDTO> executeQuery (PreparedStatement preparedStatement){
        try (final var resultset = preparedStatement.executeQuery()){

            return fillResults(resultset);

        } catch (DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }
    }

    private void SetParameterValues (PreparedStatement preparedStatement, final List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index ++) {
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch (SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
        }
    }

    private final List<QualificationDTO> fillResults(final ResultSet resultSet){

        try {
            var results = new ArrayList<QualificationDTO>();

            while(resultSet.next()) {
                results.add(fillQualificationDTO(resultSet));
            }
            return results;
        } catch (final DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_FILL_RESULTS, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
        }
    }
    
    private final DriverDTO fillDriver(final ResultSet resultSet) {
        try {
        	return DriverDTO.create(resultSet.getString("IdDirver"),resultSet.getString("License"),fillAuthorizedCategoryDTO(resultSet));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER_DTO, exception);
        }
    }
    
	private final AuthorizedCategoryDTO fillAuthorizedCategoryDTO(final ResultSet resultSet) {
		try {
			return AuthorizedCategoryDTO.create(resultSet.getString("IdCategory"),
					resultSet.getString("NameCategory"),resultSet.getString("Validity"));
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_AUTHORIZED_CATEGOTY_DTO, exception);			
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_AUTHORIZED_CATEGOTY_DTO, exception);
		}
	}
	
	private final QualificationDTO fillQualificationDTO(final ResultSet resultSet) {
		try {
			return QualificationDTO.create(resultSet.getString("id"),resultSet.getInt("Score"),
					resultSet.getString("Comment"), fillDriver(resultSet));
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_AUTHORIZED_CATEGOTY_DTO, exception);			
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_AUTHORIZED_CATEGOTY_DTO, exception);
		}
	}

    @Override
    public void update(QualificationDTO qualification) {
        final var sql = "UPDATE INTO SCORE(id, score, comment, idUser) VALUES (?,?,?,?) ";

        try (final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, qualification.getIdAsString());
            preparedStatement.setDouble(2, qualification.getScore());
            preparedStatement.setString(3, qualification.getComment());
            preparedStatement.setString(4, qualification.getdriver().getIdAsString());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            String message = Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_UPDATE_ROUTE_REQUEST.concat(qualification.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }

    }

    @Override
    public void delete(UUID id) throws SQLException {
        final var sql = "DELETE FROM SCORE WHERE idQualification = ?";
        final var idAsString = getUUIDAsString(id);

        try (final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, idAsString);

            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            String message = Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_UPDATE_ROUTE_REQUEST;
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }
    }
}
