package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.DriverPerVehicleDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.AuthorizedCategoryDTO;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.DriverPerVehicleDTO;
import edu.uco.carpooling.domain.VehicleDTO;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class DriverPerVehiclePostgresSqlDAO extends DAORelational implements DriverPerVehicleDAO {

	protected DriverPerVehiclePostgresSqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(DriverPerVehicleDTO driverPerVehicle) {
		final var sql = "INSERT INTO \"DriverVehicle\"(\"idVehicle\",\"idDriver\",state) + VALUES(?,?,?)";
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, driverPerVehicle.getDriver().getIdAsString());
			preparedStatement.setString(2, driverPerVehicle.getVehicle().getIdAsString());
			preparedStatement.setString(3, driverPerVehicle.getState());

			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			String message = Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER_PER_VEHICLE
					.concat(driverPerVehicle.getVehicle().getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DRIVER_PER_VEHICLE,
					exception);
		}
	}

   /* @Override
    public List<DriverPerVehicleDTO> find(DriverPerVehicleDTO driverPerVehicle) {

        var sqlBuilder = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        creatSelectFrom(sqlBuilder);
        creatWhere(sqlBuilder, driverPerVehicle, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);

    }*/

    private final void creatSelectFrom(final StringBuilder sqlBuilder) {

        sqlBuilder.append("SELECT     DPR.Id AS IdDriverPerVehicle");
        sqlBuilder.append("           Dr.Id AS IdDriver ");
        sqlBuilder.append("           Vh.Id AS IdVehicle ");



        sqlBuilder.append("FROM       DriverPerVehicle DPR");
        sqlBuilder.append("INNER JOIN Driver Dr");
        sqlBuilder.append("ON         Dr.Id = DPR.IdDriver");
        sqlBuilder.append("INNER JOIN Vehicle Vh");
        sqlBuilder.append("ON         Vh.Id = DPR.IdVehicle");
    }

    private void  createOrderBy(final StringBuilder sqlBuilder) {
        sqlBuilder.append("ORDER BY   Dr.id ASC,");
        sqlBuilder.append("           Vh.id ASC");
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

    private final DriverPerVehicleDTO fillDriverPerVehicleDTO(final ResultSet resultSet) {
        try {
            return DriverPerVehicleDTO.create(resultSet.getString("IdDriverPerVehicle"),
                    fillDriverDTO(resultSet),
                    fillVehicleDTO(resultSet));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.AuthotizedCategoryPostgresSqlDAOPostgresSqlDAO.TECHNICAL_PROBLEM_FILL_AUTHORIZED_CATEGORY_AUTHORIZED_CATEGORY_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.AuthotizedCategoryPostgresSqlDAOPostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_AUTHORIZED_CATEGORY_DTO, exception);
        }
    }

    private final VehicleDTO fillVehicleDTO(final ResultSet resultSet) {
        try {
            return VehicleDTO.create(resultSet.getString("IdVehicle"),resultSet.getString("plate"),
                    fillDriverDTO(resultSet),
                    resultSet.getInt("capacity"),resultSet.getString("numEnrollment"));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO, exception);
        }
    }
    
    private final DriverDTO fillDriverDTO(final ResultSet resultSet) {
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
            return AuthorizedCategoryDTO.create(resultSet.getString("idCategory"),
                    resultSet.getString("category"),
                    resultSet.getString("validity"));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
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
