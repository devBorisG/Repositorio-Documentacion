package edu.uco.carpooling.data.dao.relational.postgresql;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getRandomUUIDAsString;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.CustomerDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.CustomerDTO;

public final class CustomerPostgreSqlDAO extends DAORelational implements CustomerDAO {

	public CustomerPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final CustomerDTO user) {
		final var sql = "INSERT INTO Customer(id, dni, firstName,secondName,firstSurname,secondSurname, password, referencePoint, born "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareCall(sql)) {
			preparedStatement.setString(1, user.getIdAsString());
			preparedStatement.setString(2, user.getDniAsString());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getSecondName());
			preparedStatement.setString(5, user.getFirstSurname());
			preparedStatement.setString(6, user.getSecondSurname());
			preparedStatement.setString(7, user.getPassword());
			preparedStatement.setString(8, user.getReferencePoint());
			preparedStatement.setString(9, user.getBornAsString());

			insertPhone(user.getIdAsString(), user.getPhoneAsString());
			insertCompanyEmail(user.getIdAsString(), user.getCompanyEmail());

		} catch (SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_CUSTOMER
					.concat(user.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CUSTOMER, exception);
		}

	}

	@Override
	public final List<CustomerDTO> find(final CustomerDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final void update(final CustomerDTO user) {
		// TODO Auto-generated method stub

	}

	@Override
	public final void delete(final UUID id) {
		// TODO Auto-generated method stub

	}

	private final void insertPhone(final String idAsString, final String phoneAsString) {
		final var sql = "INSERT INTO Phone(id, phone, Customer_id) " + "VALUES (?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, getRandomUUIDAsString());
			preparedStatement.setString(2, phoneAsString);
			preparedStatement.setString(3, idAsString);
		} catch (SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_PHONE_TO_CUSTOMER
					.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_PHONE_TO_CUSTOMER, exception);
		}
	}

	private final void insertCompanyEmail(final String idAsString, final String email) {
		final var sql = "INSER INTO CompanyEmail(id, email, Customer_id)" + "VALUES(?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, getRandomUUIDAsString());
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, idAsString);
		} catch (SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_EMAIL_TO_CUSTOMER
					.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_EMAIL_TO_CUSTOMER, exception);
		}
	}

}
