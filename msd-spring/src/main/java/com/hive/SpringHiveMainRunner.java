package com.hive;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import static com.hive.constants.MSDConstants.*;

/***
 * Main SpringBoot class to identify the class as a spring application
 * 
 * @author kumar
 *
 */

@SpringBootApplication
@ComponentScan(basePackages={BASE_PACKAGE})
public class SpringHiveMainRunner extends SpringBootServletInitializer{	
	
	public static void main(String args[]) {
		SpringApplication.run(SpringHiveMainRunner.class, args);
		
	}
}