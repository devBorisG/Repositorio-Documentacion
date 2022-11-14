package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
<<<<<<< HEAD
=======
import java.sql.ResultSet;
>>>>>>> dev_federico
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.ObjectHelper;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
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
		final var sql = "INSERT INTO \"Customer\"(id, dni, \"firstName\",\"secondName\",\"firstSurname\",\"secondSurname\", password, \"referencePoint\", born "
=======
		final var sql = "INSERT INTO \"User\"(id, dni, \"firstName\",\"secondName\","
				+ "\"firstSurname\",\"secondSurname\", password, \"phone\",\"companyEmail\")"
>>>>>>> dev_federico
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

			insertPhone(customer.getIdAsString(), customer.getPhone());
			insertCompanyEmail(customer.getIdAsString(), customer.getCompanyEmail());
=======
			preparedStatement.setInt(8, customer.getPhone());
			preparedStatement.setString(9, customer.getCompanyEmail());
>>>>>>> dev_federico
			
			preparedStatement.executeUpdate();
			
			insertPhone(customer.getIdAsString(), customer.getPhone());
			insertCompanyEmail(customer.getIdAsString(), customer.getCompanyEmail());
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
		
		creatSelectFrom(sqlBuilder);
		creatWhere(sqlBuilder, user, parameters);
		createOrderBy(sqlBuilder);
		
		return prepareAndExecuteQuery(sqlBuilder, parameters);
	}
	
	
	
	private final List<CustomerDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
		try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){
			
			SetParameterValues(preparedStatement, parameters);
			
			return executeQuery(preparedStatement);
			
		} catch (final DataCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_PREPARED_STAMENT, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
		}
	}
	
	private final void creatSelectFrom(final StringBuilder sqlBuilder) {
		sqlBuilder.append("SELECT     U.Id AS UserId,");
		sqlBuilder.append("           U.dni AS DniUser,");
		sqlBuilder.append("           U.firstName AS Name,");
		sqlBuilder.append("           U.secondName AS SecondName,");
		sqlBuilder.append("           U.firstSurname AS FirstSurname,");
		sqlBuilder.append("           U.SecondSurname AS SecondSurname,");
		sqlBuilder.append("           U.phone As Phone,");
		sqlBuilder.append("           U.companyEmail AS Email");
		sqlBuilder.append("FROM       User U");	
	}
	
	private final void creatWhere(final StringBuilder sqlBuilder, final CustomerDTO user, final List<Object> parameters) {
		if(!ObjectHelper.isNull(user)) {
			UUIDHelper.isDefaultUUID(user.getId());
			sqlBuilder.append("WHERE U.id = ? ");
			parameters.add(user.getIdAsString());
		}
	}
	
	private void  createOrderBy(final StringBuilder sqlBuilder) {
		sqlBuilder.append("ORDER BY   U.firstName ASC,");
	}
	
	private final List<CustomerDTO> executeQuery (PreparedStatement preparedStatement){
		try (final var resultset = preparedStatement.executeQuery()){
			
			return fillResults(resultset);
			
		} catch (DataCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
		}
	}
	
	private void SetParameterValues (PreparedStatement preparedStatement, final List<Object> parameters) {
		try {
			for(int index = 0; index < parameters.size(); index ++) {
				preparedStatement.setObject(index + 1, parameters.get(index));
			}
		} catch (SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
		}
	}
	
private final List<CustomerDTO> fillResults(final ResultSet resultSet){
		
		try {
			var results = new ArrayList<CustomerDTO>();
			
			while(resultSet.next()) {
				results.add(fillCustomer(resultSet));
			}
			return results;
		} catch (final DataCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_FILL_RESULTS, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
		}
	}

	private final CustomerDTO fillCustomer(final ResultSet resultSet) {
		try {
			
				return CustomerDTO.create(resultSet.getString("IdCustomer"), resultSet.getString("dniCustomer"), 
						resultSet.getString("FirstNameCutomer"), resultSet.getString("SecondNameCustomer"), 
						resultSet.getString("FirstSurnameCustomer"), resultSet.getString("SecondSurnameCustomer"), 
						resultSet.getString("Password"),resultSet.getInt("Phone"), 
						resultSet.getString("Email"));
						
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER_DTO, exception);
		}
	}

	@Override
<<<<<<< HEAD
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
			
			
			preparedStatement.executeUpdate();
			preparedStatementPhone.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_CUSTOMER
					.concat(user.getIdAsString());
=======
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
			
			
		} catch (final SQLException exception) {
			final String message = Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_CUSTOMER
					.concat(customer.getIdAsString());
>>>>>>> dev_federico
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
			
			deletePhone(idAsString);
			deleteCompanyEmail(idAsString);
			
			preparedStatement.setString(1, idAsString);
			
=======
>>>>>>> dev_federico
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

=======
>>>>>>> dev_federico
}
