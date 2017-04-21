package ru.project.master.slave.datasources;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.Maps;

import ru.project.master.slave.properties.Properties;
import ru.project.master.slave.routing.RoutingDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan
public class DataSourcesConfiguration {

	@Autowired
	private Properties props;

	@Bean(name = "masterDataSource")
	@Primary
	public DataSource masterDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(props.getProperty("hibernate.master.datasource.driver-class-name"));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setUsername(props.getProperty("hibernate.master.datasource.username"));
		dataSource.setPassword(props.getProperty("hibernate.master.datasource.password"));
		dataSource.setUrl(props.getProperty("hibernate.master.datasource.url"));
		return dataSource;
	}

	@Bean
	public RoutingDataSource routingDataSource() {
		RoutingDataSource dataSource = new RoutingDataSource();
		dataSource.setDefaultTargetDataSource(masterDataSource());
		Map<Object, Object> targets = Maps.newHashMap();
		targets.put(DataSourceType.MASTER, masterDataSource());
		targets.put(DataSourceType.SLAVE, slaveDataSource());
		dataSource.setTargetDataSources(targets);
		dataSource.afterPropertiesSet();
		return dataSource;
	}

	@Bean(name = "slaveDataSource")
	public DataSource slaveDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(props.getProperty("hibernate.slave.datasource.driver-class-name"));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setUsername(props.getProperty("hibernate.slave.datasource.username"));
		dataSource.setPassword(props.getProperty("hibernate.slave.datasource.password"));
		dataSource.setUrl(props.getProperty("hibernate.slave.datasource.url"));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(masterDataSource());
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setDataSource(masterDataSource());
		java.util.Properties additionalProperties = new java.util.Properties();
		additionalProperties.put("hibernate.dialect", props.getProperty("hibernate.dialect"));
		factory.setJpaProperties(additionalProperties);
		factory.afterPropertiesSet();

		return factory.getObject();
	}
}
