package edu.uco.budget.data.dao.relational.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDAsString;
import edu.uco.budget.crosscutting.exception.DataBudgetException;
import edu.uco.budget.crosscutting.helper.ObjectHelper;
import edu.uco.budget.crosscutting.helper.UUIDHelper;
import edu.uco.budget.crosscutting.messages.Messages;
import edu.uco.budget.data.dao.BudgetDAO;
import edu.uco.budget.data.dao.relational.DAORelational;
import edu.uco.budget.domain.BudgetDTO;

public final class BudgetSqlServerDAO extends DAORelational implements BudgetDAO {

	public BudgetSqlServerDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final BudgetDTO budget) {
		final var sql = "INSERT INTO Budget(id, idYear, idPerson) VALUES (?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {

			preparedStatement.setString(1, budget.getIdAsString());
			preparedStatement.setString(2, budget.getYear().getIdAsString());
			preparedStatement.setString(3, budget.getPerson().getIdAsString());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			final String message = Messages.BudgetSqlServerDAO.TECHNICAL_PROBLEM_CREATE_BUDGET
					.concat(budget.getIdAsString());
			throw DataBudgetException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_BUDGET, exception);
		}
	}

	@Override
	public final List<BudgetDTO> find(final BudgetDTO budget) {
		
		var results = new ArrayList<BudgetDTO>();
		var setWhere = true;
		var parameters = new ArrayList<Object>();
		final var sqlBuilder = new StringBuilder();
		
		sqlBuilder.append("SELECT Bu.idYear AS IdYear, ");
		sqlBuilder.append("Ye.year AS NumberYear, ");
		sqlBuilder.append("Bu.idPerson AS IdPerson ");
		sqlBuilder.append("Pe.idCard AS IdCardPerson, ");
		sqlBuilder.append("Pe.firstName AS FirstNamePerson, ");
		sqlBuilder.append("Pe.secondName AS SecondNamePerson, ");
		sqlBuilder.append("Pe.firstSurname AS FirstSurnamePerson, ");
		sqlBuilder.append("Pe.secondSurname AS SecondSurnamePerson ");
		sqlBuilder.append("FROM Budget Bu ");
		sqlBuilder.append("INNER JOIN Year Ye ");
		sqlBuilder.append("ON Bu.idYear = Ye.id ");
		sqlBuilder.append("INNER JOIN Person Pe ");
		sqlBuilder.append("ON Bu.idPerson = Pe.id ");
		
		if(!ObjectHelper.isNull(budget)) {
			
			if(!UUIDHelper.isDefaultUUID(budget.getId())) {
				sqlBuilder.append("WHERE Bu.id = ? ");
				setWhere = false;
				parameters.add(budget.getIdAsString());
			}
			
			if(!UUIDHelper.isDefaultUUID(budget.getYear().getId())) {
				sqlBuilder.append(setWhere ? "WHERE ":"AND ").append("Bu.idYear = ? ");
				setWhere = false;
				parameters.add(budget.getYear().getIdAsString());
			}
			
			if(!UUIDHelper.isDefaultUUID(budget.getPerson().getId())) {
				sqlBuilder.append(setWhere ? "WHERE ":"AND ").append("Bu.idPerson = ? ");
				parameters.add(budget.getPerson().getIdAsString());
			}
			
		}
		
		sqlBuilder.append("ORDER BY Pe.idCard ASC, ");
		sqlBuilder.append("Ye.year ASC ");
		
		try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){
			
			for(int index = 0; index < parameters.size(); index++) {
				preparedStatement.setObject(index+1, parameters.get(index));
			}
			
			try (final var resulSet = preparedStatement.executeQuery()) {
				//TODO Fill the list with the results!!!
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (SQLException exception) {
			// TODO: handle exception
		} catch (Exception excetpion) {
			// TODO: handle exception
		}
		
		return results;
	}

	@Override
	public final void update(final BudgetDTO budget) {
		final var sql = "UPDATE Budget SET idYear = ?, idPerson = ? WHERE id = ?";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {

			preparedStatement.setString(1, budget.getYear().getIdAsString());
			preparedStatement.setString(2, budget.getPerson().getIdAsString());
			preparedStatement.setString(3, budget.getIdAsString());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			final String message = Messages.BudgetSqlServerDAO.TECHNICAL_PROBLEM_UPDATE_BUDGET
					.concat(budget.getIdAsString());
			throw DataBudgetException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_BUDGET, exception);
		}
	}

	@Override
	public final void delete(final UUID id) {
		final var sql = "DELETE FROM Budget WHERE id = ?";
		final var idAsString = getUUIDAsString(id);
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)){
			
			preparedStatement.setString(1, idAsString);
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			final String message = Messages.BudgetSqlServerDAO.TECHNICAL_PROBLEM_DELETE_BUDGET.concat(idAsString);
			throw DataBudgetException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_BUDGET, exception);
		}
	}

}
