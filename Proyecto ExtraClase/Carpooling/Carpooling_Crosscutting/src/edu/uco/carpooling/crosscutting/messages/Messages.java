package edu.uco.carpooling.crosscutting.messages;

public class Messages {
	
	private Messages() {
		super();
	}

	public static class DAOFactory {
		
		private DAOFactory() {
			super();
		}
		
		public static final String TECHNICAL_MONGO_DB_NOT_IMPLEMENTED="DAOFactory for MONGODB is'nt implemented yet";
		public static final String TECHNICAL_MY_SQL_NOT_IMPLEMENTED="DAOFactory for MYSQL is'nt implemented yet";
		public static final String TECHNICAL_ORACLE_NOT_IMPLEMENTED="DAOFactory for ORACLE is'nt implemented yet";
		public static final String TECHINICAL_SQL_SERVER_NOT_IMPLEMENTED="DAOFactory for SQLSERVER is'nt implemented yet";
		public static final String TECHNICAL_UNEXPECTED_DAOFACTORY= "Unexpected DAOFactory is'nt implemented";
	}
	
	public static class SqlConnectionHelper {
		
		private SqlConnectionHelper() {
			super();
		}
		
		public static final String TECHNICAL_CONNECTION_IS_NULL="Connection is null";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED="Connection is closed";
		public static final String TECHNICAL_CONNECTION_ALREDY_IS_CLOSED = "Connection alredy is closed";
		public static final String TECHNICAL_PROBLEM_CLOSING_CONNECTION = "There was a problem try close the connection. Please verify the technicals details";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION = "Connection is closed to start a new transaction";
		public static final String TECHNICAL_PROBLEM_TRY_INIT_TRANSACTION = "There was a problem trying to start the transaction. Please verify the technical details";	
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION = "Connection is closed to commit the current transaction";
		public static final String TECHNICAL_PROBLEM_TRY_COMMIT_TRANSACTION = "There was a problem trying to commit the current trasaction. Please verify te technical details";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION = "Connection is closed to rollback the current transaction";
		public static final String TECHNICAL_PROBLEM_TRY_ROLLBACK_TRANSACTION = "There was a problem trying to rollback the current trasaction. Please verify te technical details";

	}
	
	public static class PostgreSqlDAOFactory{
		
		private PostgreSqlDAOFactory() {
			super();
		}
		
		public static final String TECHNICAL_CONNECTION_NOT_POSSIBLE = "The connection is not possible";
		public static final String TECHNICAL_OPEN_CONNECTION_UNEXPECTED_ERROR = "The was an unexpected error trying openning connection in PostgreSqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_INIT_TRANSACTION = "There was a problem trying to init transaction with the current connection in PostgreSqlDAOFactory";
		public static final String TECHNICAL_INIT_TRANSACTION_UNEXPECTED_ERROR = "There was an unexpected error trying init transaction in PostgreSqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_CONFIRM_TRANSACTION = "There was a problem trying to confirm transaction with the current connection in PostgreSqlDAOFactory";
		public static final String TECHNICAL_CONFIRM_TRANSACTION_UNEXPECTED_ERROR = "There was an unexpected error trying confirm transaction in PostgreSqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_CANCEL_TRANSACTION = "There was a problem trying to cancel transaction with the current connection in PostgreSqlDAOFactory";
		public static final String TECHNICAL_CANCEL_TRANSACTION_UNEXPECTED_ERROR = "There was an unexpected error trying cancel transaction in PostgreSqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_CLOSE_CONNECTION = "There was a problem trying to close connection in PostgreSqlDAOFactory";
		public static final String TECHNICAL_CLOSE_CONNECTION_UNEXPECTED_ERROR = "There was an unexpected error trying close connection in PostgreSqlDAOFactory";
	}
	
	public static class DriverPostgreSqlDAO{
		
		private DriverPostgreSqlDAO() {
			super();
		}
		
		public static final String TECHNICAL_PROBLEM_CREATE_DRIVER = "There was a problem trying create driver in DriverPostgreSql with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DRIVER = "There was an unexpected problem trying create driver in DriverPostgreSql";
		public static final String TECHNICAL_PROBLEM_UPDATE_DRIVER = "There was a problem trying update driver in DriverPostgreSql with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_DRIVER = "There was an unexpected problem trying update driver in DriverPostgreSql";
		public static final String TECHNICAL_PROBLEM_DELETE_DRIVER = "There was a problem trying delete driver in DriverPostgreSql with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_DRIVER = "There was an unexpected problem trying delete driver in DriverPostgreSql";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS_DRIVER = "There was a problem trying fill result on driver";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS_DRIVER = "There was an unexpected problem trying fill result on driver";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS_CATEGORY_AUTHORIZED = "There was a problem trying fill result on category authorized";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS_CATEGORY_AUTHORIZED = "There was an unexpected problem trying fill result on category authorized";

		
	}
	
	
	public static class CustomerPostgreSqlDAO{
		
		private CustomerPostgreSqlDAO() {
			super();
		}
		
		public static final String TECHNICAL_PROBLEM_CREATE_CUSTOMER = "There was a problem trying create customer in CustomerPostgreSql with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CUSTOMER = "There was an unexpected problem trying create customer in CustomerPostgreSql";
		public static final String TECHNICAL_PROBLEM_UPDATE_CUSTOMER = "There was a problem trying update customer in CustomerPostgreSql with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_CUSTOMER = "There was an unexpected problem trying update customer in CustomerPostgreSql";
		public static final String TECHNICAL_PROBLEM_DELETE_CUSTOMER = "There was a problem trying delete customer in CustomerPostgreSql with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_CUSTOMER = "There was an unexpected problem trying delete customer in CustomerPostgreSql";
		public static final String TECHNICAL_PROBLEM_PREPARED_STAMENT = "There was a problem trying to prepared the sql stament the parameters";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STAMENT = "There was an unexpected problem trying to prepared the sql stament the parameters";
	 	public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to excute query to find the specific customer";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY = "There was an unexpected problem trying to execute query to finad the specific customer";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was an unexpected problem trying to set the parameters values to the query";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER = "There was an unexpected problem trying fill routeStatusDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO = "There was a problem filling DriverDTO from the resultSet ";

	
	}
	
	public static class UUIDHelper{
		private UUIDHelper() {
			super();
		}
		
		public static final String TECHICAL_UUID_FROM_STRING_INAVLID ="The UUID to covert does not have a valied";
		public static final String TECNICAL_UUID_FROM_STRING_UNEXPECTED_ERROR = "There was an unexpected exception trying to convert a UUID from String";
	}

	public static class DriverPerVehiclePostgresSqlDAO {
		private DriverPerVehiclePostgresSqlDAO() {
			super();
		}

		public static final String TECHNICAL_PROBLEM_CREATE_DRIVER_PER_VEHICLE = "There was a problem trying to create the driver per vehicle in SQLServerDaoFactory with idVehicle= ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DRIVER_PER_VEHICLE = "There was an unexpected problem trying to create driver per vehivle in PostgreSQLDaoFactory";
		public static final String TECHNICAL_PROBLEM_UPDATE_DRIVER_PER_VEHICLE = "There was a problem trying to update the driver per vehicle in SQLServerDaoFactory with id= ";
		public static final String TECHNICAL_PROBLEM_DELETE_DRIVER_PER_VEHICLE = "There was a problem trying to delete the driver per vehicle in PostgreSQLDaoFactory with idDriver= ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_DRIVER_PER_VEHICLE = "The was an unexpected error tryinf to delete driver per vehicle in PostgreSQLDapFactory";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS = "There was an unexpected problem trying recovering results from the select ";
		public static final String TECHNICAL_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO = "There was a problem filling authotizedcategoryDTO from the resultSet ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_DRIVER_PER_VEHICLE_DTO = "There was an unexpected problem trying fill driverpervehicleDTO from the resultSet ";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to excute query to find the specific driver per vehicle";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific driver per vehicle";
		public static final String TECHNICAL_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was a problem trying to set the parameters values to the query";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was an unexpected problem trying to set the parameters values to the query";
		public static final String TECHNICAL_PROBLEM_PREPARED_STAMENT = "There was a problem trying to prepared the sql stament the parameters";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STAMENT = "There was an unexpected problem trying to prepared the sql stament the parameters";
		public static final String TECHNICAL_PROBLEM_FILL_ROUTE_STATUS_DTO = "There was a problem filling authotizedcategoryDTO from the resultSet ";
	}

	public static class RouteqlServerDAO{
	 	private RouteqlServerDAO(){
	 		super();	
	 	}	
	 	
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ROUTE = "There was an unexpected problem trying to create the route in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_CREATE_ROUTE = "There was a problem trying to create the route in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_UPDATE_ROUTE = "There was a problem trying to update the route in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_DELETE_ROUTE = "There was a problem trying to delete the route in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS = "There was an unexpected problem trying recovering results from the select ";
	 	public static final String TECHNICAL_PROBLEM_FILL_ROUTE_DTO = "There was a problem filling routeDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_ROUTE_DTO = "There was an unexpected problem trying fill routeDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to excute query to find the specific route";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY = "There was an unexpected problem trying to execute query to finad the specific routes";
	 	public static final String TECHNICAL_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was a problem trying to set the parameters values to the query";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was an unexpected problem trying to set the parameters values to the query";
	 	public static final String TECHNICAL_PROBLEM_PREPARED_STAMENT = "There was a problem trying to prepared the sql stament the parameters";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STAMENT = "There was an unexpected problem trying to prepared the sql stament the parameters";
	 	public static final String TECHNICAL_PROBLEM_FILL_ROUTESTATUS_DTO = "There was a problem filling routeStatusDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_ROUTESTATUS_DTO = "There was an unexpected problem trying fill routeStatusDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_FILL_DRIVER_DTO = "There was a problem filling DriverDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_DRIVER_DTO = "There was an unexpected problem trying fill DriverDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_FILL_AUTHORIZED_CATEGOTY_DTO = "There was a problem filling authorizedCategoryDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_AUTHORIZED_CATEGOTY_DTO = "There was an unexpected problem trying fill authorizedCategoryDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_FILL_VEHICLE_DTO = "There was a problem filling vehicleDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_VEHICLE_DTO = "There was an unexpected problem trying fill vehicleDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_FILL_DRIVERPERVEHICLE_DTO = "There was a problem filling driverPervehicleDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_DRIVERPERVEHICLE_DTO = "There was an unexpected problem trying fill driverPervehicleDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_FILL_POINT_INTEREST_DTO = "There was a problem filling pointInterestDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_POINT_INTEREST_DTO = "There was an unexpected problem trying fill pointInterestDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_FILL_DETAIL_ROUTE_DTO = "There was a problem filling detailRouteDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_DETAIL_ROUTE_DTO = "There was an unexpected problem trying fill detailRouteDTO from the resultSet ";
	}
	
	public static class RouteRequestPostgreSQLDAO{
	 	private RouteRequestPostgreSQLDAO(){
	 		super();	
	 	}	
	 	
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ROUTE_REQUEST = "There was an unexpected problem trying to create the route request in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_CREATE_ROUTE_REQUEST = "There was a problem trying to create the routeRequest in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_UPDATE_ROUTE_REQUEST = "There was a problem trying to update the routeRequest in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_DELETE_ROUTE_REQUEST = "There was a problem trying to delete the routeRequest in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select ";
	 	public static final String TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO = "here was a problem filling CustomerDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER_DTO= "There was an unexpected problem trying fill CustomerDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS = "There was an unexpected problem trying recovering results from the select ";
	 	public static final String TECHNICAL_PROBLEM_FILL_ROUTE_REQUEST_REQUEST_DTO = "There was a problem filling routerequestDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_ROUTE_REQUEST_DTO = "There was an unexpected problem trying fill routerequestDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to excute query to find the specific route request";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific route request";
	 	public static final String TECHNICAL_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was a problem trying to set the parameters values to the query";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was an unexpected problem trying to set the parameters values to the query";
	 	public static final String TECHNICAL_PROBLEM_PREPARED_STAMENT = "There was a problem trying to prepared the sql stament the parameters";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STAMENT = "There was an unexpected problem trying to prepared the sql stament the parameters";
	 	public static final String TECHNICAL_PROBLEM_FILL_ROUTESTATUS_DTO = "There was a problem filling routeStatusDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_ROUTESTATUS_DTO = "There was an unexpected problem trying fill routeStatusDTO from the resultSet ";
	}
	
	public static class RouteStatusPosgreAqlDAO{
	 	private RouteStatusPosgreAqlDAO(){
	 		super();	
	 	}	
	 	
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ROUTE_STATUS = "There was an unexpected problem trying to create the route status in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_CREATE_ROUTE_STATUS = "There was a problem trying to create the routeStatus in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_UPDATE_ROUTE_STATUS = "There was a problem trying to update the routeStatus in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_DELETE_ROUTE_STATUS = "There was a problem trying to delete the routeStatus in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS = "There was an unexpected problem trying recovering results from the select ";
	 	public static final String TECHNICAL_PROBLEM_FILL_ROUTE_ROUTE_STATUS_DTO = "There was a problem filling routerstatusDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_ROUTE_STATUS_DTO = "There was an unexpected problem trying fill routerstatusDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to excute query to find the specific route status";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific route status";
	 	public static final String TECHNICAL_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was a problem trying to set the parameters values to the query";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was an unexpected problem trying to set the parameters values to the query";
	 	public static final String TECHNICAL_PROBLEM_PREPARED_STAMENT = "There was a problem trying to prepared the sql stament the parameters";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STAMENT = "There was an unexpected problem trying to prepared the sql stament the parameters";
	 	public static final String TECHNICAL_PROBLEM_FILL_ROUTE_STATUS_DTO = "There was a problem filling routeStatusDTO from the resultSet ";
	}
	
	public static class DetailRoutePostgreSqlDAO{
	 	private DetailRoutePostgreSqlDAO(){
	 		super();	
	 	}	
	 	
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DETAIL_ROUTE = "There was an unexpected problem trying to create the detail route in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_CREATE_DETAIL_ROUTE  = "There was a problem trying to create the detail route in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_UPDATE_DETAIL_ROUTE  = "There was a problem trying to update the detail route in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_DELETE_DETAIL_ROUTE  = "There was a problem trying to delete the detail route in PostgreSQLDaoFactory with id= ";
	 	public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS = "There was an unexpected problem trying recovering results from the select ";
	 	public static final String TECHNICAL_PROBLEM_FILL_ROUTE_DETAIL_ROUTE_DTO = "There was a problem filling detailRouteDTO from the resultSet ";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_DETAIL_ROUTE_DTO = "There was an unexpected problem trying fill detailRouteDTO from the resultSet ";
	 	public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to excute query to find the specific detail Route";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific detail Route";
	 	public static final String TECHNICAL_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was a problem trying to set the parameters values to the query";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was an unexpected problem trying to set the parameters values to the query";
	 	public static final String TECHNICAL_PROBLEM_PREPARED_STAMENT = "There was a problem trying to prepared the sql stament the parameters";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STAMENT = "There was an unexpected problem trying to prepared the sql stament the parameters";
	 	public static final String TECHNICAL_PROBLEM_FILL_ROUTE_STATUS_DTO = "There was a problem filling routeStatusDTO from the resultSet ";
	}
	
	
	public static class VehiclePosgreSqlDao{
		private VehiclePosgreSqlDao() {
			super();
		}

		public static final String TECHNICAL_PROBLEM_CREATE_VEHICLE = "There was a problem trying to create the Vehicle in PostgreSqlDao with id= ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_VEHICLE = "The was an unexpected error trying to create Vehicle in PostgreSqlDao";
		public static final String TECHNICAL_PROBLEM_DELETE_VEHICLE = "The was a problem trying to delete the vehicle in PostgreSqlDao with id= ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_VEHICLE = "The was a problem trying to delete the vehicle in PostgreSqlDao";
		public static final String TECHNICAL_PROBLEM_PREPARED_STATEMENT = "There was a problem trying to prepared the sql stament the parameters";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT = "There was an unexpected problem trying to prepared the sql stament the parameters";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to excute query to find the specific vehicle";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY = "There was an unexpected problem trying to execute query to find the Vehicle";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS = "There was an unexpected problem trying recovering results from the select ";
	 	public static final String TECHNICAL_PROBLEM_FILL_VEHICLE = "There was a problem filling VehicleDTO from the resultSet";
	 	public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_VEHICLE = "There was an unexpected problem filling VehicleDTO from the resultSet";
	}

	public static class AuthotizedCategoryPostgresSqlDAOPostgresSqlDAO {

		private AuthotizedCategoryPostgresSqlDAOPostgresSqlDAO() {
			super();
		}

		public static final String TECHNICAL_PROBLEM_CREATE_AUTHORIZED_CATEGORY = "There was a problem trying to create the authotized category in SQLServerDaoFactory with id= ";
		public static final String TECHNICAL_PROBLEM_UPDATE_AUTHORIZED_CATEGORY = "There was a problem trying to update the authotized category in SQLServerDaoFactory with id= ";
		public static final String TECHNICAL_PROBLEM_DELETE_AUTHORIZED_CATEGORY = "There was a problem trying to delete the authotized category in SQLServerDaoFactory with id= ";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS = "There was an unexpected problem trying recovering results from the select ";
		public static final String TECHNICAL_PROBLEM_FILL_AUTHORIZED_CATEGORY_AUTHORIZED_CATEGORY_DTO = "There was a problem filling authotizedcategoryDTO from the resultSet ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_AUTHORIZED_CATEGORY_DTO = "There was an unexpected problem trying fill authotizedcategoryDTO from the resultSet ";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to excute query to find the specific detail Route";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific authotized category";
		public static final String TECHNICAL_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was a problem trying to set the parameters values to the query";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was an unexpected problem trying to set the parameters values to the query";
		public static final String TECHNICAL_PROBLEM_PREPARED_STAMENT = "There was a problem trying to prepared the sql stament the parameters";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STAMENT = "There was an unexpected problem trying to prepared the sql stament the parameters";
		public static final String TECHNICAL_PROBLEM_FILL_ROUTE_STATUS_DTO = "There was a problem filling authotizedcategoryDTO from the resultSet ";
	}

	public static class PointInterestPostgresSqlDAO {
		private PointInterestPostgresSqlDAO() {
			super();
		}

		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_POINT_INTEREST = "There was an unexpected problem trying to create the point interest in SQLServerDaoFactory with id= ";
		public static final String TECHNICAL_PROBLEM_CREATE_POINT_INTEREST = "There was a problem trying to create the point interest in SQLServerDaoFactory with id= ";
		public static final String TECHNICAL_PROBLEM_UPDATE_POINT_INTEREST = "There was a problem trying to update the point interest in SQLServerDaoFactory with id= ";
		public static final String TECHNICAL_PROBLEM_DELETE_POINT_INTEREST = "There was a problem trying to delete the point interest in SQLServerDaoFactory with id= ";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS = "There was an unexpected problem trying recovering results from the select ";
		public static final String TECHNICAL_PROBLEM_FILL_ROUTE_POINT_INTEREST_DTO = "There was a problem filling pointInterestDTO from the resultSet ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_POINT_INTEREST_DTO = "There was an unexpected problem trying fill pointInterestDTO from the resultSet ";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to excute query to find the specific detail Route";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECEUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific point interest";
		public static final String TECHNICAL_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was a problem trying to set the parameters values to the query";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETER_VALUES_QUERY = "There was an unexpected problem trying to set the parameters values to the query";
		public static final String TECHNICAL_PROBLEM_PREPARED_STAMENT = "There was a problem trying to prepared the sql stament the parameters";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STAMENT = "There was an unexpected problem trying to prepared the sql stament the parameters";
		public static final String TECHNICAL_PROBLEM_FILL_ROUTE_STATUS_DTO = "There was a problem filling routeStatusDTO from the resultSet ";
	}

	public static class CreateRouteRequestUseCaseImpl {
		private CreateRouteRequestUseCaseImpl() {
			super();
		}

		public static final String BUSSINES_CUSTOMER_DOES_NOT_EXIST = "El cliente que desea buscar no existe";
		public static final String BUSSINES_ROUTE_REQUEST_DOES_EXIST = "la peticion ruta ya feu creada";

	}

	public static class CreateVehicleUseCaseImpl {
		private CreateVehicleUseCaseImpl() {
			super();
		}

		public static final String BUSSINES_DRIVER_DOES_NOT_EXISTS = "The driver for whom the vehicle was to be created does not exist.";
		public static final String BUSSINES_VEHICLE_EXIST = "Is alredy exists a vehicle in the application, please, try add other vehicle";
	}

  	public static class CreateVehicleCommandImpl {
		private CreateVehicleCommandImpl() {
			super();
		}
		
		public static final String USER_PROBLEM_CREATE_VEHICLE = "The vehicle could not be created, please try again";
		public static final String USER_UNEXPECTED_PROBLEM_CREATE_VEHICLE  = "An unexpected problem occurred, please try again";
	}
	
	public static class VehicleController{
		private VehicleController() {
			super();
		}
		public static final String SUCCESS_CREATE_VEHICLE = "The vehicle has been created succesfully";
		public static final String ERROR_CREATE_VEHICLE = "There was an error trying to create the vehicle, please try again...";
		public static final String FATAL_CREATE_VEHICLE = "There was an unexpected error trying to create thge vehicle, please try again...";
	}
		
	public static class CreateDriverPerVehicleUseCaseImpl{
		private CreateDriverPerVehicleUseCaseImpl() {
			super();
		}
		
	}
	
	public static class CreateVehicleValidator{
		private CreateVehicleValidator() {
			super();
		}
		
		public static final String ERROR_OWNER_ID_IS_DEFAULT = "Owner id is equals to default value, please verify that all is correct...";
		public static final String ERROR_PLATE_IS_EMPTY = "Plate is empty, please verify that all is correct...";
		public static final String ERROR_NUMBER_ENROLLMENT_IS_EMPTY = "Number enrollment is emply, please verify that all is correct...";
		public static final String ERROR_NUMBER_ENROLLMENT_INCORRECT_LENGTH = "Number Enrollment must be 11 digits";
		public static final String ERROR_LENGTH_PLATE_IS_NOT_CORRECT = "The plate length is not correct for a licence plate";
		public static final String ERROR_DONT_HAVE_CORRECT_FORMAT = "Verify that plate have te correct format (NNN-123), three letters first and three numbers at the end";
	}
	
	public static class CreateCustomerValidator{
		private CreateCustomerValidator() {
			super();
		}
		
		public static final String ERROR_CUSTOMER_ID_IS_DEFAULT = "Customer id is equal to default value";
		public static final String ERROR_INVALID_FORMAT_EMAIL = "Email is not valied";
		public static final String ERROR_INVALID_PASSWORD = "Password is not valied";
	}
	
	public static class CreateDriverValidator{
		private CreateDriverValidator() {
			super();
		}
		
		public static final String ERROR_DRIVER_ID_IS_DEFAULT = "Driver id is equal to default value";
		public static final String BUSSINES_DRIVER_NUMBER_LICENSE_IS_INCORRECT = "Number of license es incorrect";
		public static final String BUSSINES_DRIVER_LICENSE_IS_EMPTY = "The file license number i s empty";

	}
	
	public static class CreateRouteRequestValidator{
		private CreateRouteRequestValidator() {
			super();
		}
		
		public static final String ERRROR_ROUTE_REQUEST_ID_IS_DEFAULT = "Route Request id is equal to default value";
		public static final String ERROR_DATE_IS_LESS_THAN_ACTUALLY_DATE = "the entered date is less than the current date";
		public static final String ERROR_HOUR_IS_LESS_THAN_ACTUALLY_HOUR = "The entered hour is less than the current hour";
	}
	public static class DriverController{
		private DriverController() {
			super();
		}

		public static final String CONTROLLER_CREATE_DRIVER_SUCCESFUL = "Driver has been create succssefully";
		public static final String CONTROLLER_ERROR_TRY_TO_CREATE_DRIVER = "There was an error trying to create driver. Please try again...";
		public static final String CONTROLLER_UNEXPECTED_ERROR_TRY_TO_CREATE_DRIVER = "There was a unexpected error trying to create driver. Please try again...";
		public static final String CONTROLLER_ERROR_TRY_FOUND_DRIVER_BYID = "The driver wasyou try to found does not exist, try again";
		public static final String CONTROLLER_SUCCES_FOUND_DRIVER_BYID= "Succes found driver by id";

	}
	
	public static class ValidateCustomer{
		private ValidateCustomer() {
			super();
		}
			public static final String BUSSINES_CUSTOMER_DOES_NOT_EXIST = "Id customer that you uses not exist";
			public static final String BUSSINES_CUSTOMER_EMAIL_IS_INCORRECT = "The email has the format incorrent";
			public static final String BUSSINES_CUSTOMER_PASSWORD_IS_INCORRECT = "Password format is incorrent, you need minimun 6 characteres";
			public static final String BUSSINES_CUSTOMER_FIRST_NAME_IS_EMPTY = "The field firstName is empty";
			public static final String BUSSINES_CUSTOMER_FIRST_SURNNAME_IS_EMPTY = "The field firstsurname is empty";
			public static final String BUSSINES_CUSTOMER_SECOND_SURNNAME_IS_EMPTY = "The field secondsurname is empty";
			public static final String BUSSINES_CUSTOMER_DNI_IS_INCORRECT = "The field dni has the incorrect format you need almost 10 characters";
		}
	
	public static class CustomerController{
		private CustomerController() {
			super();
		}

		public static final String CONTROLLER_CREATE_CUSTOMER_SUCCESFUL = "Customer has been create succssefully";
		public static final String CONTROLLER_ERROR_TRY_TO_CREATE_CUSTOMER = "There was an error trying to create driver. Please try again...";
		public static final String CONTROLLER_UNEXPECTED_ERROR_TRY_TO_CREATE_CUSTOMER = "There was a unexpected error trying to create Customer. Please try again...";
		public static final String CONTROLLER_ERROR_TRY_FOUND_CUSTOMER_BYID = "The customer was you try to found does not exist, try again";
		public static final String CONTROLLER_SUCCES_FOUND_CUSTOMER_BYID= "Succes found customer with id";

	}
	
	public static class RouteRequestController{
		private RouteRequestController() {
			super();
		}
		public static final String CONTROLLER_CREATE_ROUTE_REQUEST_SUCCESFUL = "Route Request has been create succssefully";
		public static final String CONTROLLER_ERROR_TRY_TO_CREATE_CUSTOMER_ROUTE_REQUEST = "There was an error trying to create route request. Please try again...";
		public static final String CONTROLLER_UNEXPECTED_ERROR_TRY_TO_CREATE_ROUTE_REQUEST = "There was a unexpected error trying to create route request. Please try again...";
	}
}



