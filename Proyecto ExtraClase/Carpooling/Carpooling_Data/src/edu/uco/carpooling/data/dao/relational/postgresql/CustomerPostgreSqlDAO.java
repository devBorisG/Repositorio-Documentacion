package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		final var sql = "INSERT INTO public.user(id, dni, firstname,secondname,"
				+ "firstsurname,secondsurname, password, phone,companyemail)"
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
			throw DataCarpoolingException.createTechnicalException(Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_PREPARED_STAMENT, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
		}
	}
	
	private final void creatSelectFrom(final StringBuilder sqlBuilder) {
		sqlBuilder.append("SELECT u.id AS UserId,");
		sqlBuilder.append(" u.dni AS DniUser,");
		sqlBuilder.append(" u.firstname AS Name,");
		sqlBuilder.append(" u.secondname AS SecondName,");
		sqlBuilder.append(" u.firstsurname AS FirstSurname,");
		sqlBuilder.append(" u.secondsurname AS SecondSurname,");
		sqlBuilder.append(" u.password AS Password,");
		sqlBuilder.append(" u.phone As Phone,");
		sqlBuilder.append(" u.companyemail AS Email");
		sqlBuilder.append(" FROM public.user u");	
	}
	
	private final void creatWhere(final StringBuilder sqlBuilder, final CustomerDTO user, final List<Object> parameters) {
		if(!ObjectHelper.isNull(user)) {
			UUIDHelper.isDefaultUUID(user.getId());
			sqlBuilder.append(" WHERE u.id = ? ");
			parameters.add(user.getIdAsString());
		}
	}
	
	private void  createOrderBy(final StringBuilder sqlBuilder) {
		sqlBuilder.append("ORDER BY   U.firstsurname ASC,");
	}

	private final List<CustomerDTO> executeQuery(PreparedStatement preparedStatement) {
		
		try (final var resultset = preparedStatement.executeQuery()) {
			
			return fillResults(resultset);
			
		} catch (DataCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
			throw DataCarpoolingException
					.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY
							.concat("\nMore info: ").concat(exception.getMessage()), exception);
		}
	}
	
	private void SetParameterValues (PreparedStatement preparedStatement, final List<Object> parameters) {
		try {
			for(int index = 0; index < parameters.size(); index ++) {
				preparedStatement.setString(index + 1, parameters.get(index).toString());
			}
		} catch (SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
			throw DataCarpoolingException.createTechnicalException(
					Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
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
			throw DataCarpoolingException.createTechnicalException(Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER, exception);      
	}

	private final CustomerDTO fillCustomer(final ResultSet resultSet) {
		try {
			
				return CustomerDTO.create(resultSet.getString("UserId"), resultSet.getString("DniUser"), 
						resultSet.getString("Name"), resultSet.getString("SecondName"), 
						resultSet.getString("FirstSurname"), resultSet.getString("SecondSurname"), 
						resultSet.getString("Password"),resultSet.getInt("Phone"), 
						resultSet.getString("Email"));
						
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.CustomerPostgreSqlDAO.TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.CustomerPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER, exception);
		}
	}

	public final void update(final CustomerDTO customer) {
		final var sql = "UPDATE User SET firstName = ?," + "secondName = ?," + "firstSurname = ?,"
				+ "secondSurname = ?," + "password = ?," + "phone = ?," + "companyEmail = ?," + "WHERE id = ?";

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
