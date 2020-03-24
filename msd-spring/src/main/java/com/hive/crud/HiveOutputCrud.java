package com.hive.crud;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.hive.constants.MSDConstants.*;
/***
 * A class to interact with Hive and store the end results.
 * 
 * @author kumar
 *  
 *
 */
@RestController
@RequestMapping(HIVE_OUTPUT_MAPPING)
public class HiveOutputCrud{
	private static final Logger logger = LoggerFactory.getLogger(HiveOutputCrud.class);
	String configPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()+APPLICATION_PROPERTIES;
	
	@Autowired
	@Qualifier(HIVE_JDBC_TEMPLATE)
	private JdbcTemplate hiveJdbcTemplate;

	//For fetching the properties which act as our resource repository.
	private Properties getConfig() {
		try {
		Properties appProps = new Properties();
			appProps.load(new FileInputStream(configPath));
			return appProps;
		}
		catch(Exception e) {
			logger.error(APPL_PROP_NOTFOUND_MESSAGE);
		}
		return null;
	}
	//For creating a table in Hive Database
	@GetMapping(HIVE_CREATE_MAPPING)
	public void create(@PathVariable String value) {
		
		StringBuffer sql = new StringBuffer(this.getConfig().getProperty(HIVE_OUTPUT+value+CREATE_TABLE));
		logger.info(sql.toString());
		hiveJdbcTemplate.execute(sql.toString());
	}

	//For inserting into HIVE Database
	@GetMapping(HIVE_INSERT_MAPPING)
	public void insert(@PathVariable String counter, @PathVariable String param1, @PathVariable String param2, @PathVariable String param3) throws InterruptedException {
		String sqlToInsert = StringUtils.EMPTY;
		param1 = param1.replace(UNDERSCORE, SPACE);
		sqlToInsert=this.getConfig().getProperty(HIVE_OUTPUT+counter+INSERT_TABLE)
				.replace(QMARK, "'"+param1+"'")
				.replace(HASH, "'"+param2+"'")
				.replace(DOLLAR, "'"+param3+"'");
		System.out.println(sqlToInsert);
		hiveJdbcTemplate.execute(sqlToInsert);
	}
	
	//For emptying HIVE table
		@GetMapping(HIVE_DELETE_MAPPING)
		public void delete(@PathVariable String value) throws InterruptedException {
			String sqlToInsert = StringUtils.EMPTY;
			sqlToInsert=this.getConfig().getProperty(HIVE_OUTPUT+value+DELETE_TABLE);
			System.out.println(sqlToInsert);
			hiveJdbcTemplate.execute(sqlToInsert);
		}

	//For fetching records from a particular table
	@GetMapping(HIVE_SELECT_MAPPING)
	public List<Map<String, String>> select(@PathVariable String value) {
		String sql = this.getConfig().getProperty(HIVE_OUTPUT+value+SELECT_TABLE);
		List<Map<String, Object>> rows = hiveJdbcTemplate.queryForList(sql);
		List<Map<String, String>> outputRows = new ArrayList<Map<String,String>>();
		rows.forEach((row)-> {
			Map<String, String> outputMap = new HashMap<String, String>();
			for(Map.Entry<String, Object> entry:row.entrySet()) {
				outputMap.put(entry.getKey().substring(entry.getKey().indexOf(DOT)+1, entry.getKey().length()), String.valueOf(entry.getValue()));
			}
			outputRows.add(outputMap);
		});
		return outputRows;
	}

}

