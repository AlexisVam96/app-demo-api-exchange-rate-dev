package com.nttdata.demo.channel_api_client;

import com.nttdata.demo.channel_api_client.entity.ExchangeRate;
import com.nttdata.demo.channel_api_client.repository.ExchangeRateRepository;
import com.nttdata.demo.channel_api_client.service.ExchangeRateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AppDemoApiExchangeRateTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("list all Exchange rate successful")
    @Test
    public void listAllExchangeRate() {
        ExchangeRate exchangeRate1 = new ExchangeRate();
        exchangeRate1.setId(1);
        exchangeRate1.setType("USD");
        exchangeRate1.setValor(3.78);
        exchangeRate1.setCreate_at(LocalDateTime.now());

        ExchangeRate exchangeRate2 = new ExchangeRate();
        exchangeRate2.setId(2);
        exchangeRate2.setType("EUR");
        exchangeRate2.setValor(4.01);
        exchangeRate2.setCreate_at(LocalDateTime.now());

        List<ExchangeRate> exchangeRateList = Arrays.asList(exchangeRate1, exchangeRate2);

        when(exchangeRateRepository.findAll()).thenReturn(Flux.fromIterable(exchangeRateList));

        StepVerifier.create(exchangeRateService.getAllExchangeRate())
                .expectNext(exchangeRate1)
                .expectNext(exchangeRate2)
                .verifyComplete();
    }

    @DisplayName("find Exchange rate by id successful")
    @Test
    public void testFindExchangeRateById() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(1);
        exchangeRate.setType("USD");
        exchangeRate.setValor(3.78);
        exchangeRate.setCreate_at(LocalDateTime.now());

        when(exchangeRateRepository.findById(anyInt())).thenReturn(Mono.just(exchangeRate));

        StepVerifier.create(exchangeRateService.findById(1))
                .expectNext(exchangeRate)
                .verifyComplete();
    }

    @DisplayName("find Exchange rate by type successful")
    @Test
    public void testFindExchangeRateByType() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(1);
        exchangeRate.setType("USD");
        exchangeRate.setValor(3.78);
        exchangeRate.setCreate_at(LocalDateTime.now());

        when(exchangeRateRepository.findExchangeRateByType(anyString())).thenReturn(Mono.just(exchangeRate));

        StepVerifier.create(exchangeRateService.findExchangeRateByType("USD"))
                .expectNext(exchangeRate)
                .verifyComplete();
    }

    @DisplayName("find Exchange rate by type and last create_at successful")
    @Test
    public void testFindExchangeRateByTypeAndMaxCreateAt() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(1);
        exchangeRate.setType("USD");
        exchangeRate.setValor(3.78);
        exchangeRate.setCreate_at(LocalDateTime.now());

        when(exchangeRateRepository.findExchangeRateByTypeAndMaxCreateAt(anyString())).thenReturn(Mono.just(exchangeRate));

        StepVerifier.create(exchangeRateService.findExchangeRateByTypeAndMaxCreateAt("USD"))
                .expectNext(exchangeRate)
                .verifyComplete();
    }

    @DisplayName("save Exchange rate successful")
    @Test
    public void saveExchangeRate() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(1);
        exchangeRate.setType("USD");
        exchangeRate.setValor(3.78);
        exchangeRate.setCreate_at(LocalDateTime.now());

        when(exchangeRateRepository.save(any(ExchangeRate.class))).thenReturn(Mono.just(exchangeRate));

        StepVerifier.create(exchangeRateService.save(exchangeRate))
                .expectNext(exchangeRate)
                .verifyComplete();
    }

    @DisplayName("save Exchange rate without date successful")
    @Test
    public void saveExchangeRateWithoutDate() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(1);
        exchangeRate.setType("USD");
        exchangeRate.setValor(3.78);

        when(exchangeRateRepository.save(any(ExchangeRate.class))).thenReturn(Mono.just(exchangeRate));

        StepVerifier.create(exchangeRateService.save(exchangeRate))
                .expectNext(exchangeRate)
                .verifyComplete();
    }

    @DisplayName("update Exchange rate successful")
    @Test
    public void updateExchangeRate() {

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(1);
        exchangeRate.setType("USD");
        exchangeRate.setValor(3.78);
        exchangeRate.setCreate_at(LocalDateTime.now());

        ExchangeRate exchangeRateUpdate = new ExchangeRate();
        exchangeRateUpdate.setType("EUR");
        exchangeRateUpdate.setValor(4.01);
        exchangeRateUpdate.setCreate_at(LocalDateTime.now().plusDays(1));

        when(exchangeRateRepository.findById(anyInt())).thenReturn(Mono.just(exchangeRate));
        when(exchangeRateRepository.save(any(ExchangeRate.class))).thenReturn(Mono.just(exchangeRateUpdate));

        Mono<ExchangeRate> result = exchangeRateService.update(1, exchangeRateUpdate);

        StepVerifier.create(result)
                .expectNextMatches(exchange ->
                            exchange.getType().equals("EUR") &&
                            exchange.getCreate_at().equals(exchangeRateUpdate.getCreate_at()))
                .verifyComplete();

        verify(exchangeRateRepository).findById(1);
        verify(exchangeRateRepository).save(exchangeRate);
    }

    @DisplayName("delete Exchange rate successful")
    @Test
    public void deleteExchangeRate() {

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(1);
        exchangeRate.setType("USD");
        exchangeRate.setValor(3.78);
        exchangeRate.setCreate_at(LocalDateTime.now());

        when(exchangeRateRepository.delete(any(ExchangeRate.class))).thenReturn(Mono.empty());

        StepVerifier.create(exchangeRateService.delete(exchangeRate))
                .verifyComplete();

        verify(exchangeRateRepository).delete(exchangeRate);
    }
}
