package com.tecacet.movie.jpa.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = { "com.tecacet.movie.jpa.repository" })
@EnableTransactionManagement
@ComponentScan(basePackages = "com.tecacet.movie.jpa.service")
public class PersistanceConfiguration {

	private static final String[] ENTITY_PACKAGES = { "com.tecacet.movie.jpa.model" };

	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl("jdbc:h2:file:./movies;MODE=PostgreSQL");
		hikariConfig.setUsername("sa");
		hikariConfig.setPassword("");
		hikariConfig.setDriverClassName("org.h2.Driver");
		return new HikariDataSource(hikariConfig);
	}

	/**
	 * Creates the bean that creates the JPA entity manager factory.
	 * 
	 * @param dataSource
	 *            The datasource that provides the database connections.
	 * @param env
	 *            The runtime environment of our application.
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES);
		return entityManagerFactoryBean;
	}

	/**
	 * Creates the transaction manager bean that integrates the used JPA provider
	 * with the Spring transaction mechanism.
	 * 
	 * @param entityManagerFactory
	 *            The used JPA entity manager factory.
	 * @return
	 */
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

}
