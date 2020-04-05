package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;
import javax.swing.plaf.basic.ComboPopup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.luv2code.springsecurity.demo")
@PropertySource("persistence-mysql.properties")
public class DemoAppConfig {
	
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/view/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
				
	}
	
	@Bean
	public DataSource getDataSource() {
		
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
			securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			securityDataSource.setUser(env.getProperty("jdbc.user"));
			securityDataSource.setPassword(env.getProperty("jdbc.password"));
			
			securityDataSource.setInitialPoolSize(getPropertyAsInt(env.getProperty("connection.pool.initialPoolSize")));
			securityDataSource.setMinPoolSize(getPropertyAsInt(env.getProperty("connection.pool.minPoolSize")));
			securityDataSource.setMaxPoolSize(getPropertyAsInt(env.getProperty("connection.pool.maxPoolSize")));
			securityDataSource.setMaxIdleTime(getPropertyAsInt(env.getProperty("connection.pool.maxIdleTime")));
			
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	private int getPropertyAsInt(String propName) {
		String propValue = env.getProperty(propName);
		return Integer.parseInt(propValue);
	}
	
}