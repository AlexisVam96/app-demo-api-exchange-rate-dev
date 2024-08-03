package com.nttdata.demo.channel_api_client.controller;

import com.nttdata.demo.channel_api_client.entity.ExchangeRate;
import com.nttdata.demo.channel_api_client.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/app/demo/api/exchange-rate")
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @GetMapping
    public Mono<ResponseEntity<Flux<ExchangeRate>>> getAll() {
        return  Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(exchangeRateService.getAllExchangeRate()));

    }

    @GetMapping("/{type}")
    public Mono<ResponseEntity<ExchangeRate>> findByType(@PathVariable String type){
        return exchangeRateService.findExchangeRateByType(type)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<ExchangeRate>> save(@RequestBody ExchangeRate exchangeRate){
        return exchangeRateService.save(exchangeRate)
                .map(exchange -> ResponseEntity.created(URI.create("/app/demo/api/exchange-rate/")).body(exchange));
    }

    @GetMapping("/max-create/{type}")
    public Mono<ResponseEntity<ExchangeRate>> findByMaxCreateAt(@PathVariable String type){
        return exchangeRateService.findExchangeRateByTypeAndMaxCreateAt(type)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ExchangeRate>> update(@PathVariable Integer id, @RequestBody ExchangeRate exchangeRate){
        return exchangeRateService.update(id, exchangeRate)
                .map(exchange -> ResponseEntity.created(URI.create("/app/demo/api/exchange-rate/")).body(exchange))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Integer id){
        return exchangeRateService.findById(id)
                .flatMap(p -> exchangeRateService.delete(p)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
