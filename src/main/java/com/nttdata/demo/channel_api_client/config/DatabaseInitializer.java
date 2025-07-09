package com.nttdata.demo.channel_api_client.config;

import io.r2dbc.spi.ConnectionFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private ConnectionFactory connectionFactory;

    @PostConstruct
    public void initialize() {
        Mono.from(connectionFactory.create())
                .flatMapMany(connection -> {
                    String script;
                    try (var inputStream = getClass().getClassLoader().getResourceAsStream("import.sql")) {
                        if (inputStream == null) {
                            throw new RuntimeException("Archivo import.sql no encontrado en classpath");
                        }
                        script = new String(inputStream.readAllBytes());
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to read SQL script", e);
                    }

                    return Mono.from(connection.createStatement(script).execute())
                            .doFinally(signalType -> connection.close())
                            .then();
                })
                .subscribe();
    }
}
