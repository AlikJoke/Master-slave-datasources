package ru.project.master.slave.datasources;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.Maps;

import ru.project.master.slave.properties.Properties;
import ru.project.master.slave.routing.RoutingDataSource;

@Configuration
@ComponentScan
@EnableJpaRepositories("ru.project.master.slave.dao")
@EnableTransactionManagement
public class DataSourcesConfiguration {

	@Autowired
	private Properties props;

	@Bean(name = "masterDataSource")
	@Primary
	public DataSource masterDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(props.getProperty("spring.datasource.master.driver-class-name"));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setUsername(props.getProperty("spring.datasource.master.username"));
		dataSource.setPassword(props.getProperty("spring.datasource.master.password"));
		dataSource.setUrl(props.getProperty("spring.datasource.master.url"));
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
		dataSource.setDriverClassName(props.getProperty("spring.datasource.slave.driver-class-name"));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setUsername(props.getProperty("spring.datasource.slave.username"));
		dataSource.setPassword(props.getProperty("spring.datasource.slave.password"));
		dataSource.setUrl(props.getProperty("spring.datasource.slave.url"));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(masterDataSource());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.parseBoolean(props.getProperty("spring.jpa.generate-ddl")));
		Map<String, String> additionalProperties = Maps.newHashMap();
		additionalProperties.put("hibernate.dialect", props.getProperty("spring.jpa.dialect"));
		LocalContainerEntityManagerFactoryBean factory = builder.dataSource(routingDataSource())
				.packages("ru.project.master.slave.orm").persistenceUnit("routingEMF").properties(additionalProperties)
				.build();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.afterPropertiesSet();

		return factory;
	}
}
