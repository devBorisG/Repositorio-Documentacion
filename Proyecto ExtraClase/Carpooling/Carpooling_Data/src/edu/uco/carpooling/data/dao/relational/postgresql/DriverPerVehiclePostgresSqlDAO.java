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
import edu.uco.carpooling.data.dao.DriverPerVehicleDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.AuthorizedCategoryDTO;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.DriverPerVehicleDTO;
import edu.uco.carpooling.domain.VehicleDTO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class DriverPerVehiclePostgresSqlDAO extends DAORelational implements DriverPerVehicleDAO {


    protected DriverPerVehiclePostgresSqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(DriverPerVehicleDTO driverPerVehicle) {
        final var sql = "INSERT INTO DRIVERVEHICLE(idDriverVehicle,Driver,Vehicle) + VALUES(?,?,?)";
        try(final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,driverPerVehicle.getIdAsString());
            preparedStatement.setString(2,driverPerVehicle.getDriver().getIdAsString());
            preparedStatement.setString(3,driverPerVehicle.getVehicle().getIdAsString());

        } catch (final SQLException exception) {
            String message = Messages.AuthotizedCategoryPostgresSqlDAOPostgresSqlDAO.TECHNICAL_PROBLEM_CREATE_AUTHORIZED_CATEGORY.concat(driverPerVehicle.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.AuthotizedCategoryPostgresSqlDAOPostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }

    }

    @Override
    public List<DriverPerVehicleDTO> find(DriverPerVehicleDTO driverPerVehicle) {

        var sqlBuilder = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        creatSelectFrom(sqlBuilder);
        creatWhere(sqlBuilder, driverPerVehicle, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);

    }

    private final void creatSelectFrom(final StringBuilder sqlBuilder) {

        sqlBuilder.append("SELECT     DPR.Id AS IdDriverPerVehicle");
        sqlBuilder.append("           Dr.Id AS IdDriver ");
        sqlBuilder.append("           Vh.Id AS IdVehicle ");



        sqlBuilder.append("FROM       DriverPerVehicle DPR");
        sqlBuilder.append("INNER JOIN Driver Dr");
        sqlBuilder.append("ON         Dr.Id = DPR.IdDriver");
        sqlBuilder.append("INNER JOIN Vehicle Vh");
        sqlBuilder.append("ON         Vh.Id = DPR.IdVehicle");
    }

    private final void creatWhere(final StringBuilder sqlBuilder, final DriverPerVehicleDTO driverPerVehicle, final List<Object> parameters) {
        if(!ObjectHelper.isNull(driverPerVehicle)) {
            var setWhere = true;

            if (!UUIDHelper.isDefaultUUID(driverPerVehicle.getId())) {
                sqlBuilder.append("WHERE DPR.id = ? ");
                setWhere = false;
                parameters.add(driverPerVehicle.getIdAsString());
            }

            if (!UUIDHelper.isDefaultUUID(driverPerVehicle.getDriver().getId())) {
                sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("DPR.idDriver = ? ");
                parameters.add(driverPerVehicle.getDriver().getIdAsString());
            }

            if (!UUIDHelper.isDefaultUUID(driverPerVehicle.getVehicle().getId())) {
                sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("DPR.idVehicle = ? ");
                parameters.add(driverPerVehicle.getVehicle().getIdAsString());
            }
        }
    }


    private void  createOrderBy(final StringBuilder sqlBuilder) {
        sqlBuilder.append("ORDER BY   Dr.id ASC,");
        sqlBuilder.append("           Vh.id ASC");
    }

    private final List<DriverPerVehicleDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
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
    private final List<DriverPerVehicleDTO> executeQuery (PreparedStatement preparedStatement){
        try (final var resultset = preparedStatement.executeQuery()){

            return fillResults(resultset);

        } catch (DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }
    }
    private void SetParameterValues (PreparedStatement preparedStatement, final List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index ++) {
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch (SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY, exception);
        }
    }

    private final List<DriverPerVehicleDTO> fillResults(final ResultSet resultSet){

        try {
            var results = new ArrayList<DriverPerVehicleDTO>();

            while(resultSet.next()) {
                results.add(fillDriverPerVehicleDTO(resultSet));
            }
            return results;
        } catch (final DataCarpoolingException exception) {
            throw exception;
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_FILL_RESULTS, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
        }
    }

    private final DriverPerVehicleDTO fillDriverPerVehicleDTO(final ResultSet resultSet) {
        try {
            return DriverPerVehicleDTO.create(resultSet.getString("IdDriverPerVehicle"),
                    fillDriverDTO(resultSet),
                    fillVehicleDTO(resultSet));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.AuthotizedCategoryPostgresSqlDAOPostgresSqlDAO.TECHNICAL_PROBLEM_FILL_AUTHORIZED_CATEGORY_AUTHORIZED_CATEGORY_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.AuthotizedCategoryPostgresSqlDAOPostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_AUTHORIZED_CATEGORY_DTO, exception);
        }
    }

    private final VehicleDTO fillVehicleDTO(final ResultSet resultSet) {
        try {
            return VehicleDTO.create(resultSet.getString("IdVehicle"),
                    resultSet.getString("registration"),
                    resultSet.getInt("model"),
                    resultSet.getString("brand"),
                    resultSet.getString("lineup"),
                    resultSet.getString("plate"),
                    resultSet.getInt("capacity"));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO, exception);
        }
    }

    private final DriverDTO fillDriverDTO(final ResultSet resultSet) {
        try {
            return DriverDTO.create(resultSet.getString("idDirver"),
                    resultSet.getString("dni"),
                    resultSet.getString("firstName"),
                    resultSet.getString("secondName"),
                    resultSet.getString("firstSurname"),
                    resultSet.getString("secondSurname"),
                    resultSet.getString("password"),
                    resultSet.getInt("Phone"),
                    resultSet.getString("companyEmail"),
                    resultSet.getString("referencePoint"),
                    resultSet.getString(""),  //Revisar cual colimna
                    fillAuthorizedCategoryDTO(resultSet));

        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO, exception);
        }
    }

    private final AuthorizedCategoryDTO fillAuthorizedCategoryDTO(final ResultSet resultSet) {
        try {
            return AuthorizedCategoryDTO.create(resultSet.getString("idCategory"),
                    resultSet.getString("category"),
                    resultSet.getDate("date"));
        } catch (final SQLException exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS, exception);
        }
    }




    @Override
    public void update(DriverPerVehicleDTO driverPerVehicle) {
        final var sql = "UPDATE INTO DRIVERVEHICLE(idDriverVehicle,Driver,Vehicle) + VALUES(?,?,?)";

        try (final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, driverPerVehicle.getIdAsString());
            preparedStatement.setString(2, driverPerVehicle.getDriver().getIdAsString());
            preparedStatement.setString(3, driverPerVehicle.getVehicle().getIdAsString());



            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            String message = Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_PROBLEM_UPDATE_DRIVER_PER_VEHICLE.concat(driverPerVehicle.getIdAsString());
            throw DataCarpoolingException.createTechnicalException(message, exception);
        } catch (final Exception exception) {
            throw DataCarpoolingException.createTechnicalException(Messages.DriverPerVehiclePostgresSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
        }
    }

    @Override
    public void delete(UUID id) throws SQLException {
        final var sql = "DELETE FROM DRIVERVEHICLE WHERE id = ?";
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
