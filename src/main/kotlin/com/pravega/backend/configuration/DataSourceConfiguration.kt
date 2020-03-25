package com.pravega.backend.configuration

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
class DataSourceConfiguration {
    @Bean
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
                .driverClassName("com.impossibl.postgres.jdbc.PGDriver")
                .url("jdbc:pgsql://localhost:5432/dev")
                .username("postgres")
                .password("postgres")
                .build();
    }
}