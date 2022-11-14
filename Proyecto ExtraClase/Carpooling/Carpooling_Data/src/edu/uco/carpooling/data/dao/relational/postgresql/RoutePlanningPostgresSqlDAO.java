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
import edu.uco.carpooling.data.dao.RoutePlanningDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.RoutePlanningDTO;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;


public class RoutePlanningPostgresSqlDAO extends DAORelational implements RoutePlanningDAO {

	protected RoutePlanningPostgresSqlDAO(Connection connection) {
		super(connection);
		}

	@Override
	public void create(RoutePlanningDTO routePlanning) {
		final var sql = "INSERT INTO ROUTEPLANNING(id,detailPointOfInteresRoute,startPoint,endPoint)+ VALUES(?,?,?,?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, routePlanning.getIdAsString());
			preparedStatement.setObject(2, routePlanning.getDetailPointOfInteresRoute());
			preparedStatement.setString(3, routePlanning.getStartPoint());
			preparedStatement.setString(4, routePlanning.getEndPoint());

			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			String message = Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_CREATE_ROUTE.concat(routePlanning.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ROUTE, exception);
		}
	}

	@Override
	public List<RoutePlanningDTO> find(RoutePlanningDTO routePlanning) {
        
		var sqlBuilder = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        creatSelectFrom(sqlBuilder);
        creatWhere(sqlBuilder, routePlanning, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);
	}
	
	

    private final void creatSelectFrom(final StringBuilder sqlBuilder) {

        sqlBuilder.append("SELECT     RP.Id AS IdRoutePlanning");
        sqlBuilder.append("           RP.detailPointOfInteresRoute AS detail ");
        sqlBuilder.append("           RP.startPoint AS startPoint ");
        sqlBuilder.append("           RP.startPoint AS endPoint ");


    }
    
    private final void creatWhere(final StringBuilder sqlBuilder, final RoutePlanningDTO routeplanning, final List<Object> parameters) {
        if(!ObjectHelper.isNull(routeplanning)) {

            if (!UUIDHelper.isDefaultUUID(routeplanning.getId())) {
                sqlBuilder.append("WHERE SC.id = ? ");
                parameters.add(routeplanning.getIdAsString());
            }

        }
    }
    
    private void  createOrderBy(final StringBuilder sqlBuilder) {
        sqlBuilder.append("ORDER BY   RP.id ASC,");
    }
    
    private final List<RoutePlanningDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){

            SetParameterValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (final DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_PREPARED_STAMENT, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
        }
    }

    
    private final List<RoutePlanningDTO> executeQuery (PreparedStatement preparedStatement){
        try (final var resultset = preparedStatement.executeQuery()){

            return fillResults(resultset);

        } catch (DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }
    }
    

    private void SetParameterValues (PreparedStatement preparedStatement, final List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index ++) {
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch (SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
        }
    }
	
    

    private final List<RoutePlanningDTO> fillResults(final ResultSet resultSet){

        try {
            var results = new ArrayList<RoutePlanningDTO>();

            while(resultSet.next()) {
                results.add(fillRoutePlanningDTO(resultSet));
            }
            return results;
        } catch (final DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_PROBLEM_FILL_RESULTS, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
        }
    }
    
    private final RoutePlanningDTO fillRoutePlanningDTO(final ResultSet resultSet) {
        try {
        	ArrayList<String> array = (ArrayList<String>) resultSet.getArray("detail");
        	return RoutePlanningDTO.create(resultSet.getString("IdRoutePlanning"),
        			array, 
					resultSet.getString("startPoing"),
					resultSet.getString("endPoint"));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER_DTO, exception);
        }
    }
	
	

	@Override
	public void update(RoutePlanningDTO routePlanning) {
        final var sql = "UPDATE INTO ROUTEPLANNING(idAuthorizedCategory,Category,Validity) + VALUES(?,?,?)";

        try (final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, routePlanning.getIdAsString());
            preparedStatement.setObject(2, routePlanning.getDetailPointOfInteresRoute());
            preparedStatement.setString(3, routePlanning.getStartPoint());
            preparedStatement.setString(4, routePlanning.getEndPoint());




            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            String message = Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_UPDATE_DRIVER_PER_VEHICLE.concat(routePlanning.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }		
	}

	@Override
	public void delete(UUID id) throws SQLException {
        final var sql = "DELETE FROM ROUTEPLANNING WHERE id = ?";
        final var idAsString = getUUIDAsString(id);

        try (final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, idAsString);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            String message = Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_DELETE_DRIVER_PER_VEHICLE;
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }

    
	}
	
	


}
