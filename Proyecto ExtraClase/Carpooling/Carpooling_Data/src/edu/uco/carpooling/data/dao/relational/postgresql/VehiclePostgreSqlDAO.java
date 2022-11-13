package edu.uco.carpooling.data.dao.relational.postgresql;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.VehicleDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.VehicleDTO;

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
		final var sql = "DELETE FROM \"Driver\" WHERE id= ?";
		final var idAsString = getUUIDAsString(id);

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, idAsString);

			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			final String message = Messages.VehiclePosgreSqlDao.TECHNICAL__PROBLEM_DELETE_VEHICLE.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.VehiclePosgreSqlDao.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_VEHICLE, exception);
		}

	}

}
