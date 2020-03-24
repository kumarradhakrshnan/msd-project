package com.hive.driver;

import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static com.hive.constants.MSDConstants.*;

/***
 * The main MSD Driver which runs Spark job on Hive DB to give end results
 * 
 * @author kumar
 *
 */
public class MSDDriver {
	
	public static final Logger log = Logger.getLogger(MSDDriver.class);
	
	public static void main(String[] args) {
		
		
		//Fetching the configuration file which holds our resource mapping sheet.
		try {
		String configPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()+APPLICATION_PROPERTIES;
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(configPath));

		//Initialising the spark session and connecting to the hive database
		SparkSession spark = SparkSession.builder()
				.master(MASTER)
				.appName(APP_NAME)
				.config(SPARK_SQL_WAREHOUSE_DIRECTORY,appProps.getProperty(SPARK_SQL_WAREHOUSE_DIRECTORY))
				.enableHiveSupport()
				.getOrCreate();

		//Clearing out the existing table and creating a new table with the data from URI
		spark.sql(appProps.getProperty(HIVE_INPUT_DELETETABLE));
		spark.sql(appProps.getProperty(HIVE_INPUT_CREATETABLE));

		//Fetching the entire table for our manipulations
		Dataset<Row> jdbcDF = spark.sql(appProps.getProperty(HIVE_INPUT_SELECTTABLE));
		//Invoke the REST URI mapped to Springboot Classes to write onto Hive;
		
		for(int i=1; i<=2; i++) {
			URL url = new URL(appProps.getProperty(HIVE_OUTPUT+i+CREATE_URL));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(appProps.getProperty(MSD_PROTOCOL));
			log.info(SUCCESS_CREATE+con.getResponseCode());
		}

		
		//Answer for Query 1
		jdbcDF.groupBy(appProps.getProperty(GROUPBY_1_1), appProps.getProperty(GROUPBY_1_2))
		.avg(appProps.getProperty(AVG_1))
		.select(appProps.getProperty(SELECT_1_1),appProps.getProperty(SELECT_1_2), appProps.getProperty(SELECT_1_3))
		.orderBy(appProps.getProperty(ORDERBY_1))
		.collectAsList()
		.forEach((row)->{
			try {
				URL url = new URL(appProps.getProperty(HIVE_OUTPUT+ONE+INSERT_URL)+SLASH+
						row.getString(0).replaceAll(SPACE, UNDERSCORE)+SLASH+
						row.getInt(1)+SLASH+
						row.getDouble(2));
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod(appProps.getProperty(MSD_PROTOCOL));
				log.info(SUCCESS_INSERT+con.getResponseCode());
			}catch(Exception e) {
				log.info(FAILURE_INSERT+e);
			}
		});

		//Answer for Query 2
		jdbcDF.groupBy(appProps.getProperty(GROUPBY_2_1), appProps.getProperty(GROUPBY_2_2), appProps.getProperty(GROUPBY_2_3))
		.avg(appProps.getProperty(AVG_2))
		.select(appProps.getProperty(SELECT_2_1), appProps.getProperty(SELECT_2_2), appProps.getProperty(SELECT_2_3),appProps.getProperty(SELECT_2_4))
		.filter(appProps.getProperty(FILTER_2))
		.collectAsList()
		.forEach((row)->{
			try {
			URL url = new URL(appProps.getProperty(HIVE_OUTPUT+TWO+INSERT_URL)+SLASH+
					row.getString(0).replaceAll(SPACE, UNDERSCORE)+SLASH+
					row.getInt(1)+SLASH+
					row.getDouble(2));

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(appProps.getProperty(MSD_PROTOCOL));
			log.info(SUCCESS_INSERT+con.getResponseCode());
			}catch(Exception e) {
				log.info(FAILURE_INSERT+e);
			}
		});

		spark.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

