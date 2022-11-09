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
import edu.uco.carpooling.data.dao.PointInterestDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.PointInterestDTO;
import edu.uco.carpooling.domain.RouteDTO;

public class PointInterestPostgresSqlDAO extends DAORelational implements PointInterestDAO {


    protected PointInterestPostgresSqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(PointInterestDTO pointInterest) {
        final var sql = "INSESRT INTO POINTINTEREST(id,keyPoint,routeCod,city,"
                + "pointInterest,routeStatus VALUES(?,?,?,?,?,?)";

        try (final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,pointInterest.getIdAsString());
            preparedStatement.setString(1, pointInterest.getKeyPoint());
            preparedStatement.setString(2, pointInterest.getRouteCod());
            preparedStatement.setString(2, pointInterest.getCity());


            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            String message = Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_CREATE_ROUTE.concat(pointInterest.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ROUTE, exception);
        }

    }

    @Override
    public List<PointInterestDTO> find(PointInterestDTO pointInterest) {
        var sqlBuilder = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        createSelectFrom(sqlBuilder);
        createWhere(sqlBuilder, pointInterest, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);
    }

    private final List<PointInterestDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){

            SetParameterValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (final DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_PREPARED_STAMENT, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
        }
    }

    private final void createSelectFrom(final StringBuilder sqlbuilder) {
        sqlbuilder.append("SELECT      PI.Id AS IdPointInterest,");
        sqlbuilder.append("            PI.keyPoint AS PointOfInterest");
//        revisar
        sqlbuilder.append("            RC.id AS CodigoRuta");
        sqlbuilder.append("            PI.city AS city");



        sqlbuilder.append("FROM        PointInterest PI");
        sqlbuilder.append("INNER JOIN  Route Ro");
        sqlbuilder.append("ON          Ro.IdRoute = PI.routeCod");

    }

    private final void createWhere(final StringBuilder sqlBuilder, final PointInterestDTO pointInterest,
                                   final List<Object> parameters) {
        if(!ObjectHelper.isNull(pointInterest)) {
            var setWhere = true;

            if (!UUIDHelper.isDefaultUUID(pointInterest.getId())) {
                sqlBuilder.append("WHERE PI.id = ?");
                setWhere = false;
                parameters.add(pointInterest.getIdAsString());
            }

            if (!UUIDHelper.isDefaultUUID((UUID) pointInterest.getRouteCod())) {
                sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("Ro.routeCod");
                parameters.add(pointInterest.getRouteCod());
            }


        }
    }

    private void createOrderBy(final StringBuilder sqlBuilder) {
        sqlBuilder.append("ORDER BY    PI.IdPointInterest");
        sqlBuilder.append("ORDER BY    Ro.routeCod");

    }

    private final List<PointInterestDTO> executeQuery(PreparedStatement preparedStatement){
        try  (final var resultset = preparedStatement.executeQuery()){
            return fillResults (resultset);
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

    private final List<PointInterestDTO> fillResults(final ResultSet resultSet){

        try {
            var results = new ArrayList<PointInterestDTO>();

            while(resultSet.next()) {
                results.add(fillPointInterestDTO(resultSet));
            }
            return results;
        } catch (final DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_FILL_RESULTS, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
        }
    }

    private final PointInterestDTO fillPointInterestDTO(final ResultSet resultSet) {
        try {
            return PointInterestDTO.create(UUID.fromString(resultSet.getString("IdPointInterest")),
                    resultSet.getString("KeyPoints"),
                    fillRouteDTO(resultSet),
                    resultSet.getString("City"));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_FILL_POINT_INTEREST_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_POINT_INTEREST_DTO, exception);
        }
    }
    private final RouteDTO fillRouteDTO(final ResultSet resultSet) {
        try {
            return RouteDTO.create(resultSet.getString("idRoute"),
                    resultSet.getInt("quotas"),
                    fillRouteStatusDTO(resultSet),
                    fillDriverPerVehicleDTO(resultSet),
                    fillPointInterestDTO(resultSet),
                    fillDetailRouteDTO(resultSet));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_PROBLEM_FILL_ROUTE_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_ROUTE_DTO, exception);
        }
    }


    @Override
    public final void update(final PointInterestDTO pointInterest) {
        final var sql = "UPTADE INTO PointInterest(id,keyPoint ,idRoute,city) VALUES (?,?,?,?)";


        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, pointInterest.getIdAsString());
            preparedStatement.setString(2, pointInterest.getKeyPoint());
//            Revisar
            preparedStatement.setString(3, pointInterest.get().getIdAsString());
            preparedStatement.setString(4, pointInterest.getCity());


            preparedStatement.executeUpdate();

        } catch ( final SQLException exception) {
            final var message = Messages.BudgetSqlServerDAO.TECHNICAL_PROBLEM_UPDATE_BUDGET
                    .concat(pointInterest.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        }catch ( final Exception exception) {
            final var message = Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDAATE_BUDGET
                    .concat(pointInterest.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        }
    }

    @Override
    public void delete(UUID id) throws SQLException {
        final var sql = "DELETE FROM Budget WHERE id = ?";
        final var idAsString = getUUIDAsString(id);

        try (final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,idAsString);
        }

    }
}