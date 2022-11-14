package edu.uco.carpooling.data.dao.relational.postgresql;
	
	import java.sql.Connection;
<<<<<<< HEAD
	import java.sql.SQLException;
	import java.util.List;
	import java.util.UUID;
	
	import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
	import edu.uco.carpooling.crosscutting.messages.Messages;
	import edu.uco.carpooling.data.dao.DriverDAO;
	import edu.uco.carpooling.data.dao.relational.DAORelational;
	import edu.uco.carpooling.domain.DriverDTO;
	import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getRandomUUIDAsString;
	import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;
=======
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
	import edu.uco.carpooling.data.dao.DriverDAO;
	import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.AuthorizedCategoryDTO;
import edu.uco.carpooling.domain.DriverDTO;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;
>>>>>>> dev_federico
	
	public final class DriverPostresSqlDAO extends DAORelational implements DriverDAO {
	
		public DriverPostresSqlDAO(Connection connection) {
			super(connection);
		}
	
		@Override
		public void create(final DriverDTO driver) {
<<<<<<< HEAD
			final var sql = "INSERT INTO \"Driver\"(id,dni,\"firstName\",\"secondName\",\"firstSurname\",\"secondSurname\", password, born) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)) {
				preparedStatement.setString(1, driver.getIdAsString());
				preparedStatement.setString(2, driver.getDni());
				preparedStatement.setString(3, driver.getFirstName());
				preparedStatement.setString(4, driver.getSecondName());
				preparedStatement.setString(5, driver.getFirstSurname());
				preparedStatement.setString(6, driver.getSecondSurname());
				preparedStatement.setString(7, driver.getPassword());
	
				preparedStatement.executeUpdate();
				
				insertPhone(driver.getIdAsString(), driver.getPhone());
				insertCompanyEmail(driver.getIdAsString(), driver.getCompanyEmail());
=======
			final var sql = "INSERT INTO \"Driver\"(id,\"licenseNumber\", \"authorizedCategories\")"
					+ "VALUES (?, ?, ?)";
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)) {
				preparedStatement.setString(1, driver.getIdAsString());
				preparedStatement.setString(2, driver.getLicenseNumber());
				preparedStatement.setString(3, driver.getAuthorizedCategory().getIdAsString());
	
				preparedStatement.executeUpdate();
				
>>>>>>> dev_federico
			} catch (final SQLException exception) {
				final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER
						.concat(driver.getIdAsString());
				throw DataCarpoolingException.createTechnicalException(message, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(
						Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DRIVER, exception);
			}
			
<<<<<<< HEAD
		}
	
		@Override
		public List<DriverDTO> find(final DriverDTO driver) {
			// TODO Auto-generated method stub
			return null;
		}
	
		@Override
		public void update(final DriverDTO driver) {
			final var sql = "UPDATE \"Driver\" SET \"firstName\" = ?," + "\"secondName\" = ?," + "\"firstSurname\" = ?,"
					+ "\"secondSurname\" = ?," + "password = ?," + "WHERE id = ?";
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)) {
				preparedStatement.setString(1, driver.getFirstName());
				preparedStatement.setString(2, driver.getSecondName());
				preparedStatement.setString(3, driver.getFirstSurname());
				preparedStatement.setString(4, driver.getSecondSurname());
				preparedStatement.setString(5, driver.getPassword());
				preparedStatement.setString(6, driver.getIdAsString());
	
				preparedStatement.executeUpdate();
			} catch (final SQLException exception) {
				final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_DRIVER
						.concat(driver.getIdAsString());
				throw DataCarpoolingException.createTechnicalException(message, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(
						Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_DRIVER, exception);
			}
		}
	
		@Override
		public void delete(final UUID id) {
			final var sql = "DELETE FROM \"Driver\" WHERE id = ?";
			final var idAsString = getUUIDAsString(id);
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)){
				preparedStatement.setString(1, idAsString);
				
				preparedStatement.executeUpdate();
			} catch (final SQLException exception) {
				final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_DELETE_DRIVER.concat(idAsString);
				throw DataCarpoolingException.createTechnicalException(message, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(
						Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_DRIVER, exception);
			}
		}
	
		private final void insertPhone(final String idAsString, final int phone) {
	
			final var sql = "INSERT INTO \"Phone\"(id, phone, \"Driver_id\") " + "VALUES (?, ?, ?)";
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)) {
				preparedStatement.setString(1, getRandomUUIDAsString());
				preparedStatement.setInt(2, phone);
				preparedStatement.setString(3, idAsString);
	
				preparedStatement.executeUpdate();
			} catch (final SQLException exception) {
				final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER
						.concat(idAsString);
				throw DataCarpoolingException.createTechnicalException(message, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(
						Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DRIVER, exception);
			}
		}
	
		private final void insertCompanyEmail(final String idAsString, final String email) {
	
			final var sql = "INSERT INTO \"CompanyEmail\"(id, email, \"Driver_id\")" + "VALUES(?, ?, ?)";
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)) {
				preparedStatement.setString(1, getRandomUUIDAsString());
				preparedStatement.setString(2, email);
				preparedStatement.setString(3, idAsString);
	
				preparedStatement.executeUpdate();
			} catch (final SQLException exception) {
				final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER
						.concat(idAsString);
				throw DataCarpoolingException.createTechnicalException(message, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(
						Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DRIVER, exception);
			}
		}
=======
		}
	
		@Override
		public List<DriverDTO> find(final DriverDTO driver) {
			var sqlBuilder = new StringBuilder();
			final var parameters = new ArrayList<Object>();
			
			createSelectFrom(sqlBuilder);
			createWhere(sqlBuilder, driver, parameters);
			createOrderBy(sqlBuilder);
			
			return prepareAndExecuteQuery(sqlBuilder, parameters);
		}
		
		private final List<DriverDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
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
			sqlbuilder.append("SELECT      Dr.id AS IdDriver,");
			sqlbuilder.append("            Dr.licenseNumber AS license,");
			sqlbuilder.append("            Dr.authorizedCategories AS Categorie,");
			sqlbuilder.append("            Au.id AS IDCategorie,");
			sqlbuilder.append("            Au.licenseNumber AS License,");
			sqlbuilder.append("            Au.authorizedCategories,");
			sqlbuilder.append("            U.Id AS UserId,");
			sqlbuilder.append("            U.dni AS DniUser,");
			sqlbuilder.append("            U.firstName AS Name,");
			sqlbuilder.append("            U.secondName AS SecondName,");
			sqlbuilder.append("            U.firstSurname AS FirstSurname,");
			sqlbuilder.append("            U.SecondSurname AS SecondSurname,");
			sqlbuilder.append("            U.phone As Phone,");
			sqlbuilder.append("            U.companyEmail AS Email");
			sqlbuilder.append("FROM        Driver Dr ");
			sqlbuilder.append("INNER JOIN  authorizedCategories Au,");
			sqlbuilder.append("ON          Dr.authorizedCategorie = Au.id");
			sqlbuilder.append("INNER JOIN  User U");
			sqlbuilder.append("ON          Dr.id = U.id");
		}
		
		private final void createWhere(final StringBuilder sqlBuilder, final DriverDTO driver,
				final List<Object> parameters) {
			if(!ObjectHelper.isNull(driver)) {
					var setWhere = true;
				
				if (!UUIDHelper.isDefaultUUID(driver.getId())) {
					sqlBuilder.append("WHERE Ro.id = ?");
						setWhere = false;
						parameters.add(driver.getIdAsString());
				}
				
				if (!UUIDHelper.isDefaultUUID(driver.getAuthorizedCategory().getId())) {
						sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("Ro.IdDriverVehicle");
						parameters.add(driver.getAuthorizedCategory().getIdAsString());
				}
				
				if (!UUIDHelper.isDefaultUUID(driver.getId())) {
						sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("RO.IdDetailRoute");
						parameters.add(driver.getIdAsString());
				}
			}
		}
		
		private void createOrderBy(final StringBuilder sqlBuilder) {
			sqlBuilder.append("ORDER BY    Dr.licenseNumber");
			sqlBuilder.append("ORDER BY    U.firstName");
		}
		
		private final List<DriverDTO> executeQuery(PreparedStatement preparedStatement){
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
		
		private final List<DriverDTO> fillResults(final ResultSet resultSet){
			
			try {
				var results = new ArrayList<DriverDTO>();
				
				while(resultSet.next()) {
					results.add(fillDriverDTO(resultSet));
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
		
		private final DriverDTO fillDriverDTO(final ResultSet resultSet) {
	        try {
	        	return DriverDTO.create(resultSet.getString("id"),resultSet.getString("licenseNumber"),
	        			fillAuthorizedCategoryDTO(resultSet));
	        } catch (final SQLException exception) {
	            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO, exception);
	        } catch (final Exception exception) {
	            throw DataCarpoolingException.createTechnicalException(Messages.RouteRequestPostgreSQLDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER_DTO, exception);
	        }
	    }
		
		private final AuthorizedCategoryDTO fillAuthorizedCategoryDTO(final ResultSet resultSet) {
			try {
				return AuthorizedCategoryDTO.create(resultSet.getString("IdCategory"),
						resultSet.getString("NameCategory"),resultSet.getString("Validity"));
			} catch (final SQLException exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_AUTHORIZED_CATEGOTY_DTO, exception);			
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.RouteqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_AUTHORIZED_CATEGOTY_DTO, exception);
			}
		}

		@Override
		public void update(final DriverDTO driver) {
			final var sql = "UPDATE \"Driver\" \"SET licenseNumber\" = ?" + "\"authorizedCategories\" = ? " + "WHERE id = ?";
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)) {
				preparedStatement.setString(1, driver.getIdAsString());
				preparedStatement.setString(2, driver.getLicenseNumber());
				preparedStatement.setString(3, driver.getAuthorizedCategory().getIdAsString());
	
				preparedStatement.executeUpdate();
				
			} catch (final SQLException exception) {
				final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_DRIVER
						.concat(driver.getIdAsString());
				throw DataCarpoolingException.createTechnicalException(message, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(
						Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_DRIVER, exception);
			}
		}
	
		@Override
		public void delete(final UUID id) {
			final var sql = "DELETE FROM \"Driver\" WHERE id = ?";
			final var idAsString = getUUIDAsString(id);
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)){
				preparedStatement.setString(1, idAsString);
				
				preparedStatement.executeUpdate();
			} catch (final SQLException exception) {
				final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_DELETE_DRIVER.concat(idAsString);
				throw DataCarpoolingException.createTechnicalException(message, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(
						Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_DRIVER, exception);
			}
		}
>>>>>>> dev_federico
	}
