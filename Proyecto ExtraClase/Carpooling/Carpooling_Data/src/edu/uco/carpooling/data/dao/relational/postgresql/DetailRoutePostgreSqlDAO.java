package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.DateHelper;
import edu.uco.carpooling.crosscutting.helper.ObjectHelper;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.DetailRouteDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.RouteDetailDTO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class DetailRoutePostgreSqlDAO extends DAORelational implements DetailRouteDAO{

	protected DetailRoutePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final RouteDetailDTO routeDetail) {
		final var sql = "INSERT INTO RouteDetail(id,CreationTime,EndCreation,Date) VALUES (?,?,?,?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, routeDetail.getIdAsString());
			preparedStatement.setTime(2, routeDetail.getCreationTime());
			preparedStatement.setTime(3, routeDetail.getCreationTime());
			preparedStatement.setDate(4, routeDetail.getDate());
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			String message = Messages.DetailRoutePostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DETAIL_ROUTE.concat(routeDetail.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DETAIL_ROUTE, exception);
		}
	}

	@Override
	public List<RouteDetailDTO> find(RouteDetailDTO routeDetail) {
		var sqlBuilder = new StringBuilder();
		final var parameters = new ArrayList<Object>();
		
		createSelectFrom(sqlBuilder);
		createWhere(sqlBuilder, routeDetail, parameters);
		createOrderBy(sqlBuilder);
		
		return prepareAndExecuteQuery(sqlBuilder, parameters);
	}
	
	private final List<RouteDetailDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {
		try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){
			
			setParameterValues(preparedStatement, parameters);
			
			return executeQuery(preparedStatement);
			
		} catch (final DataCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_PROBLEM_PREPARED_STAMENT, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
		}
	}
	
	private final void createSelectFrom(final StringBuilder sqlBuilder) {
		sqlBuilder.append("SELECT      DR.Id AS IdDetailRoute,");
		sqlBuilder.append("            DR.startHour AS Begin,");
		sqlBuilder.append("            DR.endingHour AS End");
		sqlBuilder.append("            DR.Date AS Date,");
		sqlBuilder.append("FROM        DetailRoute DR");
	}
	
	private final void createWhere(final StringBuilder sqlBuilder, final RouteDetailDTO routeDetail, final List<Object> parameters) {
		if(!ObjectHelper.isNull(routeDetail)) {
			var setWhere = true;
					
			if (!UUIDHelper.isDefaultUUID(routeDetail.getId())) {
				sqlBuilder.append("WHERE DR.Id = ? ");
				setWhere = false;
				parameters.add(routeDetail.getIdAsString());
			}
			
			if (!DateHelper.isDefaultDate(routeDetail.getDate())) {
				sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("DR.Date= ? ");
				parameters.add(routeDetail.getDate());
			}
		}
	}
	
	private void createOrderBy(final StringBuilder sqlBuilder) {
		sqlBuilder.append("ORDER BY   DR.Date ASC,");	
	}	
	
	private final List<RouteDetailDTO> executeQuery(PreparedStatement preparedStatement){
		try (final var resultset = preparedStatement.executeQuery()){
			return fillResults(resultset);	
		}catch (final DataCarpoolingException exception) {
			throw exception;
		}catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
		}catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
		}
	}
	
	private void setParameterValues(PreparedStatement preparedStatement, final List<Object> parameters) {
		try {
			for(int index = 0; index < parameters.size(); index++) {
				preparedStatement.setObject(index + 1, parameters.get(index));
			}
		} catch (SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
		}catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
		}
	}
	
private final List<RouteDetailDTO> fillResults(final ResultSet resultSet){
		
		try {
			var results = new ArrayList<RouteDetailDTO>();
			
			while(resultSet.next()) {
				results.add(fillBudgetDTO(resultSet));
			}
			
			return results;
		} catch (final DataCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_PROBLEM_FILL_RESULTS, exception);
		}
		catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
		}
	}

	private final RouteDetailDTO fillBudgetDTO(final ResultSet resultset) {
		try {
			
			return  RouteDetailDTO.create(resultset.getString("idDetailroute"), resultset.getTime("Begin"), resultset.getTime("End"),
					resultset.getDate("Date"));
			
		} catch (final DataCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_PROBLEM_FILL_ROUTE_DETAIL_ROUTE_DTO, exception);
		}catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_DETAIL_ROUTE_DTO, exception);
		}	
	}

	@Override
	public void update(RouteDetailDTO routeDetail) {
		final var sql = "UPDATE BUDGET SET idYear = ?, idPerson = ? WHERE id = ?";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setTime(1, routeDetail.getCreationTime());
			preparedStatement.setTime(2, routeDetail.getEndTime());
			preparedStatement.setDate(3, routeDetail.getDate());
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			String message = Messages.DetailRoutePostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_DETAIL_ROUTE.concat(routeDetail.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		}catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
		}
	}

	@Override
	public void delete(UUID id) {
		final var sql = "DELETE FROM DETAILROUTE WHERE id = ?";
		final var idAsString = getUUIDAsString(id);
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			String message = Messages.DetailRoutePostgreSqlDAO.TECHNICAL_PROBLEM_DELETE_DETAIL_ROUTE;
			throw DataCarpoolingException.createTechnicalException(message, exception);
		}catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.DetailRoutePostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
		}
	} 
}
