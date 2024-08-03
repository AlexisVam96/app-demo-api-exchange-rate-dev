package com.nttdata.demo.channel_api_client.mapper;

import com.nttdata.demo.channel_api_client.entity.ExchangeRate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ExchangeRateMapperImpl implements ExchangeRateMapper{

    @Override
    public Flux<ExchangeRate> getAllExchangeRate() {

        return null;
    }
}
