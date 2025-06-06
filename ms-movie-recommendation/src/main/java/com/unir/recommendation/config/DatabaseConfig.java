package com.unir.recommendation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.unir.recommendation.repository")
@EnableTransactionManagement
public class DatabaseConfig {
    // Configuraciones
}