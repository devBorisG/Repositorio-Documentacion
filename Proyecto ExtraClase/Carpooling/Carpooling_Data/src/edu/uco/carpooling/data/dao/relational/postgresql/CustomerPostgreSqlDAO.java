package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.CustomerDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.CustomerDTO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getRandomUUIDAsString;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

public final class CustomerPostgreSqlDAO extends DAORelational implements CustomerDAO {

	public CustomerPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final CustomerDTO user) {
		final var sql = "INSERT INTO Customer(id, dni, firstName,secondName,firstSurname,secondSurname, password, referencePoint"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareCall(sql)) {
			preparedStatement.setString(1, user.getIdAsString());
			preparedStatement.setString(2, user.getDni());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getSecondName());
			preparedStatement.setString(5, user.getFirstSurname());
			preparedStatement.setString(6, user.getSecondSurname());
			preparedStatement.setString(7, user.getPassword());
			preparedStatement.setString(8, user.getReferencePoint());

			insertPhone(user.getIdAsString(), user.getPhone());
			insertCompanyEmail(user.getIdAsString(), user.getCompanyEmail());
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_CUSTOMER
					.concat(user.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
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
		final var sql = "UPDATE Customer SET firstName = ?," + "secondName = ?," + "firstSurname = ?,"
				+ "secondSurname = ?," + "password = ?," + "referencePoint = ?" + "WHERE id = ?";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getSecondName());
			preparedStatement.setString(3, user.getFirstSurname());
			preparedStatement.setString(4, user.getSecondSurname());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getReferencePoint());
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_CUTOMER
					.concat(user.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_CUSTOMER, exception);
		}

	}

	@Override
	public final void delete(final UUID id) {
		final var sql = "DELETE FROM Customer WHERE id = ?";
		final var idAsString = getUUIDAsString(id);

		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_DELETE_CUSTOMER.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} 
		
		catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_CUSTOMER, exception);
		}

	}

	private final void insertPhone(final String idAsString, final int phone) {
		final var sql = "INSERT INTO Phone(id, phone, Customer_id) " + "VALUES (?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, getRandomUUIDAsString());
			preparedStatement.setInt(2, phone);
			preparedStatement.setString(3, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
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
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_EMAIL_TO_CUSTOMER
					.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_EMAIL_TO_CUSTOMER, exception);
		}
	}

}
