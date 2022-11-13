package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.CustomerDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.CustomerDTO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

public final class CustomerPostgreSqlDAO extends DAORelational implements CustomerDAO {

	public CustomerPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final CustomerDTO customer) {
		final var sql = "INSERT INTO \"Customer\"(id, dni, \"firstName\",\"secondName\","
				+ "\"firstSurname\",\"secondSurname\", password, \"phone\",\"companyEmail\""
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareCall(sql)) {
			preparedStatement.setString(1, customer.getIdAsString());
			preparedStatement.setString(2, customer.getDni());
			preparedStatement.setString(3, customer.getFirstName());
			preparedStatement.setString(4, customer.getSecondName());
			preparedStatement.setString(5, customer.getFirstSurname());
			preparedStatement.setString(6, customer.getSecondSurname());
			preparedStatement.setString(7, customer.getPassword());
			preparedStatement.setInt(8, customer.getPhone());
			preparedStatement.setString(9, customer.getCompanyEmail());

			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_CUSTOMER
					.concat(customer.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CUSTOMER, exception);
		}

	}

	@Override
	public final List<CustomerDTO> find(final CustomerDTO user) {

		var sqlBuilder = new StringBuilder();
		final var parameters = new ArrayList<Object>();
		
		return null;
	}

	@Override
	public final void update(final CustomerDTO customer) {
		final var sql = "UPDATE User SET \"firstName\" = ?," + "\"secondName\" = ?," + "\"firstSurname\" = ?,"
				+ "\"secondSurname\" = ?," + "password = ?," + "\"phone\" = ?," + "companyEmail = ?," + "WHERE id = ?";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getSecondName());
			preparedStatement.setString(3, customer.getFirstSurname());
			preparedStatement.setString(4, customer.getSecondSurname());
			preparedStatement.setString(5, customer.getPassword());
			preparedStatement.setInt(6, customer.getPhone());
			preparedStatement.setString(7, customer.getCompanyEmail());			
			preparedStatement.setString(8, customer.getIdAsString());

			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_CUSTOMER
					.concat(customer.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_CUSTOMER, exception);
		}

	}

	@Override
	public final void delete(final UUID id) {
		final var sql = "DELETE FROM \"Customer\" WHERE id = ?";
		final var idAsString = getUUIDAsString(id);

		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			
			preparedStatement.setString(1, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_DELETE_CUSTOMER.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_CUSTOMER, exception);
		}
	}
}
