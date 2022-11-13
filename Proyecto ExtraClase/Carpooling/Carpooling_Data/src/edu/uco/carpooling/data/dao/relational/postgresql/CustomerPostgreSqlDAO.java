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
<<<<<<< HEAD
		final var sql = "INSERT INTO \"Customer\"(id, dni, \"firstName\",\"secondName\","
				+ "\"firstSurname\",\"secondSurname\", password, \"phone\",\"companyEmail\""
=======
		final var sql = "INSERT INTO \"Customer\"(id, dni, \"firstName\",\"secondName\",\"firstSurname\",\"secondSurname\", password, \"referencePoint\", born "
>>>>>>> main
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareCall(sql)) {
			preparedStatement.setString(1, customer.getIdAsString());
			preparedStatement.setString(2, customer.getDni());
			preparedStatement.setString(3, customer.getFirstName());
			preparedStatement.setString(4, customer.getSecondName());
			preparedStatement.setString(5, customer.getFirstSurname());
			preparedStatement.setString(6, customer.getSecondSurname());
			preparedStatement.setString(7, customer.getPassword());
<<<<<<< HEAD
			preparedStatement.setInt(8, customer.getPhone());
			preparedStatement.setString(9, customer.getCompanyEmail());

			preparedStatement.executeUpdate();
			
=======

			insertPhone(customer.getIdAsString(), customer.getPhone());
			insertCompanyEmail(customer.getIdAsString(), customer.getCompanyEmail());
			
			preparedStatement.executeUpdate();
			
			insertPhone(customer.getIdAsString(), customer.getPhone());
			insertCompanyEmail(customer.getIdAsString(), customer.getCompanyEmail());
>>>>>>> main
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
<<<<<<< HEAD
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

=======
	public final void update(final CustomerDTO user) {
		final var sql = "UPDATE Customer SET \"firstName\" = ?," + "\"secondName\" = ?," + "\"firstSurname\" = ?,"
				+ "\"secondSurname\" = ?," + "password = ?," + "WHERE id = ?";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			final var preparedStatementPhone = updatePhone(user.getIdAsString(), user.getPhone());
			updateCompanyEmail(user.getIdAsString(), user.getCompanyEmail());
			
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getSecondName());
			preparedStatement.setString(3, user.getFirstSurname());
			preparedStatement.setString(4, user.getSecondSurname());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getIdAsString());
			
>>>>>>> main
			
			preparedStatement.executeUpdate();
			preparedStatementPhone.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_CUSTOMER
<<<<<<< HEAD
					.concat(customer.getIdAsString());
=======
					.concat(user.getIdAsString());
>>>>>>> main
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
			
<<<<<<< HEAD
=======
			deletePhone(idAsString);
			deleteCompanyEmail(idAsString);
			
>>>>>>> main
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
<<<<<<< HEAD
=======

	private final void insertPhone(final String idAsString, final int phone) {
		final var sql = "INSERT INTO \"Phone\"(id, phone, \"Customer_id\") " + "VALUES (?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, getRandomUUIDAsString());
			preparedStatement.setInt(2, phone);
			preparedStatement.setString(3, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_CUSTOMER
					.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CUSTOMER, exception);
		}
	}

	private final void insertCompanyEmail(final String idAsString, final String email) {
		final var sql = "INSERT INTO \"CompanyEmail\"(id, email, \"Customer_id\")" + "VALUES(?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, getRandomUUIDAsString());
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_CUSTOMER
					.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CUSTOMER, exception);
		}
	}
	
	private final PreparedStatement updatePhone(final String idAsString, final int phone) {
		final var sql = "UPDATE \"Phone\" SET phone = ? WHERE \"Customer_id\" = ?";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setInt(1, phone);
			preparedStatement.setString(2, idAsString);
			
			return preparedStatement;
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_CUSTOMER
					.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_CUSTOMER, exception);
		}
	}
	
	private final void updateCompanyEmail(final String idAsString, final String email) {
		final var sql = "UPDATE \"CompanyEmail\" SET email = ? WHERE \"Customer_id\" = ?";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_CUSTOMER
					.concat(idAsString);
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_CUSTOMER, exception);
		}
		
	}
	
	private final void deletePhone(final String idAsString) {
		final var sql = "DELETE FROM \"Phone\" WHERE \"Customer_id\"= ?";
		
		try(final var preparedStatement = getConnection().prepareStatement(sql)){
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
	
	private final void deleteCompanyEmail(final String idAsString) {
		final var sql = "DELETE FROM \"CompanyEmail\" WHERE \"Customer_id\"= ?";
		
		try(final var preparedStatement = getConnection().prepareStatement(sql)){
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

>>>>>>> main
}
