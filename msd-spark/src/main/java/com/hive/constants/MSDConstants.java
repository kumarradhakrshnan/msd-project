package com.hive.constants;

/***
 * 
 * An interface file to maintain constants.
 * 
 * @author raadh
 *
 */
public interface MSDConstants {

	public static final String MASTER = "local[*]";
	public static final String APP_NAME = "MSDDriver";
	public static final String HIVE_INPUT_DELETETABLE = "hive.input.deleteTable.sql";
	public static final String HIVE_INPUT_CREATETABLE = "hive.input.createTable.sql";
	public static final String HIVE_INPUT_SELECTTABLE = "hive.input.selectTable.sql";
	public static final String SPARK_SQL_WAREHOUSE_DIRECTORY = "spark.sql.warehouse.directory";
	public static final String APPLICATION_PROPERTIES = "application.properties";
	public static final String HIVE_OUTPUT = "hive.output.";
	public static final String CREATE_URL = ".create.url";
	public static final String INSERT_URL = ".insert.url";
	public static final String SPACE = " ";
	public static final String BLANK = "";
	public static final String UNDERSCORE= "_";
	public static final String MSD_PROTOCOL = "msd.protocol";
	public static final String NA = "NA";
	public static final String ONE = "1";
	public static final String TWO = "2";
	public static final String SLASH = "/";
	public static final String SELECT_1_1 = "msd.select.1.1";
	public static final String SELECT_1_2 = "msd.select.1.2";
	public static final String SELECT_1_3 = "msd.select.1.3";
	public static final String AVG_1 = "msd.avg.1";
	public static final String ORDERBY_1 = "msd.orderBy.1";
	public static final String GROUPBY_1 = "msd.groupBy.1";
	public static final String GROUPBY_1_1 = "msd.groupBy.1.1";
	public static final String GROUPBY_1_2 = "msd.groupBy.1.2";
	public static final String FILTER_1 = "msd.filter.1";
	public static final String SELECT_2_1 = "msd.select.2.1";
	public static final String SELECT_2_2 = "msd.select.2.2";
	public static final String SELECT_2_3 = "msd.select.2.3";
	public static final String SELECT_2_4 = "msd.select.2.4";
	public static final String AVG_2 = "msd.avg.2";
	public static final String ORDERBY_2 = "msd.orderBy.2";
	public static final String GROUPBY_2_1 = "msd.groupBy.2.1";
	public static final String GROUPBY_2_2 = "msd.groupBy.2.2";
	public static final String GROUPBY_2_3 = "msd.groupBy.2.3";
	public static final String FILTER_2 = "msd.filter.2";
	public static final String SUCCESS_INSERT="Successfully inserted into table with response code:";
	public static final String SUCCESS_CREATE="Successfully created table with response code:";
	public static final String FAILURE_INSERT="Error while trying to invoke insertion into tables:";
}
