package ee.ttu.catering.config.unittest;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;

import ee.ttu.catering.config.AbstractDbEnv;
import ee.ttu.catering.config.dialect.MySqlDialetResolver;


@PropertySource("classpath:unittest_db.properties")
public class UnitTestEnv extends AbstractDbEnv {
	
	private static final String TYPE = "test";
	
	@Bean
	protected DataSource dataSource() {
		
		DataSource dataSource = new EmbeddedDatabaseBuilder()
		.setType(EmbeddedDatabaseType.H2)
		.setName("unittest")
		.addScript("classpath:unittest_testdata.sql")
		.build();
		
		return dataSource;
	}
	
	@Bean
	@Override
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactory.setPackagesToScan(packagesToScan);
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactory.setJpaProperties(hibernateProperties());
		
		return entityManagerFactory;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		
		properties.put(Environment.DIALECT, getProperty(HIBERNATE_DIALECT));
		properties.put(Environment.HBM2DDL_AUTO, getProperty(HIBERNATE_AUTO));
		properties.put(Environment.SHOW_SQL, getProperty(HIBERNATE_SHOW_SQL));
		properties.put(Environment.FORMAT_SQL, getProperty(HIBERNATE_FORMAT_SQL));
//		properties.put(Environment.DIALECT_RESOLVERS, MySqlDialetResolver.class.getName());
//		properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
		
		return properties;
	}

	@Override
	protected String getConfigurationType() {
		return TYPE;
	}
	
}
