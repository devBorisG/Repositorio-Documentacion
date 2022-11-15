package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.DriverPerVehicleDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.DriverPerVehicleDTO;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class DriverPerVehiclePostgresSqlDAO extends DAORelational implements DriverPerVehicleDAO {

	public DriverPerVehiclePostgresSqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final DriverPerVehicleDTO driverPerVehicle) {
		final var sql = "INSERT INTO drivervehicle(iddriver,idvehicle) VALUES(?,?)";
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, driverPerVehicle.getDriver().getIdAsString());
			preparedStatement.setString(2, driverPerVehicle.getVehicle().getIdAsString());

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


    @Override
    public void delete(UUID id) throws SQLException {
        final var sql = "DELETE FROM drivervehicle WHERE id = ?";
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
