package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.ObjectHelper;
import edu.uco.carpooling.crosscutting.helper.StringHelper;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.RouteRequestDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.domain.RouteRequestDTO;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class RouteRequestPostgreSqlDAO extends DAORelational implements RouteRequestDAO{

	protected RouteRequestPostgreSqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final RouteRequestDTO routeRequest) {
		final var sql = "INSERT INTO ROUTEREQUEST(id,routeOrigin,routeDestination,condirmedRoute,date,idUser,requestHour)"
				+ "VALUES(?,?,?,?,?,?,?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, routeRequest.getIdAsString());
			preparedStatement.setString(2, routeRequest.getRouterequesOrigin());
			preparedStatement.setString(3, routeRequest.getRouterequesEnd());
			preparedStatement.setString(4, routeRequest.getStatus());
			preparedStatement.setDate(5, routeRequest.getServiceRequestDate());
			preparedStatement.setString(6, routeRequest.getCustomer().getIdAsString());
			preparedStatement.setTime(7, routeRequest.getServiceRequestTime());
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			String message = Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_CREATE_ROUTE_REQUEST.concat(routeRequest.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ROUTE_REQUEST, exception);
		}
	}

	@Override
	public List<RouteRequestDTO> find(final RouteRequestDTO routeRequest) {
		
		var sqlBuilder = new StringBuilder();
		final var parameters = new ArrayList<Object>();
		
		creatSelectFrom(sqlBuilder);
		creatWhere(sqlBuilder, routeRequest, parameters);
		createOrderBy(sqlBuilder);
		
		return prepareAndExecuteQuery(sqlBuilder, parameters);
	}
	
	private final List<RouteRequestDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
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
		sqlBuilder.append("SELECT     RR.Id AS IdRouteRequest");
		sqlBuilder.append("           RR.RequestHour AS Hour");
		sqlBuilder.append("           RR.RequestDate AS Date");
		sqlBuilder.append("           RR.Customer AS NameCustomer");
		sqlBuilder.append("           RR.BeginRequest AS BeginRoute");
		sqlBuilder.append("           RR.EndRequest AS EndRoute");
		sqlBuilder.append("           RR.Status AS Status");
		sqlBuilder.append("           Us.dni AS Numberdni");
		sqlBuilder.append("           Us.firstName AS firstNameCustomer");
		sqlBuilder.append("           Us.secondName AS secondNameCustomer");
		sqlBuilder.append("           Us.firstSurname AS firstSurnameCustomer");
		sqlBuilder.append("           Us.secondSurname AS secondSurnameCustomer");
		sqlBuilder.append("           Us.password AS passwordCustomer");
		sqlBuilder.append("           Us.phone AS phoneCustomer");
		sqlBuilder.append("           Us.companyEmail AS companyEmailCustomer");
		sqlBuilder.append("           St.status AS statusRequest");
		sqlBuilder.append("           St.valueDefault AS Messages");
		sqlBuilder.append("FROM       RouteRequest RR");
		sqlBuilder.append("INNER JOIN User Us");
		sqlBuilder.append("ON         Us.Id = RR.IdCustomer");
		sqlBuilder.append("INNER JOIN Status ST");
		sqlBuilder.append("ON         ST.id = RR.IdStatus");
	}

	private final void creatWhere(final StringBuilder sqlBuilder, final RouteRequestDTO routeRequest, final List<Object> parameters) {
		if(!ObjectHelper.isNull(routeRequest)) {
			var setWhere = true;
					
			if (!UUIDHelper.isDefaultUUID(routeRequest.getId())) {
				sqlBuilder.append("WHERE RR.id = ? ");
				setWhere = false;
				parameters.add(routeRequest.getIdAsString());
			}
			
			if (!UUIDHelper.isDefaultUUID(routeRequest.getCustomer().getId())) {
				sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("RR.IdCustomer = ? ");
				parameters.add(routeRequest.getCustomer().getIdAsString());
			}
			
			if (!StringHelper.isEmpty(routeRequest.getStatus())) {
				sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("RR.IdStatus = ? ");
				parameters.add(routeRequest.getStatus());
			}
		}
	}
	
	private void  createOrderBy(final StringBuilder sqlBuilder) {
		sqlBuilder.append("ORDER BY   Us.dni ASC,");
		sqlBuilder.append("           ST.status ASC");	
	}
	
	private final List<RouteRequestDTO> executeQuery (PreparedStatement preparedStatement){
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
	
	private final List<RouteRequestDTO> fillResults(final ResultSet resultSet){
		
		try {
			var results = new ArrayList<RouteRequestDTO>();
			
			while(resultSet.next()) {
				results.add(fillRouteRequestDTO(resultSet));
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
	
	private final RouteRequestDTO fillRouteRequestDTO(final ResultSet resultSet) {
		try {
			
			return RouteRequestDTO.create(resultSet.getString("id"), resultSet.getTime("Time"),
					resultSet.getDate("Date"),fillCustomer(resultSet), resultSet.getString("status"),
					resultSet.getString("BeginRoute"), resultSet.getString("EndRoute"));
		
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_FILL_ROUTE_DTO, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_ROUTE_DTO, exception);
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
	public final void update(final RouteRequestDTO routeRequest) {
		final var sql = "UPDATE ROUTEREQUEST SET RequesHour = ?, RequestDate = ?, Customer = ?,"
				+ "idCustomer = ?, BeginRequest = ?, EndRequest = ? WHERE idRequets = ?";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setTime(1, routeRequest.getServiceRequestTime());
			preparedStatement.setDate(2, routeRequest.getServiceRequestDate());
			preparedStatement.setString(3, routeRequest.getCustomer().getIdAsString());
			preparedStatement.setString(4, routeRequest.getRouterequesOrigin());
			preparedStatement.setString(5, routeRequest.getRouterequesEnd());
			preparedStatement.setString(6, routeRequest.getIdAsString());
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			String message = Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_UPDATE_ROUTE_REQUEST.concat(routeRequest.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
		}
	}

	@Override
	public final void delete(final UUID id) {
		final var sql = "DELETE FROM ROUTEREQUEST WHERE idRequets = ?";
		final var idAsString = getUUIDAsString(id);
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			String message = Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_UPDATE_ROUTE_REQUEST;
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
		}
	}

	
	
}
