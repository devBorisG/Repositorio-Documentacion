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
import edu.uco.carpooling.data.dao.QualificationDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.domain.QualificationDTO;

public class QualificationPostgresSqlDAO extends DAORelational implements QualificationDAO {


    protected QualificationPostgresSqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(QualificationDTO qualification) {
        final var sql = "INSERT INTO SCORE(idQualification,score,comment,user)"
                + "VALUES(?,?,?,?)";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, qualification.getIdAsString());
            preparedStatement.setDouble(2, qualification.getScore());
            preparedStatement.setString(3, qualification.getComment());
//           REVISAR
            preparedStatement.setString(4, qualification.getUser().getIdAsString());

            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            String message = Messages.RouteRequestSqlServerDAO.TECHNICAL_PROBLEM_CREATE_ROUTE_REQUEST.concat(qualification.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ROUTE_REQUEST, exception);
        }

    }

    @Override
    public List<QualificationDTO> find(QualificationDTO qualification) {
        var sqlBuilder = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        creatSelectFrom(sqlBuilder);
        creatWhere(sqlBuilder, qualification, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);
    }

    private final List<QualificationDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){

            SetParameterValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (final DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_PROBLEM_PREPARED_STAMENT, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
        }
    }

    private final void creatSelectFrom(final StringBuilder sqlBuilder) {
        sqlBuilder.append("SELECT     SC.Id AS IdQualification");
        sqlBuilder.append("           SC.Score AS Puntaje");
        sqlBuilder.append("           SC.Description AS comment");
        sqlBuilder.append("           SC.User AS user");



        sqlBuilder.append("FROM       Score SC");
        sqlBuilder.append("INNER JOIN User Us");
        sqlBuilder.append("ON         Us.Id = SC.IdUser");

    }

    private final void creatWhere(final StringBuilder sqlBuilder, final QualificationDTO qualification, final List<Object> parameters) {
        if(!ObjectHelper.isNull(qualification)) {
            var setWhere = true;

            if (!UUIDHelper.isDefaultUUID(qualification.getId())) {
                sqlBuilder.append("WHERE SC.id = ? ");
                setWhere = false;
                parameters.add(qualification.getIdAsString());
            }

            if (!UUIDHelper.isDefaultUUID(qualification.getUser().getId())) {
                sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("Us.IdUser = ? ");
                parameters.add(qualification.setUser().getIdAsString());
            }

        }
    }

    private void  createOrderBy(final StringBuilder sqlBuilder) {
        sqlBuilder.append("ORDER BY   SC.id ASC,");
        sqlBuilder.append("           US.Id ASC");
    }

    private final List<QualificationDTO> executeQuery (PreparedStatement preparedStatement){
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

    private final List<QualificationDTO> fillResults(final ResultSet resultSet){

        try {
            var results = new ArrayList<QualificationDTO>();

            while(resultSet.next()) {
                results.add(fillRouteRequestDTO(resultSet));
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

    private final QualificationDTO fillRouteRequestDTO(final ResultSet resultSet) {
        try {

            return QualificationDTO.create(resultSet.getString("idRequest"),
                    resultSet.getDouble("score"),
                    resultSet.getString("comment"),
                    fillUser(resultSet));

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
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER_DTO, exception);
        }
    }







    @Override
    public void update(QualificationDTO qualification) {
        final var sql = "UPDATE INTO SCORE(id, score, comment, idUser) VALUES (?,?,?,?) ";

        try (final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, qualification.getIdAsString());
            preparedStatement.setDouble(2, qualification.getScore());
            preparedStatement.setString(3, qualification.getComment());
//            get id as strign revisar
            preparedStatement.setString(4, qualification.getUser().getIdAsString());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            String message = Messages.RouteRequestSqlServerDAO.TECHNICAL_PROBLEM_UPDATE_ROUTE_REQUEST.concat(qualification.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }

    }

    @Override
    public void delete(UUID id) throws SQLException {
        final var sql = "DELETE FROM SCORE WHERE idQualification = ?";
        final var idAsString = getUUIDAsString(id);

        try (final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, idAsString);

            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            String message = Messages.RouteRequestSqlServerDAO.TECHNICAL_PROBLEM_UPDATE_ROUTE_REQUEST;
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }
    }
}
