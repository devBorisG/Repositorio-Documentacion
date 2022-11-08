package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.BooleanHelper;
import edu.uco.carpooling.crosscutting.helper.ObjectHelper;
import edu.uco.carpooling.crosscutting.helper.StringHelper;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.RouteStatusDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.RouteStatusDTO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class RouteStatusPosgreAqlDAO extends DAORelational implements RouteStatusDAO{

	protected RouteStatusPosgreAqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final RouteStatusDTO status) {
		final var sql = "INSERT INTO STATUS (id,status,messages) VALUES (?,?,?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, status.getIdAsString());
			preparedStatement.setBoolean(2, status.isStatus());
			preparedStatement.setString(3, status.getValueDefault());
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			String message = Messages.RouteStatusPosgreAqlDAO.TECHNICAL_PROBLEM_CREATE_ROUTE_STATUS.concat(status.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteStatusPosgreAqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ROUTE_STATUS, exception);
		}
	}

	@Override
	public List<RouteStatusDTO> find(RouteStatusDTO status) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private final void createSelectFrom(final StringBuilder sqlBuilder) {
		sqlBuilder.append("SELECT      St.id AS IdStatus,");
		sqlBuilder.append("            St.Status,");
		sqlBuilder.append("            St.Messages,");
		sqlBuilder.append("FROM        Status ST,");
	}
	
	/*private final void createWhere(final StringBuilder sqlBuilder,final RouteStatusDTO status, final List<Object> parameters) {
		if(!ObjectHelper.isNull(status)) {
			var setWhere = true;
					
			if (!UUIDHelper.isDefaultUUID(status.getId())) {
				sqlBuilder.append("WHERE St.id = ? ");
				setWhere = false;
				parameters.add(status.getIdAsString());
			}
			
			if (!BooleanHelper.getDefaultBoolean(status.isStatus())) {
				sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("Bu.idYear = ? ");
				parameters.add(status.isStatus());
			}
			
			if (StringHelper.applyTrim(status.getValueDefault())) {
				sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("Bu.idPerson = ? ");
				parameters.add(status);
			}
		}
	}*/

	@Override
	public final void update(final RouteStatusDTO status) {
		final var sql = "UPDATE Status SET status = ?, messages = ? WHERE id = ?";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, status.getIdAsString());
			preparedStatement.setBoolean(2, status.isStatus());
			preparedStatement.setString(3, status.getIdAsString());
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			String message = Messages.RouteStatusPosgreAqlDAO.TECHNICAL_PROBLEM_UPDATE_ROUTE_STATUS.concat(status.getIdAsString());
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteStatusPosgreAqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
		}
	}

	@Override
	public void delete(UUID id) {
		final var sql = "DELETE FROM Status WHERE id = ?";
		final var idAsString = getUUIDAsString(id);
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			String message = Messages.RouteStatusPosgreAqlDAO.TECHNICAL_PROBLEM_DELETE_ROUTE_STATUS;
			throw DataCarpoolingException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.RouteStatusPosgreAqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY, exception);
		}
	}
}
