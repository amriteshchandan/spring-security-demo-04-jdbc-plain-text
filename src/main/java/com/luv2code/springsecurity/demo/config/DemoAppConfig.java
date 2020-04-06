package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

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
@PropertySource("classpath:persistence-mysql.properties")
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
			logger.info("jdbc.driver >>>> " + env.getProperty("jdbc.driver"));
			logger.info("jdbc.url >>>> " + env.getProperty("jdbc.url"));
			logger.info("jdbc.user >>>> " + env.getProperty("jdbc.user"));
			logger.info("jdbc.password >>>> " + env.getProperty("jdbc.password"));
			logger.info("connection.pool.initialPoolSize >>>> " + getPropertyAsInt("connection.pool.initialPoolSize"));
			logger.info("connection.pool.minPoolSize >>>> " + getPropertyAsInt("connection.pool.minPoolSize"));
			logger.info("connection.pool.maxPoolSize >>>> " + getPropertyAsInt("connection.pool.maxPoolSize"));
			logger.info("connection.pool.maxIdleTime >>>> " + getPropertyAsInt("connection.pool.maxIdleTime"));
			
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
			securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			securityDataSource.setUser(env.getProperty("jdbc.user"));
			securityDataSource.setPassword(env.getProperty("jdbc.password"));
			
			securityDataSource.setInitialPoolSize(getPropertyAsInt("connection.pool.initialPoolSize"));
			securityDataSource.setMinPoolSize(getPropertyAsInt("connection.pool.minPoolSize"));
			securityDataSource.setMaxPoolSize(getPropertyAsInt("connection.pool.maxPoolSize"));
			securityDataSource.setMaxIdleTime(getPropertyAsInt("connection.pool.maxIdleTime"));
			
		} catch (PropertyVetoException pve) {
			logger.info("Exception Here 1");
			pve.printStackTrace();
		} catch (Exception e) {
			logger.info("Exception Here 2");
			e.printStackTrace();
		}
		return securityDataSource;
		
	}
	
	private int getPropertyAsInt(String propName) {
		logger.info("propName :: " + propName + " ::length:: " + propName.length());
		String propValue = env.getProperty(propName);
		return Integer.parseInt(propValue);
	}
	
}