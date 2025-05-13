package com.example.demo;

import org.fluentd.logger.FluentLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FluentdLoggerConfig {

    @Bean
    public FluentLogger fluentLogger() {
        return FluentLogger.getLogger("fluentd", "127.0.0.1", 24224); // Замість localhost використовуйте адресу вашого Fluentd сервера
    }
}
