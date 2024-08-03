package com.nttdata.demo.channel_api_client.repository;

import com.nttdata.demo.channel_api_client.entity.ExchangeRate;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ExchangeRateRepository extends R2dbcRepository<ExchangeRate, Integer> {

    public Mono<ExchangeRate> findExchangeRateByType(String type);

    @Query("SELECT * FROM tb_exchange_rate WHERE type = $1 AND create_at = (SELECT MAX(create_at) FROM tb_exchange_rate WHERE type = $1)")
    public Mono<ExchangeRate> findExchangeRateByTypeAndMaxCreateAt(String type);
}
