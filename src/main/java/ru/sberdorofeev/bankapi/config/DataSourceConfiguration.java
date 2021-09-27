package ru.sberdorofeev.bankapi.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    private String migrationVersion;


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

}
