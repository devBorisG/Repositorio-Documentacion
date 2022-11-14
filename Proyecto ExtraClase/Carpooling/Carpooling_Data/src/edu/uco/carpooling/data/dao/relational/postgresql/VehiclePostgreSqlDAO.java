package edu.uco.carpooling.data.dao.relational.postgresql;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.ObjectHelper;
import edu.uco.carpooling.crosscutting.helper.StringHelper;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.VehicleDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.VehicleDTO;
import edu.uco.carpooling.domain.builder.DriverDTOBuilder;

public final class VehiclePostgreSqlDAO extends DAORelational implements VehicleDAO {

	public VehiclePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final VehicleDTO vehicle) {
		final var sql = "INSERT INTO \"Vehicle\"(id, plate, capacity, \"numEnrollment\") VALUES (?,?,?,?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, vehicle.getIdAsString());
			preparedStatement.setString(2, vehicle.getPlate());
			preparedStatement.setInt(3, vehicle.getCapacity());
			preparedStatement.setString(4, vehicle.getNumberEnrollment());

			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.VehiclePosgreSqlDao.TECHNICAL_PROBLEM_CREATE_VEHICLE
					.concat(vehicle.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.VehiclePosgreSqlDao.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_VEHICLE, exception);
		}
	}

	@Override
	public final void delete(final UUID id) {
		final var sql = "DELETE FROM \"Driver\" WHERE id = ?";
		final var idAsString = getUUIDAsString(id);

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, idAsString);

			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			final String message = Messages.VehiclePosgreSqlDao.TECHNICAL_PROBLEM_DELETE_VEHICLE.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.VehiclePosgreSqlDao.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_VEHICLE, exception);
		}

	}

	@Override
	public final List<VehicleDTO> findPlate(final VehicleDTO vehicle) {
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);
		createWhere(sqlBuilder, vehicle);

		return preparedAndExecuteQuery(sqlBuilder, vehicle);

	}

	private List<VehicleDTO> preparedAndExecuteQuery(StringBuilder sqlBuilder, VehicleDTO vehicle) {
		try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
			preparedStatement.setString(1, vehicle.getIdAsString());
			return executeQuery(preparedStatement);
		} catch (DataCarpoolingException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.VehiclePosgreSqlDao.TECHNICAL_PROBLEM_PREPARED_STATEMENT, exception);
		} catch (Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.VehiclePosgreSqlDao.TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT, exception);
		}
	}

	private List<VehicleDTO> executeQuery(PreparedStatement preparedStatement) {
		try (final var resultSet = preparedStatement.executeQuery()) {
			return fillResults(resultSet);
		} catch (DataCarpoolingException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw DataCarpoolingException
					.createTechnicalException(Messages.VehiclePosgreSqlDao.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
		} catch (Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.VehiclePosgreSqlDao.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY, exception);
		}
	}

	private final List<VehicleDTO> fillResults(final ResultSet resultSet) {
		try {
			var results = new ArrayList<VehicleDTO>();

			while (resultSet.next()) {
				results.add(fillVehicleDTO(resultSet));
			}

			return results;
		} catch (DataCarpoolingException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw DataCarpoolingException
					.createTechnicalException(Messages.VehiclePosgreSqlDao.TECHNICAL_PROBLEM_FILL_RESULTS, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.VehiclePosgreSqlDao.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
		}
	}

	private final VehicleDTO fillVehicleDTO(final ResultSet resultSet) {
		try {
			return VehicleDTO.create(resultSet.getString("IdVehicle"), resultSet.getString("Plate"),
					DriverDTOBuilder.getDriverDTOBuilder().build(), resultSet.getInt("Capacity"),
					resultSet.getString("NumberEnrollment"));
		} catch (final SQLException exception) {
			throw DataCarpoolingException
					.createTechnicalException(Messages.VehiclePosgreSqlDao.TECHNICAL_PROBLEM_FILL_VEHICLE, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.VehiclePosgreSqlDao.TECHNICAL_UNEXPECTED_PROBLEM_FILL_VEHICLE, exception);
		}
	}

	private final void createWhere(final StringBuilder sqlBuilder, final VehicleDTO vehicle) {
		
		if (!ObjectHelper.isNull(vehicle) && !StringHelper.isEmpty(vehicle.getPlate())) {
			sqlBuilder.append("WHERE plate = ?");
		}
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder) {
		sqlBuilder.append("SELECT id AS IdVehicle, ");
		sqlBuilder.append("plate AS Plate, ");
		sqlBuilder.append("capacity AS Capacity, ");
		sqlBuilder.append("\"numEnrollment\" AS NumberEnrollment ");
		sqlBuilder.append("FROM \"Vehicle\" ");

	}
}
