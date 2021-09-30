package ru.sberdorofeev.bankapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${migration.version:}")
    private String migrationVersion;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClass);

        hikariConfig.setMinimumIdle(5);
        hikariConfig.setMaximumPoolSize(15);
        hikariConfig.setPoolName("bank-api-connection-pool");

        return new HikariDataSource(hikariConfig);
    }

    @Bean(initMethod = "migrate") // flyway.migrate();
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure() // Flyway flyway = Flyway.configure()...
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .target(getMigrationVersion())
                .load();
    }
    private MigrationVersion getMigrationVersion() {
        return migrationVersion == null || migrationVersion.trim().isEmpty() ?
                MigrationVersion.LATEST :
                MigrationVersion.fromVersion(migrationVersion);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("ru.sberdorofeev.bankapi.model");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.getProperty(
                "hibernate.show_sql", "true");

        return hibernateProperties;
    }

}
