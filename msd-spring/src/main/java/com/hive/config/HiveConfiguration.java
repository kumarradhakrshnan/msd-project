package com.hive.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import static com.hive.constants.MSDConstants.*;

/***
 * 
 * A class file which defines and connects to hive datasource and establishes a template to connect with it
 * 
 * @author raadh
 *
 */

@Configuration
@ComponentScan
@PropertySource(CLASSPATH_APP_PROPERTIES)
public class HiveConfiguration implements EnvironmentAware {
	private static final Logger logger = LoggerFactory.getLogger(HiveConfiguration.class);
	private static Environment env;

	@Override
    public void setEnvironment(final Environment environment) {
        env = environment;
    }
	
	//Creating a datasource for connecting to Hive Server
	@Bean(name = HIVE_JDBC_DATA_SOURCE)
	@Qualifier(HIVE_JDBC_DATA_SOURCE)
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setUrl(env.getProperty(HIVE_URL));
		dataSource.setDriverClassName(env.getProperty(HIVE_DRIVER));
		dataSource.setUsername(env.getProperty(HIVE_USERNAME));
		dataSource.setPassword(env.getProperty(HIVE_PASSWORD));
		logger.debug(HIVE_DEBUG_DATASOURCE_MESSAGE);
		return dataSource;
	}

	//A jdbc template which links to Hive Database to perform operations.
	@Bean(name = HIVE_JDBC_TEMPLATE)
	public JdbcTemplate hiveJdbcTemplate(
			@Qualifier(HIVE_JDBC_DATA_SOURCE) DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}