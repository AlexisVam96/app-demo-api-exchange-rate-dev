package com.nttdata.demo.channel_api_client.service;

import com.nttdata.demo.channel_api_client.entity.ExchangeRate;
import com.nttdata.demo.channel_api_client.mapper.ExchangeRateMapper;
import com.nttdata.demo.channel_api_client.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ExchangeRateServiceImpl implements  ExchangeRateService{

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public Flux<ExchangeRate> getAllExchangeRate() {
        return exchangeRateRepository.findAll();
    }

    @Override
    public Mono<ExchangeRate> findById(Integer id) {
        return exchangeRateRepository.findById(id);
    }

    @Override
    public Mono<ExchangeRate> findExchangeRateByType(String type) {
        return exchangeRateRepository.findExchangeRateByType(type);
    }

    @Override
    public Mono<ExchangeRate> save(ExchangeRate exchangeRate) {

        ExchangeRate exchange = ExchangeRate.builder()
                .type(exchangeRate.getType())
                .valor(exchangeRate.getValor())
                .create_at(exchangeRate.getCreate_at())
                .build();

        if(Objects.isNull(exchange.getCreate_at()))
            exchange.setCreate_at(LocalDateTime.now());

        return exchangeRateRepository.save(exchange);

    }

    @Override
    public Mono<ExchangeRate> findExchangeRateByTypeAndMaxCreateAt(String type) {
        return exchangeRateRepository.findExchangeRateByTypeAndMaxCreateAt(type);
    }

    @Override
    public Mono<ExchangeRate> update(Integer id, ExchangeRate exchangeRate) {
        return exchangeRateRepository.findById(id)
                .flatMap(exchange -> {
                    exchange.setType(exchangeRate.getType());
                    exchange.setValor(exchangeRate.getValor());
                    exchange.setCreate_at(exchangeRate.getCreate_at());
                    return exchangeRateRepository.save(exchange);
                });
    }

    @Override
    public Mono<Void> delete(ExchangeRate exchangeRate) {
        return exchangeRateRepository.delete(exchangeRate);
    }

}
