package com.nttdata.demo.channel_api_client.mapper;

import com.nttdata.demo.channel_api_client.entity.ExchangeRate;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ExchangeRateMapper {

    public Flux<ExchangeRate> getAllExchangeRate();
}
