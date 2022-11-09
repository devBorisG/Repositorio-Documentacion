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
import edu.uco.carpooling.data.dao.AuthorizedCategoryDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.AuthorizedCategoryDTO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class AuthorizedCategoryPostgresSqlDAO extends DAORelational implements AuthorizedCategoryDAO {

    protected AuthorizedCategoryPostgresSqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(AuthorizedCategoryDTO authorizedCategory) {

        final var sql = "INSERT INTO DRIVERVEHICLE(idDriverVehicle,Category,Validity) + VALUES(?,?,?)";
        try(final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,authorizedCategory.getIdAsString());
            preparedStatement.setString(2,authorizedCategory.getCategory());
            preparedStatement.setString(3,authorizedCategory.getValidity().toString());

        } catch (final SQLException exception) {
            String message = Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER_PER_VEHICLE.concat(authorizedCategory.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }

    }


    @Override
    public List<AuthorizedCategoryDTO> find(AuthorizedCategoryDTO authorizedCategory) {

        var sqlBuilder = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        creatSelectFrom(sqlBuilder);
        creatWhere(sqlBuilder, authorizedCategory, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);
    }

    private final void creatSelectFrom(final StringBuilder sqlBuilder) {

        sqlBuilder.append("SELECT     AO.Id AS IdAuthorizedCategory");
        sqlBuilder.append("           AO.Category AS Category ");
        sqlBuilder.append("           AO.Validity AS Validity ");


    }

    private final void creatWhere(final StringBuilder sqlBuilder, final AuthorizedCategoryDTO authorizedCategory, final List<Object> parameters) {
        if(!ObjectHelper.isNull(authorizedCategory)) {
            if(!UUIDHelper.isDefaultUUID(authorizedCategory.getId())) {
                sqlBuilder.append("WHERE AO.id = ? ");
                parameters.add(authorizedCategory.getIdAsString());
            }
        }
    }
    private void  createOrderBy(final StringBuilder sqlBuilder) {
        sqlBuilder.append("ORDER BY   AO.id ASC,");

    }

    private final List<AuthorizedCategoryDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){

            SetParameterValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (final DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_PREPARED_STAMENT, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
        }
    }

    private final List<AuthorizedCategoryDTO> executeQuery (PreparedStatement preparedStatement){
        try (final var resultset = preparedStatement.executeQuery()){

            return fillResults(resultset);

        } catch (DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }
    }
    private void SetParameterValues (PreparedStatement preparedStatement, final List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index ++) {
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch (SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
        }
    }

    private final List<AuthorizedCategoryDTO> fillResults(final ResultSet resultSet){

        try {
            var results = new ArrayList<AuthorizedCategoryDTO>();

            while(resultSet.next()) {
                results.add(fillAuthorizedCategoryDTO(resultSet));
            }
            return results;
        } catch (final DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_FILL_RESULTS, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
        }
    }

    private final AuthorizedCategoryDTO fillAuthorizedCategoryDTO(final ResultSet resultSet) {
        try {
            return AuthorizedCategoryDTO.create(resultSet.getString("idCategory"),
                    resultSet.getString("category"),
                    resultSet.getDate("date"));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO, exception);
        }
    }


    @Override
    public void update(AuthorizedCategoryDTO authorizedCategory) {
        final var sql = "UPDATE INTO DRIVERVEHICLE(idAuthorizedCategory,Category,Validity) + VALUES(?,?,?)";

        try (final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, authorizedCategory.getIdAsString());
            preparedStatement.setString(2, authorizedCategory.getCategory());
            preparedStatement.setString(3, authorizedCategory.getValidity().toString());



            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            String message = Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_UPDATE_DRIVER_PER_VEHICLE.concat(authorizedCategory.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }

    }

    @Override
    public void delete(UUID id) throws SQLException {
        final var sql = "DELETE FROM DRIVERVEHICLE WHERE id = ?";
	        final var idAsString = getUUIDAsString(id);

	        try (final var preparedStatement = getConnection().prepareStatement(sql)){
	            preparedStatement.setString(1, idAsString);

	            preparedStatement.executeUpdate();
	        } catch (final SQLException exception) {
	            String message = Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_DELETE_DRIVER_PER_VEHICLE;
	            throw DataCarpoolingException.createTechnicalException(message, exception);
	        } catch (final Exception exception) {
	            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
	        }

	    }
}
