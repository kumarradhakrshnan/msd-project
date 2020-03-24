package com.hive.constants;

/**
 * Interface file to maintain the constants.
 * 
 * @author kumar
 *
 */
public interface MSDConstants {

	public static final String HIVE_OUTPUT_MAPPING = "/hiveoutput";
	public static final String BASE_PACKAGE = "com";
	public static final String APPLICATION_PROPERTIES = "application.properties";
	public static final String HIVE_JDBC_TEMPLATE = "hiveJdbcTemplate";
	public static final String CLASSPATH_APP_PROPERTIES = "classpath:application.properties";
	public static final String HIVE_JDBC_DATA_SOURCE = "hiveJdbcDataSource";
	public static final String HIVE_URL = "hive.url";
	public static final String HIVE_DRIVER = "hive.driver";
	public static final String HIVE_USERNAME = "hive.username";
	public static final String HIVE_PASSWORD = "hive.password";
	public static final String HIVE_INSERT_MAPPING = "/insert/{counter}/{param1}/{param2}/{param3}";
	public static final String HIVE_CREATE_MAPPING = "/create/{value}";
	public static final String HIVE_SELECT_MAPPING = "/select/{value}";
	public static final String HIVE_DELETE_MAPPING = "/delete/{value}";
	public static final String HIVE_OUTPUT = "hive.output.";
	public static final String HIVE_DEBUG_DATASOURCE_MESSAGE = "This is the configuration for Hive DataSource";
	public static final String APPL_PROP_NOTFOUND_MESSAGE = "Application.Properties file not found";
	public static final String QMARK ="?";
	public static final String HASH ="#";
	public static final String DOLLAR ="$";
	public static final String DOT =".";	
	public static final String SPACE=" ";	
	public static final String UNDERSCORE="_";	
	public static final String INSERT_TABLE = ".insertTable.sql";
	public static final String SELECT_TABLE = ".selectTable.sql";
	public static final String CREATE_TABLE = ".createTable.sql";
	public static final String DELETE_TABLE = ".dropTable.sql";

}
