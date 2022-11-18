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

	public RouteRequestPostgreSqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final RouteRequestDTO routeRequest) {
		final String sql = "INSERT INTO routerequest(id,routeorigin,routedestination,confirmedroute,date,hour,iduser) VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, routeRequest.getIdAsString());
			preparedStatement.setString(2, routeRequest.getRouteRequestOrigin());
			preparedStatement.setString(3, routeRequest.getRouteRequestEnd());
			preparedStatement.setBoolean(4, false);
			preparedStatement.setDate(5, routeRequest.getServiceRequestDate());
			preparedStatement.setTime(6, routeRequest.getServiceRequestTime());
			preparedStatement.setString(7, routeRequest.getCustomer().getIdAsString());
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			String message = Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_CREATE_ROUTE_REQUEST.concat(routeRequest.getIdAsString()).concat(exception.getMessage());
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
		
		return prepareAndExecuteQuery(sqlBuilder, parameters);
	}
	
	private final List<RouteRequestDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
		try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){
			
			setParameterValues(preparedStatement, parameters);
			
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
		sqlBuilder.append("SELECT     RR.id AS id,");
		sqlBuilder.append(" RR.hour AS hour,");
		sqlBuilder.append(" RR.date AS date,");
		sqlBuilder.append(" RR.iduser AS iduser,");
		sqlBuilder.append(" RR.routeorigin AS routeorigin,");
		sqlBuilder.append(" RR.routedestination AS routedestination,");
		sqlBuilder.append(" RR.confirmedroute AS confirmedroute,");
		sqlBuilder.append(" Us.dni AS dni,");
		sqlBuilder.append(" Us.firstname AS firstname,");
		sqlBuilder.append(" Us.secondname AS secondname,");
		sqlBuilder.append(" Us.firstsurname AS firstsurname,");
		sqlBuilder.append(" Us.secondsurname AS secondsurname,");
		sqlBuilder.append(" Us.password AS password,");
		sqlBuilder.append(" Us.phone AS phone,");
		sqlBuilder.append(" Us.companyemail AS email");
		sqlBuilder.append(" FROM       public.routerequest RR");
		sqlBuilder.append(" INNER JOIN public.user Us");
		sqlBuilder.append(" ON Us.id = RR.iduser");
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
				sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("RR.iduser = ? ");
				parameters.add(routeRequest.getCustomer().getIdAsString());
			}
			
			if (!StringHelper.isEmpty(routeRequest.getStatus())) {
				sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("RR.confirmedroute = ? ");
				parameters.add(routeRequest.getStatus());
			}
		}
	}
	
	private final List<RouteRequestDTO> executeQuery (PreparedStatement preparedStatement){
		try (final var resultset = preparedStatement.executeQuery()){
			
			return fillResults(resultset);
			
		} catch (DataCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY
					.concat(". More info: ").concat(exception.getMessage()), exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY
					.concat(". More info: ").concat(exception.getMessage()), exception);
		}
	}
	
	private void setParameterValues (PreparedStatement preparedStatement, final List<Object> parameters) {
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
			
			return RouteRequestDTO.create(resultSet.getString("id"), resultSet.getTime("hour"),
					resultSet.getDate("date"),fillCustomer(resultSet), resultSet.getString("confirmedroute"),
					resultSet.getString("routeorigin"), resultSet.getString("routedestination"));
		
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_FILL_ROUTE_DTO, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_ROUTE_DTO, exception);
		}
	}
	
	private final CustomerDTO fillCustomer(final ResultSet resultSet) {
		try {
			
				return CustomerDTO.create(resultSet.getString("iduser"), resultSet.getString("dni"), 
						resultSet.getString("firstname"), resultSet.getString("secondname"), 
						resultSet.getString("firstsurname"), resultSet.getString("secondsurname"), 
						resultSet.getString("password"),resultSet.getInt("phone"), 
						resultSet.getString("email"));
						
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
			preparedStatement.setString(4, routeRequest.getRouteRequestOrigin());
			preparedStatement.setString(5, routeRequest.getRouteRequestEnd());
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
