package com.nttdata.demo.channel_api_client.service;

import com.nttdata.demo.channel_api_client.entity.ExchangeRate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeRateService {

    public Mono<ExchangeRate> findById(Integer id);
    public Flux<ExchangeRate> getAllExchangeRate();
    public Mono<ExchangeRate> findExchangeRateByType(String type);
    public Mono<ExchangeRate> findExchangeRateByTypeAndMaxCreateAt(String type);
    public Mono<ExchangeRate> save(ExchangeRate exchangeRate);
    public Mono<ExchangeRate> update(Integer id, ExchangeRate exchangeRate);
    public Mono<Void> delete(ExchangeRate exchangeRate);

}
