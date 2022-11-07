package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.RouteDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.RouteDTO;
import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.ObjectHelper;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;


public class RoutePostrgeSqlDAO extends DAORelational implements RouteDAO{
	
	

	protected RoutePostrgeSqlDAO(final Connection connection) {
		
		super(connection);
	}

	@Override
	public final void create(final RouteDTO route) {
		final var sql = "INSESRT INTO ROUTE(id,quota,driverPerVehicle,detailRoute,"
				+ "pointInterest,routeStatus VALUES(?,?,?,?,?,?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, route.getIdAsString());
			preparedStatement.setString(2, route.getQuotaAsStrin());
			preparedStatement.setString(2, route.getDriverPerVehicleDTO().getIdAsString());
			preparedStatement.setString(3, route.getDetailRouteDTO().getIdAsString());
			preparedStatement.setString(4, route.getPointInterest().getIdAsString());
			preparedStatement.setString(5, route.getRouteStatus().getIdAsString());
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			String message = Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_CREATE_ROUTE.concat(route.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ROUTE, exception);
		}
	}

	@Override
	public List<RouteDTO> find(RouteDTO route) {
		
		var sqlBuilder = new StringBuilder();
		final var parameters = new ArrayList<Object>();
		
		createSelectFrom(sqlBuilder);
		createWhere(sqlBuilder, route, parameters);
		createOrderBy(sqlBuilder);
		
		return null;
	}
	
	private final void createSelectFrom(final StringBuilder sqlbuilder) {
		sqlbuilder.append("SELECT      Ro.Id AS IdRoute,");
		sqlbuilder.append("            Ro.Quotas AS quotas");
		sqlbuilder.append("            Ro.DriverPerVehicle AS IdDriverVehicle");
		sqlbuilder.append("            Ro.DetailRoute AS IdDetailRoute");
		sqlbuilder.append("            Ro.PointInterest AS IdPointInterest");
		sqlbuilder.append("            Ro.RouteStatus AS IdRouteStatus");
		sqlbuilder.append("            DV.Id AS IdDriverVehicle");
		sqlbuilder.append("            DV.Driver AS IdDriver");
		sqlbuilder.append("            DV.Vehicle AS IdVehicle");
		sqlbuilder.append("            DT.Id AS IdDetailRoute");
		sqlbuilder.append("            DT.CreationTime AS HourStartRoute");
		sqlbuilder.append("            DT.EndTime AS HourEndtRoute");
		sqlbuilder.append("            DT.Date AS Date");
		sqlbuilder.append("            DT.Route AS IdRoute");
		sqlbuilder.append("            PI.Id AS IDPointInteres");
		sqlbuilder.append("            PI.Points AS IdKeyPoints");
		sqlbuilder.append("            PI.Route AS IdRoute");
		sqlbuilder.append("            PI.KeyPointssCity AS NameCity");
		sqlbuilder.append("            RS.Id AS IdRouteStatus");
		sqlbuilder.append("            RS.TrueS As TrueStatus");
		sqlbuilder.append("            RS.FalseS AS FalseStatus");
		sqlbuilder.append("            RS.Wainting AS WaintingStatus");
		sqlbuilder.append("FROM        Route Ro");
		sqlbuilder.append("INNER JOIN  DriverPerVehicle DV");
		sqlbuilder.append("ON          Ro.IdDriverVehicle = DV.id");
		sqlbuilder.append("INNER JOIN  DetailRoute DT");
		sqlbuilder.append("ON          Ro.IdDetailRoute = DT.Id");
		sqlbuilder.append("INNER JOIN  PointInterest PI");
		sqlbuilder.append("ON          Ro.IdPointInterest = PI.Id");
		sqlbuilder.append("INNER JOIN  RouteStatus RS");
		sqlbuilder.append("ON          Ro.IdRouteStatus = RS.id");
	}

	private final void createWhere(final StringBuilder sqlBuilder, final RouteDTO route,
			final List<Object> parameters) {
		if(!ObjectHelper.isNull(route)) {
				var setWhere = true;
			
			if (!UUIDHelper.isDefaultUUID(route.getId())) {
				sqlBuilder.append("WHERE Ro.id = ?");
					setWhere = false;
					parameters.add(route.getIdAsString());
			}
			
			if (!UUIDHelper.isDefaultUUID(route.getDriverPerVehicleDTO().getId())) {
					sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("Ro.IdDriverVehicle");
					parameters.add(route.getDriverPerVehicleDTO().getIdAsString());
			}
			
			if (!UUIDHelper.isDefaultUUID(route.getDetailRouteDTO().getId())) {
					sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("RO.IdDetailRoute");
					parameters.add(route.getDetailRouteDTO().getIdAsString());
			}
			
			if (!UUIDHelper.isDefaultUUID(route.getPointInterest().getId())) {
					sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("RO.IdPointInterest");
					parameters.add(route.getPointInterest().getIdAsString());
			}
			
			if (!UUIDHelper.isDefaultUUID(route.getRouteStatus().getId())) {
					sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("RO.IdRouteStatus");
					parameters.add(route.getRouteStatus().getIdAsString());
			}
		}
	}
	
	private void createOrderBy(final StringBuilder sqlBuilder) {
		sqlBuilder.append("ORDER BY    DV.IdVehicle");
		sqlBuilder.append("ORDER BY    DT.Date");
		sqlBuilder.append("ORDER BY    PI.NameCiity");
	}
	
	private final List<RouteDTO> executeQuery(PreparedStatement preparedStatement){
		try  (final var resultset = preparedStatement.executeQuery()){
			return fillresults (resultset);
		} catch (DataCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
		}
	}
	
	private void SetParameterValues(PreparedStatement preparedStatement, final List<Object> parameters) {
		try {
			for(int index = 0; index < parameters.size(); index ++) {
				preparedStatement.setObject(index + 1, parameters.get(index));
			}
		} catch (SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
		}
	}
	
	private final List<Object> fillResults(final ResultSet resultSet){
		
		try {
			var results = new ArrayList<RouteDTO>();
			
			while(resultSet.next()) {
				results.add(fillBudgetDTO(resultSet));
			}
		} catch (final DataCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_FILL_RESULTS, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
		}
	}
	
	@Override
	public final void update(final RouteDTO route) {
		final var sql = "UPDATE ROUTE SET idDriverPerVehicle = ?, idDetailRoute = ?,"
				+ "idPointInterest = ?,idRouteStatus = ? WHERE = id = ?";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, route.getDriverPerVehicleDTO().getIdAsString());
			preparedStatement.setString(2, route.getDetailRouteDTO().getIdAsString());
			preparedStatement.setString(3, route.getPointInterest().getIdAsString());
			preparedStatement.setString(4, route.getRouteStatus().getIdAsString());
			preparedStatement.setString(5, route.getIdAsString());
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			String message = Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_UPDATE_ROUTE.concat(route.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
		}
	}

	@Override
	public final void delete(final UUID id) {
		final var sql = "DELETE FROM ROUTE WHERE id = ?";
		final var idAsString = getUUIDAsString(id);
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			String message = Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_DELETE_ROUTE;
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
		}
	}

}
