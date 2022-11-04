package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.DriverDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.DriverDTO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getRandomUUIDAsString;

public final class DriverPostresSqlDAO extends DAORelational implements DriverDAO {

	public DriverPostresSqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(final DriverDTO driver) {
		final var sql = "INSERT INTO Driver(id,dni,firstName,secondName,firstSurname,secondSurname, password, born) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, driver.getIdAsString());
			preparedStatement.setString(2, driver.getDniAsString());
			preparedStatement.setString(3, driver.getFirstName());
			preparedStatement.setString(4, driver.getSecondName());
			preparedStatement.setString(5, driver.getFirstSurname());
			preparedStatement.setString(6, driver.getSecondSurname());
			preparedStatement.setString(7, driver.getPassword());
			preparedStatement.setString(8, driver.getBornAsString());

			insertPhone(driver.getIdAsString(), driver.getPhoneAsString());
			insertCompanyEmail(driver.getIdAsString(), driver.getCompanyEmail());

		} catch (final SQLException exception) {
			final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER
					.concat(driver.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DRIVER, exception);
		}
	}

	@Override
	public List<DriverDTO> find(final DriverDTO driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(final DriverDTO driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(final UUID id) {
		// TODO Auto-generated method stub

	}

	private final void insertPhone(final String idAsString, final String phoneAsString) {

		final var sql = "INSERT INTO Phone(id, phone, Driver_id) " + "VALUES (?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, getRandomUUIDAsString());
			preparedStatement.setString(2, phoneAsString);
			preparedStatement.setString(3, idAsString);
		} catch (SQLException exception) {
			final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_PHONE_TO_DRIVER.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_PHONE_TO_DRIVER, exception);
		}
	}

	private final void insertCompanyEmail(final String idAsString, final String email) {

		final var sql = "INSER INTO CompanyEmail(id, email, Driver_id)" + "VALUES(?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, getRandomUUIDAsString());
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, idAsString);
		} catch (SQLException exception) {
			final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_EMAIL_TO_DRIVER.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_EMAIL_TO_DRIVER, exception);
		}
	}
}
