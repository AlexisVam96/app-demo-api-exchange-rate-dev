package com.nttdata.demo.channel_api_client.config;

import io.r2dbc.spi.ConnectionFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private ConnectionFactory connectionFactory;

    @PostConstruct
    public void initialize() {
        Mono.from(connectionFactory.create())
                .flatMapMany(connection -> {
                    // Lee el script SQL
                    String script;
                    try {
                        script = Files.lines(Paths.get("src/main/resources/import.sql"))
                                .collect(Collectors.joining("\n"));
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to read SQL script", e);
                    }

                    // Ejecuta el script SQL
                    return Mono.from(connection.createStatement(script).execute())
                            .doFinally(signalType -> connection.close())
                            .then();
                })
                .subscribe();
    }
}
